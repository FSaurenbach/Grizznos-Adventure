
@file:Suppress("CanBeVal", "CanBeVal", "CanBeVal")

package scenes

import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korge.view.tiles.BaseTileMap
import com.soywiz.korge.view.tiles.TileSet
import com.soywiz.korge.view.tiles.tileMap
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs
import game_logic.myfuncs.bitmap

class MainMenuScene : Scene() {
	override suspend fun Container.sceneInit() {
		var background = SolidRect(views.virtualWidth, views.virtualHeight, Colors["#ffe357c6"])
		
		addChild(background)
		keys{
			 down(Key.ESCAPE){views.gameWindow.exit()}
		}
		container {
			var ShootingGameButton = uiButton(512.0, 64.0, "ShootingGame") {
				/*uiSkin = UISkin{
					textSize = 50.0
				}*/
				realUiSkin.textSize = 50.0
				colorMul = Colors.GREEN
				
				onClick {
					sceneContainer.pushTo<MainGameScene>(0.5.seconds, transition = MaskTransition(transition = TransitionFilter.Transition.DIAGONAL2, smooth = true))
				}
			}
			var TankGameButton = uiButton(512.0, 64.0, "Tank Game") {
				uiSkin = UISkin{
					textSize = 50.0
				}
				colorMul = Colors.GREEN
				y = ShootingGameButton.y
				y += 100.0
				onClick {
					sceneContainer.pushTo<TankGame>(0.5.seconds, transition = MaskTransition(transition = TransitionFilter.Transition.DIAGONAL2, smooth = true))
			
				}
			}
			var exitButton = uiButton(512.0, 64.0, "Exit") {
				uiSkin = UISkin{
					textSize = 50.0
				}
				colorMul = Colors.RED
				
				y = TankGameButton.y
				y += 100.0
				position(x, y)
				onClick {
					views.gameWindow.close()
				}
			}
			
		}.centerOnStage()
	}
}