package com.example.givinghand.ui.theme

import androidx.lifecycle.ViewModel
import com.example.givinghand.model.User
import com.example.givinghand.model.UserUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserUIState())
    val uiState: StateFlow<UserUIState> = _uiState.asStateFlow()

    fun loginUser(user: User.UnauthorizedUser) {
        val previousUser = _uiState.value.user
        updateUser(user, previousUser)
    }


    private fun updateUser(newUser: User, previousUser: User?) {
        _uiState.update { currentState ->
            currentState.copy(
                user = if (newUser is User) newUser else currentState.user,

                )
        }
    }

}