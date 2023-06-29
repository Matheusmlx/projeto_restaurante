package com.example.projeto_restaurante.users

import org.springframework.stereotype.Service

@Service
class UsersService(val userRepository: userRepository) {
    fun save(user: User) = userRepository.save(user);

    fun getById(id: Long) = userRepository.getById(id);

    fun findAll() = userRepository.findAll();

    fun delete(id: Long) = userRepository.delete(id);
}