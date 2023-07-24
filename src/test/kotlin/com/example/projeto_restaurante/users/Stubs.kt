package com.example.projeto_restaurante.users

import kotlin.random.Random

object Stubs {
    fun userStub(
        id: Long? = Random.nextLong(1, 1000),
        roles: List<String> = listOf("USER")
    ): User {
        val name = "user-${id ?: "new "}"
        return User(
            id = id,
            name = name,
            email = "$name@email.com",
            password = "123456",
            roles = roles.mapIndexed {i, it -> Role(i.toLong(), it)}.toMutableSet()
        )
    }
}