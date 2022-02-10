@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)

package de.schneckedde.grizzno

import addTouchGamepad
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.ui.uiText
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlin.math.absoluteValue

class MainGameScene : Scene() {
	override suspend fun Container.sceneInit() {
		var mystage = Stage(views)
		var background = image(resourcesVfs["background.png"].readBitmap())
		addChild(background)
		var player_sprite = resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap()
		
		val Player = sprite(player_sprite)
		
		addChild(Player)
		
		keys {
			down(Key.ESCAPE) {
				sceneContainer.changeTo<MainMenuScene>()
			}
			down(Key.H){
				background.centerOnStage()
			}
			var x_joystick = 0.0
			var y_joystick = 0.0
			fun move_player_by_joystick(x: Double, y: Double) {
				x_joystick = x * 1.7
				y_joystick = y * 1.7
				
			}
			
			
			var joystick = addTouchGamepad(
				mystage,
				views.virtualWidth.toDouble(), views.virtualHeight.toDouble(),
				onStick = { x, y -> move_player_by_joystick(x, y) },
			)
			var meters: Double = 0.0
			var meters_text = uiText("Meter: 0", 500.0, 500.0)
			meters_text.textSize += 60
			InputHandler().move_player_by_keys(mystage, Player)
			Player.addUpdater {
				Player.x += x_joystick
				
				meters += x_joystick.absoluteValue
				
				Player.y += y_joystick
				meters += y_joystick.absoluteValue
				meters_text.text = "Meter: ${meters.toInt()}"
				
				
			}
			
		}
		
	}
}

