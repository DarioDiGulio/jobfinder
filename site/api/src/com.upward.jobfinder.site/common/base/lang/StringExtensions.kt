package com.upward.jobfinder.site.common.base.lang

fun String.splitWithoutPrefix(value: String) = this.removePrefix(value).split(value)
