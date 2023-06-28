package com.example.projeto_restaurante.users

import org.springframework.stereotype.Component

@Component
class UsersRepositor {
    val users = listOf<User>();

    fun save(user: User) {

    }
}