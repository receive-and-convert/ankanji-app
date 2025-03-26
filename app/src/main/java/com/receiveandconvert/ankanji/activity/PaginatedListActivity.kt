package com.receiveandconvert.ankanji.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.receiveandconvert.ankanji.component.button.MultiChoiceButtons
import com.receiveandconvert.ankanji.constant.DummyData.dummyCards
import com.receiveandconvert.ankanji.enum.CardLevel
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.SegmentedButtonParameter
import com.receiveandconvert.ankanji.util.convertToMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class CardDataSource {
	fun getCards(position: Int, loadSize: Int): List<Card> {
		val startIndex = position * loadSize
		return if (startIndex >= dummyCards.size) {
			emptyList()
		} else {
			val endIndex = minOf(startIndex + loadSize, dummyCards.size)
			dummyCards.subList(startIndex, endIndex)
		}
	}
}

internal class CardPagingSource(
	private val dataSource: CardDataSource,
	private val selectedOptions: List<Boolean>
) : PagingSource<Int, Card>() {
	override fun getRefreshKey(state: PagingState<Int, Card>): Int? {
		// Get the anchor position, which is the most recently accessed index
		val anchorPosition = state.anchorPosition ?: return null

		// Get the closest page to the anchor position
		val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

		// Determine the refresh key based on the anchor page's keys
		return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Card> {
		val position = params.key ?: 0
		return try {
			val cards = dataSource.getCards(position, params.loadSize)
				.filter { selectedOptions[it.level.ordinal] }
				.sortedByDescending { it.level }
			LoadResult.Page(
				data = cards,
				prevKey = if (position == 0) null else position - 1,
				nextKey = if (cards.isEmpty()) null else position + 1
			)
		} catch (exception: Exception) {
			LoadResult.Error(exception)
		}
	}
}

internal class CardViewModel(private val dataSource: CardDataSource) : ViewModel() {
	private val _selectedOptions = MutableStateFlow(List(CardLevel.entries.size) { true })
	val selectedOptions: StateFlow<List<Boolean>> = _selectedOptions.asStateFlow()

	val pager = Pager(PagingConfig(pageSize = 20)) {
		CardPagingSource(dataSource, _selectedOptions.value)
	}.flow.cachedIn(viewModelScope)

	fun updateSelectedOptions(index: Int, isSelected: Boolean) {
		val updatedOptions = _selectedOptions.value.toMutableList().apply {
			this[index] = isSelected
		}
		_selectedOptions.value = updatedOptions
	}
}


@Preview
@Composable
private fun Preview(modifier: Modifier = Modifier) {
	CardListPage(CardViewModel(CardDataSource()), modifier)
}

@Composable
internal fun CardListPage(viewModel: CardViewModel, modifier: Modifier = Modifier) {
	val selectedOptions: List<Boolean> by viewModel.selectedOptions.collectAsState()
	val cards = viewModel.pager.collectAsLazyPagingItems()

	Scaffold(modifier) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize()
		) {
			MultiChoiceButtons(
				options = CardLevel.entries.convertToMap { it.name to SegmentedButtonParameter(label = { Text(it.name) }) },
				selectedOptions = selectedOptions,
				onCheckedChange = { index, checked -> viewModel.updateSelectedOptions(index, checked)  }
			)

			LazyColumn {
				items(cards.itemCount, key = { it }) { index ->
					val card = cards[index]
					if (card == null) return@items

					ListItem(
						leadingContent = { Text(card.kanji) },
						overlineContent = { Text(card.translation) },
						headlineContent = { Text(card.kana) },
						trailingContent = { Text(card.level.name) },
						supportingContent = { Text(card.usageType) },
						modifier = Modifier
							.fillParentMaxWidth()
							.padding(horizontal = 8.dp, vertical = 0.dp),
					)
				}
				cards.apply {
					when {
						loadState.refresh is LoadState.Loading -> {
							// Show loading spinner animation
						}
						loadState.append is LoadState.Loading -> {
							// Show loading spinner at the bottom
						}
						loadState.append is LoadState.Error -> {
							// Handle error
						}
					}
				}
			}
		}
	}
}
