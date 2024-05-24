package com.dock.dock.controller;

import com.dock.dock.controller.dto.portador.PortadorRequestDTO;
import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.service.PortadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PortadorController.class)
class PortadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortadorService mockPortadorService;
    @MockBean
    private ModelMapper mockMapper;

    @Test
    void testCriarPortador() throws Exception {
        // Setup
        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setCpf("123.456.789-00");
        portadorEntity.setNomeCompleto("João da Silva");
        final PortadorRequestDTO source = new PortadorRequestDTO();
        source.setCpf("123.456.789-00");
        source.setNomeCompleto("João da Silva");
        when(mockMapper.map(source, PortadorEntity.class)).thenReturn(portadorEntity);

        // Configure PortadorService.criarPortador(...).
        when(mockPortadorService.criarPortador(portadorEntity)).thenReturn(portadorEntity);

        // Configure ModelMapper.map(...) for the response.
        when(mockMapper.map(portadorEntity, PortadorRequestDTO.class)).thenReturn(source);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/portador")
                        .content(new ObjectMapper().writeValueAsString(source)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(new ObjectMapper().writeValueAsString(portadorEntity)).isEqualTo(new ObjectMapper().writeValueAsString(source));
    }


    @Test
    void testRemoverPortador() throws Exception {
        // Setup
        String cpf = "123.456.789-00";
        doNothing().when(mockPortadorService).removerPortador(cpf);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/portador/{cpf}", cpf)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(mockPortadorService).removerPortador(cpf);
    }
}
