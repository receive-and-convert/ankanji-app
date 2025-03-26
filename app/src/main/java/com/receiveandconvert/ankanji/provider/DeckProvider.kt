package com.receiveandconvert.ankanji.provider

import com.receiveandconvert.ankanji.model.Deck

interface DeckProvider {
	fun getDecks(): List<Deck>
}
