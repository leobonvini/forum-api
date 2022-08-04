package br.com.alura.forum.integration

import br.com.alura.forum.configuration.DatabaseContainerConfiguration
import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var topicRepository: TopicRepository

    private val pagination = PageRequest.of(0, 5)
    private val topic = TopicTest.build()

    @Test
    fun mustGenerateAReport() {
        topicRepository.save(topic)
        val report = topicRepository.report()

        assertThat(report).isNotNull
        assertThat(report.first()).isExactlyInstanceOf(TopicPerCategory::class.java)
    }

    @Test
    fun mustSearchATopicByName() {
        topicRepository.save(topic)
        val result = topicRepository.findByCourseName(nameCourse = "Kotlin", pagination = pagination)

        assertThat(result.totalElements).isEqualTo(1)
    }
}
