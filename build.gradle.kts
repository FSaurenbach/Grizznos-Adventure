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
	bundle("https://github.com/korlibs/korge-bundles.git::korge-box2d::7439e5c7de7442f2cd33a1944846d44aea31af0a##9fd9d54abd8abc4736fd3439f0904141d9b6a26e9e2f1e1f8e2ed10c51f490fd")
	
	id = "de.schneckedde.grizzno"
	name = "Grizznos Adventure"
	
	orientation = Orientation.PORTRAIT
	authorName = "Ferdinand Saurenbach"
	authorEmail = "ferdi.saurenbach@gmail.com"
	androidSdk(compileSdk = 30, minSdk = 20, targetSdk = 30)
	serializationJson()
	targetAll()
}