package com.king.day7

import java.io.File
import java.util.*

fun main() {
    val handyHaversacks = HandyHaversacks()
    val rules = mutableListOf<String>()
    File("src/main/resources/day7/inputs.txt").forEachLine {
        rules.add(it)
    }
    handyHaversacks.calculate(rules)
}

class HandyHaversacks {
    fun calculate(rules: List<String>): Int {
        val colouredBagAndContents = mutableMapOf<String, List<BagAndQuantity>>()
        rules.forEach {
            val primaryBag = it.split("bags")[0].trim()
            val bagsAndQuantities = mutableListOf<BagAndQuantity>()
            it.split(", ").forEach {
                if (it.split(" ").size > 4 && !it.contains("no")) {
                    bagsAndQuantities.add(
                        BagAndQuantity(
                            it.split(" ")[5] + " " + it.split(" ")[6],
                            it.split(" ")[4].toInt()
                        )
                    )
                } else {
                    if (!it.contains("no")) {
                        bagsAndQuantities.add(
                            BagAndQuantity(
                                it.split(" ")[1] + " " + it.split(" ")[2],
                                it.split(" ")[0].toInt()
                            )
                        )
                    }
                }
            }
            colouredBagAndContents[primaryBag] = bagsAndQuantities
        }
        val bagInput = "shiny gold"
        val stack = Stack<String>()
        stack.push(bagInput)
        val colours = mutableSetOf<String>()
        var count = 0;
        while (stack.isNotEmpty()) {
            val colour = stack.pop()
            val contents = colouredBagAndContents[colour]
                if (contents != null) {
                    contents.forEach {
                        val bag = it.bag
                        if (bag == bagInput) {
                            colours.add(colour)
                        }
                        stack.push(bag)
                        colours.forEach {
                            if (it == bag) colours.add(colour)
                        }
                    }
                } else if (stack.isEmpty()) {
                    return colours.size
                }
        }
        return count
    }
}

fun findBag(colouredBagAndContents: Map<String, BagAndQuantity>) {

}

data class BagAndQuantity(val bag: String, val quantity: Int)