package com.foti.compass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var view : CompassView = CompassView(this)
        //view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        setContentView(view)
        //setContentView(CompassView(this))
    }
}