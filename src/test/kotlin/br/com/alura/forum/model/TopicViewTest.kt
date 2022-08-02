package br.com.alura.forum.model

import br.com.alura.forum.dto.TopicView
import java.time.LocalDate
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = TopicView(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo kotlin basico",
        status = StatusTopic.NOT_RESPONDED,
        creationDate = LocalDateTime.now(),
        alterationDate = LocalDate.now()
    )
}
