package com.example.copy_dog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.copy_dog.ui.DogScreen
import com.example.copy_dog.ui.DogViewModel
import com.example.copy_dog.ui.theme.Copy_dogTheme

class MainActivity : ComponentActivity() {
    private val viewModel: DogViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Copy_dogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DogScreen(viewModel.uiState)
                }
            }
        }
    }
}