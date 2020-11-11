package com.foti.playground

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View

class myView (context: Context?) : View(context){

    val mPaint = Paint().apply {
        color = Color.RED
        strokeWidth= 30f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPoint(width/2f, height/2f, mPaint)


        val cx = width/2f
        val cy = height /2f
        val sides = 9
        val rad = 300
        val path = Path()
        val angle = 2.0 * Math.PI / sides
        path.moveTo(cx + (rad * Math.cos(0.0)).toFloat(),cy + (rad * Math.sin(0.0)).toFloat())
        for (i in 1 until sides) {
            path.lineTo(cx + (rad * Math.cos(angle * i)).toFloat(), cy + (rad * Math.sin(angle * i)).toFloat())
        }
        path.close()
    }
}