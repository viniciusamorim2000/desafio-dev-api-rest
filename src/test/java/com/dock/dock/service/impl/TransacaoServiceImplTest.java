package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.domain.entity.enums.TipoTransacao;
import com.dock.dock.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceImplTest {

    @InjectMocks
    private TransacaoServiceImpl transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private ContaServiceImpl contaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConsultarExtrato() {
        Integer numeroConta = 123;
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = LocalDate.now();
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        when(transacaoRepository.findByDataHoraTransacaoBetweenAndNumeroConta(any(), any(), any()))
                .thenReturn(Collections.singletonList(transacaoEntity));

        var result = transacaoService.consultarExtrato(numeroConta, dataInicio, dataFim);

        assertEquals(1, result.size());
        assertEquals(transacaoEntity, result.get(0));
    }

    @Test
    public void testEfetuarTransacao() {
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacaoEntity.setValorTransacao(BigDecimal.TEN);

        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNumero(123123);
        contaEntity.setSaldo(BigDecimal.TEN);

        transacaoEntity.setNumeroConta(contaEntity); // Corrigido aqui

        when(contaService.consultaContaPorNumeroConta(123123)).thenReturn(contaEntity);
        when(transacaoRepository.save(any(TransacaoEntity.class))).thenReturn(transacaoEntity);


        TransacaoEntity result = transacaoService.efetuarTransacao(transacaoEntity);

        assertEquals(transacaoEntity, result);
        verify(contaService, times(1)).salvarConta(contaEntity);
    }

}
