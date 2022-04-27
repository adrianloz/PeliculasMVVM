package com.smartsoft.peliculasmvvm.models

import java.io.Serializable

data class NowPlaying(
    var page: Int = 0,
    var results: List<Result>? = null,
    var total_pages: Int = 0,
    var total_results: Int = 0
): Serializable