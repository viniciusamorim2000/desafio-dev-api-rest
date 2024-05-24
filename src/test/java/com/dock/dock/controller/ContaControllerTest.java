package com.dock.dock.controller;

import com.dock.dock.controller.dto.conta.ContaResponseDTO;
import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.service.ContaService;
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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContaController.class)
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService mockContaService;
    @MockBean
    private ModelMapper mockMapper;

    @Test
    void testCriarConta() throws Exception {
        // Setup
        final ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNumero(123456);
        contaEntity.setAgencia(7890);
        contaEntity.setSaldo(new BigDecimal("1000.00"));
        contaEntity.setAtiva(true);
        final PortadorEntity portadorEntity = new PortadorEntity();
        contaEntity.setPortadorEntity(portadorEntity);
        when(mockContaService.criarConta("12345678901")).thenReturn(contaEntity);

        final ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
        contaResponseDTO.setNumero(123456);
        contaResponseDTO.setAgencia(7890);
        contaResponseDTO.setSaldo(new BigDecimal("1000.00"));
        when(mockMapper.map(contaEntity, ContaResponseDTO.class)).thenReturn(contaResponseDTO);


        final MockHttpServletResponse response = mockMvc.perform(post("/api/contas")
                        .param("cpf", "12345678901")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(contaResponseDTO));
    }

    @Test
    void testConsultarConta() throws Exception {

        final ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNumero(123456);
        contaEntity.setAgencia(7890);
        contaEntity.setSaldo(new BigDecimal("1000.00"));
        contaEntity.setAtiva(true);
        final PortadorEntity portadorEntity = new PortadorEntity();
        contaEntity.setPortadorEntity(portadorEntity);
        when(mockContaService.consultaContaPorCpf("12345678901")).thenReturn(contaEntity);

        final ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
        contaResponseDTO.setNumero(123456);
        contaResponseDTO.setAgencia(7890);
        contaResponseDTO.setSaldo(new BigDecimal("1000.00"));
        when(mockMapper.map(contaEntity, ContaResponseDTO.class)).thenReturn(contaResponseDTO);


        final MockHttpServletResponse response = mockMvc.perform(get("/api/contas/{cpf}", "12345678901")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(contaResponseDTO));
    }

    @Test
    void testAlterarStatusDaConta() throws Exception {

        final ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNumero(123456);
        contaEntity.setAgencia(7890);
        contaEntity.setSaldo(new BigDecimal("1000.00"));
        contaEntity.setAtiva(true);
        final PortadorEntity portadorEntity = new PortadorEntity();
        contaEntity.setPortadorEntity(portadorEntity);
        when(mockContaService.alterarStatusDaConta("12345678901", false)).thenReturn(contaEntity);

        final ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
        contaResponseDTO.setNumero(123456);
        contaResponseDTO.setAgencia(7890);
        contaResponseDTO.setSaldo(new BigDecimal("1000.00"));
        when(mockMapper.map(contaEntity, ContaResponseDTO.class)).thenReturn(contaResponseDTO);


        final MockHttpServletResponse response = mockMvc.perform(put("/api/contas/{cpf}", "12345678901")
                        .param("statusConta", "false")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("O Status da conta: [123456], vinculada ao CPF: [12345678901], foi alterada para: [bloqueada].");
    }
}
