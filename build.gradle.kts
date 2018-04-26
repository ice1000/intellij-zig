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

val pluginComingVersion = "0.0.4"
val pluginVersion = if (isCI) "$pluginComingVersion-$commitHash" else pluginComingVersion
val packageName = "org.ziglang"
val kotlinVersion: String by extra

group = packageName
version = pluginVersion

buildscript {
	var kotlinVersion: String by extra
	var grammarKitVersion: String by extra

	grammarKitVersion = "2018.1.1"
	kotlinVersion = "1.2.40"

	repositories {
		mavenCentral()
		maven("https://jitpack.io")
	}

	dependencies {
		classpath(kotlin("gradle-plugin", kotlinVersion))
		classpath("com.github.JetBrains:gradle-grammar-kit-plugin:$grammarKitVersion")
	}
}

plugins {
	idea
	java
	id("org.jetbrains.intellij") version "0.3.1"
	kotlin("jvm") version "1.2.40"
}

idea {
	module {
		// https://github.com/gradle/kotlin-dsl/issues/537/
		excludeDirs = excludeDirs + file("pinpoint_piggy") + file("zig")
		generatedSourceDirs.add(file("gen"))
	}
}

allprojects {
	apply {
		plugin("org.jetbrains.grammarkit")
	}

	intellij {
		updateSinceUntilBuild = false
		instrumentCode = true
		when (System.getProperty("user.name")) {
			"ice1000" -> {
				val root = "/home/ice1000/.local/share/JetBrains/Toolbox/apps"
				localPath = "$root/IDEA-U/ch-0/181.4668.68"
				alternativeIdePath = "$root/PyCharm-C/ch-0/181.4668.75"
			}

			"hoshino" -> localPath = ext["ideaC_path"].toString()
			else -> version = "2018.1"
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
	compileOnly(kotlin("stdlib-jdk8", kotlinVersion))
	compile(kotlin("stdlib-jdk8", kotlinVersion).toString()) {
		exclude(module = "kotlin-runtime")
		exclude(module = "kotlin-reflect")
		exclude(module = "kotlin-stdlib")
	}
	compile("org.eclipse.mylyn.github", "org.eclipse.egit.github.core", "2.1.5") {
		exclude(module = "gson")
	}
	testCompile(kotlin("test-junit", kotlinVersion))
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
