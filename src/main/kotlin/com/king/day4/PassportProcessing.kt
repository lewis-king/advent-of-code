package com.king.day4

import java.io.File

fun main() {
    val entries = mutableListOf<Map<String, String>>()
    var entry = mutableMapOf<String, String>()
    File("src/main/resources/day4/inputs.txt").forEachLine { it ->
        if (it.isEmpty()) {
            entries.add(entry)
            entry = mutableMapOf<String, String>()
        } else {
            it.split(" ").forEach {
                val keyAndVal = it.split(":")
                entry[keyAndVal[0]] = keyAndVal[1]
            }
        }
    }
    entries.forEach {
        println(it.entries)
    }
    val validPassports = PassportProcessing().processPassport(entries)
    println(validPassports)
}

class PassportProcessing {

    private val requiredKeys = listOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")

    fun processPassport(entries: List<Map<String, String>>): Int {
         return entries.filter { it.keys.containsAll(requiredKeys) }.filter { dataValidation(it) }.count()
    }

    private fun dataValidation(entry: Map<String, String>): Boolean {
        val validationErrors = mutableListOf<Boolean>()
        entry.entries.forEach {
            validationErrors.add(when (it.key) {
                "byr" -> rangeCheck(1920, 2002, it.value.toInt())
                "iyr" -> rangeCheck(2010, 2020, it.value.toInt())
                "eyr" -> rangeCheck(2020, 2030, it.value.toInt())
                "hgt" -> (it.value.endsWith("cm") && rangeCheck(150, 193, it.value.removeSuffix("cm").toInt())) || ((it.value.endsWith("in") && rangeCheck(59, 76, it.value.removeSuffix("in").toInt())))
                "hcl" -> it.value.startsWith("#") && it.value.removePrefix("#").length == 6 && "[a-f0-9]+".toRegex().matches(it.value.removePrefix("#"))
                "ecl" -> setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(it.value)
                "pid" -> it.value.length == 9
                "cid" -> true
                else -> false
            })
        }
        return !validationErrors.contains(false)
    }

    private fun rangeCheck(lowerBound: Int, upperBound: Int, input: Int): Boolean {
        return input in lowerBound..upperBound
    }

}