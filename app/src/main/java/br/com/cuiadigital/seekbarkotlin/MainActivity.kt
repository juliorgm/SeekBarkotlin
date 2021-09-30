package br.com.cuiadigital.seekbarkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import br.com.cuiadigital.seekbarkotlin.customview.CustomSeekBar
import br.com.cuiadigital.seekbarkotlin.customview.ProgressItem
import br.com.cuiadigital.seekbarkotlin.model.UserLimit
import br.com.cuiadigital.seekbarkotlin.model.mockUserLImit
import com.google.android.material.slider.RangeSlider
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
//
//    private val seekbarMain by lazy { findViewById<SeekBar>(R.id.seekBar) }
    private val seekbarCustom by lazy { findViewById<CustomSeekBar>(R.id.customseekbar) }
    private val tvMax by lazy { findViewById<TextView>(R.id.tvMax) }
    private val tvMin by lazy { findViewById<TextView>(R.id.tvMin) }
    private val edMaxDefined by lazy { findViewById<TextView>(R.id.edMaxDefined) }
    lateinit var limit : UserLimit

    private val max = 1500.0
    private val available = 0.0
    private val pinkLightSpan = 300.0
    private val graySpan = 400.0
    private val grayLightSpan = 150.0

    private var progressItemList: ArrayList<ProgressItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        limit = mockUserLImit.getUserLimit()

        initViews()

        seekbarCustom.progress = 30
        seekbarCustom.secondaryProgress = 60
        seekbarCustom.max = 100
        seekbarCustom.incrementProgressBy(10);

        initDataToSeekbar();


    }

    private fun initViews() {
        tvMax.text = limit.max.toString()
        tvMin.text = limit.min.toString()
        seekbarCustom.max = limit.max.toInt()
    }

    private fun setMaxDefinedByUser(maxDefinedLimit: Int) {
        limit.maxDefinedBUser = maxDefinedLimit.toDouble()
        edMaxDefined.text = maxDefinedLimit.toString()
        seekbarCustom.progress = maxDefinedLimit
    }

    private fun initDataToSeekbar() {
        progressItemList = arrayListOf()

        progressItemList.add(ProgressItem(R.color.gray_dark, getPercentFrom(graySpan)))
        progressItemList.add(ProgressItem(R.color.gray_light, getPercentFrom(grayLightSpan)))
        progressItemList.add(ProgressItem(R.color.pink, getPercentFrom(available)))
        progressItemList.add(ProgressItem(R.color.pink_light, getPercentFrom(pinkLightSpan)))

        seekbarCustom.initData(progressItemList)

        seekbarCustom.progressDrawable = ColorBarDrawable(intArrayOf(ContextCompat.getColor(this, R.color.pink_light),R.color.pink))


        seekbarCustom.invalidate()
    }

    private fun getPercentFrom(span: Double): Double {
        val progressPercent = span / max * 100
        return progressPercent
    }
}