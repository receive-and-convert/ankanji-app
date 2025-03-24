package com.receiveandconvert.ankanji.model

data class ExpressionCard(
		override val kanji: String = "",
		override val kana: String,
		override val translation: String
): Card(kanji, kana, translation)
