package com.mehdi.service

import com.mehdi.model.Investment
import com.mehdi.model.Saving

fun interface InterestCalculator {
    fun calculate(investment: Investment): Saving
}