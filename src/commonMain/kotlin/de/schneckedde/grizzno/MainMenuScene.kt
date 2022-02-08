package de.schneckedde.grizzno

import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.centerOnStage
import com.soywiz.korge.view.container
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors

class MainMenuScene() : Scene() {
	override suspend fun Container.sceneInit() {
		container {
			var playButton = uiButton(256.0, 32.0) {
				text = "Play"
				colorMul = Colors.GREEN
				
				position(centerOnStage())
				onClick {
					print("User pressed Play")
					sceneContainer.changeTo<MainGameScene>()
				}
			}
			var exitButton = uiButton(256.0, 32.0) {
				text = "Exit"
				colorMul = Colors.RED
				var x = playButton.x
				var y = playButton.y
				y += 100.0
				position(x, y)
				onClick {
					print("User pressed Play")
					sceneContainer.changeTo<MainGameScene>()
				}
			}
			
		}
		
		
	}
}
