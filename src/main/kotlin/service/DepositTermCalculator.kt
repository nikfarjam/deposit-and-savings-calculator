package com.mehdi.service

import com.mehdi.model.Investment
import com.mehdi.model.Saving

class DepositTermCalculator : InterestCalculator {
    override fun calculate(investment: Investment): Saving {
        TODO("Not yet implemented")
        return Saving(0.0, 0.0)
    }
}