package com.eea_tech_interview.service.extensions

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
     class OnIdeal<T>()
     class OnSuccess<T>(data: T?) : Resource<T>(data)
     class OnFailure<T>(error: T? = null) : Resource<T>(error)
     class OnLoading<T>() : Resource<T>()
}
