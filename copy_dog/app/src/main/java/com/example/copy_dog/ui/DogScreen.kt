package com.example.copy_dog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DogScreen(uiState: UiState) {
    // Add a gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFEEF2FF), Color(0xFFE0F7FA))
                )
            )
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        when {
            uiState.isLoading -> LoadingScreen()
            uiState.isError -> ErrorScreen()
            else -> PhotosGridScreen(photos = uiState.imageList)
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color(0xFF42A5F5),
            strokeWidth = 5.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Fetching cute dogs...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "🐾 Oops! Something went wrong.",
            color = Color(0xFFEF5350),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Please try again later.",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun PhotosGridScreen(photos: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "🐶 Dog Gallery",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF37474F)
            ),
            modifier = Modifier.padding(12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(photos) { photo ->
                DogPhotoCard(photo)
            }
        }
    }
}

@Composable
fun DogPhotoCard(photo: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photo)
                .crossfade(true)
                .build(),
            contentDescription = "Dog Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
