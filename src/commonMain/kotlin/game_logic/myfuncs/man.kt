package game_logic.myfuncs

import com.soywiz.korge.view.views

suspend fun man(s: String) {
    s.lowercase()
    when (s) {
        "exit" -> {
            views().gameWindow.close()
        }
        "keys" -> {}

    }

}

