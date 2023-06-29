package com.example.projeto_restaurante.users

import org.springframework.stereotype.Component

@Component
class userRepository {
    val users = mutableListOf<User>();

    fun save(user : User ): User {
        user.id = (users.size + 1).toLong()
        users.add(user)
        return user
    }
    fun getById(userId: Long) = users.firstOrNull { user: User -> user.id == userId };

    fun findAll(): List<User> = users

    fun delete(userId: Long) = users.removeIf{ user: User -> user.id == userId  };
}