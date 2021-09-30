package br.com.cuiadigital.seekbarkotlin

import android.graphics.*

import android.graphics.drawable.Drawable


class ColorBarDrawable(private val themeColors: IntArray) : Drawable() {
    override fun draw(canvas: Canvas) {

        // get drawable dimensions
        val bounds: Rect = bounds
        val width: Int = bounds.right - bounds.left
        val height: Int = bounds.bottom - bounds.top

        // draw background gradient
        val backgroundPaint = Paint()
        val barWidth = width / themeColors.size
        val barWidthRemainder = width % themeColors.size
        for (i in themeColors.indices) {
            backgroundPaint.setColor(themeColors[i])
            canvas.drawRect((i * barWidth).toFloat(), 0f,
                ((i + 1) * barWidth).toFloat(), height.toFloat(), backgroundPaint)
        }

        // draw remainder, if exists
        if (barWidthRemainder > 0) {
            canvas.drawRect(
                (themeColors.size * barWidth).toFloat(),
                0f,
                (themeColors.size * barWidth + barWidthRemainder).toFloat(),
                height.toFloat(),
                backgroundPaint
            )
        }
    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(cf: ColorFilter?) {}
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}