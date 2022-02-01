import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korge.Korge
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveBy
import com.soywiz.korge.view.tween.rotateBy
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.dynamic.KDynamic.Companion.keys
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


suspend fun main() = Korge(width = 1920, height = 1080) {



    suspend fun ride_left() = coroutineScope {  // this: CoroutineScope

        launch {


            val backgroundColor = roundRect(1920.0, 1080.0, 5.0, 5.0) {
                fill = RGBA(240, 228, 218)
            }
            val input = views.input



            val image = image(resourcesVfs["flat-car.png"].readBitmap()) {
                anchor(.5, .5)
                scale(.4)
                position(256, 256)

            }
 fd
            keys {
                down(Key.ESCAPE) {
                    sendText("Escape pressed")
                }
                down(Key.UP) {
                    image.moveBy(0.0, -29.0)
                    println(image.y)
                }
                down(Key.LEFT) {
                    sendText("J")
                }
                down(Key.DOWN) {
                    sendText("J")
                }
            }
        }

        println("Hello")
        pr
    }

    val input = views.input
    ride_left()



}

fun sendText(s: String) {
    println(s)

}
