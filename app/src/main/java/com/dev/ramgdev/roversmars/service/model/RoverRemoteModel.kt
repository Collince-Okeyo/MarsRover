package com.dev.ramgdev.roversmars.service.model

import com.google.gson.annotations.SerializedName

data class RoverRemoteModel(
    val id: Int,
    @field:SerializedName("landing_date") val landingDate: String,
    @field:SerializedName("launched_date") val launchedDate: String,
    val name: String,
    val status: String
)
