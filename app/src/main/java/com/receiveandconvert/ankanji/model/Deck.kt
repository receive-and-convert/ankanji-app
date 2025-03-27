package com.receiveandconvert.ankanji.model

import com.receiveandconvert.ankanji.provider.model.CardFilterInput

data class Deck(
	val id: Int,
	val name: String,
	val description: String,
	val cardFilter: CardFilterInput,
	val enabled: Boolean
)
