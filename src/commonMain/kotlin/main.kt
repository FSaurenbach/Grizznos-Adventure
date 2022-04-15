import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korgw.GameWindow
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scenes.*

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {
	/** Defines the opening scene**/
	override val quality = GameWindow.Quality.QUALITY

	//override val mainScene = MainMenuScene::class
	override val mainScene = EditorScene::class
	override val title = "Grizznos Adventure by Schneckedde"
	override val size: SizeInt = SizeInt(1920, 1080)
	
	/** Adds the scenes to the module*/
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainMenuScene() }
		mapPrototype { MainGameScene() }
		mapPrototype { OptionsScene() }
		mapPrototype { TankGame() }
		mapPrototype { EditorScene() }
	}
}
