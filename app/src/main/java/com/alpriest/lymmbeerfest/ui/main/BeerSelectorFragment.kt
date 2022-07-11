package com.alpriest.lymmbeerfest.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.LuckyWheel
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.OnLuckyWheelReachTheTarget
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelItem

import java.util.ArrayList
import java.util.Random

class BeerSelectorFragment(config: Config) : Fragment() {
    private val random = Random()
    private var wheel: LuckyWheel? = null
    private var data = ArrayList(config.brews)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.beer_selector, container, false)

        this.wheel = fragment.findViewById(R.id.lwv)
        wheel?.addWheelItems(wheelItems())

        wheel?.setLuckyWheelReachTheTarget(object: OnLuckyWheelReachTheTarget{
            override fun onReachTarget(target: Int) {
                val textView = fragment.findViewById<TextView>(R.id.answer)
                val brew = data[target]
                textView.text = "Number " + brew.number + "\n" + brew.name
                fragment.findViewById<View>(R.id.answer).visibility = View.VISIBLE
            }
        })

        positionWheelHalfOffScreen()

        fragment.findViewById<View>(R.id.spin_wheel).setOnClickListener {
            fragment.findViewById<View>(R.id.answer).visibility = View.INVISIBLE
            wheel?.rotateWheelTo(random.nextInt(data!!.size))
        }

        return fragment
    }

    private fun positionWheelHalfOffScreen() {
        val metrics = activity?.resources?.displayMetrics?: return
        val layoutParams = wheel?.layoutParams?: return

        layoutParams.width = metrics.widthPixels * 2
        layoutParams.height = metrics.heightPixels * 2
        wheel?.layoutParams = layoutParams
        wheel?.translationY = (metrics.heightPixels / 5 * 6).toFloat()
    }

    private fun wheelItems(): ArrayList<WheelItem> {
        val result = ArrayList<WheelItem>()

        for (brew in data) {
            result.add(WheelItem(brew.androidColor(), brew.name))
        }

        return result
    }
}

class Brew(var number: String, var name: String, var brewery: String, var description: String, var sponsor: String, var abv: Double, private val colour: String) {

    fun androidColor(): Int {
        return Color.parseColor("#$colour")
    }
}
