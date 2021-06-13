package com.rashmiranjan.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tvInput: TextView
    lateinit var btnClear: Button

    var lastNumeric = false
    var lastDot = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        btnClear = findViewById(R.id.btnClear)

    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeric = false
        lastDot = false

    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {

        if (lastNumeric) {
            var tvValue = tvInput.text.toString()
            var preFix = ""

            try {

                if (tvValue.startsWith("-")) {
                    preFix = "-"
                    tvValue = tvValue.substring(1)
                }

                when {
                    tvValue.contains("-") -> {
                        val splitValue = tvValue.split("-")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (preFix.isNotEmpty()) {
                            one = preFix + one
                        }

                        tvInput.text =
                            removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                    }
                    tvValue.contains("*") -> {
                        val splitValue = tvValue.split("*")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (preFix.isNotEmpty()) {
                            one = preFix + one
                        }

                        tvInput.text =
                            removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                    }
                    tvValue.contains("+") -> {
                        val splitValue = tvValue.split("+")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (preFix.isNotEmpty()) {
                            one = preFix + one
                        }

                        tvInput.text =
                            removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                    tvValue.contains("/") -> {
                        val splitValue = tvValue.split("/")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (preFix.isNotEmpty()) {
                            one = preFix + one
                        }

                        tvInput.text =
                            removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

}


