package com.receiveandconvert.ankanji.provider

import android.content.res.Resources
import com.receiveandconvert.ankanji.R
import com.receiveandconvert.ankanji.model.card.Card
import com.receiveandconvert.ankanji.model.card.CardLevel
import com.receiveandconvert.ankanji.model.card.CardType
import com.receiveandconvert.ankanji.provider.model.CardFilterInput
import com.receiveandconvert.ankanji.util.fetchCards

class CardProviderImpl(
	private val resources: Resources
) : CardProvider {
	override fun getCards(input: CardFilterInput?): List<Card> {
		val cards = mutableListOf<Card>()

		// Helper function to fetch and add cards for a given level and type.
		fun fetchAssign(level: CardLevel, type: CardType) {
			cardResourceMap[level]?.get(type)?.let { resourceId ->
				cards.addAll(resources.fetchCards(resourceId, level, type))
			}
		}

		// If no input is provided, default to fetching N5 cards for all types.
		if (input == null) {
			CardType.entries.map { fetchAssign(CardLevel.N5, it) }
			return cards
		}

		// Determine which levels and types to fetch.
		val selectedLevels = if (input.cardLevels.isEmpty()) CardLevel.entries else input.cardLevels
		val selectedTypes = if (input.cardTypes.isEmpty()) CardType.entries else input.cardTypes

		// Iterate over the selected levels and types.
		selectedLevels.forEach { level -> selectedTypes.forEach { type -> fetchAssign(level, type) } }

		return cards
	}

	companion object {
		// Mapping of card level and type to the corresponding raw resource ID.
		val cardResourceMap = mapOf(
			CardLevel.N5 to mapOf(
				CardType.KANJI to R.raw.n5_kanji,
				CardType.VOCABULARY to R.raw.n5_vocabulary,
				CardType.EXPRESSION to R.raw.n5_expression
			),
			CardLevel.N4 to mapOf(
				CardType.KANJI to R.raw.n4_kanji,
				CardType.VOCABULARY to R.raw.n4_vocabulary,
				CardType.EXPRESSION to R.raw.n4_expression
			)
		)
	}
}
