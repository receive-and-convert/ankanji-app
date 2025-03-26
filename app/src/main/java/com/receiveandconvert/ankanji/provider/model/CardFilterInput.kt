package com.receiveandconvert.ankanji.provider.model

import com.receiveandconvert.ankanji.model.card.CardLevel
import com.receiveandconvert.ankanji.model.card.CardType

data class CardFilterInput (
	val cardLevels: List<CardLevel> = emptyList(),
	val cardTypes: List<CardType> = emptyList()
)
