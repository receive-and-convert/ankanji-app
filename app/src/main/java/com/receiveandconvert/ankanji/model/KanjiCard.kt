package com.receiveandconvert.ankanji.model

data class KanjiCard(
	override val kanji: String,
	override val kana: String,
	override val translation: String,
	val type: String
): Card(kanji, kana, translation)
