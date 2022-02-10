@file:Suppress("CanBeVal", "CanBeVal", "CanBeVal")

package de.schneckedde.grizzno

import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.net.URL

class MainMenuScene : Scene() {
	override suspend fun Container.sceneInit() {
		var background = SolidRect(views.virtualWidth, views.virtualHeight, Colors["#ffe357c6"])
		addChild(background)
		var width = 768.0
		var height = 96.0
		container {
			var playButton = uiButton(512.0, 64.0) {
	
				colorMul = Colors.GREEN
				
				position(centerOnStage())
				onClick {
					sceneContainer.changeTo<MainGameScene>()
				}
			}
			var creditsButton = uiButton(512.0, 64.0) {
				
				colorMul = Colors.RED
				var x = playButton.x
				var y = playButton.y
				y += 100.0
				position(x, y)
			}
			
			var exitButton = uiButton(512.0, 64.0) {
		
				colorMul = Colors.RED
				var x = creditsButton.x
				var y = creditsButton.y
				y += 100.0
				position(x, y)
				onClick {
					views.gameWindow.close()
				}
			}
			text("Play"){
				textSize = 50.0
				centerOn(playButton)
				
			}
			text("Credits"){
				textSize = 50.0
				centerOn(creditsButton)
			}
			text("Exit"){
				textSize = 50.0
				centerOn(exitButton)
				
			}
			
		}
		
		
	}
}