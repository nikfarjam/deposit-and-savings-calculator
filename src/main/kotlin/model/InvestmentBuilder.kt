package model

import com.mehdi.model.Investment
import com.mehdi.model.PaymentType

class InvestmentBuilder private constructor() {
    private var startDeposit = 0.0
    private var interestRate = 0.0
    private var termInMonth = 0
    private var paymentType: PaymentType? = null

    fun withStartDeposit(startDeposit: Double): InvestmentBuilder {
        this.startDeposit = startDeposit
        return this
    }

    fun withInterestRate(interestRate: Double): InvestmentBuilder {
        this.interestRate = interestRate
        return this
    }

    fun withTermInMonth(termInMonth: Int): InvestmentBuilder {
        this.termInMonth = termInMonth
        return this
    }

    fun withPaymentType(paymentType: PaymentType?): InvestmentBuilder {
        this.paymentType = paymentType
        return this
    }

    fun build(): Investment {
        return Investment(startDeposit, interestRate, termInMonth, paymentType!!)
    }

    companion object {
        fun anInvestment(): InvestmentBuilder {
            return InvestmentBuilder()
        }
    }
}
