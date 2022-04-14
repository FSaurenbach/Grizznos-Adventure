package scenes

import com.soywiz.klock.Frequency
import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.draggable
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.onMove
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.IPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class PlayerPos(
	var posX: Double,
	var posY:Double
	
)

class EditorScene() : Scene() {
	override suspend fun Container.sceneInit() {
		var PlayerPos = PlayerPos(0.0, 0.0)
		var le = container {
			var d = roundRect(100, 100, 5, fill = Colors.RED)
		}
		var json = Json {
			prettyPrint = true
		}
		le.draggable { info ->
			le.xy(info.viewNextXY)
		}
		keys {
			down(Key.K) {
				var vfs = resourcesVfs["editor.json"]
				var sting = vfs.readString()
				var jsons = json.decodeFromString<PlayerPos>(sting)
				le.x = jsons.posX
				le.y = jsons.posY
			}}
			le.onMove {
			PlayerPos.posX = le.x
				PlayerPos.posY = le.y
			var jsons = json.encodeToString(PlayerPos)
			resourcesVfs["editor.json"].writeString(jsons)
		}
		
		
		
		
		
	}
}
