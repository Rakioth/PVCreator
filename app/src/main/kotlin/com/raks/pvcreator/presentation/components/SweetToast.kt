package com.raks.pvcreator.presentation.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.radusalagean.infobarcompose.InfoBar
import com.radusalagean.infobarcompose.InfoBarMessage
import com.raks.pvcreator.R

@Composable
fun BoxScope.SweetToast(
    paddingValues: PaddingValues,
    message:       InfoBarMessage?,
    onDismiss:     () -> Unit,
    modifier:      Modifier        = Modifier,
    padding:       Dp              = 15.dp,
) {
    InfoBar(
        modifier        = modifier
                   .padding(
                       start  = padding,
                       top    = padding + paddingValues.calculateTopPadding(),
                       end    = padding,
                       bottom = padding + paddingValues.calculateBottomPadding()
                   )
                   .align(Alignment.BottomCenter),
        offeredMessage  = message,
        content         = {
            Row {
                Icon(
                    modifier           = Modifier
                                  .padding(8.dp)
                                  .align(Alignment.CenterVertically),
                    imageVector        = Icons.Rounded.Info,
                    contentDescription = stringResource(R.string.label_info),
                    tint               = MaterialTheme.colorScheme.primary,
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text     = stringResource(R.string.label_theme),
                    color    = MaterialTheme.colorScheme.primary,
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.inversePrimary,
        onDismiss       = onDismiss,
    )
}
