package ru.netology.models

data class FullPost(
    val post: Post,
    val author: Author,
    val comments: List<Comment>
)
