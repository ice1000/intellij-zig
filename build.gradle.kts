import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
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

val pluginComingVersion = "0.1.3"
val pluginVersion = if (isCI) "$pluginComingVersion-$commitHash" else pluginComingVersion
val packageName = "org.ziglang"

group = packageName
version = pluginVersion
plugins {
    java
    id("org.jetbrains.intellij") version "0.4.8"
    id("org.jetbrains.grammarkit") version "2019.1"
    kotlin("jvm") version "1.3.30"
}

fun fromToolbox(root: String, ide: String) = file(root)
	.resolve(ide)
	.takeIf { it.exists() }
	?.resolve("ch-0")
	?.listFiles()
	.orEmpty()
	.filterNotNull()
	.filter { it.isDirectory }
	.maxBy {
		val (major, minor, patch) = it.name.split('.')
		String.format("%5s%5s%5s", major, minor, patch)
	}
	?.also { println("Picked: $it") }

allprojects {
	apply { plugin("org.jetbrains.grammarkit") }

	intellij {
		updateSinceUntilBuild = false
		instrumentCode = true
		val user = System.getProperty("user.name")
		val os = System.getProperty("os.name")
		val root = when {
			os.startsWith("Windows") -> "C:\\Users\\$user\\AppData\\Local\\JetBrains\\Toolbox\\apps"
			os == "Linux" -> "/home/$user/.local/share/JetBrains/Toolbox/apps"
			else -> return@intellij
		}
		val intellijPath = sequenceOf("IDEA-C-JDK11", "IDEA-C", "IDEA-JDK11", "IDEA-U")
			.mapNotNull { fromToolbox(root, it) }.firstOrNull()
		intellijPath?.absolutePath?.let { localPath = it }
		val pycharmPath = sequenceOf("PyCharm-C", "IDEA-C-JDK11", "IDEA-C", "IDEA-JDK11", "IDEA-U")
			.mapNotNull { fromToolbox(root, it) }.firstOrNull()
		pycharmPath?.absolutePath?.let { alternativeIdePath = it }

	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<PatchPluginXmlTask> {
	changeNotes(file("res/META-INF/change-notes.html").readText())
	pluginDescription(file("res/META-INF/description.html").readText())
	version(pluginVersion)
	pluginId(packageName)
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
	compile(kotlin("stdlib-jdk8"))
	compile("org.eclipse.mylyn.github", "org.eclipse.egit.github.core", "2.1.5") {
		exclude(module = "gson")
	}
	testCompile(kotlin("test-junit"))
	testCompile("junit", "junit", "4.12")
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

val genParser = task<GenerateParser>("genParser") {
	group = tasks["init"].group
	description = "Generate the Parser and PsiElement classes"
	source = "grammar/zig-grammar.bnf"
	targetRoot = "gen/"
	pathToParser = "org/ziglang/ZigParser.java"
	pathToPsiRoot = "org/ziglang/psi"
	purgeOldFiles = true
}

val genLexer = task<GenerateLexer>("genLexer") {
	group = genParser.group
	description = "Generate the Lexer"
	source = "grammar/zig-lexer.flex"
	targetDir = "gen/org/ziglang"
	targetClass = "ZigLexer"
	purgeOldFiles = true
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
	kotlinOptions {
		jvmTarget = "1.8"
		languageVersion = "1.3"
		apiVersion = "1.3"
	}
}

tasks.withType<Delete> {
	dependsOn(cleanGenerated)
}
