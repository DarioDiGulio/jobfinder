package com.upward.jobfinder.backoffice.modules.common.base.lang

fun String.splitWithoutPrefix(value: String) = this.removePrefix(value).split(value)
