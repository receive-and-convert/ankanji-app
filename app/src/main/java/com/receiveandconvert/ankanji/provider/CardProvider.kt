package com.receiveandconvert.ankanji.provider

import com.receiveandconvert.ankanji.model.card.Card
import com.receiveandconvert.ankanji.provider.model.CardFilterInput

interface CardProvider {
	fun getCards(input: CardFilterInput? = null): List<Card>
}
