package com.comp304.santiago_dongheun_comp304sec002_lab04

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.Category
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.Place
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.PlacesRepositoryImpl
import com.comp304.santiago_dongheun_comp304sec002_lab04.ui.theme.Santiago_Dongheun_COMP304Sec002_Lab04Theme
import com.comp304.santiago_dongheun_comp304sec002_lab04.viewmodel.PlacesViewModel

@Composable
fun SantiagoScreen(
    viewModel: PlacesViewModel,
    category: Category,
    onPlaceSelected: (Int) -> Unit,
    onBackButtonPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = category.name,
                showBackButton = true,
                onBackButtonPressed = onBackButtonPressed
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            items(viewModel.getPlacesByCategory(category)) {
                PlaceCard(
                    place = it,
                    onPlaceSelected = {
                        onPlaceSelected(viewModel.getPlaceIndex(it))
                    })
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

    }
}

@Composable
fun PlaceCard(
    place: Place,
    onPlaceSelected: (Place) -> Unit
) {
    Card(
        onClick = {
            onPlaceSelected(place)
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = place.image),
                contentDescription = place.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = place.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(
                text = place.address,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesScreenPreview() {
    val tasksViewModel = PlacesViewModel(PlacesRepositoryImpl())
    Santiago_Dongheun_COMP304Sec002_Lab04Theme {
        SantiagoScreen(
            tasksViewModel,
            category = Category.Museum,
            onPlaceSelected = {},
            onBackButtonPressed = {}
        )
    }
}