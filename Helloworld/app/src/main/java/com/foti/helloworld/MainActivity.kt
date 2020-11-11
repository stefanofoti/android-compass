package com.foti.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text=savedInstanceState?.getCharSequence("time")

        val sdf : SimpleDateFormat = SimpleDateFormat("hh-mm-ss")

        /*
        findViewById<Button>(R.id.button).setOnClickListener{
            findViewById<TextView>(R.id.textView).text=sdf.format(System.currentTimeMillis())
        };
         */
        //short version using kotlinx
        button.setOnClickListener{
            textView.text=sdf.format(System.currentTimeMillis());
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putCharSequence("time", textView.text.toString())
    }

}