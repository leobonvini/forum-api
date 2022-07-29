package br.com.alura.forum.service

import br.com.alura.forum.model.User
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UserService(var users: List<User>) {

    init {
        val user = User(
            id = 1,
            name = "Ana da Silva",
            email = "ana@email.com"
        )
        users = Arrays.asList(user)
    }

    fun searchForId(id: Long): User {
        return users.stream().filter { c ->
            c.id == id
        }.findFirst().get()
    }
}
