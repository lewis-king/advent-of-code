package com.king.day3

import java.io.File

fun main() {

    val field = mutableListOf<List<Char>>()

    File("src/main/resources/day3/inputs.txt").forEachLine {
        field.add(it.toCharArray().toList())
    }
    val yDimension = field.size
    val xDimension = field[0].size
    val fieldArr = Array(yDimension) {Array(xDimension) {'X'} }
    for (x in field.indices) {
        fieldArr[x] = field[x].toTypedArray()
    }

    val slopes = listOf(Coordinate(1, 1), Coordinate(3, 1), Coordinate(5, 1), Coordinate(7, 1), Coordinate(1, 2))
    var totalProductTreesEncountered = 1L


    slopes.forEach {
        val treesEncountered = TobogganTrajectory().trip(it.x, it.y, fieldArr).toLong()
        println("Path with coordinates: $it.x, $it.y would encounter $treesEncountered")
        totalProductTreesEncountered *= treesEncountered
    }

    println("Number of total trees in all paths are: $totalProductTreesEncountered")
}

class TobogganTrajectory {

    fun trip(x: Int, y: Int, field: Array<Array<Char>>): Int {
        val position = Coordinate(0, 0)
        val fieldDepth = field.size
        var treeCount = 0

        while (position.y < fieldDepth - 1) {
            position.x += x
            position.y += y
            if (position.x >= field[0].size) {
                position.x = position.x - field[0].size
            }
            val currentObject = field[position.y][position.x]
            if (currentObject == '#') {
                //field[position.y][position.x] = 'X'
                treeCount++
            }
            else {
                //field[position.y][position.x] = 'O'
            }

        }
        prettyPrintArray(field)
        return treeCount
    }

    }

fun prettyPrintArray(fieldArr: Array<Array<Char>>) {
    fieldArr.forEach{
        println(it.contentToString())
    }
}

data class Coordinate(var x: Int, var y: Int)