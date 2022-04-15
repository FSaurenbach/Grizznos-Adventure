@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)
package scenes

import com.soywiz.korev.Key
import com.soywiz.korge.input.draggable
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onMove
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.container
import com.soywiz.korge.view.roundRect
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class PlayerPos(
	var posX: Double,
	var posY: Double

)

class EditorScene() : Scene() {
	override suspend fun Container.sceneInit() {
		
		var PlayerPos = PlayerPos(0.0, 0.0)
		var le = container {
			var d = roundRect(100, 100, 5, fill = Colors.RED).draggable {}
			var d2 = roundRect(100, 100, 5, fill = Colors.RED).draggable {}
			var d3 = roundRect(100, 100, 5, fill = Colors.RED).draggable {}
			var d4 = roundRect(100, 100, 5, fill = Colors.RED).draggable {}
			var d5 = roundRect(100, 100, 5, fill = Colors.RED).draggable {}
			var d6 = roundRect(100, 100, 5, fill = Colors.RED).draggable {}
		}
		var json = Json {
			prettyPrint = true
		}
		keys {
			down(Key.K) {
				var vfs = resourcesVfs["editor.json"]
				var sting = vfs.readString()
				var jsons = json.decodeFromString<PlayerPos>(sting)
				le.children[0].x = jsons.posX
				le.children[0].y = jsons.posY
			}
		}
		le.onMove {
			PlayerPos.posX = le.children[0].x
			PlayerPos.posY = le.children[0].y
			var jsons = json.encodeToString(PlayerPos)
			resourcesVfs["editor.json"].writeString(jsons)
		}
		/*var play = uiButton("Play").centerOnStage().onClick {
			sceneContainer.changeTo<TankGame>(time = 1.seconds)
		}*/
		
		
	}
}
