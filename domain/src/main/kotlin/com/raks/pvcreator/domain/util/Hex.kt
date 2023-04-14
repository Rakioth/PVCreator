package com.raks.pvcreator.domain.util

fun ByteArray.formatHex(): String    = joinToString("") { "%02x".format(it) }
fun String.parseHex():     ByteArray = chunked(2).map { it.toInt(16).toByte() }.toByteArray()
