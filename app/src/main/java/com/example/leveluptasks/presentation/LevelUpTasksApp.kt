package com.example.leveluptasks.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.leveluptasks.R
import com.example.leveluptasks.navigation.AppNavGraph
import com.example.leveluptasks.navigation.LevelUpTasksScreen
import com.example.leveluptasks.ui.home.TasksViewModel
import com.example.leveluptasks.ui.settings.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelUpAppBar(
    currentScreen: LevelUpTasksScreen,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        actions = {
            IconButton(onClick = onToggleDarkMode) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                    contentDescription = stringResource(R.string.toggle_dark_mode)
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
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
    tasksViewModel: TasksViewModel = viewModel(),
    settingsViewModel: SettingsViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val isDarkMode by settingsViewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LevelUpTasksScreen.valueOf(
        backStackEntry?.destination?.route ?: LevelUpTasksScreen.Home.name
    )
    Scaffold(
        topBar = {
            LevelUpAppBar(
                currentScreen = currentScreen,
                canNavigateUp = navController.previousBackStackEntry != null,
                isDarkMode = isDarkMode.isDarkMode,
                onToggleDarkMode = { settingsViewModel.switchDarkMode(!isDarkMode.isDarkMode) },
                navigateUp = { navController.navigateUp() },

            )
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            tasksViewModel = tasksViewModel,
            modifier = Modifier.padding(innerPadding)
        )

    }
}