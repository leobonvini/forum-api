package br.com.alura.forum.service

import br.com.alura.forum.model.Author
import br.com.alura.forum.repository.AuthorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val repository: AuthorRepository
) : UserDetailsService {

    fun searchById(id: Long): Author {
        return repository.getReferenceById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val author = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(author)
    }
}
