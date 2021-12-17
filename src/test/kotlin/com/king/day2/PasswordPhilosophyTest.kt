package com.king.day2

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class PasswordPhilosophyTest {

    private val passwordPhilosophy = PasswordPhilosophy()

    @Test
    fun testPasswordAndPolicyCreation() {
        val passwordAndPolicy = passwordPhilosophy.createPasswordAndPolicy("7-16 h: hmgwvnnvhhzfhlhqhh")

        assertEquals(7, passwordAndPolicy.lowerBound)
        assertEquals(16, passwordAndPolicy.upperBound)
        assertEquals("h", passwordAndPolicy.letterRequirement)
        assertEquals("hmgwvnnvhhzfhlhqhh", passwordAndPolicy.password)
    }

    @Test
    fun testScenario1() {
        val inputs = listOf(PasswordAndPolicy(4, 7, "w", "wwwcwww"),
            PasswordAndPolicy(4, 5, "w", "wwwcwww"),
            PasswordAndPolicy(4, 7, "w", "wwwcwww"))

        assertEquals(2, passwordPhilosophy.validPasswordPolicies(inputs))
    }

    @Test
    fun testScenario_indexes_1() {
        val inputs = listOf(PasswordAndPolicy(1, 3, "a", "abcde"),
            PasswordAndPolicy(1, 3, "b", "cdefg"),
            PasswordAndPolicy(2, 9, "c", "ccccccccc"))

        assertEquals(1, passwordPhilosophy.validPasswordPoliciesIndexes(inputs))
    }
}