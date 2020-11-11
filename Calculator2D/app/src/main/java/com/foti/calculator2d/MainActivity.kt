package com.foti.calculator2d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foti.playground.MyView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(MyView(this))
    }
}