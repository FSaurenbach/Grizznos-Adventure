import com.soywiz.korge.Korge
import com.soywiz.korge.view.centerOnStage
import com.soywiz.korge.view.container
import com.soywiz.korgw.GameWindow
import de.schneckedde.grizzno.add
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() = Korge(
    width = 1920,
    height = 1080,
    quality = GameWindow.Quality.PERFORMANCE,
    title = "Grizznos Adventure by Schneckedde"
) {
    val tree = container {
        centerOnStage()
    }


    suspend fun start_game() = coroutineScope {  // this: CoroutineScope
        launch {


        }
        println("Hello")

    }
    add().a(tree, this, "Car")
    add().a(tree, this, "a")

    start_game()


}

