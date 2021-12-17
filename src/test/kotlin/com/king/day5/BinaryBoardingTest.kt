package com.king.day5

import BinaryBoarding
import org.junit.Assert.*
import org.junit.Test

class BinaryBoardingTest {

    private val binaryBoarding = BinaryBoarding()

    @Test
    fun testBoardingPass1() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("BFFFBBFRRR", 127, 7)
        assertEquals(567, seatId)
    }

    @Test
    fun testBoardingPass2() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("FFFBBBFRRR", 127, 7)
        assertEquals(119, seatId)
    }

    @Test
    fun testBoardingPass3() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("BBFFBBFRLL", 127, 7)
        assertEquals(820, seatId)
    }

    @Test
    fun testBoardingPass4() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("FBFBBFFRLR", 127, 7)
        assertEquals(357, seatId)
    }

    @Test
    fun testBoardingPass5() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("FFFFFFFRRR", 127, 7)
        assertEquals(7, seatId)
    }

    @Test
    fun testBoardingPass6() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("FFFFFFFRRL", 127, 7)
        assertEquals(6, seatId)
    }

    @Test
    fun testBoardingPass7() {
        val (rowId, columnId, seatId) = binaryBoarding.findSeatId("BBBBBBBRRR", 127, 7)
        assertEquals(1023, seatId)
    }

}