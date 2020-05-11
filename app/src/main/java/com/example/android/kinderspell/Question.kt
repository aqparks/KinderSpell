package com.example.android.kinderspell

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Question(@DrawableRes val drawableResId: Int, val answer: String)