package br.com.alura.forum.service

import br.com.alura.forum.model.Author
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val author: Author
) : UserDetails {

    override fun getAuthorities() = author.role

    override fun getPassword() = author.password

    override fun getUsername() = author.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
