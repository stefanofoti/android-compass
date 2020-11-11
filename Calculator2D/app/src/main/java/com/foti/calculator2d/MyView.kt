package com.foti.playground

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.solver.widgets.Rectangle

class MyView(context: Context?) : View(context), View.OnTouchListener {

    init {
        setOnTouchListener(this)
    }

    var dx = 0f
    var dy = 0f

    val vLines = 4f
    val hLines = 6f

    var r = Rect()

    var op1 = ""

    val mPaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    val mPaint2 = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.FILL
    }

    val whitePaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.WHITE
        strokeWidth = 2f
        textSize = 150f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        dx = width / vLines
        dy = height / hLines
        r.bottom = dy.toInt()
        r.top = height
        r.left = 0
        r.right = width
        canvas.drawText(op1,0f,dy/2,whitePaint)
        canvas.drawRect(r, mPaint2)

        for (i in 0..vLines.toInt()) {
            canvas.drawLine(i * dx, dy, i * dx, height.toFloat() - dy, mPaint)
        }
        for (i in 0..hLines.toInt()) {
            canvas.drawLine(0f, i * dy, width.toFloat(), i * dy, mPaint)
        }

        var n = 0
        var r = Rect()
        for (y in 0..2) {
            for (x in 0..2) {
                n = 3 * y + (x + 1)
                whitePaint.getTextBounds(n.toString(), 0, 1, r)
                val offx = (r.right - r.left) / 2
                val offy = (r.top - r.bottom) / 2
                canvas.drawText(n.toString(), x * dx - offx + dx / 2, height - (y + 1) * dy - offy - dy / 2, whitePaint)
            }
        }
        whitePaint.getTextBounds("0", 0, 1, r)
        canvas.drawText("0", width/2f, height.toFloat()-dy/2, whitePaint)

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        var n = 0
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = (event?.x / dy).toInt()
                val y = ((height - event?.y) / dy).toInt()
                n = 4 * y + x
                when (n) {
                    4, 5, 6 -> {
                        n -= 3
                    }
                    8, 9, 10 -> {
                        n -= 4
                    }
                    12, 13, 14 -> {
                        n -= 5
                    }
                }
                op1 = op1+n.toString()
                invalidate()
                Toast.makeText(context, "" + n, Toast.LENGTH_SHORT).show()
            }
        }
        //Toast.makeText(context, ""+event?.x+" "+event?.y, Toast.LENGTH_SHORT).show()         return true     } 
        return true
    }
}