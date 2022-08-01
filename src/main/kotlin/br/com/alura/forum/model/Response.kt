package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Response(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val message: String,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val author: Author,

    @ManyToOne
    val topic: Topic,

    val solution: Boolean
)
