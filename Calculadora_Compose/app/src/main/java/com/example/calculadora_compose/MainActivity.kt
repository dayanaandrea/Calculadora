package com.example.calculadora_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora_compose.ui.theme.Calculadora_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculadora_ComposeTheme {
                // Llama a la función para mostrar por  pantalla  la calculadora
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    // Texto mostrado
    var displayText by rememberSaveable { mutableStateOf("") }
    // Obtiene la orientacion de la pantalla
    val configuration = LocalConfiguration.current
    // Verifica si la orientación  es vertical
    val isPortrait =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    // Colocamos  todos los elementos en una columna
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pantalla de la calculadora

        Text(
            text = displayText,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isPortrait) 100.dp else 100.dp)
                .padding(16.dp),
            color = Color.White,
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )

        // Filas de botones
        Column {
            val buttonRowActual = if (isPortrait) buttonRows else buttonRowsA


            for (row in buttonRowActual) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (button in row) {
                        CalculatorButton(
                            text = button,
                            onClick = {
                                displayText += button
                            }
                        )
                    }
                }
            }


        }


    }
}


// Lista de botones de la calculadora
private val buttonRows = listOf(
    listOf("7", "8", "9", "/"),
    listOf("4", "5", "6", "*"),
    listOf("1", "2", "3", "-"),
    listOf("0", "AC", "=", "+")
)

// Lista de botones de la calculadora
private val buttonRowsA = listOf(
    listOf("6", "7", "8", "9",  "/"),
    listOf("2", "3", "4", "5",  "*"),
    listOf("0", "1", "AC", "=", "+" ,"-"),

)

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        border = BorderStroke(3.dp, Color.Black),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = if (text == "AC") Color.Red else Color.Black
        )
    }
}


// Apaisado
@Preview(
    showBackground = true,
    heightDp = 412,
    widthDp = 873
)
// Vertical
@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 873
)
@Composable
fun CalculatorScreenPreview() {
    Calculadora_ComposeTheme {
        CalculatorScreen()
    }
}


