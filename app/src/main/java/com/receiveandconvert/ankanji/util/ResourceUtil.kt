package com.receiveandconvert.ankanji.util

import android.content.res.Resources
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.enum.JapaneseLevel
import com.receiveandconvert.ankanji.model.enum.LearningType
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resources.fetchCards(resourceId: Int): List<Card> {
	val reader = BufferedReader(InputStreamReader(openRawResource(resourceId)))
	val parser = CSVParserBuilder().withSeparator(';').build()
	val csvReader = CSVReaderBuilder(reader).withCSVParser(parser).build()

	val cards = mutableListOf<Card>()
	csvReader.use {
		it.skip(1)
		var nextLine = it.readNext()
		while (nextLine != null) {
			cards += Card(
				kanji = nextLine[0],
				translation = nextLine[1],
				learningType = LearningType.safeValueOf(nextLine[2]),
				onyomi = nextLine[3],
				kunyomi = nextLine[4],
				level = JapaneseLevel.N5
			)
			nextLine = it.readNext()
		}
	}
	return cards
}
