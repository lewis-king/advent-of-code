package com.king.day6

import org.junit.Assert.*
import org.junit.Test

class CustomCustomsTest {

    val customCustoms = CustomCustoms()

    @Test
    fun testGroupsQsAnsweredYes() {
        val groups = mutableListOf(setOf('a', 'b', 'c'), setOf('a', 'b', 'c'), setOf('a', 'b', 'c'), setOf('a'), setOf('b'))
        assertEquals(11, customCustoms.calculateGroupMarkedYes(groups))
    }

    @Test
    fun testGroupsQsAnsweredYes_2() {
        val groups = mutableListOf(setOf('o', 'm', 'l', 'j', 'u', 't'), setOf('o', 'l', 'm', 's', 'u', 'j'), setOf('m', 'l', 'm', 'u', 'j', 'c', 'n', 'b', 'x', 'k', 'o', 'e', 'p'), setOf('t', 'l', 'q', 'j', 'o', 'm', 'u'))
        assertEquals(16, customCustoms.calculateGroupMarkedYes(groups))
    }
}