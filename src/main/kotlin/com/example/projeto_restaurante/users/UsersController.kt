package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.users.response.UserResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(val service: UsersService) {

    @GetMapping
    fun listUsers() = service.findAll().map { UserResponse(
        id = it.id,
        name = it.name,
        email = it.email

    ) }

    @PostMapping
    fun createUser(@RequestBody user: User) = service.save(user)

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) = service.getById(id)


}