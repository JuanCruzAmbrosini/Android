/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import android.app.FragmentManager.BackStackEntry
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuPreview
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

enum class LaunchTrayScreen( @StringRes val title: Int ){
    START(title = R.string.start_order),
    ENTREE(title = R.string.choose_entree),
    SIDE_DISH(title = R.string.choose_side_dish),
    ACCOMPANIMENT(title = R.string.choose_accompaniment),
    CHECKOUT(title = R.string.order_checkout)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchTrayAppBar(
    currentScreen: LaunchTrayScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){

    CenterAlignedTopAppBar(

        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
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
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LaunchTrayScreen.valueOf(
        backStackEntry?.destination?.route?: LaunchTrayScreen.START.name
    )

    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(
        topBar = {
            LaunchTrayAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()},
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LaunchTrayScreen.START.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = LaunchTrayScreen.START.name) {

                StartOrderScreen(
                    onStartOrderButtonClicked = { navController.navigate(LaunchTrayScreen.ENTREE.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }

            composable (route = LaunchTrayScreen.ENTREE.name) {

                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = { cancelLaunchAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = {navController.navigate(LaunchTrayScreen.SIDE_DISH.name)},
                    onSelectionChanged = {viewModel.updateEntree(it)},
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }

            composable (route = LaunchTrayScreen.SIDE_DISH.name) {

                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = { cancelLaunchAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = {navController.navigate(LaunchTrayScreen.ACCOMPANIMENT.name)},
                    onSelectionChanged = {viewModel.updateSideDish(it)},
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }

            composable (route = LaunchTrayScreen.ACCOMPANIMENT.name) {

                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { cancelLaunchAndNavigateToStart(viewModel, navController) },
                    onNextButtonClicked = {navController.navigate(LaunchTrayScreen.CHECKOUT.name)},
                    onSelectionChanged = {viewModel.updateAccompaniment(it)},
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }

            composable (route = LaunchTrayScreen.CHECKOUT.name) {

                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = { cancelLaunchAndNavigateToStart(viewModel, navController) },
                    onCancelButtonClicked = { cancelLaunchAndNavigateToStart(viewModel, navController) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )

            }

        }
    }
}

private fun cancelLaunchAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
){
    viewModel.resetOrder()
    navController.popBackStack(LaunchTrayScreen.START.name, inclusive = false)
}

