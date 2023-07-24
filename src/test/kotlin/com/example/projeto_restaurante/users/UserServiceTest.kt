package com.example.projeto_restaurante.users

import com.example.projeto_restaurante.RoleRepository
import com.example.projeto_restaurante.secury.Jwt
import com.example.projeto_restaurante.users.Stubs.userStub
import com.example.projeto_restaurante.users.exception.BadRequestException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull


internal class UserServiceTest {
    private val usersRepositoryMock = mockk<UsersRepository>()
    private val roleRepositoryMock = mockk<RoleRepository>()
    private val jwtMock = mockk<Jwt>()

    private val service = UsersService(usersRepositoryMock, roleRepositoryMock,  jwtMock)

    @Test
    fun `Delete should return false if user does not exists`() {
        every { usersRepositoryMock.findByIdOrNull(1) } returns null
        service.deleteById(1) shouldBe false
    }

    @Test
    fun `Delete must return true if the user is deleted`() {
        val user = userStub()
        every { usersRepositoryMock.findByIdOrNull(1) } returns user
        justRun { usersRepositoryMock.delete(user) }
        service.deleteById(1) shouldBe true
    }

    @Test
    fun `Delete should throw if the user is the last main`() {
        shouldThrow<BadRequestException> {
            service.deleteById(1)
        } shouldHaveMessage "Cannot delete the last  system admin!!"
    }
}