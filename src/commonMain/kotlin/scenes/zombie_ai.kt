package scenes

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korma.geom.Point

class zombie_ai()

fun zombie_ai.move_zombie(zombie: Image, Player: Image) {
	zombie.addUpdater {
		var posPlayer: Point = Player.pos
		var posZombie: Point = zombie.pos
		zombie.x += 0.1
		
	}
}