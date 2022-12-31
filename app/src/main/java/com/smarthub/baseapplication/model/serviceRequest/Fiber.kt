package com.smarthub.baseapplication.model.serviceRequest

data class Fiber(
    val CableLength: String,
    val FiberCore: String,
    val FiberLaying: String,
    val LMLength: String,
    val Remark: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)