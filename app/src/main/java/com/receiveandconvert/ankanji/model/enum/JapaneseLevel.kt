package com.receiveandconvert.ankanji.model.enum

enum class JapaneseLevel {
    N5,
    N4,
    N3,
    N2,
    N1;

    companion object {
        fun safeValueOf(value: String): JapaneseLevel {
            return try {
                valueOf(value.uppercase())
            } catch (e: IllegalArgumentException) {
                N5
            }
        }
    }
}
