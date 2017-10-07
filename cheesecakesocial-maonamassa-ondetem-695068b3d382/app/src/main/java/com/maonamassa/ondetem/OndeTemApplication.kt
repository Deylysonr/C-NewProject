package com.maonamassa.ondetem

import android.app.Application

import com.crashlytics.android.Crashlytics
import com.google.firebase.auth.FirebaseUser

import io.fabric.sdk.android.Fabric


class OndeTemApplication: Application() {
    var userid: String? = null
    var user: FirebaseUser? = null
    var isAdmin = false

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        instance = this
    }

    companion object {
        var instance: OndeTemApplication? = null
            private set
    }

}

