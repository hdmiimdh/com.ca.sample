package com.ca.sample.controller;

import com.ca.sample.dto.ErrorMessageDto;
import com.ca.sample.exception.IllegalVendingTypeException;
import com.ca.sample.exception.VendingNotFoundException;
import com.ca.sample.exception.VendingOutOfCapacityException;
import com.ca.sample.utils.MessagesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    private final MessagesUtils messagesUtils;

    @Autowired
    public ExceptionHandlerController(final MessagesUtils messagesUtils) {
        this.messagesUtils = messagesUtils;
    }

    @ExceptionHandler({IllegalVendingTypeException.class, VendingOutOfCapacityException.class,
            HttpMessageConversionException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleVendingExceptions(final RuntimeException e) {

        logger.error("Vending exception: {}", e.getMessage());

        final ErrorMessageDto errorMessage = new ErrorMessageDto(
                messagesUtils.getMessage("vending.machine.violation"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({VendingNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDto> handleVendingOutOfItemException(final VendingNotFoundException e) {

        logger.error("VendingNotFoundException: {}", e.getMessage());

        final ErrorMessageDto errorMessage = new ErrorMessageDto(
                messagesUtils.getMessage("vending.machine.item.not.found.violation"));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
