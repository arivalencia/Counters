package com.ari.counters.framework

import android.content.Intent
import com.ari.counters.CountersApp
import com.ari.counters.R
import com.ari.counters.domain.contracts.ShareData
import javax.inject.Inject

class ShareTextPlain constructor(
    private val info: String
    //@ApplicationContext private val context: Context
): ShareData {

    override suspend fun share() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, info)
        val intent = Intent.createChooser(shareIntent, CountersApp.context.getString(R.string.share_info))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        CountersApp.context.startActivity(intent)
    }

}