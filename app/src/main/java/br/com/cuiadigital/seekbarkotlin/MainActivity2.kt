package br.com.cuiadigital.seekbarkotlin

import android.content.res.Resources
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() {
    private val seekbar by lazy { findViewById<SeekBar>(R.id.myseekbar) }
    private val display by lazy { findViewById<TextView>(R.id.dispay) }
    private val image by lazy { findViewById<ImageView>(R.id.image) }

    val max = 1050
    val min = 50
    val spent = 350
    var limitUser = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSeekbarDrawable()

        seekbar.max = max - min
        seekbar.progress = limitUser
        seekbar.secondaryProgress = spent

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                    display.text = (progress + min).toString()

                setSeekbarDrawable()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                display.text = seekBar.progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                display.text = seekBar.progress.toString()
            }
        })
    }


    fun setSeekbarDrawable(){
        if (seekbar.progress < spent) {
            seekbar.progressDrawable = getDrawable(R.drawable.bg_progress_two)

        }else{
            seekbar.progressDrawable = getDrawable(R.drawable.bg_progress_one)
        }

        val desiredHeight = (12 * Resources.getSystem().displayMetrics.density)
        val bounds = seekbar.progressDrawable.copyBounds()
        val actualTop =
            Math.round((bounds.bottom - bounds.top) / 2.0 - desiredHeight / 2.0).toInt()
        seekbar.progressDrawable.setBounds(
            bounds.left,
            actualTop,
            bounds.right,
            actualTop + desiredHeight.toInt()



        )
    }

    companion object{
        const val STEPS = 10
    }


}