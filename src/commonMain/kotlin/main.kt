import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveBy
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch



public suspend fun main() = Korge(width = 1920, height = 1080, quality = GameWindow.Quality.PERFORMANCE, title = "Grizznos Adventure by Schneckedde") {



    suspend fun start_game() = coroutineScope {  // this: CoroutineScope

        launch {



            val backgroundColor = roundRect(1920.0, 1080.0, 5.0, 5.0) {
                fill = RGBA(240, 228, 218)
            }
            val input = views.input



            var image = image(resourcesVfs["flat-car.png"].readBitmap()) {
                anchor(.5, .5)
                scale(.4)
                position(256, 256)

            }
            suspend fun move_car(x: Double, y: Double){
                var x = x
                var y = y
                image.moveBy(x, y)

            }
            keys{
                down(Key.ESCAPE) {move_car(0.0, 50.0) }
                down(Key.W) {move_car(0.0, -15.0) }
                down(Key.A) {move_car(-50.0, 0.0) }
                down(Key.S) {move_car(0.0, 50.0) }
                down(Key.D) {move_car(50.0, 0.0) }
            }


        }



        println("Hello")
    }

    val input = views.input
    start_game()



}

fun sendText(s: String) {
    println(s)


}




