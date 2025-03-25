package com.receiveandconvert.ankanji.model.constant

import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.enum.JapaneseLevel
import com.receiveandconvert.ankanji.model.enum.LearningType

object DummyData {
	val DUMMY_CARDS = listOf(
		Card(
			kanji = "日",
			level = JapaneseLevel.N5,
			learningType = LearningType.KANJI,
			onyomi = "ニチ; ジツ -",
			kunyomi = "ひ -び",
			translation = "day, sun, Japan"
		),
		Card(
			kanji = "月",
			level = JapaneseLevel.N5,
			learningType = LearningType.KANJI,
			onyomi = "ゲツ; ガツ -",
			kunyomi = "つき -",
			translation = "month, moon"
		),
		Card(
			kanji = "火",
			level = JapaneseLevel.N5,
			learningType = LearningType.KANJI,
			onyomi = "カ -",
			kunyomi = "ひ -",
			translation = "fire"
		),
		Card(
			kanji = "水",
			level = JapaneseLevel.N5,
			learningType = LearningType.KANJI,
			onyomi = "スイ -",
			kunyomi = "みず -",
			translation = "water"
		)
	)
}