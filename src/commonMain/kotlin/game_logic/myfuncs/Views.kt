package game_logic.myfuncs

import com.soywiz.korge.view.View
import com.soywiz.korge.view.position
import kotlin.random.Random

fun View.spawnRandom(view: View ){view.position(Random.nextDouble(0.0, width), Random.nextDouble(0.0, height))}