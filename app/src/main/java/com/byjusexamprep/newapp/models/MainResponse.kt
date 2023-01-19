package com.byjusexamprep.newapp.models

data class MainResponse(
    val message: String,
    val response: Response,
    val status: String,
    val type: String
)