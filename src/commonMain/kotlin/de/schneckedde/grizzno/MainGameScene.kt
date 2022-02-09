@file:Suppress(
	"LocalVariableName", "CanBeVal", "FunctionName"
)

package de.schneckedde.grizzno

import addTouchGamepad
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.camera.Camera
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees

class MainGameScene() : Scene() {
	var pressing = false
	override suspend fun Container.sceneInit() {
		var background = SolidRect(views.virtualWidth.toDouble(), views.virtualHeight.toDouble(), Colors["#1f960b"] )
		addChild(background)
		var width = views.virtualWidth / 2.0
		var height = views.virtualHeight / 2.0
		
		var ima = resourcesVfs["Top_Down_Survivor/handgun/idle/survivor-idle_handgun_0.png"].readBitmap()
		
		val rect = sprite(ima)
		
		addChild(rect)
		
		
		
		
		var x1: Double = 0.0
		var y1: Double = 0.0
		fun af(x: Double, y: Double) {
			x1 = x*1.5
			y1 = y*1.5
			
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

