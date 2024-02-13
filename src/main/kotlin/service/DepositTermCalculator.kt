package com.mehdi.service

import com.mehdi.model.Investment
import com.mehdi.model.PaymentType
import com.mehdi.model.Saving
import java.math.BigDecimal
import java.math.RoundingMode


class DepositTermCalculator : InterestCalculator {
    /**
     * Calculate Deposit Term by this formula
     * •  Monthly: F = P * (1 + r / 12) ^ t
     * •  Quarterly: F = P * (1 + r / 4) ^ (t / 3)
     * •  Annually: F = P * (1 + r) ^ (t / 12)
     * •  At Maturity: F = P * (1 + r * t / 12)
     * Where:
     * •  F is the final balance
     * •  P is the start deposit amount
     * •  r is the interest rate p.a
     * •  t is the investment term in years
     */
    override fun calculate(investment: Investment): Saving {
        val finalBalance: Double = when (investment.paymentType) {
            PaymentType.Monthly -> investment.startDeposit *
                    Math.pow(1 + investment.interestRate / 12, investment.termInMonth.toDouble())

            PaymentType.Quarterly -> investment.startDeposit *
                    Math.pow(1 + investment.interestRate / 4, investment.termInMonth.toDouble() / 3)

            PaymentType.Annually -> investment.startDeposit *
                    Math.pow(1 + investment.interestRate, investment.termInMonth.toDouble() / 12)

            PaymentType.AtMaturity -> investment.startDeposit *
                    (1 + investment.interestRate * investment.termInMonth.toDouble() / 12)
        }
        return Saving(investment.interestRate, BigDecimal(finalBalance).setScale(0, RoundingMode.HALF_UP).toDouble())
    }
}