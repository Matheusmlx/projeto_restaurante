package com.example.projeto_restaurante

import com.example.projeto_restaurante.users.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}