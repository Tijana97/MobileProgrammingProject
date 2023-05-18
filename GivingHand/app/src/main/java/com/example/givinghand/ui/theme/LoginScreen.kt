package com.example.givinghand.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.givinghand.R
import com.example.givinghand.datasource.DataSource.AuthorizedUsers
import com.example.givinghand.datasource.DataSource.UnauthorizedUsers
import com.example.givinghand.model.User

@Composable
fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.username)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )


}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
){
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }
    }


}

private fun ConfirmUnauthorizedUser(
    username: String,
    password: String
): Boolean {
    UnauthorizedUsers.forEach { user -> if(
        user.username.equals(username)
    ){
        if(user.password.equals(password)){
            return true
        }
    }

    }
    return false
}

private fun ConfirmAuthorizedUser(
    username: String,
    password: String
): Boolean {
    AuthorizedUsers.forEach { user -> if(
        user.username.equals(username)
    ){
        if(user.password.equals(password)){
            return true
        }
    }

    }
    return false
}

