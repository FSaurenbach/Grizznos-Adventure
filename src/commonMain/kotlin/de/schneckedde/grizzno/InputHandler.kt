package de.schneckedde.grizzno

import com.soywiz.korev.Key
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.addUpdater

class InputHandler {
    

    suspend fun move_car(stage: Stage, car_image: Image) {
        var stage = stage
        var car_image = car_image
        car_image.addUpdater {
            var speed = 2.5
            if ((stage.input.keys.pressing(Key.LEFT))) car_image.x -= speed
            else if ((stage.input.keys.pressing(Key.RIGHT))) car_image.x += speed
            else if ((stage.input.keys.pressing(Key.UP))) car_image.y -= speed
            else if ((stage.input.keys.pressing(Key.DOWN))) car_image.y += speed
        }


    }
}