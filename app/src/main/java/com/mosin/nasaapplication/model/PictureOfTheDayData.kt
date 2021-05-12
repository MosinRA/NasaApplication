package com.mosin.nasaapplication.model

import com.mosin.nasaapplication.api.ResponseData

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: ResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}