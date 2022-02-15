@file:Suppress(
    "LocalVariableName", "CanBeVal", "FunctionName"
)

package scenes

import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter

class OptionsScene : Scene() {
    override suspend fun Container.sceneInit() {
        var hello_text = text("Options")
        hello_text.textSize += 400
        hello_text.centerOnStage()
        var game_mode_switcher = container {
            var main_game_button = uiButton(300.0, 150.0) {
            }.position(views.virtualWidthDouble / 2.0, 1750.0).onClick {
                sceneContainer.changeTo<MainGameScene>(
                    transition = MaskTransition(TransitionFilter.Transition.SWEEP, smooth = true), time = 0.2.seconds
                )
            }
            var options_game_button = uiButton(300.0, 150.0) {
            }.position(main_game_button!!.x - 400.0, 1750.0).onClick {
                sceneContainer.changeTo<OptionsScene>(
                    transition = MaskTransition(TransitionFilter.Transition.SWEEP, smooth = true), time = 0.2.seconds
                )
            }
            text("Game").centerOn(main_game_button).textSize += 40.0
            if (options_game_button != null) {
                text("Options").centerOn(options_game_button).textSize += 40.0
            }
        }
        addChild(game_mode_switcher)
    }
}
