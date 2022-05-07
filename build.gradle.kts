import com.soywiz.korge.gradle.KorgeGradlePlugin
import com.soywiz.korge.gradle.Orientation
import com.soywiz.korge.gradle.korge

plugins {
	`java-library`
}

repositories {
	mavenCentral()
}
sourceSets {
	main {
		java.srcDir("src/core/java")
	}
}
buildscript {
	val korgePluginVersion: String = "2.7.0"
	
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
	
	orientation = Orientation.LANDSCAPE
	authorName = "Ferdinand Saurenbach"
	authorEmail = "ferdi.saurenbach@gmail.com"
	androidSdk(compileSdk = 30, minSdk = 20, targetSdk = 30)
	serializationJson()
	targetAll()
}