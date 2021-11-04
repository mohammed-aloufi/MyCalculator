package com.example.mycalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var num1: Int? = null
    var num2: Int? = null
    var operation: Char? = null
    var result: Int? = null
}