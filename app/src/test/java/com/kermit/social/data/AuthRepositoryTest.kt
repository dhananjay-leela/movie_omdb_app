package com.kermit.social.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AuthRepositoryTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var fakeSharedPreferences: SharedPreferencesMock

    @Before
    fun setUp() {
        fakeSharedPreferences = SharedPreferencesMock()
        authRepository = AuthRepository(fakeSharedPreferences)
    }

    @Test
    fun `register with new user should return true`() {
        val result = authRepository.register("newUser", "password123")
        assertTrue(result)
    }

    @Test
    fun `register with existing user should return false`() {
        authRepository.register("existingUser", "password123")
        val result = authRepository.register("existingUser", "password123")
        assertFalse(result)
    }

    @Test
    fun `signIn with valid credentials should authenticate and return true`() {
        authRepository.register("validUser", "validPassword")
        val result = authRepository.signIn("validUser", "validPassword")
        assertTrue(result)
        assertTrue(fakeSharedPreferences.getBoolean(AuthRepository.PREF_KEY_IS_AUTHENTICATED, false))
    }

    @Test
    fun `signIn with invalid credentials should not authenticate and return false`() {
        val result = authRepository.signIn("invalidUser", "invalidPassword")
        assertFalse(result)
        assertFalse(fakeSharedPreferences.getBoolean(AuthRepository.PREF_KEY_IS_AUTHENTICATED, false))
    }

    @Test
    fun `signOut should reset authentication state`() {
        authRepository.signOut()
        assertFalse(fakeSharedPreferences.getBoolean(AuthRepository.PREF_KEY_IS_AUTHENTICATED, false))
    }

    @Test
    fun `isUserAuthenticated should return correct authentication state`() {
        assertFalse(authRepository.isUserAuthenticated())
        authRepository.register("user", "password")
        authRepository.signIn("user", "password")
        assertTrue(authRepository.isUserAuthenticated())
    }
}
