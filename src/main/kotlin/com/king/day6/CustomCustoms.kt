package com.king.day6

import java.io.File


/**
 * TODO: CLEAN UP AND SIMPLIFY MASSIVELY - DON'T WATCH CRYSTAL PALACE WHILST ATTEMPTING CHALLENGE...
 */
fun main() {

    val customCustoms = CustomCustoms()

    val groups = mutableListOf<Set<Char>>()
    val groups2 = mutableMapOf<Int, MutableList<MutableSet<Char>>>()
    var group = mutableSetOf<Char>()
    var group2 = mutableSetOf<Char>()
    var groupId = 1
    File("src/main/resources/day6/inputs.txt").forEachLine {
        group2 = mutableSetOf<Char>()
        if (it == "") {
            groups.add(group)
            groupId++
            group = mutableSetOf()
        } else {
            group.addAll(it.toCharArray().toCollection(group))

            if (groups2[groupId] != null) {
                val existing = groups2[groupId]
                existing?.add(it.toCharArray().toCollection(group2))
            } else {
                groups2[groupId] = mutableListOf(it.toCharArray().toCollection(group2))
            }
        }
    }
    groups.add(group)
    println(groups)
    val totalQsMarkedYes = customCustoms.calculateGroupMarkedYes(groups)
    println(totalQsMarkedYes)
    val totalQsAllInGroupAnsweredYes = customCustoms.calculateQuestionsAllInGroupAnsweredYes(groups2)
    println(totalQsAllInGroupAnsweredYes)
}

class CustomCustoms {

    fun calculateGroupMarkedYes(groups: List<Set<Char>>): Int {
        return groups.map { it.size }.sum()
    }

    fun calculateQuestionsAllInGroupAnsweredYes(groups2: MutableMap<Int, MutableList<MutableSet<Char>>>): Int {
        var total = 0
        groups2.values.forEach{
            val numOfIndividualsInGroup = it.size
            var groupQuestions = mutableListOf<Char>()
            it.forEach{
                groupQuestions.addAll(it)
            }
            var questionToCount = mutableMapOf<Char, Int>()
            groupQuestions.forEach {
                if (questionToCount[it] != null) {
                    val existing = questionToCount[it]
                    if (existing != null) {
                        questionToCount[it] = existing + 1
                    }
                } else {
                    questionToCount[it] = 1
                }
            }
            questionToCount.entries.forEach {
                if (it.value >= numOfIndividualsInGroup) {
                    total++
                }
            }
            //questionToCount.entries.filter { it.value >= numOfIndividualsInGroup }.count()
        }
        return total
    }

}