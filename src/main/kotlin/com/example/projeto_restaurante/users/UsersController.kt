package com.example.projeto_restaurante.users

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController {

    @GetMapping("/users")
    fun getUser() = User(
        "meu@email.com",
        "1234",
        "Sample User"
    )


}