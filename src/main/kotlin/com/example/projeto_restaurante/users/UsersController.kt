package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.users.request.LoginRequest
import com.example.projeto_restaurante.users.request.UserRequest
import com.example.projeto_restaurante.users.response.UserResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus

import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Transactional
@RestController
@RequestMapping("/users")
class UsersController(val service: UsersService) {

    @GetMapping
    fun listUsers(@RequestParam("role") role: String?) = service.findAll(role).map { it.toResponse() }

    @PostMapping
    fun createUser(@RequestBody @Validated req: UserRequest) =
        service.save(req)
            .toResponse()
            .let { ResponseEntity.status(CREATED).body(it)}

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) =
        service.getById(id).orElse(null)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun deleteUserById(@PathVariable("id") id: Long) {
        if(service.deleteById(id)) ResponseEntity.ok()
    }
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @SecurityRequirement(name = "AuthServer")
    fun login(@Valid @RequestBody credentials: LoginRequest) =
        service.login(credentials)?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

}
