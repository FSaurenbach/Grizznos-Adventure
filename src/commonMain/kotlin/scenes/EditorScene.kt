@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)
package scenes

import com.soywiz.klock.seconds
import com.soywiz.korge.input.draggable
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import kotlinx.serialization.Serializable

@Serializable
data class PlayerPos(
	var posX: Double, var posY: Double

)

class EditorScene : Scene() {
	override suspend fun Container.sceneInit() {
		print("sceneInit")
		var d1 = roundRect(100, 100, 5, fill = Colors.RED)
		d1.name = "grid"
		var d2 = roundRect(100, 100, 5, fill = Colors.RED).alignLeftToRightOf(d1)
		d2.name = "grid"
		var d3 = roundRect(100, 100, 5, fill = Colors.RED).alignLeftToRightOf(d2)
		d3.name = "grid"
		var d4 = roundRect(100, 100, 5, fill = Colors.RED).alignLeftToRightOf(d3)
		d4.name = "grid"
		var d5 = roundRect(100, 100, 5, fill = Colors.RED).alignLeftToRightOf(d4)
		d5.name = "grid"
		var d6 = roundRect(100, 100, 5, fill = Colors.RED).alignLeftToRightOf(d5)
		d6.name = "grid"
		
		PlayerPos(0.0, 0.0)
		var d = roundRect(50, 50, 5, fill = Colors.WHITE).draggable {}
		d.onCollision(filter = { view -> view.name == "grid" }) {
			this.centerOn(it)
		}
		var dd = roundRect(50, 50, 5, fill = Colors.WHITE).draggable {}
		dd.onCollision(filter = { view -> view.name == "grid" }) {
			this.centerOn(it)
		}
		var ddd = roundRect(50, 50, 5, fill = Colors.WHITE).draggable {}
		ddd.onCollision(filter = { view -> view.name == "grid" }) {
			this.centerOn(it)
		}
		
		var play = uiButton("Play the Game").centerOnStage().onClick {
			sceneContainer.changeTo<TankGame>(time = 1.seconds)
		}
		
		
	}
}
