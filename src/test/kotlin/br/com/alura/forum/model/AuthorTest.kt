package br.com.alura.forum.model

object AuthorTest {
    fun build() = Author(id = 1, name = "Pedro", email = "pedro@email.com", password = "123456")
    fun buildToToken() = Author(name = "Ana", email = "ana@email.com", password = "123456")
}
