package com.wtf.andriod_calapp

import com.wtf.andriod_calapp.CalculatorOperations.Add.symbol


sealed class CalculatorOperations(val symbol: String) {
    object Add : CalculatorOperations("+")
    object Subtract : CalculatorOperations("-")
    object Divide : CalculatorOperations("/")
    object Multiply : CalculatorOperations("x")
}




