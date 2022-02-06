package de.schneckedde.grizzno
import de.schneckedde.grizzno.MainGameScene
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.container
import com.soywiz.korge.view.position

class MainMenuScene() : Scene() {
	override suspend fun Container.sceneInit() {
		var playButton = uiButton(256.0, 32.0) {
			text = "Play"
			position(views.virtualWidth / 2 - 128, views.virtualHeight / 2 - 64)
			onClick {
				print("§jfa")
				sceneContainer.changeTo<MainGameScene>()
			}
		}
	}
}
