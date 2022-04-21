package com.gonexwind.ourstory.utils

import com.gonexwind.ourstory.utils.Utils.formatDate
import org.junit.Assert

import org.junit.Test
import java.time.format.DateTimeParseException
import java.time.zone.ZoneRulesException

class UtilsTest {

    @Test
    fun `given correct ISO 8601 format then should format correctly`() {
        val currentDate = "2022-02-02T10:10:10Z"
        Assert.assertEquals("02 Feb 2022 | 17:10", formatDate(currentDate, "Asia/Jakarta"))
        Assert.assertEquals("02 Feb 2022 | 18:10", formatDate(currentDate, "Asia/Makassar"))
        Assert.assertEquals("02 Feb 2022 | 19:10", formatDate(currentDate, "Asia/Jayapura"))
    }

    @Test
    fun `given wrong ISO 8601 format then should throw error`() {
        val wrongFormat = "2022-02-02T10:10"
        Assert.assertThrows(DateTimeParseException::class.java) {
            formatDate(wrongFormat, "Asia/Jakarta")
        }
    }

    @Test
    fun `given invalid timezone then should throw error`() {
        val wrongFormat = "2022-02-02T10:10:10Z"
        Assert.assertThrows(ZoneRulesException::class.java) {
            formatDate(wrongFormat, "Asia/Pati")
        }
    }
}