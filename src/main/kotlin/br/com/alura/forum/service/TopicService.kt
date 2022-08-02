package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
) {

    private val notFoundMessage: String = "Topic not found!"

    fun list(

        nameCourse: String?,
        pagination: Pageable
    ): Page<TopicView> {
        val topics = if (nameCourse == null) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(nameCourse, pagination)
        }
        return topics.map { t ->
            topicViewMapper.map(t)
        }
    }

    fun searchById(id: Long): TopicView {
        val topic = repository.findById(id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }
        return topicViewMapper.map(topic)
    }

    fun create(form: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(form)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic = repository.findById(form.id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }
        topic.title = form.title
        topic.message = form.message
        topic.alterationDate = LocalDate.now()
        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun report(): List<TopicPerCategory> {
        return repository.report()
    }
}
