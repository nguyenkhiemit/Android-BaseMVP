package com.jobs.basemvp.data.prefs

/**
 * Created by Khiem on 12/6/2016.
 */
import android.content.Context
import android.content.SharedPreferences
import com.jobs.basemvp.utils.Constants

class PrefsUtils(private val mContext: Context) {

    companion object {
        val TOKEN_KEY = "token_key"
    }

    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    val token: String
        get() = getPref(TOKEN_KEY)

    init {
        val prefsFile = mContext.packageName
        sharedPreferences = mContext.getSharedPreferences(prefsFile, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun delete(key: String) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit()
        }
    }

    fun savePref(key: String, value: Any?) {
        delete(key)
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value != null) {
            throw RuntimeException()
        }
        editor.commit()
    }

    fun <T> getPref(key: String): T {
        return sharedPreferences.all[key] as T
    }

    fun <T> getPref(key: String, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T?
        return returnValue ?: defValue
    }

    fun checkPrefExits(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

}
