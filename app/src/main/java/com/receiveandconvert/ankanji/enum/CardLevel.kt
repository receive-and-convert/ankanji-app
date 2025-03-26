package com.receiveandconvert.ankanji.enum

import com.receiveandconvert.ankanji.enum.CardLevel.valueOf

enum class CardLevel {
	N5,
	N4,
	N3,
	N2,
	N1;

	companion object {
		fun safeValueOf(value: String): CardLevel {
			return try {
				valueOf(value.uppercase())
			} catch (e: IllegalArgumentException) {
				N5
			}
		}
	}
}
