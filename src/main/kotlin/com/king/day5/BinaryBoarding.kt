import java.io.File

fun main() {

    val binaryBoarding = BinaryBoarding()
    var maxSeatId = 0
    val allRowIds = mutableListOf<Int>()
    val allColumnIds = mutableListOf<Int>()
    val allSeatIds = mutableListOf<Int>()
    val all = mutableListOf<Triple<Int, Int, Int>>()

    File("src/main/resources/day5/inputs.txt").forEachLine {
        val triple = binaryBoarding.findSeatId(it, 127, 7)
        all.add(triple)
        val (rowId, columnId, seatId) = triple
        allSeatIds.add(seatId)
        maxSeatId = maxOf(maxSeatId, seatId)
        allRowIds.add(rowId)
        allColumnIds.add(columnId)
    }
    println("Max seatId: $maxSeatId")
    allSeatIds.sort()
    println(allSeatIds)
    for (i in allSeatIds.indices) {
        if ((allSeatIds[i] + 1) != allSeatIds[i+1]) {
            println(allSeatIds[i] + 1)
        }
    }
}


class BinaryBoarding {

    fun findSeatId(input: String, numOfRows: Int, numOfColumns: Int): Triple<Int, Int, Int> {
        val rows = 0..numOfRows
        val columns = 0..numOfColumns
        val rowInputArray = input.substring(0, 7).toCharArray()
        val columnInputArray = input.substring(7, 10).toCharArray()

        val rowId = find(rows, rowInputArray, 'F', 'B')
        val columnId = find(columns, columnInputArray, 'L', 'R')

        val seatId = rowId * 8 + columnId
        //println("Row found: $rowId, Column found: $columnId, which is seatId: $seatId")

        return Triple(rowId, columnId, seatId)
    }

    private fun find(rows: IntRange, movements: CharArray, leftSignal: Char, rightSignal: Char): Int {
        var max = rows.last
        var min = 0
        var midPoint = 0
        var index = 0;
        for (half in movements.indices) {
            midPoint = (min + max) / 2
            if (rightSignal == movements[half]) {
                min = midPoint + 1
                index = max
            } else if (leftSignal == movements[half]) {
                max = midPoint
                index = min
            }
        }
        return index
    }

}
