package com.example.projeto_restaurante.secury

data class UserToken(
    val id:Long,
    val name: String,
    val roles: Set<String>
){
    constructor(): this(0, "", setOf())
}