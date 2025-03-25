package com.receiveandconvert.ankanji.util

fun <T, K, V> List<T>.convertToMap(transform: (T) -> Pair<K, V>): MutableMap<K, V> {
	val destination = mutableMapOf<K, V>()
	forEach { item ->
		val (key, value) = transform(item)
		destination[key] = value
	}
	return destination
}