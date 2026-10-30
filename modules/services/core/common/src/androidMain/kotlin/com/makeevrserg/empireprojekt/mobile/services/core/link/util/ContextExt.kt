package com.makeevrserg.empireprojekt.mobile.services.core.link.util

import android.content.Context
import android.content.Intent
import android.net.Uri

internal fun Context.openInBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
    }
    startActivity(intent)
}
