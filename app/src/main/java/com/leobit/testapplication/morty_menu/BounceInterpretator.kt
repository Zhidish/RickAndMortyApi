package com.leobit.testapplication.morty_menu

import android.view.animation.Interpolator

class BounceInterpretator(var amplitude: Double, var frequence : Double) : Interpolator {


    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time / amplitude) *
                Math.cos(frequence * time) + 1).toFloat();

    }
}