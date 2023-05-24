package com.example.givinghand.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.givinghand.R

@Composable
fun WelcomeScreen(
    onLoginButtonClicked: () -> Unit,
    onSignupButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onLoginButtonClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Log in")
        }
        Button(
            onClick = onSignupButtonClicked,
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Sign Up")
        }
    }
}

@Preview
@Composable
fun StartOrderPreview(){
    WelcomeScreen(
        onLoginButtonClicked = {},
        onSignupButtonClicked = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    )
}