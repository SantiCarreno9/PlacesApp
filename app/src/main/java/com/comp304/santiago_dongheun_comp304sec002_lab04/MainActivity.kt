package com.comp304.santiago_dongheun_comp304sec002_lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.Category
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.PlacesRepositoryImpl
import com.comp304.santiago_dongheun_comp304sec002_lab04.ui.theme.Santiago_Dongheun_COMP304Sec002_Lab04Theme
import com.comp304.santiago_dongheun_comp304sec002_lab04.viewmodel.PlacesViewModel
import org.koin.androidx.compose.koinViewModel

enum class AppScreen {
    Home,
    Places,
    Map
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Santiago_Dongheun_COMP304Sec002_Lab04Theme {
                PlacesApp()
            }
        }
    }
}

@Composable
fun PlacesApp() {
    val navController = rememberNavController()
    val placesViewModel: PlacesViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.name
    ) {
        composable(route = AppScreen.Home.name) {
            MainActivityContent(
                viewModel = placesViewModel,
                onCategorySelected = {
                    navController.navigate(AppScreen.Places.name + "/${it}")
                }
            )
        }
        composable(
            route = AppScreen.Places.name + "/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = ""
                })
        ) {
            val category = it.arguments?.getString("category")
            SantiagoScreen(
                viewModel = placesViewModel,
                category = Category.valueOf(category!!),
                onPlaceSelected = { index ->
                    navController.navigate(AppScreen.Map.name + "/$index")
                },
                onBackButtonPressed = {
                    navController.navigate(AppScreen.Home.name)
                }
            )
        }
        composable(
            route = AppScreen.Map.name + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    nullable = false
                    defaultValue = 1
                })
        ) {
            val index = it.arguments?.getInt("index")
            val place = placesViewModel.getPlace(index ?: 0)
            DongheunScreen(
                place = place,
                onBackButtonPressed = {
                    navController.navigate(AppScreen.Places.name + "/${place.category}")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean,
    onBackButtonPressed: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (showBackButton) {
                    IconButton(onClick = onBackButtonPressed) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            "Go Back button",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(if(showBackButton) -25.dp else 0.dp)
                )
            }
        }
    )
}

@Composable
fun MainActivityContent(
    viewModel: PlacesViewModel,
    onCategorySelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Seoul Explorer",
                showBackButton = false
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Discover Seoul",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Experience the best of Korean culture",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.getCategories()) { category ->
                    CategoryButton(
                        name = category,
                        onCategorySelected = onCategorySelected
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryButton(
    name: String,
    onCategorySelected: (String) -> Unit
) {
    val icon: ImageVector = when(name) {
        "Historic" -> Icons.Default.Home
        "Park" -> Icons.Default.Favorite
        "Museum" -> Icons.Default.Place
        "Shopping" -> Icons.Default.ShoppingCart
        else -> Icons.Default.Place
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onCategorySelected(name) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = when(name) {
                        "Historic" -> "Historical Sites"
                        "Park" -> "Parks & Nature"
                        "Museum" -> "Museums & Culture"
                        "Shopping" -> "Shopping Districts"
                        else -> name
                    },
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = when(name) {
                        "Historic" -> "Explore Seoul's Rich History"
                        "Park" -> "Discover Natural Beauty"
                        "Museum" -> "Experience Korean Culture"
                        "Shopping" -> "Shop in Style"
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = icon,
                contentDescription = name,
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val tasksViewModel = PlacesViewModel(PlacesRepositoryImpl())
    Santiago_Dongheun_COMP304Sec002_Lab04Theme {
        MainActivityContent(
            tasksViewModel,
            onCategorySelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    Santiago_Dongheun_COMP304Sec002_Lab04Theme {
        TopBar(
            title = "Places",
            onBackButtonPressed = {},
            showBackButton = true
        )
    }
}