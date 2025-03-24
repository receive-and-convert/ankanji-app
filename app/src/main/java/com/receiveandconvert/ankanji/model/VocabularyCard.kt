package com.receiveandconvert.ankanji.model

data class VocabularyCard(
	override val kanji: String,
	override val kana: String,
	override val translation: String,
	val type: String
): Card(kanji, kana, translation)
