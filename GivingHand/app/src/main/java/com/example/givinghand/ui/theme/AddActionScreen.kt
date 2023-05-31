package com.example.givinghand.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.R
import com.example.givinghand.data.Action
import com.example.givinghand.data.User
import kotlinx.coroutines.launch

@Composable
fun AddActionScreen(
    onSubmitButtonClicked: () -> Unit

){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val message = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val name = remember { mutableStateOf(TextFieldValue()) }
        val max_volunteers = remember { mutableStateOf(TextFieldValue()) }
        val address = remember { mutableStateOf(TextFieldValue()) }
        val description = remember { mutableStateOf(TextFieldValue()) }
        val current_volunteers = remember { mutableStateOf(TextFieldValue()) }
        val date = remember { mutableStateOf(TextFieldValue()) }
        val category_id = remember { mutableStateOf(TextFieldValue()) }
        val coroutineScope = actionViewModel.viewModelScope
        val context = LocalContext.current

        TextField(
            label = { Text(text = "Name") },
            value = name.value,
            onValueChange = { name.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Max number of volunteers") },
            value = max_volunteers.value,
            onValueChange = { max_volunteers.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Address") },
            value = address.value,
            onValueChange = { address.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Description") },
            value = description.value,
            onValueChange = { description.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Current number of volunteers") },
            value = current_volunteers.value,
            onValueChange = { current_volunteers.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Date") },
            value = date.value,
            onValueChange = { date.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Type of action") },
            value = category_id.value,
            onValueChange = { category_id.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray, // Change background color
                cursorColor = Color.DarkGray, // Change cursor color
                focusedIndicatorColor = MaterialTheme.colors.primary, // Change focused indicator color
                unfocusedIndicatorColor = MaterialTheme.colors.primary, // Change unfocused indicator color
            ),
        )

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if(!confirmActionData(name.value, max_volunteers.value, address.value, description.value, current_volunteers.value, date.value, category_id.value))
                            message.value = "All fields must be filled in."
                        else {
                            val action: Action = Action(
                                name = name.value.text,
                                max_volunteers = max_volunteers.value.text.toInt(),
                                address = address.value.text,
                                description = description.value.text,
                                current_volunteers = current_volunteers.value.text.toInt(),
                                date = date.value.text,
                                category_id = category_id.value.text.toInt()
                            )
                            actionViewModel.insertAction(action)
                            onSubmitButtonClicked()
                            Toast.makeText(context, "Action added successfully!", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
                ,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green500),

            ) {
                Text(text = "Submit")
            }

        }
    }
}

fun confirmActionData(name: TextFieldValue, max_volunteers: TextFieldValue, address: TextFieldValue,
                      description: TextFieldValue, current_volunteers: TextFieldValue, date: TextFieldValue, category_id: TextFieldValue): Boolean{
    if(name.text.isNotBlank() && max_volunteers.text.isNotBlank() && address.text.isNotBlank()
        && description.text.isNotBlank() && current_volunteers.text.isNotBlank() && date.text.isNotBlank() && category_id.text.isNotBlank()){
        return true
    }
    return false
}