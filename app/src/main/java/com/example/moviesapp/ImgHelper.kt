package com.example.moviesapp

import android.net.Uri

object ImgHelper {

    fun getImageUri(imageName: String?): Uri {
        return Uri.parse(Constants.IMAGE_BASE_URL + imageName)
    }

}