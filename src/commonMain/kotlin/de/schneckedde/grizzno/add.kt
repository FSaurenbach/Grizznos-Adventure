package de.schneckedde.grizzno

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.scale
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class add {

    suspend fun a(tree: Container, stage: Stage, what: String) {
        var what = what
        var stage = stage
        var tree = tree
        val red = "\u001b[31m"
        val reset = "\u001b[0m"


        when (what) {
            "Car" -> Car(tree, stage)
            else -> println(red + "ERROR: You tried to add an unknown object \"$what\"" + reset)

        }

    }


    suspend fun Car(tree: Container, stage: Stage) {
        //fun Key.isPressed(): Boolean = stage.views.input.keys[this]
        
        val car_image = Image(resourcesVfs["flat-car.png"].readBitmap())
        
        InputHandler().move_car(stage, car_image)
        car_image.scale(0.5f, 0.5f)
        tree.addChild(car_image)
    }
}