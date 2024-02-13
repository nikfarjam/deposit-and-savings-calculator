package com.mehdi.model


// TODO: It is strongly commanded to use BigDecimal for financial calculation,
// but in order to develop faster, I used Double
data class Saving(
    val interestRate: Double,
    val finalBalance: Double
)
