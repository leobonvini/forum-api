package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.TopicTest
import br.com.alura.forum.model.TopicViewTest
import br.com.alura.forum.repository.TopicRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {
    private val topic = TopicTest.build()
    private val topicView = TopicViewTest.build()

    private val pages = PageImpl(listOf(topic))

    private val pageable: Pageable = mockk()
    private val topicViewMapper: TopicViewMapper = mockk()
    private val topicFormMapper: TopicFormMapper = mockk()

    private val topicRepository: TopicRepository = mockk {
        every { findAll(pageable) } returns pages
        every { findByCourseName(any(), any()) } returns pages
    }

    private val topicService = TopicService(
        repository = topicRepository,
        topicViewMapper = topicViewMapper,
        topicFormMapper = topicFormMapper
    )

    @Test
    fun mustListByCourseName() {
        val slot = slot<Topic>()
        every { topicViewMapper.map(capture(slot)) } returns topicView

        topicService.list("Kotlin Basico", pageable)

        verify(exactly = 0) { topicRepository.findAll(pageable) }
        verify(exactly = 1) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }

        assertThat(slot.captured.title).isEqualTo(topic.title)
        assertThat(slot.captured.message).isEqualTo(topic.message)
        assertThat(slot.captured.status).isEqualTo(topic.status)
    }

    @Test
    fun mustListAllTopicsWhenCourseNameIsNull() {
        val slot = slot<Topic>()
        every { topicViewMapper.map(capture(slot)) } returns topicView

        topicService.list(null, pageable)

        verify(exactly = 1) { topicRepository.findAll(pageable) }
        verify(exactly = 0) { topicRepository.findByCourseName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }

        assertThat(slot.captured.title).isEqualTo(topic.title)
        assertThat(slot.captured.message).isEqualTo(topic.message)
        assertThat(slot.captured.status).isEqualTo(topic.status)
    }

    @Test
    fun mustThrowExceptionIfTopicIdIsNotFound() {
        every { topicRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> {
            topicService.searchById(2)
        }

        assertThat(actual.message).isEqualTo("Topic not found!")
    }
}
