package com.mehdi.service

import com.mehdi.model.Saving

class ConsoleReportGenerator : ReportGenerator {
    override fun init() {
    }

    override fun writeHeader(saving: Saving) {
        println("====================== Deposit Saving =======================")
    }

    override fun writeBody(saving: Saving) {
        println("Total Saving is ${saving.finalBalance}")
    }

    override fun writeFooter(saving: Saving) {
        println("=============================================================")
    }

    override fun message(): String {
        return "Write result on the console"
    }
}