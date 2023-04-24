package com.romagnolicamilla.domain.pin

import android.content.SharedPreferences

interface EncryptSharedPreferences {
    fun getSharedPreferences(): SharedPreferences
}