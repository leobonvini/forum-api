package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(

    @field:NotEmpty
    @field:Size(min = 5, max = 100, message = "Title must have between 5 a 100 characters.")
    val title: String,

    @field:NotEmpty
    @field:Size(min = 5, message = "Message must have at least 5 characters.")
    val message: String,

    @field:NotNull
    val idCourse: Long,

    @field:NotNull
    val idAuthor: Long
)
