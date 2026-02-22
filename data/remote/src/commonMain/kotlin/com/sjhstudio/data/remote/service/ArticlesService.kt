package com.sjhstudio.data.remote.service

import com.sjhstudio.data.remote.BuildKonfig.API_KEY
import com.sjhstudio.data.remote.BuildKonfig.BASE_URL
import com.sjhstudio.data.remote.model.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {

    suspend fun getArticles(
        country: String = "ko",
        category: String,
    ) {
        val response: ArticlesResponse = httpClient.get("${BASE_URL}/v2/top-headlines?country=$country&category=$category&apiKey=${API_KEY}").body()
    }
}