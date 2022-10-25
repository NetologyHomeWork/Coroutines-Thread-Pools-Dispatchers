package ru.netology.network

import ru.netology.models.Author
import ru.netology.models.Comment
import ru.netology.models.Post

interface ApiRepository {

    suspend fun getPosts(): List<Post>

    suspend fun getAuthor(authorId: Long): Author

    suspend fun getCommentsById(postId: Long): List<Comment>
}