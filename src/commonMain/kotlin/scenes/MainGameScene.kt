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
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.degrees
import game_logic.movement.InputHandler
import game_logic.movement.addJoystick

var bul = 0
var myscore = 0

class MainGameScene : Scene() {
	
	override suspend fun Container.sceneInit() {
		var bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG))
		var zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap(PNG))
		/*val tileset = TileSet(resourcesVfs["tilemap.png"].readBitmap().toBMP32().scaleLinear(0.5, 0.5).slice(), 60, 60 )
		val tilemap = tileMap(Bitmap32(1, 1), tileset = tileset)*/
		
		var mystage = Stage(views)

		var myCamera = container {
			var background = Image(resourcesVfs["background.png"].readBitmap())
			addChild(background)
			keys {
				down(Key.ESCAPE) {
					
					sceneContainer.changeTo<MainMenuScene>()
				}
			}
		}
		val Player = Image(resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap())

		spawn_zombies(myCamera, Player, zombie, bullet)

		var x_joystick = 0.0
		var y_joystick = 0.0
		
		
		var score_text = uiText("Score: ${resourcesVfs["score.txt"].readString()}") {
			textSize = 70.0
		}.centerXOnStage()
		
		

		zombie.centerOnStage()
		
		addChild(Player)
		InputHandler().move_player_by_keys(stage, Player)
		fun move_player_by_joystick(x: Double, y: Double) {
			Player.addUpdater {
				
				x_joystick = x * 2.7
				y_joystick = y * 2.7
				
			}
			
		}
		Player.addUpdater {
			
			Player.x += x_joystick
			
			
			Player.y += y_joystick
			
			x_joystick = 0.0
			y_joystick = 0.0
		}
		var joystick = addJoystick(
			mystage,
			views.virtualWidth.toDouble(), views.virtualHeight.toDouble(),
		) { x, y -> move_player_by_joystick(x, y) }
		
		
		myCamera.centerOnStage()
		Player.centerOnStage()
		score_text.addUpdater {
			score_text.text = "Score: $myscore"
		}
		val center = Point(width / 2, height / 2)
		keys {
			down(Key.ESCAPE) {
				
				sceneContainer.changeTo<MainMenuScene>()
			}
			down(Key.K) {

				spawn_bullets(myCamera, Player, bullet)
				spawn_zombies(myCamera, Player, zombie, bullet)
			}
		}
		myCamera.addUpdater {
			myCamera.pos = center - Player.pos
		}
		
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

		}

		bul++

	}
}
private fun spawn_zombies(
	tree: Container,
	Player: Image,
	zombie: Image,
	bullet: Image,
	
	) {
	
	zombie.addUpdater {
		if (zombie.collidesWith(bullet)) {
			myscore += 10
			println(myscore)
			removeFromParent()
			
		}
	}
	zombie_ai().move_zombie(zombie, Player)
	tree.addChild(zombie)
}