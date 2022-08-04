package br.com.alura.forum.dto

import br.com.alura.forum.model.StatusTopic
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val creationDate: LocalDateTime,
    val alterationDate: LocalDate?
) : Serializable
