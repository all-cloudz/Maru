package com.shoebill.maru

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shoebill.maru.ui.page.AuctionPage
import com.shoebill.maru.ui.page.LoginPage
import com.shoebill.maru.ui.page.MainPage
import com.shoebill.maru.ui.theme.MaruTheme
import com.shoebill.maru.viewmodel.MapViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MaruTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 45.dp),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "auction",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("main") { backStackEntry ->
            val viewModel = hiltViewModel<MapViewModel>()
            viewModel.initFocusManager(LocalFocusManager.current)
            MainPage(mapViewModel = viewModel, navController = navController)
        }
        /** 이곳에 화면 추가 **/

        composable("auction") { navBackStackEntry ->
            AuctionPage()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaruTheme {
        MyApp()
    }
}