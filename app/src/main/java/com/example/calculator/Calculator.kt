package com.example.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "AC", "0", ".", "="
)

@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val equationText= viewModel.equationText.observeAsState()
    val resultText= viewModel.resultText.observeAsState()
    Box(modifier = modifier.fillMaxSize()) { // Ensure Box fills the screen
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxSize() // Apply fillMaxSize directly here
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = equationText.value?:"",
                style = TextStyle(
                    fontSize = 45.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = resultText.value?:"",
                style = TextStyle(
                    fontSize = 80.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier =modifier.padding(bottom = 20.dp, end = 5.dp, start = 5.dp)
            ) {
                items(buttonList) { button -> // Correct usage of items
                    CalculatorButton(btn = button, onClick = {
                        viewModel.onButtonClick(button)
                    })
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: ()->Unit) {
    Box(modifier = Modifier.padding(5.dp)
        ){
        FloatingActionButton(
            onClick = {
                onClick()
                      },
            modifier= Modifier.size(80.dp),
            shape=CircleShape,
            contentColor = getColor(btn),
            containerColor = Color.White,


        ) {
            Text(
                text = btn,
                style = TextStyle(
                    fontSize = 40.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.W500
                ))
        }
    }
}

fun getColor(btn: String): Color{
    if (btn =="C" || btn=="AC")
        return Color(0xFF9C5151)

    if(btn=="-"|| btn=="+"|| btn=="*"|| btn=="/" || btn=="("|| btn==")")
        return Color(0xFF6D7541)

    if (btn=="=")
        return Color(0xFF545436)

    return Color(0xFF332B2B)
}
