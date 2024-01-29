package com.wtf.andriod_calapp

sealed class CalculationsActions {
    data class Number(val number: Int) : CalculationsActions()
    object Clear : CalculationsActions()
    object Delete : CalculationsActions()
    object Decimal : CalculationsActions()
    object Calculate : CalculationsActions()
    data class  Operation (val operation:CalculatorOperations) : CalculationsActions()
}


