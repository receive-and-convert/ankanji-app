package com.receiveandconvert.ankanji.util

import android.content.res.Resources
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.receiveandconvert.ankanji.enum.CardLevel
import com.receiveandconvert.ankanji.enum.CardType
import com.receiveandconvert.ankanji.model.Card
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resources.fetchCards(resourceId: Int, level: CardLevel, cardType: CardType): List<Card> = fetchResource(resourceId) {
	when (cardType) {
		CardType.KANJI -> Card(
			kanji = it[0]!!,
			kana = it[1]!!,
			translation = it[2]!!,
			usageType = it[3]!!,
			level = level,
			cardType = cardType
		)
		CardType.VOCABULARY -> Card(
			kana = it[0]!!,
			kanji = it[1]!!,
			usageType = it[2]!!,
			translation = it[3]!!,
			level = level,
			cardType = cardType
		)
		CardType.EXPRESSION -> Card(
			kana = it[1]!!,
			translation = it[2]!!,
			level = level,
			cardType = cardType
		)
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
