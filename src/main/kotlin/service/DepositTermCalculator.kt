package com.mehdi.service

import com.mehdi.model.Investment
import com.mehdi.model.Saving

class DepositTermCalculator : InterestCalculator {
    override fun calculate(investment: Investment): Saving {
        return Saving(investment.interestRate, 0.0)
    }
}