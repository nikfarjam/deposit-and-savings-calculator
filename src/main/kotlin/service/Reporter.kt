package com.mehdi.service

import com.mehdi.error.CalculatorException
import com.mehdi.model.Saving

class Reporter(private val generators: List<ReportGenerator>) {

    fun showResult(saving: Saving): List<String> {
        val messages = mutableListOf<String>()

        for (reportGenerator in generators) {
            try {
                reportGenerator.init()
                reportGenerator.writeHeader(saving)
                reportGenerator.writeBody(saving)
                reportGenerator.writeFooter(saving)
                messages.add("Successful ${reportGenerator.message()}")
            } catch (ex: CalculatorException) {
                messages.add(ex.message.toString())
            }
        }

        return messages
    }
}