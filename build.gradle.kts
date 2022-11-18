import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val commitHash by lazy {
	val output: String
	val process: Process = Runtime.getRuntime().exec("git rev-parse --short HEAD")
	process.waitFor()
	output = process.inputStream.use {
		it.bufferedReader().use {
			it.readText()
		}
	}
	process.destroy()
	output.trim()
}

val isCI = !System.getenv("CI").isNullOrBlank()

val pluginComingVersion = "0.1.4"
val pluginVersion = if (isCI) "$pluginComingVersion-$commitHash" else pluginComingVersion
val packageName = "org.ziglang"

group = packageName
version = pluginVersion
plugins {
	id("java")
	id("org.jetbrains.intellij") version "1.10.0"
	id("org.jetbrains.grammarkit") version "2021.2.2"
	kotlin("jvm") version "1.7.20"
}

fun fromToolbox(root: String, ide: String) = file(root)
	.resolve(ide)
	.takeIf { it.exists() }
	?.let { sequenceOf(it.resolve("ch-0"), it.resolve("ch-1")) }
	.orEmpty()
	.filter { it.exists() }
	.map { it.listFiles() }
	.filterNotNull()
	.map { sequenceOf(*it) }
	.flatten()
	.filter { it.isDirectory }
	.maxByOrNull {
		val (major, minor, patch) = it.name.split('.')
		String.format("%5s%5s%5s", major, minor, patch)
	}
	?.also { println("Picked: $it") }

allprojects {
	apply { plugin("org.jetbrains.grammarkit") }

	intellij {
		type.set("IC")
//		updateSinceUntilBuild.set(false)
		instrumentCode.set(true)
		val user = System.getProperty("user.name")
		val os = System.getProperty("os.name")
		val root = when {
			user == "lenovo" -> "D:\\JetBrains\\Toolbox\\apps"
			os.startsWith("Windows") -> "C:\\Users\\$user\\AppData\\Local\\JetBrains\\Toolbox\\apps"
			os == "Linux" -> "/home/$user/.local/share/JetBrains/Toolbox/apps"
			else -> return@intellij
		}
		val intellijPath = sequenceOf("IDEA-C", "IDEA-U")
			.mapNotNull { fromToolbox(root, it) }.firstOrNull()
		intellijPath?.absolutePath?.let { localPath.set(it) } ?: run {
			version.set("2022.2.3")
		}
//		val pycharmPath = sequenceOf("PyCharm-C", "IDEA-C", "IDEA-U")
//			.mapNotNull { fromToolbox(root, it) }.firstOrNull()
//		pycharmPath?.absolutePath?.let { alternativeIdePath = it }
	}
}

// Set the JVM language level used to compile sources and generate files - Java 11 is required since 2020.3
kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

tasks.withType<PatchPluginXmlTask> {
	changeNotes.set(file("res/META-INF/change-notes.html").readText())
	pluginDescription.set(file("res/META-INF/description.html").readText())
	version.set(pluginVersion)
	pluginId.set(packageName)
	println(pluginId)
}

sourceSets {
	main {
		withConvention(KotlinSourceSet::class) {
			listOf(java, kotlin).forEach { it.srcDirs("src", "gen") }
		}
		resources.srcDir("res")
	}

	test {
		withConvention(KotlinSourceSet::class) {
			listOf(java, kotlin).forEach { it.srcDirs("test") }
		}
		resources.srcDir("testData")
	}
}

repositories {
	mavenCentral()
}

dependencies {
/*    compile(kotlin("stdlib-jdk11")) */
	compileOnly("org.eclipse.mylyn.github", "org.eclipse.egit.github.core", "2.1.5") {
		exclude(module = "gson")
	}
	testImplementation(kotlin("test"))
	testImplementation(kotlin("test-junit"))
}

task("displayCommitHash") {
	group = "help"
	description = "Display the newest commit hash"
	doFirst {
		println("Commit hash: $commitHash")
	}
}

task("isCI") {
	group = "help"
	description = "Check if it's running in a continuous-integration"
	doFirst {
		println(if (isCI) "Yes, I'm on a CI." else "No, I'm not on CI.")
	}
}

val genParser = task<GenerateParserTask>("genParser") {
	group = tasks["init"].group
	description = "Generate the Parser and PsiElement classes"
	source.set("grammar/zig-grammar.bnf")
	targetRoot.set("gen/")
	pathToParser.set("org/ziglang/ZigParser.java")
	pathToPsiRoot.set("org/ziglang/psi")
	purgeOldFiles.set(true)
}

val genLexer = task<GenerateLexerTask>("genLexer") {
	group = genParser.group
	description = "Generate the Lexer"
	source.set("grammar/zig-lexer.flex")
	targetDir.set("gen/org/ziglang")
	targetClass.set("ZigLexer")
	purgeOldFiles.set(true)
}

val cleanGenerated = task("cleanGenerated") {
	group = tasks["clean"].group
	description = "Remove all generated codes"
	doFirst {
		delete("gen")
	}
}

tasks.withType<KotlinCompile> {
	dependsOn(genParser)
	dependsOn(genLexer)
}

tasks.withType<Delete> {
	dependsOn(cleanGenerated)
}
