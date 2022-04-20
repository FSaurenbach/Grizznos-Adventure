import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korgw.GameWindow
import com.soywiz.korinject.AsyncInjector
import game_logic.game.MapBridge
import scenes.*

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {
	/** Defines the opening scene**/
	override val quality = GameWindow.Quality.QUALITY
	override val fullscreen = true
	//override val mainScene = MainMenuScene::class
	override val mainScene = EditorScene::class
	override val title = "Grizznos Adventure by Schneckedde"
/*	override val size: SizeInt = SizeInt(1920, 1080)*/
	val mapbridge = MapBridge()
	/** Adds the scenes to the module*/
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainMenuScene() }
		mapPrototype { TankGame(mapbridge) }
		mapPrototype { EditorScene(mapbridge) }
	}
}
