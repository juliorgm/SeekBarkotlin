package br.com.cuiadigital.seekbarkotlin

import android.content.res.ColorStateList
import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import br.com.cuiadigital.seekbarkotlin.customview.CustomSeekBar

class MainActivity2 : AppCompatActivity() {
    private val seekbar by lazy { findViewById<SeekBar>(R.id.myseekbar) }
    private val display by lazy { findViewById<TextView>(R.id.dispay) }

    val max = 1050
    val min = 50
    val spent = 350
    var limitUser = 500
    val spentPercent : Int = (spent / (max - min) * 100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val drawable = ColorBarDrawable(intArrayOf(ContextCompat.getColor(this,R.color.pink)))
        val drawable2 = ColorBarDrawable(intArrayOf(ContextCompat.getColor(this,R.color.gray_dark)))

        seekbar.max = max
        seekbar.progress = limitUser
        seekbar.secondaryProgress = spent


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (seekBar.progress % 10 == 0)
                    display.text = seekBar.progress.toString()

                if (progress < spent) {
                    seekBar.progressDrawable = ContextCompat.getDrawable( applicationContext, R.drawable.bg_seekbar_test2)
                }else{
                    seekBar.progressDrawable = ContextCompat.getDrawable( applicationContext, R.drawable.bg_seekbar_test)
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                display.text = seekBar.progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                display.text = seekBar.progress.toString()
            }

        })



    }
}