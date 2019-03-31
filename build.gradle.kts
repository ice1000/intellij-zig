import groovy.lang.Closure
import org.gradle.language.base.internal.plugins.CleanRule
import org.jetbrains.grammarkit.GrammarKitPluginExtension
import org.jetbrains.grammarkit.tasks.BaseTask
import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.*
import java.nio.file.*
import java.util.stream.Collectors

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

val pluginComingVersion = "0.1.1"
val pluginVersion = if (isCI) "$pluginComingVersion-$commitHash" else pluginComingVersion
val packageName = "org.ziglang"

group = packageName
version = pluginVersion
plugins {
    java
    id("org.jetbrains.intellij") version "0.4.6"
    id("org.jetbrains.grammarkit") version "2018.3.1"
    kotlin("jvm") version "1.2.40"
}

fun fromToolbox(path: String) = file(path).listFiles().orEmpty().filter { it.isDirectory }.maxBy {
    val (major, minor, patch) = it.name.split('.')
    String.format("%5s%5s%5s", major, minor, patch)
}

allprojects {
    apply { plugin("org.jetbrains.grammarkit") }

    intellij {
        updateSinceUntilBuild = false
        instrumentCode = true
        val user = System.getProperty("user.name")
        val os = System.getProperty("os.name")
        when {
            os.startsWith("Windows") -> {
                val root = "C:\\Users\\ice10\\AppData\\Local\\JetBrains\\Toolbox\\apps";
                val intellijPath = fromToolbox("$root\\IDEA-C-JDK11\\ch-0")
                    ?: fromToolbox("$root\\IDEA-C\\ch-0")
                    ?: fromToolbox("$root\\IDEA-JDK11\\ch-0")
                    ?: fromToolbox("$root\\IDEA-U\\ch-0")
                intellijPath?.absolutePath?.let { localPath = it }
                val pycharmPath = fromToolbox("$root\\PyCharm-C\\ch-0")
                    ?: fromToolbox("$root\\IDEA-C\\ch-0")
                    ?: fromToolbox("$root\\IDEA-C-JDK11\\ch-0")
                    ?: fromToolbox("$root\\IDEA-U\\ch-0")
                pycharmPath?.absolutePath?.let { alternativeIdePath = it }
            }
            os == "Linux" -> {
                val root = "/home/$user/.local/share/JetBrains/Toolbox/apps"
                val intellijPath = fromToolbox("$root/IDEA-C-JDK11/ch-0")
                    ?: fromToolbox("$root/IDEA-C/ch-0")
                    ?: fromToolbox("$root/IDEA-JDK11/ch-0")
                    ?: fromToolbox("$root/IDEA-U/ch-0")
                intellijPath?.absolutePath?.let { localPath = it }
                val pycharmPath = fromToolbox("$root/PyCharm-C/ch-0")
                    ?: fromToolbox("$root/IDEA-C/ch-0")
                    ?: fromToolbox("$root/IDEA-C-JDK11/ch-0")
                    ?: fromToolbox("$root/IDEA-U/ch-0")
                pycharmPath?.absolutePath?.let { alternativeIdePath = it }
            }
        }
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

java.sourceSets {
	"main" {
		resources.srcDirs("res")
		java.srcDirs("src", "gen")
		withConvention(KotlinSourceSet::class) {
			kotlin.srcDirs("src", "gen")
		}
	}

	"test" {
		resources.srcDirs("testData")
		java.srcDirs("test")
		withConvention(KotlinSourceSet::class) {
			kotlin.srcDirs("test")
		}
	}
}

repositories {
	mavenCentral()
}

dependencies {
	compileOnly(kotlin("stdlib-jdk8"))
	compileOnly(kotlin("script-util"))
	compile(kotlin("stdlib-jdk8").toString()) {
		exclude(module = "kotlin-runtime")
		exclude(module = "kotlin-reflect")
		exclude(module = "kotlin-stdlib")
	}
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
		languageVersion = "1.2"
		apiVersion = "1.2"
	}
}

tasks.withType<Delete> {
	dependsOn(cleanGenerated)
}
