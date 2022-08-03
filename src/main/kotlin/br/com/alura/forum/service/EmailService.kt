package br.com.alura.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notify(emailAuthor: String) {
        val message = SimpleMailMessage()

        message.setSubject("[Forum-API] Resposta Recebida")
        message.setText("Ola, o seu tópico foi respondido. Vamos conferir?")
        message.setTo(emailAuthor)

        javaMailSender.send(message)
    }
}
