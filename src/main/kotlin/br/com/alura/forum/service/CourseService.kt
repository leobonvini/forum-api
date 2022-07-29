package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CourseService(var courses: List<Course>) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programming"
        )
        courses = Arrays.asList(course)
    }

    fun searchForId(id: Long): Course {
        return courses.stream().filter { c ->
            c.id == id
        }.findFirst().get()
    }
}
