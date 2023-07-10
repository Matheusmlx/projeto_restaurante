package com.example.projeto_restaurante.users

import org.springframework.stereotype.Service

@Service
class UsersService(val repository: UsersRepository) {
    fun save(user: User) = repository.save(user);

    fun getById(id: Long) = repository.getById(id);

    fun findAll() = repository.findAll();

}