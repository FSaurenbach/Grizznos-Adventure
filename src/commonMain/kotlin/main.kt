import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scenes.MainGameScene
import scenes.MainMenuScene
import scenes.OptionsScene

suspend fun main() = Korge(Korge.Config(module = MainModule))

object MainModule : Module() {
	/** Defines the opening scene**/
	override val quality = GameWindow.Quality.QUALITY
	
	override val mainScene = MainMenuScene::class
	override val title = "Grizznos Adventure by Schneckedde"
	override val size: SizeInt = SizeInt(1080, 1920)
	
	/** Adds the scenes to the module*/
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainMenuScene() }
		mapPrototype { MainGameScene() }
		mapPrototype { OptionsScene() }
	}
}
