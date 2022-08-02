package br.com.alura.forum.model

object TopicTest {
    fun build() = Topic(
        id = 1,
        title = "Kotlin Basico",
        message = "Aprendendo kotlin basico",
        course = CourseTest.build(),
        author = AuthorTest.build()
    )
}
