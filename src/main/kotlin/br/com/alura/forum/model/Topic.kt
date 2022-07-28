package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: StatusTopic = StatusTopic.NOT_RESPONDED

)
