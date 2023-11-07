package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                modifier = Modifier,
                color = Color.White,
                text = state.display,
                style = MaterialTheme.typography.displayMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                modifier = Modifier,
                color = Color.White,
                text = state.input,
                style = MaterialTheme.typography.displayMedium
            )
        }
        Spacer(modifier = Modifier.size(52.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    modifier = Modifier,
                    onClick = { viewModel.allClear() }
                ) {
                    Text(
                        text = "AC",
                        color = Color.Green
                    )
                }
                OutlinedButton(
                    modifier = Modifier,
                    onClick = { viewModel.backspace(state.input) }
                ) {
                    Text(
                        text = "<-",
                        color = Color.Green
                    )
                }
                CalculatorOperatorButton(buttonText = "/", viewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CalculatorNumericButton(buttonText = "7", viewModel)
                CalculatorNumericButton(buttonText = "8", viewModel)
                CalculatorNumericButton(buttonText = "9", viewModel)
                CalculatorOperatorButton(buttonText = "x", viewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CalculatorNumericButton(buttonText = "4", viewModel)
                CalculatorNumericButton(buttonText = "5", viewModel)
                CalculatorNumericButton(buttonText = "6", viewModel)
                CalculatorOperatorButton(buttonText = "-", viewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CalculatorNumericButton(buttonText = "1", viewModel)
                CalculatorNumericButton(buttonText = "2", viewModel)
                CalculatorNumericButton(buttonText = "3", viewModel)
                CalculatorOperatorButton(buttonText = "+", viewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CalculatorNumericButton(buttonText = "0", viewModel)
                CalculatorDecimalButton(buttonText = ".", viewModel)
                CalculatorEqualButton(buttonText = "=", viewModel)
            }
        }
    }
}

@Composable
fun CalculatorNumericButton(buttonText: String, viewModel: CalculatorViewModel) {
    OutlinedButton(
        modifier = Modifier,
        onClick = { viewModel.onNumericPressed(buttonText) }
    ) {
        Text(
            text = buttonText,
            color = Color.Green
        )
    }
}

@Composable
fun CalculatorDecimalButton(buttonText: String, viewModel: CalculatorViewModel) {
    OutlinedButton(
        modifier = Modifier,
        onClick = { viewModel.onDecimalPressed(buttonText) }
    ) {
        Text(
            text = buttonText,
            color = Color.Green
        )
    }
}

@Composable
fun CalculatorOperatorButton(buttonText: String, viewModel: CalculatorViewModel) {
    OutlinedButton(
        modifier = Modifier,
        onClick = { viewModel.onOperationPressed(buttonText) }
    ) {
        Text(
            text = buttonText,
            color = Color.Green
        )
    }
}

@Composable
fun CalculatorEqualButton(buttonText: String, viewModel: CalculatorViewModel) {
    OutlinedButton(
        modifier = Modifier,
        onClick = { viewModel.calculateResults() }
    ) {
        Text(
            text = buttonText,
            color = Color.Green
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorTheme {
        CalculatorScreen(viewModel = CalculatorViewModel())
    }
}