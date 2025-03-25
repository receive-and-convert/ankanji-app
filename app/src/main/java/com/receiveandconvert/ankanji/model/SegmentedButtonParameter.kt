package com.receiveandconvert.ankanji.model

import androidx.compose.runtime.Composable

data class SegmentedButtonParameter(
	val label: (@Composable () -> Unit)? = null
)