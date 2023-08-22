package com.kermit.social.data

import android.content.SharedPreferences

class SharedPreferencesMock : SharedPreferences {
    private val prefsMap = mutableMapOf<String, Any?>()
    override fun getAll(): MutableMap<String, *> {
        TODO("Not yet implemented")
    }

    override fun getString(p0: String?, p1: String?): String? {
        TODO("Not yet implemented")
    }

    override fun getStringSet(p0: String?, p1: MutableSet<String>?): MutableSet<String>? {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: String?, p1: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(p0: String?, p1: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(p0: String?, p1: Float): Float {
        TODO("Not yet implemented")
    }

    override fun getBoolean(key: String, defValue: Boolean) = prefsMap.getOrDefault(key, defValue) as Boolean
    override fun contains(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun edit() = FakeEditor(prefsMap)
    override fun registerOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    class FakeEditor(private val prefsMap: MutableMap<String, Any?>) : SharedPreferences.Editor {
        override fun putString(p0: String?, p1: String?): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putStringSet(p0: String?, p1: MutableSet<String>?): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putInt(p0: String?, p1: Int): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putLong(p0: String?, p1: Long): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putFloat(p0: String?, p1: Float): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
            prefsMap[key] = value
            return this
        }

        override fun remove(p0: String?): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun clear(): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun commit(): Boolean {
            TODO("Not yet implemented")
        }

        override fun apply() { }
    }
}