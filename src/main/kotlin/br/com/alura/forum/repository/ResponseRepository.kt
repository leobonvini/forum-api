package br.com.alura.forum.repository

import br.com.alura.forum.model.Response
import org.springframework.data.jpa.repository.JpaRepository

interface ResponseRepository : JpaRepository<Response, Long>
