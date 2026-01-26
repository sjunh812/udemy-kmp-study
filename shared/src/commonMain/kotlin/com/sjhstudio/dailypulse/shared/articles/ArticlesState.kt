package com.sjhstudio.dailypulse.shared.articles

sealed interface ArticlesState {
    data object Loading : ArticlesState
    data class Success(val list: List<Article>) : ArticlesState
    data class Error(val error: String) : ArticlesState
}