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
import androidx.compose.ui.unit.DpSize
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
    modifier:           Modifier           = Modifier,
    size:               DpSize             = DpSize(128.dp, 128.dp),
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onScrollFinished: (snappedOption: PickerOption?) -> Unit = {},
    content: @Composable LazyItemScope.(index: Int)  -> Unit,
) {
    val lazyListState      = rememberLazyListState()
    val snapperLayoutInfo  = rememberLazyListSnapperLayoutInfo(lazyListState = lazyListState)
    val isScrollInProgress = lazyListState.isScrollInProgress

    LaunchedEffect(isScrollInProgress, options) {
        if (!isScrollInProgress) {
            val snappedItemIndex = calculateSnappedItemIndex(snapperLayoutInfo)
            val snappedOption    = snappedItemIndex?.let { options.getOrNull(it) }
            onScrollFinished(snappedOption)
            snappedItemIndex?.let { lazyListState.scrollToItem(it) }
        }
    }

    Box(
        modifier         = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (selectorProperties.enabled().value) {
            Surface(
                modifier = Modifier.size(size),
                shape    = selectorProperties.shape().value,
                color    = selectorProperties.color().value,
                border   = selectorProperties.border().value,
            ) {}
        }
        LazyColumn(
            modifier       = Modifier.size(size),
            state          = lazyListState,
            contentPadding = PaddingValues(vertical = size.height),
            flingBehavior  = rememberSnapperFlingBehavior(
                lazyListState = lazyListState
            ),
        ) {
            items(options.size) { index ->
                val rotationX = calculateAnimatedRotationX(
                    lazyListState     = lazyListState,
                    snapperLayoutInfo = snapperLayoutInfo,
                    index             = index,
                )
                Box(
                    modifier         = Modifier
                        .size(size)
                        .alpha(
                            calculateAnimatedAlpha(
                                lazyListState     = lazyListState,
                                snapperLayoutInfo = snapperLayoutInfo,
                                index             = index,
                            )
                        )
                        .graphicsLayer {
                            this.rotationX = rotationX
                        },
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
): Float {

    val distanceToIndexSnap = snapperLayoutInfo.distanceToIndexSnap(index).absoluteValue
    val layoutInfo          = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val viewPortHeight      = layoutInfo.viewportSize.height.toFloat()

    return if (distanceToIndexSnap in 0..viewPortHeight.toInt())
        1.2f - (distanceToIndexSnap / viewPortHeight)
    else 0.2f
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
private fun calculateAnimatedRotationX(
    lazyListState:     LazyListState,
    snapperLayoutInfo: SnapperLayoutInfo,
    index:             Int,
): Float {

    val distanceToIndexSnap = snapperLayoutInfo.distanceToIndexSnap(index)
    val layoutInfo          = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val viewPortHeight      = layoutInfo.viewportSize.height.toFloat()

    return -20f * (distanceToIndexSnap / viewPortHeight)
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