package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.domain.model.PickerOption
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlin.math.absoluteValue

@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun WheelPicker(
    options:            List<PickerOption>,
    count:              Int,
    rowCount:           Int,
    modifier:           Modifier                            = Modifier,
    startIndex:         Int                                 = 0,
    height:             Dp                                  = 34.dp,
    selectorProperties: SelectorProperties                  = WheelPickerDefaults.selectorProperties(),
    onScrollFinished:  (PickerOption, Int)          -> Unit,
    content: @Composable LazyItemScope.(index: Int) -> Unit,
) {
    val lazyListState      = rememberLazyListState(startIndex)
    val snapperLayoutInfo  = rememberLazyListSnapperLayoutInfo(lazyListState = lazyListState)
    val isScrollInProgress = lazyListState.isScrollInProgress

    LaunchedEffect(isScrollInProgress, count) {
        if (!isScrollInProgress) {
            val snappedItemIndex = calculateSnappedItemIndex(snapperLayoutInfo) ?: startIndex
            onScrollFinished(options[snappedItemIndex], snappedItemIndex)
            lazyListState.scrollToItem(snappedItemIndex)
        }
    }

    Box(
        modifier         = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (selectorProperties.enabled().value)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / rowCount),
                shape    = selectorProperties.shape().value,
                color    = selectorProperties.color().value,
                border   = selectorProperties.border().value,
            ) {}
        LazyColumn(
            modifier       = Modifier
                      .fillMaxWidth()
                      .height(height)
                      .padding(horizontal = 16.dp),
            state          = lazyListState,
            contentPadding = PaddingValues(vertical = height / rowCount * ((rowCount - 1) / 2)),
            flingBehavior  = rememberSnapperFlingBehavior(lazyListState = lazyListState),
        ) {
            items(count) { index ->
                val rotationX = calculateAnimatedRotationX(
                    lazyListState     = lazyListState,
                    snapperLayoutInfo = snapperLayoutInfo,
                    index             = index,
                    rowCount          = rowCount,
                )
                Box(
                    modifier         = Modifier
                                .fillMaxWidth()
                                .height(height / rowCount)
                                .alpha(
                                    calculateAnimatedAlpha(
                                        lazyListState     = lazyListState,
                                        snapperLayoutInfo = snapperLayoutInfo,
                                        index             = index,
                                        rowCount          = rowCount,
                                    )
                                )
                                .graphicsLayer { this.rotationX = rotationX },
                    contentAlignment = Alignment.Center
                ) {
                    content(index)
                }
            }
        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
private fun calculateSnappedItemIndex(snapperLayoutInfo: SnapperLayoutInfo): Int? {
    var currentItemIndex = snapperLayoutInfo.currentItem?.index

    if (snapperLayoutInfo.currentItem?.offset != 0)
        if (currentItemIndex != null)
            currentItemIndex++

    return currentItemIndex
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
private fun calculateAnimatedAlpha(
    lazyListState:     LazyListState,
    snapperLayoutInfo: SnapperLayoutInfo,
    index:             Int,
    rowCount:          Int,
): Float {

    val distanceToIndexSnap  = snapperLayoutInfo.distanceToIndexSnap(index).absoluteValue
    val layoutInfo           = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val viewPortHeight       = layoutInfo.viewportSize.height.toFloat()
    val singleViewPortHeight = viewPortHeight / rowCount

    return if (distanceToIndexSnap in 0..singleViewPortHeight.toInt())
        1.2f - (distanceToIndexSnap / singleViewPortHeight)
    else 0.2f
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
private fun calculateAnimatedRotationX(
    lazyListState:     LazyListState,
    snapperLayoutInfo: SnapperLayoutInfo,
    index:             Int,
    rowCount:          Int,
): Float {

    val distanceToIndexSnap  = snapperLayoutInfo.distanceToIndexSnap(index)
    val layoutInfo           = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val viewPortHeight       = layoutInfo.viewportSize.height.toFloat()
    val singleViewPortHeight = viewPortHeight / rowCount
    val animatedRotationX    = -20f * (distanceToIndexSnap / singleViewPortHeight)

    return if (animatedRotationX.isNaN()) 0f else animatedRotationX
}

object WheelPickerDefaults {

    @Composable
    fun selectorProperties(
        enabled: Boolean       = true,
        shape:   Shape         = RoundedCornerShape(16.dp),
        color:   Color         = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
        border:  BorderStroke? = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    ): SelectorProperties = DefaultSelectorProperties(
        enabled = enabled,
        shape   = shape,
        color   = color,
        border  = border,
    )

}

interface SelectorProperties {

    @Composable
    fun enabled(): State<Boolean>

    @Composable
    fun shape():   State<Shape>

    @Composable
    fun color():   State<Color>

    @Composable
    fun border():  State<BorderStroke?>

}

@Immutable
internal class DefaultSelectorProperties(
    private val enabled: Boolean,
    private val shape:   Shape,
    private val color:   Color,
    private val border:  BorderStroke?,
) : SelectorProperties {

    @Composable
    override fun enabled(): State<Boolean> =
        rememberUpdatedState(enabled)

    @Composable
    override fun shape():   State<Shape> =
        rememberUpdatedState(shape)

    @Composable
    override fun color():   State<Color> =
        rememberUpdatedState(color)

    @Composable
    override fun border():  State<BorderStroke?> =
        rememberUpdatedState(border)

}
