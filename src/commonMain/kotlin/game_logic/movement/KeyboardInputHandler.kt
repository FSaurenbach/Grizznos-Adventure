@file:Suppress("FunctionName", "LocalVariableName", "CanBeVal", "CanBeVal", "CanBeVal")

package game_logic.movement

import com.soywiz.korev.Key
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addUpdater
import com.soywiz.korma.geom.degrees

class InputHandler {
    
    /**Input handler for the car of "MainGameScene"**/
    fun move_view_by_keys(stage: Stage?, view: View) {
        /**Acces the "MainGameScene" per stage**/
        view.addUpdater {
            var speed = 1.291286833710724
            if ((stage!!.input.keys.pressing(Key.A))){
                view.x -= speed
                view.rotation = 180.degrees
            }
            else if ((stage.input.keys.pressing(Key.D))){ view.x += speed
            view.rotation = 90.degrees}
            else if ((stage.input.keys.pressing(Key.W))) view.y -= speed
            else if ((stage.input.keys.pressing(Key.S))) view.y += speed
        }
        
    }
}
