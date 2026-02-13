package com.sjhstudio.dailypulse.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sjhstudio.dailypulse.shared.Platform

@Composable
internal fun AboutScreen(
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Toolbar(onBackClick = onBackClick)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "About Device") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back button"
                )
            }
        }
    )
}

@Composable
private fun ContentView() {
    val items = makeItems()

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items = items) {
            RowView(title = it.first, subtitle = it.second)
        }
    }
}

@Composable
private fun RowView(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        HorizontalDivider()
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        ("Operating system" to "${platform.osName} ${platform.osVersion}"),
        ("Device" to platform.deviceModel),
        ("Density" to platform.density.toString())
    )
}

@Composable
@Preview(showSystemUi = true)
private fun AboutScreenPreview() {
    MaterialTheme {
        AboutScreen(onBackClick = {})
    }
}