package com.foti.ball2d

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyView(context: Context?) : View(context), View.OnTouchListener {

    val radius = 100f

    var bx = radius
    var by = radius

    var vx = 200f // pixels al secondo
    var vy = 400f

    var current = System.currentTimeMillis()

    var barrierx = 0f
    var barriery = 0f

    var m = Matrix()

    val ballPainter = Paint().apply {
        shader = RadialGradient(0f,0f, radius, Color.WHITE, Color.BLACK, Shader.TileMode.CLAMP)
    }

    init {
        setOnTouchListener(this)
    }

    override fun onDraw(cv: Canvas?) {
        super.onDraw(cv)
        val now = System.currentTimeMillis()
        val dt = now-current
        current=now
        bx += vx * dt / 1000
        by += vy * dt / 1000

        if(bx>width-radius || bx<radius){
            vx*=-1f
            //bx+=vx*dt/1000
        }
        if(by>height-radius || by<radius){
            vy*=-1f
            //by+=vy*dt/1000
        }

        if(((barriery-by)<radius*2) && ((by-barriery)>0)){
            vy+=-1f
            Log.d("MyView", "barrier touch, by: " + by +", barriery: " + barriery)
            //by+=vy*dt/1000
        }

        val offx = 2*bx/width-1
        val offy = 2*by/height-1
        m.setTranslate(bx-radius*offx,by-radius*offy)
        ballPainter.shader.setLocalMatrix(m)

        cv?.drawCircle(bx, by, radius, ballPainter)

        invalidate()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                barrierx=event?.x
                barriery=event?.y
            }
            MotionEvent.ACTION_UP -> {
                barrierx = 0f
                barriery = 0f
            }
        }


        return true
    }


}