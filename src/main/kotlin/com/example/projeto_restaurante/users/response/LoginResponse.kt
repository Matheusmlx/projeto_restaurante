package com.example.projeto_restaurante.users.response

data class LoginResponse(
    val token: String,
    val user: UserResponse
)
