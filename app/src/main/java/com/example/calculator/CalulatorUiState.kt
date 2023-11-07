package com.example.calculator

data class CalculatorUiState(
    val answer: Int = 0,
    val display: String = "0",
    val input: String = ""
)
