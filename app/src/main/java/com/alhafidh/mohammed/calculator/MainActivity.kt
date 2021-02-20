package com.alhafidh.mohammed.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    // Represent whether the lastly pressed key is numeric or not
    var lastNumeric=false
    // If true, do not allow to add another DOT
    var lastDot=false
    private lateinit var tvInput: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        // Text of button is appended to textview
        if(tvInput.text.equals("0")){
            tvInput.text=""
        }
        var button=view as Button
        if(button.text=="0"&&tvInput.text=="") {
            // Do nothing
        }else{
            tvInput.append(button.text)
            lastNumeric = true

        }
    }

    fun onDecimalPoint(view: View) {
        // If the last appended value is numeric then append(".") or do not
        if(lastNumeric&&!lastDot){
            tvInput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
    fun onClear(view: View) {
        tvInput.text=""
        lastNumeric=false
        lastDot=false
    }
    fun onOperator(view: View) {
        if(lastNumeric&&!isOperatorAdded(tvInput.text.toString())){
            var button=view as Button

            tvInput.append("\n"+button.text+"\n")
            lastNumeric=false
            lastDot=false
        }
    }
    fun onEqual(view: View) {

        // If the last input is a number only, solution can be found.
        if(lastNumeric){

            // Read the textView value
            var value=tvInput.text.toString()
            var prefix=""
            try{

                // Here if the value starts with '-' then we will separate it and perform the calculation with value.
                if(value.startsWith("-")){
                    prefix="-"
                    value=value.substring(1)
                }

                // If the inputValue contains the Division operator
                if(value.contains("/"))
                {
                    // Split the input using division operator
                    val splitedValue=value.split("/")
                    var firstValue=splitedValue[0]
                    var secondValue=splitedValue[1]

                    // If the prefix is not empty then we will append it with first value.
                    if(!prefix.isEmpty()){
                        firstValue=prefix+firstValue
                    }

                    // Calculate result
                    tvInput.text=removeZeroAfterDon((firstValue.toDouble()/secondValue.toDouble()).toString())
                }else if(value.contains("*")){

                    // Split the input using division operator
                    val splitedValue=value.split("*")
                    var firstValue=splitedValue[0]
                    var secondValue=splitedValue[1]

                    // If the prefix is not empty then we will append it with first value.
                    if(!prefix.isEmpty()){
                        firstValue=prefix+firstValue
                    }

                    // Calculate result
                    tvInput.text=removeZeroAfterDon((firstValue.toDouble()*secondValue.toDouble()).toString())

                }else if(value.contains("-")){

                    // Split the input using division operator
                    val splitedValue=value.split("-")
                    var firstValue=splitedValue[0]
                    var secondValue=splitedValue[1]

                    // If the prefix is not empty then we will append it with first value.
                    if(!prefix.isEmpty()){
                        firstValue=prefix+firstValue
                    }

                    // Calculate result
                    tvInput.text=removeZeroAfterDon((firstValue.toDouble()-secondValue.toDouble()).toString())

                }else if(value.contains("+")){

                    // Split the input using division operator
                    val splitedValue=value.split("+")
                    var firstValue=splitedValue[0]
                    var secondValue=splitedValue[1]

                    // If the prefix is not empty then we will append it with first value.
                    if(!prefix.isEmpty()){
                        firstValue=prefix+firstValue
                    }

                    // Calculate result
                    tvInput.text=removeZeroAfterDon((firstValue.toDouble()+secondValue.toDouble()).toString())

                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            (value.contains("+")||value.contains("-")||value.contains("*")||value.contains("/"))
        }
    }
    private fun removeZeroAfterDon(result:String):String{
        var value=result
        if(result.contains(".0")){
            value=result.substring(0,result.length-2)
        }
        return value
    }
}