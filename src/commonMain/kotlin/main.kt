import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveBy
import com.soywiz.korge.view.tween.moveTo
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*

suspend fun main() = Korge(width = 1280, height = 960) {
	val backgroundColor = roundRect(100.0,100.0, 5.0, 5.0){
		fill = RGBA(240, 228, 218)

}
	val image = image(resourcesVfs["car-full.png"].readBitmap()) {
		//rotation = maxDegrees
		anchor(.5, .5)
		scale(.4)
		position(256, 256)
	}
	while (true) {
		image.moveBy(15, 0)
		println(image.x)
	}
}