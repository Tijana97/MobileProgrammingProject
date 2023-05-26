package com.example.givinghand.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.givinghand.R
import com.example.givinghand.data.Action
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun ShowAction(action: Flow<List<Action>>, modifier: Modifier = Modifier){
    Card(){
        Row(modifier = Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = R.drawable.sos), contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()){
                Text(text = "action.name")
                Text(text = "action.description")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "action.address")
                Text(text = "action.date")
            }
        }
        androidx.compose.material3.Button(
            onClick = {},
            Modifier.widthIn(min = 250.dp)
        ) {
            Text("Sign Up for an Action")
        }

    }

}
