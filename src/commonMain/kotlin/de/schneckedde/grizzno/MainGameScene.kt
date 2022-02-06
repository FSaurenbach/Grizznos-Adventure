package de.schneckedde.grizzno

import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.centerOnStage
import com.soywiz.korge.view.container

class MainGameScene() : Scene() {
	override suspend fun Container.sceneInit() {
		println("fask")
		val tree = container {
				centerOnStage()
			}
		var mystage = stage
		if (mystage != null) {
			add().a(tree, mystage, "Car")
		}
		if (mystage != null) {
			add().a(tree, mystage, "a")
		}
		//sceneContainer.changeTo<MainMenuScene>()
			
			
			
		
	}
}
