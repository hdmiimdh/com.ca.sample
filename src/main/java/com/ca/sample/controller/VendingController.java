package com.ca.sample.controller;


import com.ca.sample.configuration.swagger.SwaggerConstants;
import com.ca.sample.dto.VendingItemDto;
import com.ca.sample.exception.IllegalVendingTypeException;
import com.ca.sample.service.VendingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "/api", description = "Vending resource.")
public class VendingController {

    private final Logger logger = LoggerFactory.getLogger(VendingController.class);

    private final VendingService vendingService;

    @Autowired
    public VendingController(final VendingService vendingService) {
        this.vendingService = vendingService;
    }

    @ApiOperation(value = "Get list of all items", httpMethod = SwaggerConstants.METHOD_GET)
    @GetMapping(value = "/getlist")
    public List<VendingItemDto> getlist() {

        logger.debug("Get list of all items");
        return vendingService.getlist();
    }

    @ApiOperation(value = "Add an item into the vending machine", httpMethod = SwaggerConstants.METHOD_POST)
    @PostMapping(value = "/deposit")
    public VendingItemDto deposit(@RequestBody @Valid final VendingItemDto vendingItemDto) {

        logger.debug("Add an item into the vending machine {}", vendingItemDto);
        return vendingService.deposit(vendingItemDto);
    }

    @ApiOperation(value = "Withdraw item from the machine by type", httpMethod = SwaggerConstants.METHOD_POST)
    @PostMapping(value = "/withdraw")
    public VendingItemDto withdraw(@RequestBody @Valid final VendingItemDto vendingItemDto) {

        logger.debug("Withdraw item from the machine by type {}", vendingItemDto);
        return vendingService.withdraw(vendingItemDto);
    }
}
