package com.upward.jobfinder.backoffice.modules.common.base.lang

import java.time.LocalDate
import java.time.YearMonth

fun LocalDate.monthOfYear() = YearMonth.of(year, month)