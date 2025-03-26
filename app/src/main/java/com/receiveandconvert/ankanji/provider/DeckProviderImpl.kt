package com.receiveandconvert.ankanji.provider

import com.receiveandconvert.ankanji.model.Deck

class DeckProviderImpl : DeckProvider {
	override fun getDecks(): List<Deck> {
		return listOf(
			Deck(
				id = 1,
				name = "Deck 1",
				description = "Description 1",
				cardCount = 10
			),
			Deck(
				id = 2,
				name = "Deck 2",
				description = "Description 2",
				cardCount = 20
			)
		)
	}
}