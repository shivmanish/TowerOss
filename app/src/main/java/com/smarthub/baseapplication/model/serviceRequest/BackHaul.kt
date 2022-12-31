package com.smarthub.baseapplication.model.serviceRequest

data class BackHaul(
    val Fiber: List<Fiber>,
    val Microwave: List<Microwave>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)