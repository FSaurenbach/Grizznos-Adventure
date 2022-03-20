@file:Suppress(
    "LocalVariableName", "CanBeVal", "FunctionName"
)

package scenes


import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.*
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.ui.uiText
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korim.format.PNG
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import game_logic.game.Zombie
import game_logic.movement.InputHandler
import game_logic.movement.addJoystick
import game_logic.myfuncs.follow

var bul = 0
var myscore = 0

class MainGameScene : Scene() {

    override suspend fun Container.sceneInit() {
        //var bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG))
        var zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap(PNG))
        val Player = Image(resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap())
        var mystage = Stage(views)
        lateinit var zombie1:Zombie
        var myCamera = container {
            var background = Image(resourcesVfs["background.png"].readBitmap())
            addChild(background)
            var tree = container {

            }

            zombie1 = Zombie(
                tree = tree,
                Player = Player,
                bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG)),
                zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap())
            )/*
            var zombie2 = Zombie(
                tree = tree,
                Player = Player,
                bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG)),
                zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap())
            )*/

        }


        var x_joystick = 0.0
        var y_joystick = 0.0


        var score_text = uiText("Score: ${resourcesVfs["score.txt"].readString()}") {
            textSize = 70.0
        }.centerXOnStage()

        //Zombie(tree = myCamera, Player = Player, bullet = Image(resourcesVfs["bullet.png"].readBitmap(PNG)), zombie = Image(resourcesVfs["tds_zombie/skeleton-attack_0.png"].readBitmap()))

        addChild(Player)
        InputHandler().move_player_by_keys(stage, Player)
        fun move_player_by_joystick(x: Double, y: Double) {
            Player.addUpdater {

                x_joystick = x * 2.7
                y_joystick = y * 2.7

            }

        }
        Player.addUpdater {

            Player.x += x_joystick


            Player.y += y_joystick

            x_joystick = 0.0
            y_joystick = 0.0
        }
        var joystick = addJoystick(
            mystage,
            views.virtualWidthDouble, views.virtualHeightDouble,
        ) { x, y -> move_player_by_joystick(x, y) }


        myCamera.centerOnStage()
        Player.centerOnStage()
        score_text.addUpdater {
            score_text.text = "Score: $myscore"
        }
        keys {
            down(Key.ESCAPE) {

                sceneContainer.back(0.5.seconds, transition = MaskTransition(transition = TransitionFilter.Transition.DIAGONAL1, smooth = true))//.withEasing(Easing.EASE_IN_OUT_BACK))
            }
            down(Key.K) {

                spawn_bullets(myCamera, Player, Image(resourcesVfs["bullet.png"].readBitmap(PNG)), zombie1)
            }
        }
        myCamera.follow(Player)
    }


}

private fun spawn_bullets(tree: Container, Player: View, bullet: Image, zombie: Zombie) {

    if (bul <= 5) {
        bullet.centerOn(Player)
        tree.addChild(bullet)
        bullet.centerOn(Player)
        bullet.scale = 0.1
        bullet.rotation = 90.degrees





        print(tree.width)

        print(tree.height)
        bullet.addUpdater {

            if (bullet.collidesWith(zombie)) {
                myscore += 10
                println("zombie should have died")

            }


            bullet.x += 2.0

        }

        bul++

    }
}
