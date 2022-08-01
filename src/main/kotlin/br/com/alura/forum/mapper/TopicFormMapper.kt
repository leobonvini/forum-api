package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.AuthorService
import br.com.alura.forum.service.CourseService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val authorService: AuthorService
) : Mapper<NewTopicForm, Topic> {

    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.searchById(t.idCourse),
            author = authorService.searchById(t.idAuthor)
        )
    }
}
