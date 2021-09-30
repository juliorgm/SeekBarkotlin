package br.com.cuiadigital.seekbarkotlin.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet

import kotlin.math.roundToInt
import android.graphics.drawable.BitmapDrawable

import android.graphics.drawable.Drawable

import android.graphics.Bitmap
import android.view.View
import androidx.core.graphics.drawable.toDrawable


class CustomSeekBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatSeekBar(context, attrs) {
    var mProgressItemsList = arrayListOf<ProgressItem>()

    fun initData(progressItemsList: List<ProgressItem>) {
        mProgressItemsList.addAll(progressItemsList)

        //setTintBackgrount(mProgressItemsList[0], mProgressItemsList[1])
    }

    fun getBitmapFromView(canvas: Canvas): Bitmap {
        var bitmap =
            Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
        this.draw(canvas)
        return bitmap
    }


    override fun onDraw(canvas: Canvas) {
        if (mProgressItemsList.size > 0) {

            val thumboffset = thumbOffset
            var lastProgressX = 0
            var progressItemWidth: Int
            var progressItemRight: Int
            for (i in mProgressItemsList.indices) {
                val progressItem: ProgressItem = mProgressItemsList.get(i)

                val progressPaint = Paint()
                progressPaint.color = resources.getColor(progressItem.color)

                progress.toDrawable()

                progressItemWidth = ((progressItem.progressPercent * width / 100).roundToInt())
                progressItemRight = lastProgressX + progressItemWidth

                if (i == mProgressItemsList.size - 1 && progressItemRight != width) {
                    progressItemRight = width
                }

                val progressRect = Rect()

                progressRect[lastProgressX, thumboffset / 2, progressItemRight] =
                    height - thumboffset / 2
                canvas.drawRect(progressRect, progressPaint)
                lastProgressX = progressItemRight
            }
        }
        super.onDraw(canvas)
    }
}