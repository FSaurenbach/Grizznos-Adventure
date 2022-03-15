package scenes

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater

class zombie_ai(){}
fun zombie_ai.move_zombie(zombie: Image, Player: Image){zombie.addUpdater { zombie.x += 0.001}}