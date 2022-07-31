package br.com.alura.forum.repository

import br.com.alura.forum.model.Author
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Author, Long> {
}