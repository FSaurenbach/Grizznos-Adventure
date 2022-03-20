package game_logic.game

import com.soywiz.korge.render.RenderContext
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.XY

class Zombie(
    tree: Container,
    Player: Image,

    bullet: Image,
    zombie: Image,

    position: XY? = null
) : View() {
    init {

        fun spawn_zombie(
        ) {
            print("i'm a zombiefucker")

            zombie.centerOnStage()

            tree.addChild(zombie)
        }
        spawn_zombie()

    }


    class zombie_ai() {
        fun move_zombie(zombie: Image, Player: Image) {
            zombie.addUpdater { zombie.x += 0.001 }
        }
    }

    override fun renderInternal(ctx: RenderContext) {
        TODO("Not yet implemented")
    }

}