package com.dock.dock.controller;

import com.dock.dock.controller.dto.conta.ContaResponseDTO;
import com.dock.dock.controller.dto.transacao.TransacaoRequestDTO;
import com.dock.dock.controller.dto.transacao.TransacaoResponseDTO;
import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.domain.entity.enums.TipoTransacao;
import com.dock.dock.service.TransacaoService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ModelMapper mockMapper;
    @MockBean
    private TransacaoService mockTransacaoService;

    @Test
    void testConsultarExtrato() throws Exception {

        final TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setIdTransacao(123);
        transacaoEntity.setDataHoraTransacao(LocalDateTime.of(2022, 1, 1, 10, 30));
        transacaoEntity.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacaoEntity.setValorTransacao(new BigDecimal("100.00"));
        final ContaEntity numeroConta = new ContaEntity();
        numeroConta.setNumero(456);
        transacaoEntity.setNumeroConta(numeroConta);
        final List<TransacaoEntity> transacaoEntities = List.of(transacaoEntity);
        when(mockTransacaoService.consultarExtrato(456, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 1)))
                .thenReturn(transacaoEntities);

        final TransacaoResponseDTO transacaoResponseDTO = new TransacaoResponseDTO();
        transacaoResponseDTO.setIdTransacao(123);
        final ContaResponseDTO numeroConta1 = new ContaResponseDTO();
        numeroConta1.setNumero(456);
        numeroConta1.setAgencia(789);
        numeroConta1.setSaldo(new BigDecimal("5000.00"));
        transacaoResponseDTO.setNumeroConta(numeroConta1);
        when(mockMapper.map(transacaoEntity, TransacaoResponseDTO.class)).thenReturn(transacaoResponseDTO);


        final MockHttpServletResponse response = mockMvc.perform(get("/api/transacao/extrato/{numeroConta}", 456)
                        .param("dataInicio", "2022-01-01")
                        .param("dataFim", "2022-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(List.of(transacaoResponseDTO)));
    }

    @Test
    void testConsultarExtrato_TransacaoServiceReturnsNoItems() throws Exception {

        when(mockTransacaoService.consultarExtrato(456, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 1)))
                .thenReturn(Collections.emptyList());


        final MockHttpServletResponse response = mockMvc.perform(get("/api/transacao/extrato/{numeroConta}", 456)
                        .param("dataInicio", "2022-01-01")
                        .param("dataFim", "2022-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testEfetuarTransacao() throws Exception {

        final TransacaoRequestDTO source = new TransacaoRequestDTO();
        source.setTipoTransacao(TipoTransacao.DEPOSITO);
        source.setValorTransacao(new BigDecimal("200.00"));
        source.setNumeroConta(456);

        final TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setIdTransacao(123);
        transacaoEntity.setDataHoraTransacao(LocalDateTime.now());
        transacaoEntity.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacaoEntity.setValorTransacao(new BigDecimal("200.00"));
        final ContaEntity numeroConta = new ContaEntity();
        numeroConta.setNumero(456);
        transacaoEntity.setNumeroConta(numeroConta);


        final TransacaoEntity transacaoEfetuada = new TransacaoEntity();
        transacaoEfetuada.setIdTransacao(123);
        transacaoEfetuada.setDataHoraTransacao(LocalDateTime.now());
        transacaoEfetuada.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacaoEfetuada.setValorTransacao(new BigDecimal("200.00"));
        transacaoEfetuada.setNumeroConta(numeroConta);

        when(mockTransacaoService.efetuarTransacao(transacaoEntity)).thenReturn(transacaoEfetuada);


        final MockHttpServletResponse response = mockMvc.perform(post("/api/transacao")
                        .content(new ObjectMapper().writeValueAsString(source)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        when(mockMapper.map(source, TransacaoEntity.class)).thenReturn(transacaoEntity);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }
}
