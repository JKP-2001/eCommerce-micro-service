package com.org.microservice.order.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}