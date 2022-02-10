@file:Suppress("FunctionName", "LocalVariableName", "CanBeVal", "CanBeVal", "CanBeVal")

package de.schneckedde.grizzno

import com.soywiz.korev.Key
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.addUpdater

class InputHandler {
	/**Input handler for the car of MainGameScene**/
	suspend fun move_player_by_keys(stage: Stage, player_view: Sprite) {
		/**Acces the MainGameScene per stage**/
		var stage = stage
		var player = player_view
		player.addUpdater {
			var speed = 0.291286833710724
			
			if ((stage.input.keys.pressing(Key.LEFT))) player.x -= speed
			else if ((stage.input.keys.pressing(Key.A))) player.x -= speed
			else if ((stage.input.keys.pressing(Key.RIGHT))) player.x += speed
			else if ((stage.input.keys.pressing(Key.D))) player.x += speed
			else if ((stage.input.keys.pressing(Key.UP))) player.y -= speed
			else if ((stage.input.keys.pressing(Key.W))) player.y -= speed
			else if ((stage.input.keys.pressing(Key.DOWN))) player.y += speed
			else if ((stage.input.keys.pressing(Key.S))) player.y += speed
		}
		
		
	}
}