package com.cinema.fastfurious.infraestructure.exception

import com.cinema.fastfurious.domain.DomainException
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ServerWebInputException
import java.lang.IllegalArgumentException

@Component
class ExceptionHandler : DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any> {
        val map = super.getErrorAttributes(request, options)
        val ex = getError(request)
        val httpStatus = if (isHandledException(ex)) HttpStatus.BAD_REQUEST else HttpStatus.INTERNAL_SERVER_ERROR
        map["status"] = httpStatus.value()
        map["error"] = httpStatus.reasonPhrase
        map["message"] = ex.message
        return map
    }

    private fun isHandledException(ex: Throwable) =
        ex is DomainException || ex is IllegalArgumentException || ex is ServerWebInputException
}