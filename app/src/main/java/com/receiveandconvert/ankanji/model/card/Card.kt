package com.receiveandconvert.ankanji.model.card

class Card(
	val kanji: String = "",
	val kana: String,
	val translation: String,
	val level: CardLevel,
	val usageType: String = "",
	val type: CardType
)
