package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.repository.PortadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortadorServiceImplTest {

    @InjectMocks
    private PortadorServiceImpl portadorService;

    @Mock
    private PortadorRepository portadorRepository;

    @Test
    public void testCriarPortador() {
        PortadorEntity portador = new PortadorEntity();
        portador.setCpf("47701386017");

        when(portadorRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(portadorRepository.save(any(PortadorEntity.class))).thenReturn(portador);

        PortadorEntity savedPortador = portadorService.criarPortador(portador);

        assertEquals(portador, savedPortador);
        verify(portadorRepository, times(1)).findByCpf(anyString());
        verify(portadorRepository, times(1)).save(any(PortadorEntity.class));
    }

    @Test
    public void testRemoverPortador() {
        PortadorEntity portador = new PortadorEntity();
        portador.setCpf("47701386017");

        when(portadorRepository.findByCpf(anyString())).thenReturn(Optional.of(portador));

        portadorService.removerPortador(portador.getCpf());

        verify(portadorRepository, times(1)).delete(any(PortadorEntity.class));
    }
}
