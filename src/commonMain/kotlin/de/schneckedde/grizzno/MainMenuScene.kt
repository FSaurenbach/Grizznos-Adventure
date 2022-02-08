package de.schneckedde.grizzno

import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors

class MainMenuScene() : Scene() {
	override suspend fun Container.sceneInit() {
		var background = SolidRect(views.virtualWidth, views.virtualHeight, Colors["#ffe357c6"])
		addChild(background)
		container {
			var playButton = uiButton(512.0, 64.0) {
				text = "Play"
				colorMul = Colors.GREEN
				
				position(centerOnStage())
				onClick {
					print("User pressed Play")
					sceneContainer.changeTo<MainGameScene>()
				}
			}
			var exitButton = uiButton(512.0, 64.0) {
				text = "Exit"
				colorMul = Colors.RED
				var x = playButton.x
				var y = playButton.y
				y += 100.0
				position(x, y)
				onClick {
					print("User pressed Exit")
					views.gameWindow.close()
				}
			}
			println("Play Buttons text size${playButton.textSize}")
			
		}
		
		
		
	}
}
