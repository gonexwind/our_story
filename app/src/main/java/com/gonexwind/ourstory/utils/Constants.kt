package com.gonexwind.ourstory.utils

object Constants {
    const val BASE_URL = "https://story-api.dicoding.dev/v1"
    const val LOGIN_URL = "/login"
    const val REGISTER_URL = "/register"
    const val STORY_URL = "/stories"
}

enum class AppStatus { LOADING, ERROR, DONE }