@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)

package scenes

import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.ui.uiText
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korim.format.PNG
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import game_logic.movement.InputHandler
import game_logic.movement.addJoystick
import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

var bul = true

class MainGameScene : Scene() {
	
	override suspend fun Container.sceneInit() {
		val timeInMillis = measureTimeMillis {
			
			
			var mystage = Stage(views)
			var background = image(resourcesVfs["background.png"].readBitmap())
			addChild(background)
			
			var game_mode_switcher = container {
				var main_game_button =
					uiButton(300.0, 150.0) {}.position(views.virtualWidthDouble / 2.0, 1750.0).onClick {
						sceneContainer.changeTo<MainGameScene>(
							transition = MaskTransition(TransitionFilter.Transition.SWEEP, smooth = true),
							time = 0.2.seconds
						)
					}
				var options_game_button =
					uiButton(300.0, 150.0) {}.position(main_game_button!!.x - 400.0, 1750.0).onClick {
						sceneContainer.changeTo<OptionsScene>(
							transition = MaskTransition(TransitionFilter.Transition.SWEEP, smooth = true),
							time = 0.2.seconds
						)
					}
				text("Game").centerOn(main_game_button).textSize += 40.0
				if (options_game_button != null) {
					text("Options").centerOn(options_game_button).textSize += 40.0
				}
			}
			addChild(game_mode_switcher)
			
			var player_sprite = resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap()
			
			val Player = sprite(player_sprite)
			addChild(Player)
			InputHandler().move_player_by_keys(stage, Player)
			keys {
				down(Key.ESCAPE) {
					sceneContainer.changeTo<MainMenuScene>()
				}
				down(Key.H) {
					background.centerOnStage()
				}
			}
			var x_joystick = 0.0
			var y_joystick = 0.0
			fun move_player_by_joystick(x: Double, y: Double) {
				x_joystick = x * 1.7
				y_joystick = y * 1.7
			}
			
			var joystick = addJoystick(
				mystage,
				views.virtualWidth.toDouble(), views.virtualHeight.toDouble(),
			) { x, y -> move_player_by_joystick(x, y) }
			var meters: Double = 0.0
			var meters_text = uiText("Score: 0") {
				textSize = 70.0
			}.centerXOnStage()
			Player.addUpdater {
				Player.x += x_joystick
				
				meters += x_joystick.absoluteValue
				
				Player.y += y_joystick
				meters += y_joystick.absoluteValue
				meters_text.text = "Score: ${meters.toInt()}"
			}
			var tree = container {
			
			}
			
			var bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG))
			var zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap(PNG))
			var bul = true
			tree.addUpdater {
				if (((stage!!.input.keys.pressing(Key.K))) && bul) {
					
					spawn_bullets(
						
						stage, tree, Player, bullet
					)
				}
			}
			spawn_bots(stage, tree, Player, zombie, bullet)
			
			zombie.centerOnStage()
		}
		println("(The operation took $timeInMillis ms)")
	}
	
	private fun spawn_bullets(stage: Stage?, tree: Container, Player: View, bullet: Image) {
		bullet.centerOn(Player)
		if (bul) {
			bullet.scale = 0.1
			bullet.rotation = 90.degrees
			
			bullet.addUpdater {
				
				
				bullet.x += 2.0
				
			}
			
			tree.addChild(bullet)
			bul = false
			
		}
	}
	
	private fun spawn_bots(stage: Stage?, tree: Container, Player: View, zombie: Image, bullet: Image) {
		
		zombie.centerOnStage()
		zombie.addUpdater {
			if (zombie.collidesWith(bullet)){
				removeFromParent()
			}
		}
		tree.addChild(zombie)
	}
}