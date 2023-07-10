package com.example.projeto_restaurante.users.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserRequest(
    @field:NotBlank
    val name: String?,

    @field:Email
    var email: String?,

    @field:Size(min = 8, max = 50)
    val password: String?
)
