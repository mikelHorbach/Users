package com.aitgamesstudio.users

import android.content.Context
import androidx.annotation.Nullable
import androidx.loader.content.AsyncTaskLoader


class BookLoader internal constructor(
    context: Context?
) :
    AsyncTaskLoader<String?>(context!!) {
    protected override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    @Nullable
    override fun loadInBackground(): String? {
        return NetworkUtils.getInfo()
    }

}