package com.example.calculator

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorUiState())

    private var canAddOperation = false
    private var canAddDecimal = false

    fun allClear() {
        canAddDecimal = false
        canAddOperation = false

        state = state.copy(
            answer = 0,
            input = "",
            display = ""
        )
    }

    fun clear() {
        canAddDecimal = false
        canAddOperation = false

        state = state.copy(
            input = ""
        )
    }

    fun backspace(input: String): String {
        if (input.isNotEmpty()) {
            state = state.copy(
                input = input.substring(0, input.length - 1)
            )
        }
        return input
    }

    fun onNumericPressed(buttonText: String) {
        val newInput: String = state.input + buttonText

        state = state.copy(
            input = newInput
        )
        canAddOperation = true
        canAddDecimal = true
    }

    fun onOperationPressed(buttonText: String) {
        if (canAddOperation) {
            val newInput: String = state.input + buttonText

            state = state.copy(
                input = newInput
            )
            canAddOperation = false
            canAddDecimal = false
        }
    }

    fun onDecimalPressed(buttonText: String) {
        if (canAddDecimal) {
            val newInput: String = state.input + buttonText

            state = state.copy(
                input = newInput
            )
            canAddOperation = false
        }
    }

    fun calculateResults() {
        Log.d(TAG, "Initial Input: ${state.input}")
        val brokenUpInput = breakUpInput()
        val timesDivision = timesDivisionCalculation(brokenUpInput)

        val result = subtractAdditionCalculation(timesDivision)
        state = state.copy(
            display = result.toString()
        )
    }

    private fun subtractAdditionCalculation(alteredList: MutableList<Any>): Float {
        var result = alteredList[0] as Float
        Log.d(TAG, "Beginning addition and subtraction")

        for (i in alteredList.indices) {
            if (alteredList[i] is Char && i != alteredList.lastIndex ) {
                val operator = alteredList[i]
                val nextDigit = alteredList[i + 1] as Float
                if (operator == '+') {
                    result += nextDigit
                }
                if (operator == '-') {
                    result -= nextDigit
                }
            }
        }

        return result
    }

    private fun timesDivisionCalculation(alteredList: MutableList<Any>): MutableList<Any> {
        var list = alteredList

        while (list.contains('x') || list.contains('/')) {
            list = calculateTimesAndDivision(list)
        }

        Log.d(TAG, "Returned Initial List: $list")
        return list
    }

    private fun calculateTimesAndDivision(alteredList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = alteredList.size

        Log.d(TAG, "Received List: $alteredList")

        for (i in alteredList.indices) {
            if (alteredList[i] is Char && i != alteredList.lastIndex && i < restartIndex) {
                val operator = alteredList[i]
                val previousDigit = alteredList[i - 1] as Float
                val nextDigit = alteredList[i + 1] as Float
                when (operator) {
                    'x' -> {
                        Log.d(TAG, "Performing Multiplication")
                        newList.add(previousDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        Log.d(TAG, "Performing Division")
                        newList.add(previousDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        Log.d(TAG, "No Multiplication or Division")
                        newList.add(previousDigit)
                        newList.add(operator)
                    }
                }
            }

            if (i > restartIndex) {
                newList.add(alteredList[i])
            }
        }
        Log.d(TAG, "After X & / : $newList")
        return newList
    }

    private fun breakUpInput(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""

        for (character in state.input) {
            if (character.isDigit() || character == '.') {
                currentDigit += character
            } else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if (currentDigit != "") {
            list.add(currentDigit.toFloat())
        }
        Log.d(TAG, "Input: $list")
        return list
    }
}