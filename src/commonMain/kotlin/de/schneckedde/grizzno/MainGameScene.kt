@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)

package de.schneckedde.grizzno

import com.soywiz.korev.Key
import com.soywiz.korge.box2d.registerBodyWithFixture
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import org.jbox2d.dynamics.BodyType

class MainGameScene() : Scene() {
	override suspend fun Container.sceneInit() {
		println("DOESNTWORK")
		SolidRect(900.0, 900.0, Colors.BLUE)
		var rect = SolidRect(900.0, 900.0, Colors.WHITE).position(100.0, 0.0).registerBodyWithFixture(
			1.0f, 1.0f, 1.0f, 1.0f,
			
			1.0f, 1.0f,
			
			type = BodyType.STATIC, friction = 1.0
		)
		
		val car_image = Image(resourcesVfs["flat-car.png"].readBitmap()).registerBodyWithFixture(
			0.0f, 0.0f, 0.0f,
			
			1.0f, 1.0f, 1.0f, friction = 1.0, type = BodyType.KINEMATIC
		
		)
		
		addChild(rect)
		addChild(car_image)

		
		fun move_car(carImage: Image) {
			
			carImage.addUpdater {
				
				speed = 1.0
				if (stage!!.input.keys.pressing(Key.RIGHT)) {
					carImage.x += speed
				} else if (stage!!.input.keys.pressing(Key.LEFT)) {
					carImage.x -= speed
				} else if (stage!!.input.keys.pressing(Key.DOWN)) {
					carImage.y -= speed
				} else if (stage!!.input.keys.pressing(Key.UP)) {
					carImage.y += speed
				}
				
				
			}
		}
		move_car(car_image)
		
		
	}
	
	
}

