package com.example.leveluptasks.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leveluptasks.R
import com.example.leveluptasks.ui.addtask.AddTaskScreen
import com.example.leveluptasks.ui.home.TasksViewModel
import com.example.leveluptasks.ui.home.HomeScreen

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

@Composable
fun AppNavGraph(
    navController: NavHostController,
    tasksViewModel: TasksViewModel,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = LevelUpTasksScreen.Home.name,
        modifier = modifier
    ) {
        composable(route = LevelUpTasksScreen.Home.name) {
            HomeScreen(
                viewModel = tasksViewModel,
                onAddTaskClick = { navController.navigate(LevelUpTasksScreen.AddTask.name) })

        }
        composable(route = LevelUpTasksScreen.AddTask.name) {
            AddTaskScreen(
                tasksViewModel = tasksViewModel,
                navController = navController,
                onTaskAdded = {navController.navigate(LevelUpTasksScreen.Home.name)}
            )
        }
    }

}