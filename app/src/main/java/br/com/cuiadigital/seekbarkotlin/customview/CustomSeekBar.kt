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


class CustomSeekBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatSeekBar(context, attrs) {
    var mProgressItemsList = arrayListOf<ProgressItem>()

    fun initData(progressItemsList: List<ProgressItem>) {
        mProgressItemsList.addAll(progressItemsList)

        //setTintBackgrount(mProgressItemsList[0], mProgressItemsList[1])
    }

    fun setTintBackgrount(progressSpent: ProgressItem, progressMax: ProgressItem){
        val progressBarWidth = getWidth()

        val canvas = Canvas()

        var progressSpentWith = ((progressSpent.progressPercent * width / 100).roundToInt())
        var progressMaxWith= ((progressMax.progressPercent * width / 100).roundToInt())

        val progressSpentPaint = Paint()
        progressSpentPaint.color = resources.getColor(progressSpent.color)

        val progressMaxPaint = Paint()
        progressMaxPaint.color = resources.getColor(progressMax.color)


        val progressRect = Rect()

        progressRect[0, thumbOffset / 2, progressSpentWith] = height - thumbOffset / 2
        canvas.drawRect(progressRect, progressSpentPaint)

        progressRect[1, thumbOffset / 2, progressMaxWith] = height - thumbOffset / 2
        canvas.drawRect(progressRect, progressMaxPaint)


        val view = View(this.context)

        val b: Bitmap = getBitmapFromView(canvas )
        val drawable: Drawable = BitmapDrawable(resources, b)

        progressDrawable = drawable
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