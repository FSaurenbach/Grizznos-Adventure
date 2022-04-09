package scenes

import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.animate.Animator
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tiles.BaseTileMap
import com.soywiz.korge.view.tiles.TileSet
import com.soywiz.korge.view.tiles.tileMap
import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.BmpSlice
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.degrees
import game_logic.movement.JoyBallPosition
import game_logic.movement.addJoystick
import game_logic.myfuncs.bitmap
import game_logic.myfuncs.follow

class TankGame : Scene() {
    override suspend fun Container.sceneInit() {
    
        var xJoystick = 0.0
        var yJoystick = 0.0
        
        val myCamera = container {
            val background = roundRect(views.virtualWidthDouble, views.virtualWidthDouble, 0.5, fill = Colors["#ffe800"]).centerOnStage()
            addChild(background)
            
            container {

            }


        }
        var mylist = listOf<BmpSlice>(
            resourcesVfs["tank_idle.png"].readBitmapSlice(),
            resourcesVfs["tank_move_1.png"].readBitmapSlice(),
            resourcesVfs["tank_move_2.png"].readBitmapSlice(),
            resourcesVfs["tank_move_3.png"].readBitmapSlice(),
            resourcesVfs["tank_move_4.png"].readBitmapSlice(),
            resourcesVfs["tank_move_5.png"].readBitmapSlice(),
        )
        var myan = SpriteAnimation(mylist, 1.seconds)
        var tank = Sprite(resourcesVfs["tank_idle.png"].readBitmapSlice()).centerOn(sceneContainer).addTo(this)
        tank.scale= 4.0
        tank.playAnimationLooped(spriteAnimation = myan)
       
        fun movePlayerByJoystick(x: Double, y: Double, view: View) {
            view.addUpdater {
                xJoystick = x * 2.7
                yJoystick = y * 2.7

            }

        }

       
        tank.addUpdater {
            
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