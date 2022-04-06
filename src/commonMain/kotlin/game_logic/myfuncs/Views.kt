package game_logic.myfuncs


import com.soywiz.korge.view.View
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.position
import com.soywiz.korma.geom.Point
import kotlin.random.Random

fun View.spawnRandom(view: View) {
    view.position(Random.nextDouble(0.0, width), Random.nextDouble(0.0, height))
}

fun View.follow(view: View) {
    this.addUpdater {
        this.pos = Point(width / 2, height / 2) - view.pos
    }

}