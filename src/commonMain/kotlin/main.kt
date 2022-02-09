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
	/** Defines the opening scene**/
	override val mainScene = MainMenuScene::class
	override val title = "Grizznos Adventure by Schneckedde"
	override val size: SizeInt = SizeInt(1080 , 1350)
	override val bgcolor = Colors.RED
	
	
	/** Adds the scenes to the module*/
	override suspend fun AsyncInjector.configure() {
		mapPrototype { MainMenuScene() }
		mapPrototype { MainGameScene() }
	}
}
