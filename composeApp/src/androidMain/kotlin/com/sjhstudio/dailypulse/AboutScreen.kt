package com.sjhstudio.dailypulse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun AboutScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Toolbar()
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "About Device") }
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
    Column(modifier = Modifier.fillMaxWidth()) {
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
    val platform = com.sjhstudio.dailypulse.shared.Platform()
    platform.logSystemInfo()

    return listOf(
        ("Operating system" to "${platform.osName} ${platform.osVersion}"),
        ("Device" to platform.deviceModel),
        ("Density" to platform.density.toString())
    )
}