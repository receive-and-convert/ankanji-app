package com.receiveandconvert.ankanji.model

import com.receiveandconvert.ankanji.enum.CardLevel
import com.receiveandconvert.ankanji.enum.CardType

class Card(
	val kanji: String = "",
	val kana: String,
	val translation: String,
	val level: CardLevel,
	val usageType: String = "",
	val cardType: CardType
)
