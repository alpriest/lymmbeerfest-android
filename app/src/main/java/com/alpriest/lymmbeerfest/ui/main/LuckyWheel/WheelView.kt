package com.alpriest.lymmbeerfest.ui.main.LuckyWheel

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation

/**
 * Created by mohamed on 22/04/17.
 */

internal class WheelView : View {
    private var range = RectF()
    private var archPaint: Paint? = null
    private var textPaint: Paint? = null
    private var padding: Int = 0
    private var radius: Int = 0
    private var center: Int = 0
    private var mWheelBackground: Int = 0
    private var mWheelItems: List<WheelItem>? = null
    private var mOnLuckyWheelReachTheTarget: (() -> Unit)? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    private fun initComponents() {
        //arc paint object
        archPaint = Paint()
        archPaint!!.isAntiAlias = true
        archPaint!!.isDither = true
        //text paint object
        textPaint = Paint()
        textPaint!!.color = Color.BLACK
        textPaint!!.isAntiAlias = true
        textPaint!!.isDither = true
        textPaint!!.textSize = 30f
        //rect rang of the arc
        range = RectF(
            padding.toFloat(),
            padding.toFloat(),
            (padding + radius).toFloat(),
            (padding + radius).toFloat()
        )
    }

    /**
     * Get the angele of the target
     *
     * @return Number of angle
     */
    private fun getAngleOfIndexTarget(target: Int): Float {
        return (360 / mWheelItems!!.size * target).toFloat()
    }

    /**
     * Function to set wheel background
     *
     * @param wheelBackground Wheel background color
     */
    fun setWheelBackgoundWheel(wheelBackground: Int) {
        mWheelBackground = wheelBackground
        invalidate()
    }

    /**
     * Function to set wheel listener
     *
     * @param onLuckyWheelReachTheTarget target reach listener
     */
    fun setWheelListener(onLuckyWheelReachTheTarget: (() -> Unit)) {
        mOnLuckyWheelReachTheTarget = onLuckyWheelReachTheTarget
    }

    /**
     * Function to add wheels items
     *
     * @param wheelItems Wheels model item
     */
    fun addWheelItems(wheelItems: List<WheelItem>) {
        mWheelItems = wheelItems
        invalidate()
    }

    /**
     * Function to draw wheel background
     *
     * @param canvas Canvas of draw
     */
    private fun drawWheelBackground(canvas: Canvas) {
        val backgroundPainter = Paint()
        backgroundPainter.isAntiAlias = true
        backgroundPainter.isDither = true
        backgroundPainter.color = mWheelBackground
        canvas.drawCircle(center.toFloat(), center.toFloat(), center.toFloat(), backgroundPainter)
    }

    /**
     * Function to draw image in the center of arc
     *
     * @param canvas    Canvas to draw
     * @param tempAngle Temporary angle
     * @param bitmap    Bitmap to draw
     */
    private fun drawImage(canvas: Canvas, tempAngle: Float, bitmap: Bitmap) {
        //get every arc img width and angle
        val imgWidth = radius / mWheelItems!!.size
        val angle = ((tempAngle + 360 / mWheelItems!!.size / 2) * Math.PI / 180).toFloat()
        //calculate x and y
        val x = (center + radius / 2 / 2 * Math.cos(angle.toDouble())).toInt()
        val y = (center + radius / 2 / 2 * Math.sin(angle.toDouble())).toInt()
        //create arc to draw
        val rect = Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2)
        //rotate main bitmap
        val matrix = Matrix()
        matrix.postRotate(45f)
        val rotatedBitmap =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        canvas.drawBitmap(rotatedBitmap, null, rect, null)
    }

    /**
     * Function to draw text below image
     *
     * @param canvas     Canvas to draw
     * @param tempAngle  Temporary angle
     * @param text       string to show
     */
    private fun drawText(canvas: Canvas, tempAngle: Float, text: String, textColor: Int) {
        textPaint!!.color = textColor

        canvas.save()
        canvas.rotate(tempAngle + 3.5f - 180, width / 2.0f, height / 2.0f)
        canvas.drawText(text, 20f, height / 2.0f, textPaint!!)
        canvas.restore()
    }

    /**
     * Function to rotate wheel to target
     *
     * @param target target number
     */
    fun rotateWheelToTarget(target: Int) {
        val wheelItemCenter =
            270 - getAngleOfIndexTarget(target + 1) + 360 / mWheelItems!!.size.toFloat() / 2
        val DEFAULT_ROTATION_TIME = 3000

        animate()
            .setInterpolator(DecelerateInterpolator(2f))
            .setDuration(DEFAULT_ROTATION_TIME.toLong())
            .rotation(360 + wheelItemCenter)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    if (mOnLuckyWheelReachTheTarget != null) {
                        mOnLuckyWheelReachTheTarget!!()
                    }
                    clearAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            .start()
    }

    /**
     * Function to rotate to zero angle
     *
     * @param target target to reach
     */
    fun resetRotationLocationToZeroAngle(target: Int) {
        animate().setDuration(0)
            .rotation(0f)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    rotateWheelToTarget(target)
                    clearAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawWheelBackground(canvas)
        initComponents()

        var tempAngle = 0f
        val sweepAngle = (360 / mWheelItems!!.size).toFloat()

        for (i in mWheelItems!!.indices) {
            archPaint!!.color = mWheelItems!![i].color
            canvas.drawArc(range, tempAngle, sweepAngle, true, archPaint!!)
            drawText(
                canvas,
                tempAngle,
                if (mWheelItems!![i].text == null) "" else mWheelItems!![i].text,
                mWheelItems!![i].textColor()
            )
            tempAngle += sweepAngle
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = Math.min(measuredWidth, measuredHeight)
        val DEFAULT_PADDING = 5
        padding = if (paddingLeft == 0) DEFAULT_PADDING else paddingLeft
        radius = width - padding * 2
        center = width / 2
        setMeasuredDimension(width, width)
    }
}