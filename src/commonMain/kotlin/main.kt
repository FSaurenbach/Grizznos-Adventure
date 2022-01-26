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

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#53ae00"]) {
	val minDegrees = (-16).degrees
	val maxDegrees = (+16).degrees

	val image = image(resourcesVfs["car-full.png"].readBitmap()) {
		rotation = maxDegrees
		anchor(.5, .5)
		scale(.4)
		position(256, 256)
	}
	while (true) {
		image.moveBy(5, 5)
		println(image.x)
	}
}