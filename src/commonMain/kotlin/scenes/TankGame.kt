package scenes

import MainModule.mapbridge
import com.soywiz.klock.milliseconds
import com.soywiz.klock.seconds
import com.soywiz.kmem.clamp
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.BmpSlice
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs
import game_logic.game.MapBridge
import game_logic.movement.addJoystick
import kotlin.math.pow

class TankGame(mapbridge: MapBridge) : Scene() {
	override suspend fun Container.sceneInit() {
		var speed = 0.5
		var xJoystick = 0.0
		var yJoystick = 0.0
		
		var mymap = mapbridge.map
		
		val camera = camera {
		
			
		}
		
		var sand = roundRect(2000.0, 2000.0, 0.5, fill = Colors["#ffd02f"])
		camera.addChild(sand)
		camera.addChild(mymap!!)
		
		val playerAnimation = SpriteAnimation(
			listOf<BmpSlice>(
				resourcesVfs["tank/idle.png"].readBitmapSlice(),
				resourcesVfs["tank/move_1.png"].readBitmapSlice(),
				resourcesVfs["tank/move_2.png"].readBitmapSlice(),
				resourcesVfs["tank/move_3.png"].readBitmapSlice(),
				resourcesVfs["tank/move_4.png"].readBitmapSlice(),
				resourcesVfs["tank/move_5.png"].readBitmapSlice(),
			), 0.1.seconds
		)
		
		val tank = Sprite(resourcesVfs["tank/idle.png"].readBitmapSlice()).centerOn(camera).addTo(this)
tank.centerOn(sand)
		tank.smoothing = false
		tank.scale = 4.0
		tank.playAnimationLooped(spriteAnimation = playerAnimation)
		addUpdater {
			
			tank.onCollision(filter = { view -> view.name == "base" }) {
				print("fds")
				speed = 0.2
			}
			/*speed = if (tank.collidesWith(sand)) {
				0.2
			} else {
				1.5
			}*/
		}
		
		var dx = 0.0
		var dy = 0.0
		fun movePlayerByJoystick(x: Double, y: Double, view: View) {
			view.addUpdater {
				dx = x * 8.7 * (-1)
				dy = y * 8.7 * (-1)
				
			}
			
		}
		
		var joystick = addJoystick(
			views.virtualWidthDouble,
			views.virtualHeightDouble,
			
			) { x, y -> movePlayerByJoystick(x, y, tank) }
		addUpdater {
			val scale = if (it == 0.0.milliseconds) 0.0 else (it / 16.666666.milliseconds)
			dx = dx.clamp(-10.0, +10.0)
			dy = dy.clamp(-10.0, +10.0)
			
			camera.x += (dx * scale) * speed
			camera.y += (dy * scale) * speed
			dx *= 0.9.pow(scale)
			dy *= 0.9.pow(scale)
		}
		
		
	}
	
}
