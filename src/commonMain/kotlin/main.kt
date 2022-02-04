import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.view.*
import com.soywiz.korgw.GameWindow
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.unaryMinus
import com.soywiz.korma.geom.unaryPlus
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

var movingUp = false


suspend fun main() = Korge(
    width = 1920,
    height = 1080,
    quality = GameWindow.Quality.PERFORMANCE,
    title = "Grizznos Adventure by Schneckedde"
) {


    suspend fun start_game() = coroutineScope {  // this: CoroutineScope

        launch {


            val backgroundColor = roundRect(1920.0, 1080.0, 5.0, 5.0) {
                fill = RGBA(240, 228, 218)
            }
            val input = views.input

            val rect = solidRect(100.0, 100.0, Colors["#ff4ad0"]){

            }
            var image = image(resourcesVfs["flat-car.png"].readBitmap()) {
                anchor(.5, .5)
                scale(.4)
                position(256, 256)

            }
            image.addUpdater {
                if (!(collidesWith(rect))) {
                    speed = 2.5
                    if ((input.keys.pressing(Key.RIGHT)) || (input.keys.pressing(Key.D))) {
                        image.x += speed
                        image.rotation((+90).degrees)
                    } else if ((input.keys.pressing(Key.UP)) || (input.keys.pressing(Key.W))) {
                        image.y -= speed
                        image.rotation((+0).degrees)
                    } else if ((input.keys.pressing(Key.DOWN)) || (input.keys.pressing(Key.S))) {
                        image.y += speed
                        image.rotation((-180).degrees)
                    } else if ((input.keys.pressing(Key.LEFT)) || (input.keys.pressing(Key.A))) {
                        image.x -= speed
                        image.rotation((-90).degrees)

                    }
                }
            }


        }



        println("Hello")
    }


    start_game()


}

fun sendText(s: String) {
    println(s)


}