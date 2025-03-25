package com.receiveandconvert.ankanji.constant

import com.receiveandconvert.ankanji.enum.CardLevel
import com.receiveandconvert.ankanji.enum.CardType
import com.receiveandconvert.ankanji.model.Card

object DummyData {
	val dummyExpressionCards = listOf<Card>(
		Card(
			kana = "おはよう",
			translation = "good morning",
			level = CardLevel.N5,
			cardType = CardType.EXPRESSION
		),
		Card(
			kana = "こんにちは",
			translation = "good afternoon",
			level = CardLevel.N4,
			cardType = CardType.EXPRESSION
		),
		Card(
			kana = "こんばんは",
			translation = "good evening",
			level = CardLevel.N3,
			cardType = CardType.EXPRESSION
		),
		Card(
			kana = "おやすみなさい",
			translation = "good night",
			level = CardLevel.N2,
			cardType = CardType.EXPRESSION
		),
		Card(
			kana = "さようなら",
			translation = "goodbye",
			level = CardLevel.N1,
			cardType = CardType.EXPRESSION
		)
	)

	val dummyKanjiCards = listOf<Card>(
		Card(
			kanji = "一",
			kana = "ひと",
			translation = "one",
			level = CardLevel.N5,
			usageType = "numeric, prefix, suffix",
			cardType = CardType.EXPRESSION
		),
		Card(
			kanji = "七",
			kana = "な",
			translation = "seven",
			level = CardLevel.N4,
			usageType = "numeric",
			cardType = CardType.EXPRESSION
		),
		Card(
			kanji = "万",
			kana = "まん",
			translation = "ten thousand",
			level = CardLevel.N3,
			usageType = "numeric, prefix",
			cardType = CardType.EXPRESSION
		),
		Card(
			kanji = "万引き",
			kana = "まんびき",
			translation = "shoplifting, shoplifter",
			level = CardLevel.N2,
			usageType = "n, vs",
			cardType = CardType.EXPRESSION
		),
		Card(
			kanji = "三",
			kana = "さん",
			translation = "three",
			level = CardLevel.N1,
			usageType = "numeric",
			cardType = CardType.EXPRESSION
		),
	)

	val dummyVocabularyCards = listOf<Card>(
		Card(
			kanji = "一つ",
			kana = "ひとつ",
			translation = "one",
			level = CardLevel.N5,
			usageType = "numeric",
			cardType = CardType.VOCABULARY
		),
		Card(
			kanji = "七つ",
			kana = "ななつ",
			translation = "seven",
			level = CardLevel.N4,
			usageType = "numeric",
			cardType = CardType.VOCABULARY
		),
		Card(
			kanji = "万",
			kana = "まん",
			translation = "ten thousand",
			level = CardLevel.N3,
			usageType = "numeric",
			cardType = CardType.VOCABULARY
		),
		Card(
			kanji = "万引き",
			kana = "まんびき",
			translation = "shoplifting, shoplifter",
			level = CardLevel.N2,
			usageType = "n, vs",
			cardType = CardType.VOCABULARY
		),
		Card(
			kanji = "三つ",
			kana = "みっつ",
			translation = "three",
			level = CardLevel.N1,
			usageType = "numeric",
			cardType = CardType.VOCABULARY
		)
	)

	val dummyCards = emptyList<Card>()
		.plus(dummyExpressionCards)
		.plus(dummyKanjiCards)
		.plus(dummyVocabularyCards)
}
