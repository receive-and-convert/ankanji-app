package com.receiveandconvert.ankanji.model.constant

import com.receiveandconvert.ankanji.model.Deck
import com.receiveandconvert.ankanji.model.card.Card
import com.receiveandconvert.ankanji.model.card.CardLevel
import com.receiveandconvert.ankanji.model.card.CardType
import com.receiveandconvert.ankanji.provider.model.CardFilterInput

object DummyData {
	val dummyExpressionCards = listOf<Card>(
		Card(
			kana = "おはよう",
			translation = "good morning",
			level = CardLevel.N5,
			type = CardType.EXPRESSION
		),
		Card(
			kana = "こんにちは",
			translation = "good afternoon",
			level = CardLevel.N4,
			type = CardType.EXPRESSION
		),
		Card(
			kana = "こんばんは",
			translation = "good evening",
			level = CardLevel.N3,
			type = CardType.EXPRESSION
		),
		Card(
			kana = "おやすみなさい",
			translation = "good night",
			level = CardLevel.N2,
			type = CardType.EXPRESSION
		),
		Card(
			kana = "さようなら",
			translation = "goodbye",
			level = CardLevel.N1,
			type = CardType.EXPRESSION
		)
	)

	val dummyKanjiCards = listOf<Card>(
		Card(
			kanji = "一",
			kana = "ひと",
			translation = "one",
			level = CardLevel.N5,
			usageType = "numeric, prefix, suffix",
			type = CardType.EXPRESSION
		),
		Card(
			kanji = "七",
			kana = "な",
			translation = "seven",
			level = CardLevel.N4,
			usageType = "numeric",
			type = CardType.EXPRESSION
		),
		Card(
			kanji = "万",
			kana = "まん",
			translation = "ten thousand",
			level = CardLevel.N3,
			usageType = "numeric, prefix",
			type = CardType.EXPRESSION
		),
		Card(
			kanji = "万引き",
			kana = "まんびき",
			translation = "shoplifting, shoplifter",
			level = CardLevel.N2,
			usageType = "n, vs",
			type = CardType.EXPRESSION
		),
		Card(
			kanji = "三",
			kana = "さん",
			translation = "three",
			level = CardLevel.N1,
			usageType = "numeric",
			type = CardType.EXPRESSION
		),
	)

	val dummyVocabularyCards = listOf<Card>(
		Card(
			kanji = "一つ",
			kana = "ひとつ",
			translation = "one",
			level = CardLevel.N5,
			usageType = "numeric",
			type = CardType.VOCABULARY
		),
		Card(
			kanji = "七つ",
			kana = "ななつ",
			translation = "seven",
			level = CardLevel.N4,
			usageType = "numeric",
			type = CardType.VOCABULARY
		),
		Card(
			kanji = "万",
			kana = "まん",
			translation = "ten thousand",
			level = CardLevel.N3,
			usageType = "numeric",
			type = CardType.VOCABULARY
		),
		Card(
			kanji = "万引き",
			kana = "まんびき",
			translation = "shoplifting, shoplifter",
			level = CardLevel.N2,
			usageType = "n, vs",
			type = CardType.VOCABULARY
		),
		Card(
			kanji = "三つ",
			kana = "みっつ",
			translation = "three",
			level = CardLevel.N1,
			usageType = "numeric",
			type = CardType.VOCABULARY
		)
	)

	val dummyCards = dummyExpressionCards
		.plus(dummyKanjiCards)
		.plus(dummyVocabularyCards)

	val dummyCard = Card(
		kanji = "三つ",
		kana = "みっつ",
		translation = "three",
		level = CardLevel.N1,
		usageType = "numeric",
		type = CardType.VOCABULARY
	)

	val dummyDecks = listOf(
		Deck(
			id = 1,
			name = "Level N1",
			description = "The highest level, representing the ability to understand Japanese in a variety of situations",
			cardFilter = CardFilterInput(
				cardLevels = listOf(CardLevel.N1)
			),
			enabled = false
		),
		Deck(
			id = 2,
			name = "Level N2",
			description = "Represents the ability to understand Japanese in everyday situations and in a variety of situations to a certain degree",
			cardFilter = CardFilterInput(
				cardLevels = listOf(CardLevel.N2)
			),
			enabled = false
		),
		Deck(
			id = 3,
			name = "Level N3",
			description = "A bridging level between N1/N2 and N4/5",
			cardFilter = CardFilterInput(
				cardLevels = listOf(CardLevel.N3)
			),
			enabled = false
		),
		Deck(
			id = 4,
			name = "Level N4",
			description = "Represents the ability to understand basic Japanese",
			cardFilter = CardFilterInput(
				cardLevels = listOf(CardLevel.N4)
			),
			enabled = true
		),
		Deck(
			id = 5,
			name = "Level N5",
			description = "The lowest level, representing the ability to understand some basic Japanese",
			cardFilter = CardFilterInput(
				cardLevels = listOf(CardLevel.N5)
			),
			enabled = true
		)
	)
}
