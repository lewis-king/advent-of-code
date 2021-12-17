package com.king.day2

import java.io.File

fun main() {

    val passwordPhilosophy = PasswordPhilosophy()

    val passwordsAndPolicies = mutableListOf<PasswordAndPolicy>()

    File("src/main/resources/day2/inputs.txt").forEachLine {
        passwordsAndPolicies.add(passwordPhilosophy.createPasswordAndPolicy(it))
    }

    println("Task 1: " + passwordPhilosophy.validPasswordPolicies(passwordsAndPolicies))
    println("Task 2: " + passwordPhilosophy.validPasswordPoliciesIndexes(passwordsAndPolicies))
}

class PasswordPhilosophy {
    fun validPasswordPolicies(inputs: List<PasswordAndPolicy>): Int {
        return inputs.stream().filter{isValid(it)}.count().toInt()
    }

    fun validPasswordPoliciesIndexes(inputs: List<PasswordAndPolicy>): Int {
        return inputs.stream().filter{isValidIndexes(it)}.count().toInt()
    }

    fun createPasswordAndPolicy(input: String): PasswordAndPolicy {
        val splittedInput = input.split(" ")

        val lowerBound = splittedInput[0].split("-")[0].toInt()
        val upperBound = splittedInput[0].split("-")[1].toInt()
        val letterRequirement = splittedInput[1].replace(":", "")
        val password = splittedInput[2]

        return PasswordAndPolicy(lowerBound, upperBound, letterRequirement, password)
    }

    private fun isValid(input: PasswordAndPolicy): Boolean {
        val count = input.password.toCharArray().filter { it.toString() == input.letterRequirement }.count()
        return count >= input.lowerBound && count <= input.upperBound
    }

    private fun isValidIndexes(input: PasswordAndPolicy): Boolean {
        val indexA = input.lowerBound - 1
        val indexB = input.upperBound - 1

        val actualLetterA = input.password[indexA].toString()
        val actualLetterB = input.password[indexB].toString()

        val aMatches = actualLetterA == input.letterRequirement
        val bMatches = actualLetterB == input.letterRequirement

        return aMatches.xor(bMatches)
    }
}

data class PasswordAndPolicy(
    val lowerBound: Int,
    val upperBound: Int,
    val letterRequirement: String,
    val password: String
)
