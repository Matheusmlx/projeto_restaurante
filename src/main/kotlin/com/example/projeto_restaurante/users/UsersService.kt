package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.RoleRepository
import com.example.projeto_restaurante.secury.Jwt
import com.example.projeto_restaurante.users.exception.BadRequestException
import com.example.projeto_restaurante.users.request.LoginRequest
import com.example.projeto_restaurante.users.request.UserRequest
import com.example.projeto_restaurante.users.response.LoginResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UsersService(val userepository: UsersRepository, val roleRepository: RoleRepository, val jwt: Jwt) {
    fun save(request: UserRequest): User {
        val user = User(
            email = request.email!!,
            password = request.password!!,
            name = request.name!!)

        val userRole = roleRepository.findByName("USER")
            ?: throw BadRequestException("Não existe esse tipo de usuario")

        user.roles.add(userRole)
        return userepository.save(user);
    }

    fun getById(id: Long) = userepository.findById(id)

    fun findAll(role: String?): List<User> =
        if(role == null) userepository.findAll()
        else userepository.findAllByRoles(role)

    fun deleteById(id: Long): Boolean{
        val user = userepository.findByIdOrNull(id) ?: return false
        if (user.roles.any { it.name == "ADMIN" }) {
            val count = userepository.findAllByRoles("ADMIN").size
            if (count == 1) throw BadRequestException("Cannot delete the last system admin!")
        }
        userepository.delete(user)
        return true
    }

    fun login(loginRequest: LoginRequest): LoginResponse?{
        val user = userepository.findUserByEmail(loginRequest.email!!) ?: return null
        if (user.password != loginRequest.password) return null
        return LoginResponse(
            token = jwt.createToken(user),
            user.toResponse()
        )
    }

}