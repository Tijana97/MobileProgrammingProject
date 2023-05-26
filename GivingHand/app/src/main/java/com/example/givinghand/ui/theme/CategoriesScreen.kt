package com.example.givinghand.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesScreen(
    onAllActionsClicked: () -> Unit,
    onDonateActionsClicked: () -> Unit,
    onAnimalCareActionsClicked: () -> Unit,
    onEnvironmentalActionsClicked: () -> Unit,
    onSocialActionsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onAllActionsClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Show All Actions")
        }
        Button(
            onClick = onDonateActionsClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Show Donate Actions")
        }
        Button(
            onClick = onAnimalCareActionsClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Show Animal Care Actions")
        }
        Button(
            onClick = onEnvironmentalActionsClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Show Environmental Actions")
        }
        Button(
            onClick = onSocialActionsClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Show Social Actions")
        }
    }
}