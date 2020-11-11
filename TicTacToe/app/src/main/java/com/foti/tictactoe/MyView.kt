package com.foti.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class MyView(context :Context?) : View(context), View.OnTouchListener {

    private var dx = 0f
    private var dy = 0f
    private val hLines = 3f
    private val vLines = 3f

    val mPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dx = height/hLines
        dy = width/vLines
        for(i in 1..2){
            canvas?.drawLine(0f, i*dx, width+0f, i*dx, mPaint)
        }
        for(i in 1..2){
            canvas?.drawLine(i*dy, 0f, i*dy, height+0f, mPaint)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return true
    }
}