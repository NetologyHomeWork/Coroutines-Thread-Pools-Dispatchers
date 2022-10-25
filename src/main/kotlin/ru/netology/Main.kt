package ru.netology

import kotlinx.coroutines.*
import ru.netology.models.FullPost
import ru.netology.network.ApiRepository
import ru.netology.network.ApiRepositoryImpl

fun main() {
    val repository: ApiRepository = ApiRepositoryImpl()
    runBlocking {
        val posts = repository.getPosts()
            .map { post ->
                async {
                    FullPost(
                        post,
                        repository.getAuthor(post.authorId),
                        repository.getCommentsById(post.id)
                    )
                }
            }.awaitAll()
        println(posts)
    }
}