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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.data.Action
import com.example.givinghand.datasource.DataSource
import com.example.givinghand.data.UserUIState
import com.example.givinghand.ui.theme.ActionList
import com.example.givinghand.ui.theme.ActionViewModel
import com.example.givinghand.ui.theme.AdminActionList
import com.example.givinghand.ui.theme.CategoriesScreen
import com.example.givinghand.ui.theme.LoginAdminScreen
import com.example.givinghand.ui.theme.LoginScreen
import com.example.givinghand.ui.theme.ShowAction
import com.example.givinghand.ui.theme.SignUpScreen
import com.example.givinghand.ui.theme.UserViewModel
import com.example.givinghand.ui.theme.WelcomeScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull


enum class LaunchGivingHandScreen() {
    Start(),
    Login(),
    AdminLogin(),
    AdminActions(),
    Signup(),
    ChooseCategory(),
    EditAction(),
    AddAction(),
    AddActionType(),
    ShowAction(),
    DonateActions(),
    SocialActions(),
    EnvironmentActions(),
    AnimalActions(),
    AllActions(),
    ShowUser()
}


@Composable
fun LaunchGivingHandAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
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
                    onLoginAdminButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.AdminLogin.name)
                    },
                    onSignupButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.Signup.name)
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Login.name) {
                LoginScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.ChooseCategory.name)
                    }
                )
            }
            composable(route = LaunchGivingHandScreen.AdminLogin.name) {
                LoginAdminScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.AdminActions.name)
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Signup.name) {

                SignUpScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.ChooseCategory.name)
                    }
                )
            }


            composable(route = LaunchGivingHandScreen.ChooseCategory.name) {
                CategoriesScreen(
                    onAllActionsClicked = { navController.navigate(LaunchGivingHandScreen.AllActions.name) },
                    onDonateActionsClicked = { navController.navigate(LaunchGivingHandScreen.DonateActions.name) },
                    onAnimalCareActionsClicked = { navController.navigate(LaunchGivingHandScreen.AnimalActions.name) },
                    onEnvironmentalActionsClicked = { navController.navigate(LaunchGivingHandScreen.EnvironmentActions.name) },
                    onSocialActionsClicked = { navController.navigate(LaunchGivingHandScreen.SocialActions.name) })
            }

            composable(route = LaunchGivingHandScreen.AllActions.name) {
                val actions = actionViewModel.getAllActions()
                ActionList(actions = actions, Modifier.padding(8.dp),
                onShowActonClicked = { action ->
                    navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/${action.id}")
                })
            }

            composable(route = LaunchGivingHandScreen.DonateActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 1)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/${action.id}")
                    })
            }

            composable(route = LaunchGivingHandScreen.AnimalActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 2)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/${action.id}")
                    })
            }

            composable(route = LaunchGivingHandScreen.EnvironmentActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 3)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/${action.id}")
                    })
            }

            composable(route = LaunchGivingHandScreen.SocialActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 4)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/${action.id}")
                    })
            }

            composable(route = "${LaunchGivingHandScreen.ShowAction.name}/${id}") {
                val action = actionViewModel.getActionById(id)
                ShowAction(action = action, modifier = Modifier)

            }

            composable(route = LaunchGivingHandScreen.AdminActions.name) {
                val actions = actionViewModel.getAllActions()
                AdminActionList(actions = actions, Modifier.padding(8.dp),
                onAddItemButtonClicked = {})
            }
        }
    }
}