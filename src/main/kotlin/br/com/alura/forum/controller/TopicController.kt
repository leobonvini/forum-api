package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(): List<TopicView> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun searchById(@PathVariable id: Long): TopicView {
        return service.searchById(id)
    }

    @PostMapping
    fun create(
        @RequestBody @Valid dto: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView>{
        val topicView = service.create(dto)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    fun update(@RequestBody @Valid dto: UpdateTopicForm): ResponseEntity<TopicView>{
        val topicView = service.update(dto)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        service.delete(id)
    }
}