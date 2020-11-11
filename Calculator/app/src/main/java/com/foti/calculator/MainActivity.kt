package com.foti.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var op = 0
    var operator = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener(this)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_6.setOnClickListener(this)
        btn_7.setOnClickListener(this)
        btn_8.setOnClickListener(this)
        btn_9.setOnClickListener(this)
        btn_0.setOnClickListener(this)
        btn_eq.setOnClickListener(this)
        btn_div.setOnClickListener(this)
        btn_c.setOnClickListener(this)
        btn_f.setOnClickListener(this)
        btn_minus.setOnClickListener(this)
        btn_plus.setOnClickListener(this)
        btn_prod.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_0 -> if(output.length()!=0) output.append("0")
            R.id.btn_1 -> output.append("1")
            R.id.btn_2 -> output.append("2")
            R.id.btn_3 -> output.append("3")
            R.id.btn_4 -> output.append("4")
            R.id.btn_5 -> output.append("5")
            R.id.btn_6 -> output.append("6")
            R.id.btn_7 -> output.append("7")
            R.id.btn_8 -> output.append("8")
            R.id.btn_9 -> output.append("9")

            R.id.btn_plus -> {
                op = output.text.toString().toInt()
                output.text=""
                operator = "+"
            }
            R.id.btn_minus -> {
                op = output.text.toString().toInt()
                output.text=""
                operator = "-"
            }
            R.id.btn_div -> {
                op = output.text.toString().toInt()
                output.text=""
                operator = "/"
            }
            R.id.btn_prod -> {
                op = output.text.toString().toInt()
                output.text=""
                operator = "*"
            }
            R.id.btn_eq -> {
                if(operator.equals("+")){
                    output.text = (op + (output.text.toString().toInt())).toString()
                }
                if(operator.equals("-")){
                    output.text = (op - (output.text.toString().toInt())).toString()
                }
                if(operator.equals("/")){
                    output.text = (op / (output.text.toString().toInt())).toString()
                }
                if(operator.equals("*")){
                    output.text = (op * (output.text.toString().toInt())).toString()
                }
            }
            R.id.btn_ac -> {
                output.text = ""
                op = 0
                operator = ""
            }
            R.id.btn_c -> {
                if(output.text.isNotEmpty()){
                    output.text=output.text.substring(0, output.length()-1)
                }
            }
            R.id.btn_f -> {
                //generate a rest call with OP as body
                //wait for the result
                //when the result is ready, parse the result
                //show the result on the text view
            }
        }
    }
}