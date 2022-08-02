package br.com.alura.forum.integration

import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

    @Autowired
    private lateinit var topicRepository: TopicRepository

    private val pagination = PageRequest.of(0, 5)
    private val topic = TopicTest.build()

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest")
            .apply {
                withDatabaseName("testdb")
                withUsername("leonardo")
                withPassword("123456")
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
        }
    }

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
