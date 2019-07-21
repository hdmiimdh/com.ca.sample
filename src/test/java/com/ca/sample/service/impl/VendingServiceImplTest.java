package com.ca.sample.service.impl;

import com.ca.sample.domain.VendingItem;
import com.ca.sample.dto.VendingItemDto;
import com.ca.sample.exception.VendingNotFoundException;
import com.ca.sample.exception.VendingOutOfCapacityException;
import com.ca.sample.repository.VendingRepository;
import com.ca.sample.service.converter.VendingFromDtoConverter;
import com.ca.sample.service.converter.VendingToDtoConverter;
import com.ca.sample.utils.EntityCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingServiceImplTest {

    private static final long CAPACITY = 5;

    private List<VendingItem> vendings;

    private List<VendingItemDto> vendingDtos;

    private VendingItemDto vendingDto;

    private VendingItem vendingItem;

    @Mock
    private VendingToDtoConverter vendingToDtoConverter;

    @Mock
    private VendingFromDtoConverter vendingFromDtoConverter;

    @Mock
    private VendingRepository vendingRepository;

    @InjectMocks
    private VendingServiceImpl vendingService;

    @Before
    public void setup() {

        vendings = new ArrayList<>();
        vendings.add(EntityCreator.vendingItem());
        vendings.add(EntityCreator.vendingItem());
        vendings.add(EntityCreator.vendingItem());

        vendingDtos = new ArrayList<>();
        vendingDtos.add(EntityCreator.vendingItemDto());
        vendingDtos.add(EntityCreator.vendingItemDto());
        vendingDtos.add(EntityCreator.vendingItemDto());

        vendingService.setCapacity(CAPACITY);
        vendingDto = EntityCreator.vendingItemDto();
        vendingItem = EntityCreator.vendingItem();
    }

    @Test
    public void getlist() {

        when(vendingRepository.findAll()).thenReturn(vendings);
        when(vendingToDtoConverter.convert(vendings)).thenReturn(vendingDtos);

        final List<VendingItemDto> result = vendingService.getlist();

        assertNotNull(result);
        assertEquals(vendingDtos.size(), result.size());
        assertNotNull(result.get(0));
        assertEquals(vendingDtos.get(0).getId(), result.get(0).getId());
        assertEquals(vendingDtos.get(0).getType(), result.get(0).getType());
    }

    @Test(expected = VendingOutOfCapacityException.class)
    public void depositOutOfCapacity() {

        when(vendingRepository.countByType(vendingDto.getType())).thenReturn(CAPACITY + 1);
        vendingService.deposit(vendingDto);
    }

    @Test
    public void deposit() {

        when(vendingRepository.countByType(vendingDto.getType())).thenReturn(CAPACITY - 1);
        when(vendingFromDtoConverter.convert(vendingDto)).thenReturn(vendingItem);
        when(vendingRepository.save(vendingItem)).thenReturn(vendingItem);

        final VendingItemDto result = vendingService.deposit(vendingDto);

        assertNotNull(result);
        assertEquals(vendingItem.getId(), result.getId());
    }

    @Test(expected = VendingNotFoundException.class)
    public void withdrawVendingNotFound() {

        vendingService.withdraw(vendingDto);
    }

    @Test
    public void withdraw() {

        when(vendingRepository.findFirstByType(vendingDto.getType())).thenReturn(vendingItem);
        when(vendingToDtoConverter.convert(vendingItem)).thenReturn(vendingDto);

        final VendingItemDto result = vendingService.withdraw(vendingDto);

        assertNotNull(result);
        assertEquals(vendingDto.getId(), result.getId());
        assertEquals(vendingDto.getType(), result.getType());

        verify(vendingRepository).delete(vendingItem.getId());
    }
}