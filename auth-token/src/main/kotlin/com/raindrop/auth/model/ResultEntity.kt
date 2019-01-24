package com.raindrop.auth.model

import java.io.Serializable

data class ResultEntity(
        var code: Int = 200,
        var message: String = "success",
        var data: Any = ""
) : Serializable

fun buildResultEntity(builder: ResultEntity.() -> Unit) = ResultEntity().apply(builder)
