package com.dog.app.util

import android.content.res.Resources
import com.dog.app.R
import okio.IOException
import retrofit2.HttpException

class DefaultErrorHandler(private val resourceManager: Resources) {
    fun getMessage(error: Throwable): String {
        return when (error) {
            is HttpException -> when (error.code()) {
                401 -> resourceManager.getString(R.string.unauthorized_error)
                403 -> resourceManager.getString(R.string.forbidden_error)
                404 -> resourceManager.getString(R.string.not_found_error)
                500 -> resourceManager.getString(R.string.server_error)
                else -> resourceManager.getString(R.string.unknown_error)
            }
            is IOException -> resourceManager.getString(R.string.network_error)
            else -> resourceManager.getString(R.string.unknown_error)
        }
    }
}