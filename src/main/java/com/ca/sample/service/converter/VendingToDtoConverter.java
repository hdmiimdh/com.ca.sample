package com.ca.sample.service.converter;

import com.ca.sample.domain.VendingItem;
import com.ca.sample.dto.VendingItemDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendingToDtoConverter implements Converter<VendingItem, VendingItemDto> {

    @Override
    public VendingItemDto convert(final VendingItem source) {

        if (source == null) {
            return null;
        }
        return VendingItemDto.VendingItemDtoBuilder.aVendingItemDto()
                .withId(source.getId())
                .withType(source.getType())
                .build();
    }

    public List<VendingItemDto> convert(final List<VendingItem> vendingItems) {

        if (vendingItems == null) {
            return Collections.emptyList();
        }
        return vendingItems.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
