package com.mehdi

import com.mehdi.error.CalculatorException
import com.mehdi.model.Investment
import com.mehdi.model.PaymentType
import com.mehdi.service.ConsoleReportGenerator
import com.mehdi.service.DepositTermCalculator
import com.mehdi.service.ReportGenerator
import com.mehdi.service.Reporter
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.system.exitProcess

private val logger = LoggerFactory.getLogger("Main")

fun main(args: Array<String>) {
    logger.info("App started")
    val investment: Investment = try {
        if (args.size > 1 && "-f".contentEquals(args[0])) {
            loadInvestmentFromFile(args[1])
        } else {
            readUserInputFormConsole()
        }
    } catch (ex: Exception) {
        logger.warn("Error in getting user input ${ex.message}")
        exitProcess(1)
    }

    val calculator = createCalculator("depositTerm")
    val saving = calculator.calculate(investment)
    val reporter = createReporter()
    reporter.showResult(saving)
}

private fun readUserInputFormConsole(): Investment {
    val questions = mapOf<String, String>(
        START_DEPOSIT to """
            Enter the following to calculate your saving
            Starting deposit amount: (0)
        """.trimIndent(),
        INTEREST_RATE to "Interest rate p.a for example 1%: (0%)",
        TERM to "Investment term in months (minimum 3):",
        PAYMENT_TYPE to "Interest paid at: (1 for Monthly, 2 for Quarterly, 3 for Annually, 4 for AtMaturity)"
    )
    val commands = mutableListOf<String>()
    for (key in questions.keys) {
        println("${questions[key]}")
        val answer = readlnOrNull()
        commands.add("$key:$answer")
    }
    return createInvestmentFromInputs(commands)
}

private fun loadInvestmentFromFile(filename: String): Investment {
    logger.info("Try to load input form $filename")
    if (filename.isBlank()) {
        throw CalculatorException("File name is blank")
    }
    val path = Paths.get(filename)
    if (!path.exists()) {
        throw CalculatorException("File does not exist")
    }
    if (!path.toFile().canRead()) {
        throw CalculatorException("File is not readable")
    }

    val lines = Files.readAllLines(path)
    logger.info("Loaded $path")

    return createInvestmentFromInputs(lines)
}

private fun createReporter() = Reporter(listOf<ReportGenerator>(ConsoleReportGenerator()))

private fun createCalculator(type: String) = DepositTermCalculator()

