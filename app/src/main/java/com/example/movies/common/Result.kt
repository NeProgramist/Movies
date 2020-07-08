package com.example.movies.common


data class Result<out T>(val status: Status, val data: T?, val error: Error?) {
    companion object {
        fun <T> loading(): Result<T> = Result(Status.LOADING, null, null)
        fun <T> success(data: T?): Result<T> = Result(Status.SUCCESS, data, null)
        fun <T> error(error: Error?): Result<T> = Result(Status.ERROR, null, error)
    }

    override fun toString() = when (this.status) {
        Status.LOADING -> "Loading"
        Status.SUCCESS -> "Success[data: $data]"
        Status.ERROR -> "Error[error: $error]"
    }
}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}
