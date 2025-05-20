package com.example.leveluptasks.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.leveluptasks.R
import com.example.leveluptasks.ui.addtask.AddTaskScreen
import com.example.leveluptasks.ui.home.HomeScreen
import com.example.leveluptasks.ui.home.AllTasksViewModel

enum class LevelUpTasksScreen(@StringRes val title: Int) {
    Home(R.string.home),
    AddTask(R.string.add_task),
    EditTask(R.string.edit_task),
    TaskDetails(R.string.task_details),
    Focus(R.string.focus),
//    Store,
//    Stats,
//    Login,
//    Signup
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelUpAppBar(
    currentScreen: LevelUpTasksScreen,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {Text(stringResource(currentScreen.title))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateUp){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelUpTasksApp(
    viewModel: AllTasksViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LevelUpTasksScreen.valueOf(
        backStackEntry?.destination?.route ?: LevelUpTasksScreen.Home.name
    )
    Scaffold(
        topBar = {
            LevelUpAppBar(
                currentScreen = currentScreen,
                canNavigateUp = navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()}
            )
        }
    ) { innerPadding ->
        val navHost = NavHost(
            navController = navController,
            startDestination = LevelUpTasksScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = LevelUpTasksScreen.Home.name){
                HomeScreen(viewModel = viewModel, onAddTaskClick = {navController.navigate(LevelUpTasksScreen.AddTask.name)})

            }
            composable(route = LevelUpTasksScreen.AddTask.name) {
                AddTaskScreen()
            }
        }
    }
}