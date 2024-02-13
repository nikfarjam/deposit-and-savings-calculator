package com.mehdi.model

// TODO: It is strongly commanded to use BigDecimal for financial calculation,
// but in order to develop faster, I used Double
data class Investment(
    val startDeposit: Double,
    val interestRate: Double,
    val termInMonth: Int,
    val paymentType: PaymentType
)
