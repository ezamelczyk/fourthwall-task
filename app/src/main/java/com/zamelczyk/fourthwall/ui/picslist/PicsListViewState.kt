package com.zamelczyk.fourthwall.ui.picslist

import com.zamelczyk.fourthwall.api.Pic

data class PicsListViewState(
    val loading: Boolean = false,
    val picsList: List<Pic> = emptyList()
)