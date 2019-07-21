package com.ca.sample.service.converter;

import com.ca.sample.domain.VendingItem;
import com.ca.sample.dto.VendingItemDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VendingFromDtoConverter implements Converter<VendingItemDto, VendingItem> {

    @Override
    public VendingItem convert(final VendingItemDto source) {

        if (source == null) {
            return null;
        }
        return VendingItem.VendingItemBuilder.aVendingItem()
                .withType(source.getType())
                .build();
    }
}
