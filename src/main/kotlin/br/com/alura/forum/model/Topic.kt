package br.com.alura.forum.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val course: Course,
    @ManyToOne
    val author: Author,
    @Enumerated(value = EnumType.STRING)
    val status: StatusTopic = StatusTopic.NOT_RESPONDED,
    @OneToMany(mappedBy = "topic")
    val responses: List<Response> = ArrayList(),
    var alterationDate: LocalDate? = null

)
