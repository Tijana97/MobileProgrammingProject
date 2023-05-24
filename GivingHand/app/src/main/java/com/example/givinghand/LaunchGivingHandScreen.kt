@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.givinghand

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.datasource.DataSource
import com.example.givinghand.data.UserUIState
import com.example.givinghand.ui.theme.ActionList
import com.example.givinghand.ui.theme.ActionViewModel
import com.example.givinghand.ui.theme.LoginScreen
import com.example.givinghand.ui.theme.SignUpScreen
import com.example.givinghand.ui.theme.UserViewModel
import com.example.givinghand.ui.theme.WelcomeScreen


enum class LaunchGivingHandScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Login(title = R.string.log_in),
    Signup(title = R.string.sign_up),
    ChooseAction(title = R.string.choose_action_type),
    EditAction(title = R.string.edit_action),
    AddAction(title = R.string.add_action),
    AddActionType(title = R.string.add_action_type),
    ShowAction(title = R.string.show_action),
    DonateActions(title = R.string.display_donate_actions),
    SocialActions(title = R.string.display_donate_actions),
    EnvironmentActions(title = R.string.display_environment_actions),
    AnimalActions(title = R.string.display_animal_care_actions),
    AllActions(title = R.string.display_actions),
    ShowUser(title = R.string.show_user)
}


@Composable
fun LaunchGivingHandAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}



@Composable
fun LunchGivingHandApp() {
    // TODO: Create Controller and initialization

    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LaunchGivingHandScreen.valueOf(
        backStackEntry?.destination?.route ?: LaunchGivingHandScreen.Start.name
    )

    Scaffold(
        topBar = {
            // TODO: AppBar

            LaunchGivingHandAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LaunchGivingHandScreen.Start.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = LaunchGivingHandScreen.Start.name) {
                WelcomeScreen(
                    onLoginButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.Login.name)
                    },
                    onSignupButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.Signup.name)
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Login.name) {
                LoginScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.DonateActions.name)
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Signup.name) {

                SignUpScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.DonateActions.name)
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.DonateActions.name) {
                val actions = actionViewModel.getAllActions()
                ActionList(actions = actions, Modifier.padding(8.dp))
            }
        }
    }
}