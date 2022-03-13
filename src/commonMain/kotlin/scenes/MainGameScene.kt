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
import com.soywiz.korge.ui.UIText
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.ui.uiText
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korim.format.PNG
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.dynamic.KDynamic.Companion.toDouble
import com.soywiz.korio.dynamic.KDynamic.Companion.toInt
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.degrees
import game_logic.movement.InputHandler
import game_logic.movement.addJoystick
import kotlin.properties.Delegates


var bul = 0
var myscore by Delegates.notNull<Int>()

class MainGameScene : Scene() {
	
	override suspend fun Container.sceneInit() {
		var score_file = (resourcesVfs["score.txt"])
		myscore = score_file.readString().toInt()
		var player_sprite = resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap()
		
		var bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG))
		var zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap(PNG))
		
		var move = true
		var mystage = Stage(views)
		var background = image(resourcesVfs["background.png"].readBitmap())
		
		var myCamera = container{
			
			addChild(background)
			
			keys {
				down(Key.ESCAPE) {
					
					sceneContainer.changeTo<MainMenuScene>()
				}
				down(Key.H) {
					background.centerOnStage()
				}
			}
			
		}
		val Player = Image(resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap())
		spawn_zombies(stage, myCamera, Player, zombie, bullet, 25.0,23.0)
		var x_joystick = 0.0
		var y_joystick = 0.0
		
		
		var score_text = uiText("Score: ${resourcesVfs["score.txt"].readString()}") {
			textSize = 70.0
		}.centerXOnStage()
		
		
		var game_mode_switcher = container {
			var main_game_button = uiButton(300.0, 150.0) {}.position(views.virtualWidthDouble / 2.0, 1750.0).onClick {
				sceneContainer.changeTo<MainGameScene>(
					transition = MaskTransition(TransitionFilter.Transition.SWEEP, smooth = true), time = 0.2.seconds
				)
			}
			var options_game_button = uiButton(300.0, 150.0) {}.position(main_game_button!!.x - 400.0, 1750.0).onClick {
				sceneContainer.changeTo<OptionsScene>(
					transition = MaskTransition(TransitionFilter.Transition.SWEEP, smooth = true), time = 0.2.seconds
				)
			}
			text("Game").centerOn(main_game_button).textSize += 40.0
			if (options_game_button != null) {
				text("Options").centerOn(options_game_button).textSize += 40.0
			}
		}
		addChild(game_mode_switcher)
		zombie.centerOnStage()
		
		addChild(Player)
		InputHandler().move_player_by_keys(stage, Player)
		fun move_player_by_joystick(x: Double, y: Double) {
			Player.addUpdater {
				if (move) {
					x_joystick = x * 1.7
					y_joystick = y * 1.7
				} else {
					x_joystick = 0.0
					y_joystick = 0.0
				}
			}
			
		}
		
		var joystick = addJoystick(
			mystage,
			views.virtualWidth.toDouble(), views.virtualHeight.toDouble(),
		) { x, y -> move_player_by_joystick(x, y) }
		
		
		myCamera.centerOnStage()
		Player.centerOnStage()
		Player.addUpdater {
			
			move = Player.collidesWith(background)
			Player.x += x_joystick
			
			
			Player.y += y_joystick
			
			
			
		}
		score_text.addUpdater {
			score_text.text = "Score: $myscore"
		}
		val center = Point(width / 2, height / 2)
		keys {
			down(Key.K) {
				spawn_bullets(myCamera, Player, bullet)
				println("Bullet shot")
			}
		}
		keys {
			down(Key.ESCAPE) {
				writeScore(score_text)
				
				sceneContainer.changeTo<MainMenuScene>()
			}
		}
		myCamera.addUpdater {
			myCamera.pos = center-Player.pos
		}
		
	}
	
	suspend fun writeScore(score_text: UIText) {
		var score_file = (resourcesVfs["score.txt"])
		var scores = score_file.readString().toInt()
		scores+= myscore
		print(scores)
		score_file.writeLines(listOf(scores.toString()))
		
	}
	
	
}


private fun spawn_bullets(tree: Container, Player: View, bullet: Image) {
	
	if (bul <= 5) {
		bullet.centerOn(Player)
		tree.addChild(bullet)
		
		bullet.scale = 0.1
		bullet.rotation = 90.degrees
		
		print(tree.width)
		
		print(tree.height)
		bullet.addUpdater {
			
			
			bullet.x += 2.0
			if (bullet.collidesWith(tree)) {
				
				/*
				print("removed")
				removeAllComponents()*/
			}
		}
		
		bul++
		
	}
}

private fun spawn_zombies(stage: Stage?, tree: Container, Player: Image, zombie: Image, bullet: Image, x:Double, y:Double) {
	zombie.position(x, y)
	zombie.addUpdater {
		if (zombie.collidesWith(bullet)) {
			myscore+=10
			println(myscore)
			removeFromParent()
			
		}
	}
	print("smth")
	zombie_ai().move_zombie(zombie, Player)
	tree.addChild(zombie)
}