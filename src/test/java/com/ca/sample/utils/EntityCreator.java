package com.ca.sample.utils;

import com.ca.sample.domain.VendingItem;
import com.ca.sample.dto.VendingItemDto;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public final class EntityCreator {

    private static PodamFactory podamFactory = new PodamFactoryImpl();

    private EntityCreator() {
        throw new AssertionError();
    }

    public static VendingItem vendingItem() {
        return podamFactory.manufacturePojo(VendingItem.class);
    }

    public static VendingItemDto vendingItemDto() {
        return podamFactory.manufacturePojo(VendingItemDto.class);
    }
}
