package com.ca.sample.service;

import com.ca.sample.dto.VendingItemDto;

import java.util.List;

public interface VendingService {

    List<VendingItemDto> getlist();

    VendingItemDto deposit(VendingItemDto vendingItemDto);

    VendingItemDto withdraw(VendingItemDto vendingItemDto);
}
