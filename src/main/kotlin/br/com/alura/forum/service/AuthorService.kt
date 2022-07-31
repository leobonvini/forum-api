package br.com.alura.forum.service

import br.com.alura.forum.model.Author
import br.com.alura.forum.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(private val repository: UserRepository) {

    fun searchForId(id: Long): Author {
        return repository.getReferenceById(id)

    }
}
