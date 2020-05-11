package com.example.android.kinderspell

private const val MP3 = ".mp3"

class Sound(val assetPath: String, var soundId: Int? = null) {
    val name = assetPath.split("/").last().removeSuffix(MP3)
}