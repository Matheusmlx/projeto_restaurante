package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.users.request.UserRequest
import com.example.projeto_restaurante.users.response.UserResponse
import jakarta.transaction.Transactional

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("/users")
class UsersController(val service: UsersService) {

    @GetMapping
    fun listUsers(@RequestParam("role") role: String?) = service.findAll(role).map { it.toResponse() }

    @PostMapping
    fun createUser(@RequestBody @Validated req: UserRequest) =
        service.save(req)
            .toResponse()
            .let { ResponseEntity.status(CREATED).body(it)}

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) =
        service.getById(id).orElse(null)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") id: Long) {
        val findUser = service.getById(id)

        if(findUser.isPresent) {

            ResponseEntity.ok()
        }else {
            ResponseEntity.notFound().build<User>()
        }
    }
    private fun User.toResponse() = UserResponse(id!!, name, email);
}
