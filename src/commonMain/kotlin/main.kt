import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korgw.GameWindow
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scenes.MainGameScene
import scenes.MainMenuScene
import scenes.OptionsScene
import scenes.TankGame

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {
	var DEVELEPOMENT_MODE = true
	/** Defines the opening scene**/
	override val quality = GameWindow.Quality.QUALITY

	//override val mainScene = MainMenuScene::class
	override val mainScene = TankGame::class
	override val title = "Grizznos Adventure by Schneckedde"
	override val size: SizeInt = SizeInt(1920, 1080)
	
	/** Adds the scenes to the module*/
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainMenuScene() }
		mapPrototype { MainGameScene() }
		mapPrototype { OptionsScene() }
		mapPrototype { TankGame() }
	}
}
