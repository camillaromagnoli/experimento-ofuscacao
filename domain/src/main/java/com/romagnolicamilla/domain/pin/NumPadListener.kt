package com.romagnolicamilla.domain.pin

interface NumPadListener {
    fun onNumberClicked(number: Char)
    fun onEraseClicked()
}