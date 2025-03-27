package com.receiveandconvert.ankanji.util

import android.content.res.Resources
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.receiveandconvert.ankanji.model.card.Card
import com.receiveandconvert.ankanji.model.card.CardLevel
import com.receiveandconvert.ankanji.model.card.CardType
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resources.fetchCards(resourceId: Int, level: CardLevel, cardType: CardType): List<Card> {
	fun Array<out String?>.safeGetValue(index: Int): String {
		return getOrNull(index)?.toString() ?: ""
	}

	return fetchResource(resourceId) {
		when (cardType) {
			CardType.KANJI -> Card(
				kanji = it.safeGetValue(0),
				kana = it.safeGetValue(1),
				translation = it.safeGetValue(2),
				usageType = it.safeGetValue(3),
				level = level,
				type = cardType
			)

			CardType.VOCABULARY -> Card(
				kana = it.safeGetValue(1),
				kanji = it.safeGetValue(2),
				usageType = it.safeGetValue(3),
				translation = it.safeGetValue(4),
				level = level,
				type = cardType
			)

			CardType.EXPRESSION -> Card(
				kana = it.safeGetValue(1),
				translation = it.safeGetValue(2),
				level = level,
				type = cardType
			)
		}
	}
}

private fun <T> Resources.fetchResource(resourceId: Int, transform: (Array<out String?>) -> T): List<T> {
	val reader = BufferedReader(InputStreamReader(openRawResource(resourceId)))
	val parser = CSVParserBuilder().withSeparator(',').build()
	val csvReader = CSVReaderBuilder(reader).withCSVParser(parser).build()

	val cards = mutableListOf<T>()
	csvReader.use {
		it.skip(1)
		var nextLine: Array<out String?>? = it.readNext()
		while (nextLine != null) {
			cards.add(transform(nextLine))
			nextLine = it.readNext()
		}
	}
	return cards
}
