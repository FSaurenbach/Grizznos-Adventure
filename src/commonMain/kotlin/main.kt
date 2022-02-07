import com.soywiz.korge.Korge
import com.soywiz.korge.scene.Module
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import de.schneckedde.grizzno.MainGameScene
import de.schneckedde.grizzno.MainMenuScene

suspend fun main() = Korge(Korge.Config(module = MainModule))


object MainModule : Module() {
	// define the opening scene
	override val mainScene = MainMenuScene::class
	val width = 1920
	val height = 1080
	override val quality = GameWindow.Quality.PERFORMANCE
	override val title = "Grizznos Adventure by Schneckedde"

	override val icon: String = "flat-car.png"
	override val size: SizeInt = SizeInt(1920, 1080)
	
	// add the scenes to the module
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainMenuScene() }
		mapPrototype { MainGameScene() }
	}
}
