package com.receiveandconvert.ankanji.model.enum

enum class LearningType(val username: String) {
	KANJI("Kanji"),
	KOTOBA("Kotoba");

	companion object {
		fun safeValueOf(value: String): LearningType {
			return try {
				valueOf(value.uppercase())
			} catch (e: IllegalArgumentException) {
				KOTOBA
			}
		}
	}
}