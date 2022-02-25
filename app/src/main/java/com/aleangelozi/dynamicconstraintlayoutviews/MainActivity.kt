package com.aleangelozi.dynamicconstraintlayoutviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val set = ConstraintSet()
        val clItem = findViewById<ConstraintLayout>(R.id.clItem)
        var previousid = 100

        for (i in 0..10) {

            val inflater = LayoutInflater.from(this)

            var inflatedLayout = inflater.inflate(
                R.layout.row_main,
                clItem as ViewGroup,
                false
            )

            inflatedLayout.id = 100 + i
            clItem.addView(inflatedLayout, i)
            set.clone(clItem)
            if (i == 0) {
                set.connect(
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    60
                )
                set.connect(
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT
                )
                set.connect(
                    inflatedLayout.id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
            } else {
                set.connect(
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT
                )
                set.connect(
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT
                )
                set.connect(
                    inflatedLayout.id,
                    ConstraintSet.TOP,
                    previousid,
                    ConstraintSet.BOTTOM, resources.getDimension(R.dimen._10sdp).toInt()
                )
            }
            set.applyTo(clItem)
            previousid = inflatedLayout.id

        }
    }
}