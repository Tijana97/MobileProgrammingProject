@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.givinghand

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.navArgument
import com.example.givinghand.data.ActionDao
import com.example.givinghand.data.AppDatabase
import com.example.givinghand.ui.theme.ActionList
import com.example.givinghand.ui.theme.ActionViewModel
import com.example.givinghand.ui.theme.AddActionScreen
import com.example.givinghand.ui.theme.AdminActionList
import com.example.givinghand.ui.theme.CategoriesScreen
import com.example.givinghand.ui.theme.LoginAdminScreen
import com.example.givinghand.ui.theme.LoginScreen
import com.example.givinghand.ui.theme.ShowAction
import com.example.givinghand.ui.theme.ShowActionAdmin
import com.example.givinghand.ui.theme.SignUpScreen
import com.example.givinghand.ui.theme.WelcomeScreen

enum class LaunchGivingHandScreen() {
    Start(),
    Login(),
    AdminLogin(),
    AdminActions(),
    Signup(),
    ChooseCategory(),
    EditActionScreen(),
    AddAction(),
    AddActionType(),
    ShowAction(),
    ShowActionAdmin(),
    DonateActions(),
    SocialActions(),
    EnvironmentActions(),
    AnimalActions(),
    AllActions(),
    ShowUser(),

}


@Composable
fun LaunchGivingHandAppBar(
    title: String,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                androidx.compose.material3.IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"

                    )

                }
            },
            modifier = modifier
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier
        )
    }

}



@Composable
fun LaunchGivingHandApp() {
    // TODO: Create Controller and initialization

    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val appBarTitle = stringResource(R.string.app_name)
    var topAppBarTitle by remember { mutableStateOf(appBarTitle) }
    val navController = rememberNavController()
    val context = LocalContext.current
    val database: AppDatabase = AppDatabase.getDatabase(context)
    val actionDao: ActionDao = database.ActionDao()

    val viewModelScope = rememberCoroutineScope()

    val onBackHandler = {
        topAppBarTitle = appBarTitle
        navController.navigateUp()
    }

    Scaffold(
        topBar = {
            // TODO: AppBar

            LaunchGivingHandAppBar(
                title = topAppBarTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackClick = { onBackHandler() }
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
                        topAppBarTitle = "Log In"
                    },
                    onLoginAdminButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.AdminLogin.name)
                        topAppBarTitle = "Log In as Admin"
                    },
                    onSignupButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.Signup.name)
                        topAppBarTitle = "Sign Up"
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Login.name) {
                LoginScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.ChooseCategory.name)
                        topAppBarTitle = "Select Category"
                    }
                )
            }
            composable(route = LaunchGivingHandScreen.AdminLogin.name) {
                LoginAdminScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.AdminActions.name)
                        topAppBarTitle = "Actions"
                    }
                )
            }

            composable(route = LaunchGivingHandScreen.Signup.name) {

                SignUpScreen(
                    onSubmitButtonClicked = {
                        navController.navigate(LaunchGivingHandScreen.ChooseCategory.name)
                        topAppBarTitle = "Select Category"
                    }
                )
            }


            composable(route = LaunchGivingHandScreen.ChooseCategory.name) {
                CategoriesScreen(
                    onAllActionsClicked = { navController.navigate(LaunchGivingHandScreen.AllActions.name)
                        topAppBarTitle = "All Actions"},
                    onDonateActionsClicked = { navController.navigate(LaunchGivingHandScreen.DonateActions.name)
                        topAppBarTitle = "Donate Actions"},
                    onAnimalCareActionsClicked = { navController.navigate(LaunchGivingHandScreen.AnimalActions.name)
                        topAppBarTitle = "Animal Care Actions"},
                    onEnvironmentalActionsClicked = { navController.navigate(LaunchGivingHandScreen.EnvironmentActions.name)
                        topAppBarTitle = "Environmental Actions"},
                    onSocialActionsClicked = { navController.navigate(LaunchGivingHandScreen.SocialActions.name)
                        topAppBarTitle = "Social Actions"})
            }



            composable(route = LaunchGivingHandScreen.AllActions.name) {
                val actions = actionViewModel.getAllActions()
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.DonateActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 1)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.AnimalActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 2)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name

                    })
            }

            composable(route = LaunchGivingHandScreen.EnvironmentActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 3)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }

            composable(route = LaunchGivingHandScreen.SocialActions.name) {
                val actions = actionViewModel.getActionByCategory(category_id = 4)
                ActionList(actions = actions, Modifier.padding(8.dp),
                    onShowActonClicked = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowAction.name}/$actionId")
                        topAppBarTitle = action.name
                    })
            }
            val tempActionAdminId = "temp"
            composable(
                route = LaunchGivingHandScreen.ShowActionAdmin.name + "/{$tempActionAdminId}",
                arguments = listOf(navArgument(tempActionAdminId) { defaultValue = "1" })
            ) { backStackEntry ->
                val actionIdTemp = backStackEntry.arguments?.getString(tempActionAdminId)!!.toInt()
                ShowActionAdmin(actionIdTemp, modifier = Modifier,
                    actionViewModel = actionViewModel, actionDao = actionDao, viewModelScope = viewModelScope
                )
            }
            val tempActionId = "temp"
            composable(
                route = LaunchGivingHandScreen.ShowAction.name + "/{$tempActionId}",
                arguments = listOf(navArgument(tempActionId) { defaultValue = "1" })
            ) { backStackEntry ->
                val actionIdTemp = backStackEntry.arguments?.getString(tempActionId)!!.toInt()
                ShowAction(actionIdTemp, modifier = Modifier)
            }

            composable(route = LaunchGivingHandScreen.AdminActions.name) {
                val actions = actionViewModel.getAllActions()
                AdminActionList(actions = actions, Modifier.padding(8.dp),
                    onAddItemButtonClicked = { navController.navigate(LaunchGivingHandScreen.AddAction.name)},
                    onShowActonClickedAdmin = { action ->
                        val actionId = action.id.toString()
                        navController.navigate("${LaunchGivingHandScreen.ShowActionAdmin.name}/$actionId")
                        topAppBarTitle = action.name})
            }

            composable(route = LaunchGivingHandScreen.AddAction.name) {
                AddActionScreen(
                    onSubmitButtonClicked = { navController.navigate(LaunchGivingHandScreen.AdminActions.name) },
                )
            }

        }
    }
}