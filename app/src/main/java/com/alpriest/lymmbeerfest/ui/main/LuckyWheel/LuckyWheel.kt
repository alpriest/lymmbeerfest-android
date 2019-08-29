package com.alpriest.lymmbeerfest.ui.main.LuckyWheel

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

import androidx.annotation.AttrRes

import com.alpriest.lymmbeerfest.R

/**
 * Created by mohamed on 22/04/17.
 */

class LuckyWheel : FrameLayout, View.OnTouchListener, OnRotationListener {
    private var wheelView: WheelView? = null
    private var target = -1
    private var isRotate = false

    internal val SWIPE_DISTANCE_THRESHOLD = 100
    internal var x1: Float = 0.toFloat()
    internal var x2: Float = 0.toFloat()
    internal var y1: Float = 0.toFloat()
    internal var y2: Float = 0.toFloat()
    internal var dx: Float = 0.toFloat()
    internal var dy: Float = 0.toFloat()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initComponent()
        applyAttribute(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initComponent()
        applyAttribute(attrs)
    }

    private fun initComponent() {
        View.inflate(context, R.layout.lucky_wheel_layout, this)
        setOnTouchListener(this)
        wheelView = findViewById(R.id.wv_main_wheel)
        wheelView!!.setOnRotationListener(this)
    }

    /**
     * Function to add items to wheel items
     *
     * @param wheelItems Wheel items
     */
    fun addWheelItems(wheelItems: List<WheelItem>) {
        wheelView!!.addWheelItems(wheelItems)
    }

    fun applyAttribute(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LuckyWheel, 0, 0)
        try {
            val backgroundColor = typedArray.getColor(R.styleable.LuckyWheel_background_color, Color.DKGRAY)

            wheelView!!.setWheelBackgoundWheel(backgroundColor)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        typedArray.recycle()
    }

    /**
     * Function to set lucky wheel reach the target listener
     *
     * @param onLuckyWheelReachTheTarget Lucky wheel listener
     */
    fun setLuckyWheelReachTheTarget(onLuckyWheelReachTheTarget: OnLuckyWheelReachTheTarget) {
        wheelView!!.setWheelListener(onLuckyWheelReachTheTarget)
    }

    /**
     * @param target target to rotate before swipe
     */
    fun setTarget(target: Int) {
        this.target = target
    }

    /**
     * Function to rotate wheel to degree
     *
     * @param number Number to rotate
     */
    fun rotateWheelTo(number: Int) {
        isRotate = true
        wheelView!!.resetRotationLocationToZeroAngle(number)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {

        if (target < 0 || isRotate) {
            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                y2 = event.y
                dx = x2 - x1
                dy = y2 - y1
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx < 0 && Math.abs(dx) > SWIPE_DISTANCE_THRESHOLD)
                        rotateWheelTo(target)

                } else {
                    if (dy > 0 && Math.abs(dy) > SWIPE_DISTANCE_THRESHOLD)
                        rotateWheelTo(target)
                }
            }
            else -> return true
        }
        return true
    }

    override fun onFinishRotation() {
        isRotate = false
    }
}