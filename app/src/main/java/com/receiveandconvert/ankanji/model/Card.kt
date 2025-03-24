package com.receiveandconvert.ankanji.model

import com.receiveandconvert.ankanji.model.enum.JapaneseLevel
import com.receiveandconvert.ankanji.model.enum.LearningType

data class Card(
    val kanji: String,
    val level: JapaneseLevel,
    val learningType: LearningType,
    val onyomi: String, // Chinese-adapted japanese reading
    val kunyomi: String, // Japanese reading
    val translation: String
) {
    val reading = kunyomi.ifEmpty { onyomi }
    val label = "${level.name} - ${learningType.username}"
}
