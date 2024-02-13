package com.mehdi

import com.mehdi.error.CalculatorException
import com.mehdi.model.Investment
import com.mehdi.model.PaymentType
import model.InvestmentBuilder
import org.slf4j.LoggerFactory

const val START_DEPOSIT: String = "start_deposit"
const val INTEREST_RATE: String = "interest_rate"
const val TERM: String = "term"
const val PAYMENT_TYPE: String = "payment_type"

private val logger = LoggerFactory.getLogger("Util")

fun createInvestmentFromInputs(list: List<String>): Investment {
    val builder = InvestmentBuilder.anInvestment()
        .withStartDeposit(0.0)
        .withPaymentType(PaymentType.Monthly)
        .withInterestRate(0.0)
        .withTermInMonth(3)

    for (str in list) {
        if (str.startsWith("#") || !str.contains(":")) {
            continue
        }
        val parts = str.split(":")
        if (parts.size < 2) {
            continue
        }
        val key = parts[0].trim().lowercase()
        val value = parts[1].trim().lowercase()
        fillInvestment(key, builder, value)

    }

    val investment = builder.build()
    logger.info("Investment is $investment")
    return investment
}

/**
 * TODO: This could be done by Command Pattern
 */
private fun fillInvestment(key: String, builder: InvestmentBuilder, value: String) {
    when (key) {
        START_DEPOSIT -> try {
            builder.withStartDeposit(value.toDouble())
        } catch (e: Exception) {
            logger.warn("Invalid $START_DEPOSIT")
        }

        INTEREST_RATE -> try {
            builder.withInterestRate(value.replace("%", "").toDouble() / 100)
        } catch (e: Exception) {
            logger.warn("Invalid $INTEREST_RATE")
        }

        TERM -> try {
            builder.withTermInMonth(value.toInt())
        } catch (e: Exception) {
            logger.warn("Invalid $TERM")
        }

        PAYMENT_TYPE -> try {
            builder.withPaymentType(createPaymentType(value))
        } catch (e: Exception) {
            logger.warn("Invalid $TERM")
        }
    }
}

fun createPaymentType(value: String): PaymentType {
    return when (value) {
        "1" -> PaymentType.Monthly
        "2" -> PaymentType.Quarterly
        "3" -> PaymentType.Annually
        "4" -> PaymentType.AtMaturity
        else -> throw CalculatorException("Invalid payment type")
    }
}