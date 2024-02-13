package com.mehdi

import com.mehdi.model.Investment
import com.mehdi.model.PaymentType
import com.mehdi.service.ConsoleReportGenerator
import com.mehdi.service.DepositTermCalculator
import com.mehdi.service.ReportGenerator
import com.mehdi.service.Reporter
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Main")

fun main() {
    logger.info("App started")

    val calculator = createCalculator("depositTerm")
    val saving = calculator.calculate(Investment(10000.0, 0.011, 36, PaymentType.Monthly))
    val reporter = createReporter()
    reporter.showResult(saving)
}

private fun createReporter() = Reporter(listOf<ReportGenerator>(ConsoleReportGenerator()))

private fun createCalculator(type: String) = DepositTermCalculator()