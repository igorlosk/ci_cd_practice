package com.loskutnikov.ci_cd.api;

import com.loskutnikov.ci_cd.domain.DbPhoneService;
import com.loskutnikov.ci_cd.domain.Phone;
import com.loskutnikov.ci_cd.domain.PhoneToDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;


import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhoneController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DbPhoneService dbPhoneService;

    @MockitoBean
    private PhoneToDtoMapper phoneToDtoMapper;

    @BeforeEach
    void setUp() {
        reset(dbPhoneService, phoneToDtoMapper);
    }

    @Test
    void getById_success() throws Exception {
        Long id = 1L;
        Phone phone = new Phone(id, "Apple", "17", BigDecimal.valueOf(100));
        PhoneDto dto = new PhoneDto(id, "Apple", "17", BigDecimal.valueOf(100));

        when(dbPhoneService.getById(id)).thenReturn(phone);
        when(phoneToDtoMapper.toDto(phone)).thenReturn(dto);

        mockMvc.perform(get("/api/phone/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id.intValue())) // Long в JSON становится int
                .andExpect(jsonPath("$.brandName").value("Apple"))
                .andExpect(jsonPath("$.model").value("17"))
                .andExpect(jsonPath("$.price").value(100.0));

        verify(dbPhoneService).getById(id);
        verify(phoneToDtoMapper).toDto(phone);
        verifyNoMoreInteractions(dbPhoneService, phoneToDtoMapper);
    }

    @Test
    void getById_notFound() throws Exception {
        Long id = 999L;

        when(dbPhoneService.getById(id)).thenThrow(new RuntimeException("Phone not found"));

        mockMvc.perform(get("/api/phones/{id}", id))
                .andExpect(status().isNotFound()); // или isInternalServerError() в зависимости от обработки
    }
}