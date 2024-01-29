package com.wtf.andriod_calapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.nio.file.Files.delete

class CalculatorViewModel: ViewModel() {

     var state by mutableStateOf(CalculatorState())
          private set

     fun onAction(action: CalculationsActions) {
          when (action) {
               is CalculationsActions.Number -> enterNumber(action.number)
               is CalculationsActions.Calculate -> performCalculation()
               is CalculationsActions.Decimal -> enterDecimal()
               is CalculationsActions.Operation -> enterOperation(action.operation)
               is CalculationsActions.Clear -> state = CalculatorState()
               is CalculationsActions.Delete -> performDeletion()

          }
     }

     private fun enterOperation(operation: CalculatorOperations) {
          if (state.number1.isNotBlank()) {
               state = state.copy(operation = operation)
          }
     }

     private fun performDeletion() {
          when {
               state.number1.isNotBlank() -> state = state.copy(
                    number1 = state.number1.dropLast(1)
               )

               state.number2.isNotBlank() -> state = state.copy(
                    number2 = state.number2.dropLast(1)
               )

               state.operation != null -> state = state.copy(
                    operation = null
               )
          }
     }

     private fun enterDecimal() {
          if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
               state = state.copy(
                    number1 = state.number1 + "."
               )
               return
          }
          if (!state.number2.contains(".") && state.number2.isNotBlank()) {
               state = state.copy(
                    number2 = state.number2 + "."
               )
               return
          }

     }

     private fun performCalculation() {
          val number1 = state.number1.toDoubleOrNull()
          val number2 = state.number2.toDoubleOrNull()

          if (number1 != null && number2 != null) {
               val result = when (state.operation) {
                    CalculatorOperations.Add -> number1 + number2
                    CalculatorOperations.Subtract -> number1 - number2
                    CalculatorOperations.Multiply -> number1 * number2
                    CalculatorOperations.Divide -> number1 / number2
                    null -> return
               }
               state = state.copy(
                    number1 = result.toString().take(15),
                    number2 = "",
                    operation = null
               )
          }
     }


     //To enter numbers

     private fun enterNumber(number: Int) {
          if (state.operation == null) {
               if (state.number1.length >= 12) {
                    return
               }
               state = state.copy(
                    number1 = state.number1 + number
               )
          } else { // Change here to check for state.number2
               if (state.number2.length >= 12) {
                    return
               }
               state = state.copy(
                    number2 = state.number2 + number
               )
          }
     }
}





