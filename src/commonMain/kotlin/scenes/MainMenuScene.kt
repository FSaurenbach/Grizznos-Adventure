@file:Suppress("CanBeVal", "CanBeVal", "CanBeVal")

package scenes

import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korge.view.camera.cameraContainer
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korim.color.Colors
import game_logic.myfuncs.man

class MainMenuScene : Scene() {
	override suspend fun Container.sceneInit() {
		var background = SolidRect(views.virtualWidth, views.virtualHeight, Colors["#ffe357c6"])
		addChild(background)
		keys{
			down(Key.ESCAPE){ man("Exit")}
		}
		container {
			var playButtonGame1 = uiButton(512.0, 64.0) {
				
				colorMul = Colors.GREEN
				
				position(centerOnStage())
				onClick {
					sceneContainer.pushTo<MainGameScene>(0.5.seconds, transition = MaskTransition(transition = TransitionFilter.Transition.DIAGONAL2, smooth = true))
				}
			}
			var playButtonGame2 = uiButton(512.0, 64.0) {
				
				colorMul = Colors.GREEN
				var x = playButtonGame1.x
				var y = playButtonGame1.y
				y += 100.0
				onClick {
					sceneContainer.pushTo<MainGameScene>(0.5.seconds, transition = MaskTransition(transition = TransitionFilter.Transition.DIAGONAL2, smooth = true))
				}
			}
			var exitButton = uiButton(512.0, 64.0) {
				
				colorMul = Colors.RED
				var x = playButtonGame2.x
				var y = playButtonGame2.y
				y += 100.0
				position(x, y)
				onClick {
					views.gameWindow.close()
				}
			}
			text("Play") {
				textSize = 50.0
				centerOn(playButton)
			}
			text("Exit") {
				textSize = 50.0
				centerOn(exitButton)
			}
		}
	}
}
