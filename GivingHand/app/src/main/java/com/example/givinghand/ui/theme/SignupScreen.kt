package com.example.givinghand.ui.theme

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.data.AppDatabase
import com.example.givinghand.data.User
import com.example.givinghand.datasource.DataSource.AuthorizedUsers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSubmitButtonClicked: () -> Unit
) {
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val loginMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val email = remember { mutableStateOf(TextFieldValue()) }
        val name = remember { mutableStateOf(TextFieldValue()) }
        val address = remember { mutableStateOf(TextFieldValue()) }


        Text(text = "Sing up", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Name") },
            value = name.value,
            onValueChange = { name.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Address") },
            value = address.value,
            onValueChange = { address.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = { email.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })


        if (loginMessage.value.isNotBlank()) {
            Text(
                text = loginMessage.value,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    color = Color.Red
                ),
                modifier = Modifier.padding(top = 4.dp),
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if(!checkUniqueUser(username.value, email.value))
                        loginMessage.value = "Username or email already attached to a different account."
                    else if(!confirmUserData(username.value, password.value,
                        email.value, address.value, name.value))
                        loginMessage.value = "All fields must be filled in."
                    else {
                        val user: User = User(
                            username = username.value.text,
                            password = password.value.text,
                            email = email.value.text,
                            name = name.value.text,
                            address = address.value.text,
                            authorized = 0
                        )
                        userViewModel.insertUser(user)
                        onSubmitButtonClicked()
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Sign up")
            }

        }
    }
}

fun confirmUserData(username: TextFieldValue, password: TextFieldValue, email: TextFieldValue,
address: TextFieldValue, name: TextFieldValue): Boolean{
    if(username.text.isNotBlank() && password.text.isNotBlank() && email.text.isNotBlank()
        && address.text.isNotBlank() && name.text.isNotBlank()){
        return true
    }
    return false
}

private fun checkUniqueUser(email: TextFieldValue, username:TextFieldValue): Boolean {
    AuthorizedUsers.forEach { user ->
        if (user.username == username.text
        ) {
            return false
        }
        if (user.email == email.text) {
            return false
        }
    }
    return true

}


