package com.sjhstudio.dailypulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sjhstudio.dailypulse.shared.articles.ArticlesViewModel

internal class MainActivity : ComponentActivity() {

    private val viewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppNav(viewModel = viewModel)
        }
    }
}

@Preview
@Composable
private fun AppAndroidPreview() {

}