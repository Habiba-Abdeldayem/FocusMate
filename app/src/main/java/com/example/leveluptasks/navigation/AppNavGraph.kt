package com.example.leveluptasks.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leveluptasks.R
import com.example.leveluptasks.ui.addtask.AddTaskScreen
import com.example.leveluptasks.ui.addtask.AddTaskViewModel
import com.example.leveluptasks.ui.editTaskDetails.EditTaskDetailsScreen
import com.example.leveluptasks.ui.editTaskDetails.EditTaskDetailsViewModel
import com.example.leveluptasks.ui.home.TasksViewModel
import com.example.leveluptasks.ui.home.HomeScreen

enum class LevelUpTasksScreen(@StringRes val title: Int) {
    Home(R.string.home),
    AddTask(R.string.add_task),
    EditTaskDetails(R.string.edit_task),
//    TaskDetails(R.string.task_details),
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
    addTaskViewModel: AddTaskViewModel,
    editTaskDetailsViewModel: EditTaskDetailsViewModel,
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
                onAddTaskClick = { navController.navigate(LevelUpTasksScreen.AddTask.name) },
                onTaskEditClick = { taskId ->
                    editTaskDetailsViewModel.loadTask(taskId)
                    navController.navigate(LevelUpTasksScreen.EditTaskDetails.name)
                }
            )

        }
        composable(route = LevelUpTasksScreen.AddTask.name) {
            AddTaskScreen(
                addTaskViewModel = addTaskViewModel,
                navController = navController,
                onSaveTask = {navController.navigate(LevelUpTasksScreen.Home.name)}
            )
        }
        composable(route = LevelUpTasksScreen.EditTaskDetails.name) {
            EditTaskDetailsScreen(
                editTaskDetailsViewModel = editTaskDetailsViewModel,
                onSaveTask = {navController.navigate(LevelUpTasksScreen.Home.name)}
            )
        }
    }

}