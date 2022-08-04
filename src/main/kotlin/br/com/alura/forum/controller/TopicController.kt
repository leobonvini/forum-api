package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.service.TopicService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearerAuth")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(
        @RequestParam(required = false) nameCourse: String?,
        @PageableDefault(size = 5, sort = ["creationDate"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicView> {
        return service.list(nameCourse, pagination)
    }

    @GetMapping("/{id}")
    fun searchById(@PathVariable id: Long): TopicView {
        return service.searchById(id)
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody
        @Valid
        dto: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.create(dto)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    fun update(
        @RequestBody
        @Valid
        dto: UpdateTopicForm
    ): ResponseEntity<TopicView> {
        val topicView = service.update(dto)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/report")
    fun report(): List<TopicPerCategory> {
        return service.report()
    }
}
