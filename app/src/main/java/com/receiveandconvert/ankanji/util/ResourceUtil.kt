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
	return fetchResource(resourceId) {
		when (cardType) {
			CardType.KANJI -> Card(
				kanji = it.getOrNull(0)?.toString() ?: "",
				kana = it.getOrNull(1)?.toString() ?: "",
				translation = it.getOrNull(2)?.toString() ?: "",
				usageType = it.getOrNull(3)?.toString() ?: "",
				level = level,
				type = cardType
			)

			CardType.VOCABULARY -> Card(
				kana = it.getOrNull(1)?.toString() ?: "",
				kanji = it.getOrNull(2)?.toString() ?: "",
				usageType = it.getOrNull(3)?.toString() ?: "",
				translation = it.getOrNull(4)?.toString() ?: "",
				level = level,
				type = cardType
			)

			CardType.EXPRESSION -> Card(
				kana = it.getOrNull(1)?.toString() ?: "",
				translation = it.getOrNull(2)?.toString() ?: "",
				level = level,
				type = cardType
			)
		}
	}
}

private fun <T> Resources.fetchResource(resourceId: Int, transform: (Array<out String?>) -> T): List<T> {
	val reader = BufferedReader(InputStreamReader(openRawResource(resourceId)))
	val parser = CSVParserBuilder().withSeparator(';').build()
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
