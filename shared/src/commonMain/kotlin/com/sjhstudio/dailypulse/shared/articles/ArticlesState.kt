package com.sjhstudio.dailypulse.shared.articles

data class ArticlesState(
    val type: ArticlesType,
    val articles: List<Article> = emptyList(),
    val error: String = "",
)

enum class ArticlesType {
    Loading, Success, Error
}