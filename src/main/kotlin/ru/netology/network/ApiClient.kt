package ru.netology.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.netology.models.Author
import ru.netology.models.Comment
import ru.netology.models.Post

interface ApiClient {

    @GET("api/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/api/authors/{id}")
    suspend fun getAuthor(@Path("id") authorId: Long): Response<Author>

    @GET("api/posts/{postId}/comments")
    suspend fun getCommentsById(@Path("postId") postId: Long): Response<List<Comment>>
}