package com.example.projeto_restaurante.users

import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "TblUser")
open class User(
    @Id @GeneratedValue
    var id: Long? = null,

    @Email
    @Column(unique = true, nullable = false)
    val email: String,

    @Column(length = 50)
    val password: String,

    @Column(nullable = false)
    val name: String = "",

    @ManyToMany
    @JoinTable(
        name="UserRoles",
        joinColumns = arrayOf(JoinColumn(name="idUser")),
        inverseJoinColumns = arrayOf(JoinColumn(name="idRole")))
    val roles: Set<Role> = setOf()
)