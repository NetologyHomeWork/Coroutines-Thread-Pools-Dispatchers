package ru.netology.network

import retrofit2.Response
import ru.netology.models.Author
import ru.netology.models.Comment
import ru.netology.models.Post
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ApiRepositoryImpl : ApiRepository {

    override suspend fun getPosts(): List<Post> {
        val response = ApiService.apiCall.getPosts()
        return safeApiCall(response)
    }

    override suspend fun getAuthor(authorId: Long): Author {
        val response = ApiService.apiCall.getAuthor(authorId)
        return safeApiCall(response)
    }

    override suspend fun getCommentsById(postId: Long): List<Comment> {
        val response = ApiService.apiCall.getCommentsById(postId)
        return safeApiCall(response)
    }

    private suspend fun<T> safeApiCall(response: Response<T>): T {
        return suspendCoroutine { continuation ->
            try {
                if (response.isSuccessful && response.body() != null) {
                    continuation.resume(response.body()!!)
                } else {
                    throw IllegalStateException("response fail")
                }
            } catch (e: Exception) {
                continuation.resumeWithException(e)
            }
        }
    }
}