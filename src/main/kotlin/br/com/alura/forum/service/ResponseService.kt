package br.com.alura.forum.service

import br.com.alura.forum.model.Response
import br.com.alura.forum.repository.ResponseRepository
import org.springframework.stereotype.Service

@Service
class ResponseService(
    private val responseRepository: ResponseRepository,
    private val emailService: EmailService
) {

    fun save(response: Response) {
        responseRepository.save(response)
        val emailAuthor = response.topic.author.email
        emailService.notify(emailAuthor)
    }
}