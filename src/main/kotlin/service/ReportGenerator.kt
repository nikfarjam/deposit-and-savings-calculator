package com.mehdi.service

import com.mehdi.model.Saving

interface ReportGenerator {

    fun init()

    fun writeHeader(saving: Saving)

    fun writeBody(saving: Saving)

    fun writeFooter(saving: Saving)

    fun message(): String
}