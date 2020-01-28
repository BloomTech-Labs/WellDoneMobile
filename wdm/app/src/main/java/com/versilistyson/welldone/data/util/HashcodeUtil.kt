package com.versilistyson.welldone.data.util

fun <T: Any> generateKeys(objs: List<T>): List<Int> {
    val hashCodesForObjects = mutableListOf<Int>()
    objs.forEach {
        hashCodesForObjects.add(it.hashCode())
    }
    return hashCodesForObjects
}

