package com.amar.sitambol.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.longPref
import hu.autsoft.krate.stringPref

class PrefManager(context: Context): Krate {

    private val PREF_LOGIN: String = "pref_login"
    private val PREF_ID: String = "pref_id"
    private val PREF_USERNAME: String = "pref_name"
    private val PREF_EMAIL: String = "pref_email"
    private val PREF_PASSWORD: String = "pref_password"
    private val PREF_IMAGE: String = "pref_image"

    override val sharedPreferences: SharedPreferences = context.applicationContext.getSharedPreferences(
        "sitambol", Context.MODE_PRIVATE
    )

    var prefLogin by booleanPref(PREF_LOGIN, false)
    var prefId by longPref(PREF_ID, 0L)
    var prefUsername by stringPref(PREF_USERNAME, "")
    var prefEmail by stringPref(PREF_EMAIL, "")
    var prefPassword by stringPref(PREF_PASSWORD, "")
    var prefImage by stringPref(PREF_IMAGE, "")

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

}