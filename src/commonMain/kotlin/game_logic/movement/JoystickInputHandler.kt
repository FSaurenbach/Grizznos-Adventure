package game_logic.movement

import com.soywiz.kmem.clamp
import com.soywiz.korev.TouchEvent
import com.soywiz.korge.baseview.BaseView
import com.soywiz.korge.component.TouchComponent
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.Point
import com.soywiz.korma.geom.cos
import com.soywiz.korma.geom.sin
import com.soywiz.korma.geom.vector.circle
import kotlin.math.hypot

var JoyBallPosition:Point = Point(0.0,0.0)
var pressing = false

fun Container.addJoystick(
    stage: Stage,
    width: Double = 320.0,
    height: Double = 224.0,
    radius: Double = height / 8,
    onStick: (x: Double, y: Double) -> Unit = { _, _ -> }
) {
    var rr = false
    var dragging = false
    val view = this
    lateinit var ball: View
    radius * 2

    container {
        position(450.0, 700.0)
        graphics {
            fill(Colors["#ff4ad0"]) { circle(0.0, 0.0, radius) }
            alpha(0.2)
        }
        ball = graphics {
            fill(Colors.WHITE) { circle(0.0, 0.0, radius * 0.7) }
            alpha(0.2)
        }
    }

    val addComponent = view.addComponent(object : TouchComponent {
        override val view: BaseView = view

        val start = Point(0, 0)

        override fun onTouchEvent(views: Views, e: TouchEvent) {
            val px = e.activeTouches.firstOrNull()?.x ?: 0.0
            val py = e.activeTouches.firstOrNull()?.y ?: 0.0

            when (e.type) {
                TouchEvent.Type.START -> {
                    if (px >= width / 2) return
                    start.x = px
                    start.y = py
                    ball.alpha = 0.3
                    dragging = true
                    pressing = true
                }
                TouchEvent.Type.END -> {
                    ball.position(0, 0)
                    ball.alpha = 0.2
                    dragging = false
                    onStick(0.0, 0.0)
                    pressing = false
                }
                TouchEvent.Type.MOVE -> {
                    if (dragging) {
                        val deltaX = px - start.x
                        val deltaY = py - start.y
                        val length = hypot(deltaX, deltaY)
                        val maxLength = radius * 0.3
                        val lengthClamped = length.clamp(0.0, maxLength)
                        val angle = Angle.between(start.x, start.y, px, py)
                        ball.position(cos(angle) * lengthClamped, sin(angle) * lengthClamped)
                        JoyBallPosition = ball.pos
                        if (ball.pos <Point(0)) rr = true
                        val lengthNormalized = lengthClamped / maxLength
                        onStick(cos(angle) * lengthNormalized, sin(angle) * lengthNormalized)
                    }
                }
            }
        }
    })
}
