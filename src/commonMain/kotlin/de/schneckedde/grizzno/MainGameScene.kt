@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)

package de.schneckedde.grizzno

import addTouchGamepad
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.SolidRect
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.position
import com.soywiz.korim.color.Colors

class MainGameScene() : Scene() {
	var pressing = false
	override suspend fun Container.sceneInit() {
		/*
		
		
		
		val car_image = Image(resourcesVfs["flat-car.png"].readBitmap()).position(width, height).registerBodyWithFixture(
			0.0f, 0.0f, 0.0f,
			
			1.0f, 1.0f, 1.0f, friction = 1.0, type = BodyType.KINEMATIC
		
		)
		
		addChild(rect)
		addChild(car_image)
		var mystage = stage
		if (mystage != null) {
			InputHandler().move_car(mystage, car_image)
		} else {
			throw IllegalArgumentException("var mystage cannot be null")
		}
		
		

	
		 */
		
		var width = views.virtualWidth / 2.0
		var height = views.virtualHeight / 2.0
		var rect = SolidRect(60.0, 50.0, Colors.WHITE).position(width, height)
		addChild(rect)
		var x1: Double = 0.0
		var y1: Double = 0.0
		fun af(x: Double, y: Double) {
			x1 = x
			y1 = y
			
		}
		
		var todd = addTouchGamepad(
			views.virtualWidth.toDouble(), views.virtualHeight.toDouble(),
			onStick = { x, y -> af(x, y) },
		)
		rect.addUpdater {
			rect.x += x1
			rect.y += y1
		}
		
		
	}
	
	
}

