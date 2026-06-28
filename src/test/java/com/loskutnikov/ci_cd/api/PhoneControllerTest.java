package com.loskutnikov.ci_cd.api;


import com.loskutnikov.ci_cd.domain.DbPhoneService;
import com.loskutnikov.ci_cd.domain.Phone;
import com.loskutnikov.ci_cd.domain.PhoneToDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhoneController.class)
class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DbPhoneService dbPhoneService;

    @MockitoBean
    private PhoneToDtoMapper phoneToDtoMapper;

    @BeforeEach
    void setUp() {
        // Сброс моков перед каждым тестом
        reset(dbPhoneService, phoneToDtoMapper);
    }

    @Test
    void getById_success() throws Exception {
        Long id = 1L;
        Phone phone = new Phone(id, "Apple", "17", BigDecimal.valueOf(100));
        PhoneDto dto = new PhoneDto(id, "Apple", "17", BigDecimal.valueOf(100)); // конструктор/builder под твой DTO

        when(dbPhoneService.getById(id)).thenReturn(phone);
        when(phoneToDtoMapper.toDto(phone)).thenReturn(dto);

        mockMvc.perform(get("/phones/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.brandName").value("Apple"))
                .andExpect(jsonPath("$.model").value("17"))
                .andExpect(jsonPath("$.price").value(100));

        // verify
        verify(dbPhoneService).getById(id);
        verify(phoneToDtoMapper).toDto(phone);
        verifyNoMoreInteractions(dbPhoneService, phoneToDtoMapper);
    }
}