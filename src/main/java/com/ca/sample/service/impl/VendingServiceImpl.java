package com.ca.sample.service.impl;

import com.ca.sample.domain.VendingItem;
import com.ca.sample.dto.VendingItemDto;
import com.ca.sample.exception.VendingNotFoundException;
import com.ca.sample.exception.VendingOutOfCapacityException;
import com.ca.sample.repository.VendingRepository;
import com.ca.sample.service.VendingService;
import com.ca.sample.service.converter.VendingFromDtoConverter;
import com.ca.sample.service.converter.VendingToDtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingServiceImpl implements VendingService {

    private final Logger logger = LoggerFactory.getLogger(VendingServiceImpl.class);

    @Value("${vending.machine.capacity}")
    private Long capacity;

    private final VendingToDtoConverter vendingToDtoConverter;

    private final VendingFromDtoConverter vendingFromDtoConverter;

    private final VendingRepository vendingRepository;

    @Autowired
    public VendingServiceImpl(final VendingToDtoConverter vendingToDtoConverter,
                              final VendingRepository vendingRepository,
                              final VendingFromDtoConverter vendingFromDtoConverter) {
        this.vendingToDtoConverter = vendingToDtoConverter;
        this.vendingRepository = vendingRepository;
        this.vendingFromDtoConverter = vendingFromDtoConverter;
    }

    @Override
    public List<VendingItemDto> getlist() {

        final List<VendingItem> vendings = vendingRepository.findAll();
        return vendingToDtoConverter.convert(vendings);
    }

    @Override
    public VendingItemDto deposit(final VendingItemDto vendingDto) {

        final long count = vendingRepository.countByType(vendingDto.getType());
        if (count >= capacity) {
            throw new VendingOutOfCapacityException();
        }

        final VendingItem vending = vendingFromDtoConverter.convert(vendingDto);
        final VendingItem savedVending = vendingRepository.save(vending);
        vendingDto.setId(savedVending.getId());
        return vendingDto;
    }

    @Override
    public VendingItemDto withdraw(final VendingItemDto vendingItemDto) {

        final VendingItem vending = vendingRepository.findFirstByType(vendingItemDto.getType());
        if (vending == null) {
            throw new VendingNotFoundException();
        }
        vendingRepository.delete(vending.getId());
        return vendingToDtoConverter.convert(vending);
    }

    void setCapacity(final Long capacity) {
        this.capacity = capacity;
    }
}
