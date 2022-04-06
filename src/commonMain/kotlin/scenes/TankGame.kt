package scenes

import com.soywiz.korev.Key
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.degrees
import game_logic.movement.JoyBallPosition
import game_logic.movement.addJoystick
import game_logic.myfuncs.follow

class TankGame : Scene() {
    override suspend fun Container.sceneInit() {
        var xJoystick = 0.0
        var yJoystick = 0.0
        val myCamera = container {
            val background = roundRect(1920.0 - 200.0, 1080.0 - 200.0, 0.5, fill = Colors["#ffe800"]).centerOnStage()
            addChild(background)
            container {

            }


        }

        val tank = Image(resourcesVfs["tank.png"].readBitmap()).centerOn(sceneContainer).addTo(this)
        fun movePlayerByJoystick(x: Double, y: Double, view: View) {
            view.addUpdater {
                xJoystick = x * 2.7
                yJoystick = y * 2.7

            }

        }


        tank.addUpdater {
            if (JoyBallPosition <= Point(0.0, 0.0)){
                tank.rotation = 180.degrees
            }
            else{
                tank.rotation = 0.degrees
            }
            tank.x += xJoystick


            tank.y += yJoystick
            xJoystick = 0.0
            yJoystick = 0.0
        }
        var joystick = addJoystick(
            stage!!,
            views.virtualWidthDouble, views.virtualHeightDouble,

            ) { x, y -> movePlayerByJoystick(x, y, tank) }
        myCamera.follow(tank)
        fun moveViewByKeys(stage: Stage) {
            /**Access the "MainGameScene" per stage**/
            tank.addUpdater {
                val speed = 1.291286833710724
                if ((stage.input.keys.pressing(Key.A))){
                    tank.x -= speed
                    tank.rotation = 180.degrees
                }
                else if ((stage.input.keys.pressing(Key.D))){ tank.x += speed
                    tank.rotation = 90.degrees}
                else if ((stage.input.keys.pressing(Key.W))) tank.y -= speed
                else if ((stage.input.keys.pressing(Key.S))) tank.y += speed
            }

        }
        moveViewByKeys(stage!!)


    }
}