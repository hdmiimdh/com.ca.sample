package com.ca.sample.controller;

import com.ca.sample.dto.VendingItemDto;
import com.ca.sample.service.VendingService;
import com.ca.sample.utils.EntityCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class VendingControllerTest {

    private MockMvc mockMvc;

    private List<VendingItemDto> vendingItemDtos;

    private VendingItemDto vendingItemDto;

    @Mock
    private VendingService vendingService;

    @InjectMocks
    private VendingController vendingController;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(vendingController).build();

        vendingItemDtos = new ArrayList<>();
        vendingItemDtos.add(EntityCreator.vendingItemDto());
        vendingItemDtos.add(EntityCreator.vendingItemDto());
        vendingItemDtos.add(EntityCreator.vendingItemDto());

        vendingItemDto = EntityCreator.vendingItemDto();
    }

    @Test
    public void getlist() throws Exception {

        when(vendingService.getlist()).thenReturn(vendingItemDtos);

        mockMvc.perform(get("/api/getlist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(vendingItemDtos.size())))
                .andExpect(jsonPath("$[0].id", is(vendingItemDtos.get(0).getId())))
                .andExpect(jsonPath("$[0].type", is(vendingItemDtos.get(0).getType().name())));
    }

    @Test
    public void deposit() throws Exception {

        final String body = new ObjectMapper().writeValueAsString(vendingItemDto);

        when(vendingService.deposit(vendingItemDto)).thenReturn(vendingItemDto);

        mockMvc.perform(post("/api/deposit").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(vendingItemDto.getId())))
                .andExpect(jsonPath("$.type", is(vendingItemDto.getType().name())));
    }

    @Test
    public void withdraw() throws Exception {

        final String body = new ObjectMapper().writeValueAsString(vendingItemDto);

        when(vendingService.withdraw(vendingItemDto)).thenReturn(vendingItemDto);

        mockMvc.perform(post("/api/withdraw").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(vendingItemDto.getId())))
                .andExpect(jsonPath("$.type", is(vendingItemDto.getType().name())));
    }
}