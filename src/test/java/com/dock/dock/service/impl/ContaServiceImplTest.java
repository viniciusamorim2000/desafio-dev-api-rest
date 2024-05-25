package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.ContaEntity;
import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContaServiceImplTest {

    @InjectMocks
    private ContaServiceImpl contaService;

    @Mock
    private PortadorServiceImpl portadorService;

    @Mock
    private ContaRepository contaRepository;

    @Test
    public void testConsultaContaPorCpf() {
        String cpf = "12345678901";
        PortadorEntity portador = new PortadorEntity();
        portador.setCpf(cpf);
        ContaEntity conta = new ContaEntity();
        conta.setPortadorEntity(portador);

        when(portadorService.validaCPF(anyString())).thenReturn(true);
        when(portadorService.getPortadorByCpf(anyString())).thenReturn(Optional.of(portador));
        when(contaRepository.findByPortadorEntity(any(PortadorEntity.class))).thenReturn(Optional.of(conta));

        ContaEntity contaConsultada = contaService.consultaContaPorCpf(cpf);

        assertEquals(conta, contaConsultada);
    }

}
