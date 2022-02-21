import com.soywiz.korge.gradle.KorgeGradlePlugin
import com.soywiz.korge.gradle.korge

plugins {
	//id("org.jetbrains.kotlin.jvm") version "1.6.20-M1"
	kotlin("multiplatform") version "1.6.10"
	`java-library`
	
	
}
//apply(plugin = "org.jetbrains.kotlin.jvm")
repositories {
	mavenCentral()
}
sourceSets {
	main {
		java.srcDir("src/core/java")
	}
}
dependencies {
	api("junit:junit:4.13.2")
	implementation("junit:junit:4.13.2")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
	implementation("concurrent:concurrent:1.3.4")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.6.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0-native-mt")
	
}
buildscript {
	val korgePluginVersion: String = "2.5.0"
	
	repositories {
		mavenLocal()
		mavenCentral()
		google()
		maven { url = uri("https://plugins.gradle.org/m2/") }
	}
	dependencies {
		
		classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
	}
}


apply<KorgeGradlePlugin>()

korge {
	id = "de.schneckedde.grizzno"
	name = "Grizznos Adventure"
	bundle("https://github.com/korlibs/korge-bundles.git::korge-box2d::7439e5c7de7442f2cd33a1944846d44aea31af0a##9fd9d54abd8abc4736fd3439f0904141d9b6a26e9e2f1e1f8e2ed10c51f490fd")
	
	androidSdk(compileSdk = 30, minSdk = 20, targetSdk = 30)
	
	targetAll()
}