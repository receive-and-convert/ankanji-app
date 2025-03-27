package com.receiveandconvert.ankanji.provider

import com.receiveandconvert.ankanji.model.Deck
import com.receiveandconvert.ankanji.model.constant.DummyData.dummyDecks

class DeckProviderImpl : DeckProvider {
	override fun getDecks(): List<Deck> {
		return dummyDecks
	}

	override fun getDeckById(deckId: Long): Deck? {
		return dummyDecks.find { it.id.toLong() == deckId }
	}
}