package br.com.alura.forum.controller

import br.com.alura.forum.model.Response
import br.com.alura.forum.service.ResponseService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearerAuth")
class ResponseController(
    private val responseService: ResponseService
) {

    @PostMapping
    fun save(@RequestBody @Valid response: Response) = responseService.save(response)
}
