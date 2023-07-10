package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.users.request.UserRequest
import com.example.projeto_restaurante.users.response.UserResponse
import jakarta.validation.Valid

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(val service: UsersService) {

    @GetMapping
    fun listUsers() = service.findAll().map { it.toResponse() }

    @PostMapping
    fun createUser(@RequestBody @Validated req: UserRequest) =
        service.save(User(email = req.email!!, password = req.password!!, name = req.name!!))
            .toResponse()
            .let { ResponseEntity.status(CREATED).body(it)}

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) =
        service.getById(id)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    private fun User.toResponse() = UserResponse(id!!, name, email);
    }
