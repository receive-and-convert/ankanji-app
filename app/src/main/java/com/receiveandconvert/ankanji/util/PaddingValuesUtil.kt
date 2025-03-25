package com.receiveandconvert.ankanji.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

fun PaddingValues.add(other: PaddingValues, layoutDirection: LayoutDirection): PaddingValues {
	return PaddingValues(
		start = this.calculateStartPadding(layoutDirection) + other.calculateStartPadding(layoutDirection),
		top = this.calculateTopPadding() + other.calculateTopPadding(),
		end = this.calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection),
		bottom = this.calculateBottomPadding() + other.calculateBottomPadding()
	)
}

@Composable
fun PaddingValues.add(other: PaddingValues): PaddingValues {
	val layoutDirection = LocalLayoutDirection.current
	return this.add(other, layoutDirection)
}

fun Modifier.default(paddingValues: PaddingValues = PaddingValues.Absolute()): Modifier {
	return padding(paddingValues)
}