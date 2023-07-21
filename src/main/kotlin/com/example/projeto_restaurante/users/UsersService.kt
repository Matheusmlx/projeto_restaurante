package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.RoleRepository
import com.example.projeto_restaurante.users.exception.BadRequestException
import com.example.projeto_restaurante.users.request.UserRequest
import org.springframework.stereotype.Service

@Service
class UsersService(val userepository: UsersRepository, val roleRepository: RoleRepository) {
    fun save(request: UserRequest): User {
        val user = User(
            email = request.email!!,
            password = request.password!!,
            name = request.name!!)

        val userRole = roleRepository.findByName("USERs")
            ?: throw BadRequestException("Não existe esse tipo de usuario")

        user.roles.add(userRole)
        return userepository.save(user);
    }

    fun getById(id: Long) = userepository.findById(id)

    fun findAll(role: String?): List<User> =
        if(role == null) userepository.findAll()
        else userepository.findAllByRoles(role)

    fun deleteById(id: Long) {
        userepository.deleteById(id);
    }

}