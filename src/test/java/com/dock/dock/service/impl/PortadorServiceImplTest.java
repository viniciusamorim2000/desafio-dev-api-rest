package com.dock.dock.service.impl;

import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.repository.PortadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortadorServiceImplTest {

    @Mock
    private PortadorRepository mockPortadorRepository;

    private PortadorServiceImpl portadorServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        portadorServiceImplUnderTest = new PortadorServiceImpl(mockPortadorRepository);
    }

    @Test
    void testCriarPortador_PortadorRepositoryFindByCpf() {

        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setCpf("23136168003");
        portadorEntity.setNomeCompleto("João da Silva");

        final PortadorEntity expectedResult = new PortadorEntity();
        expectedResult.setCpf("23136168003");
        expectedResult.setNomeCompleto("João da Silva");

        when(mockPortadorRepository.findByCpf("23136168003")).thenReturn(Optional.empty());

        final PortadorEntity portadorEntity1 = new PortadorEntity();
        portadorEntity1.setCpf("23136168003");
        portadorEntity1.setNomeCompleto("João da Silva");
        final PortadorEntity entity = new PortadorEntity();
        entity.setCpf("23136168003");
        entity.setNomeCompleto("João da Silva");
        when(mockPortadorRepository.save(entity)).thenReturn(portadorEntity1);


        final PortadorEntity result = portadorServiceImplUnderTest.criarPortador(portadorEntity);


        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testRemoverPortador() {

        final PortadorEntity portadorEntity1 = new PortadorEntity();
        portadorEntity1.setCpf("64315840076");
        portadorEntity1.setNomeCompleto("João da Silva");
        final Optional<PortadorEntity> portadorEntity = Optional.of(portadorEntity1);
        when(mockPortadorRepository.findByCpf("64315840076")).thenReturn(portadorEntity);


        portadorServiceImplUnderTest.removerPortador("64315840076");


        final PortadorEntity entity = new PortadorEntity();
        entity.setCpf("64315840076");
        entity.setNomeCompleto("João da Silva");
        verify(mockPortadorRepository).delete(entity);
    }


    @Test
    void testValidaCPFIsFalse() {
        assertThat(portadorServiceImplUnderTest.validaCPF("12312321312321")).isFalse();
    }

    @Test
    void testValidaCPFIsTrue() {
        assertThat(portadorServiceImplUnderTest.validaCPF("47639537029")).isTrue();
    }

    @Test
    void testGetPortadorByCpf() {
        // Setup
        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setCpf("47639537029");
        portadorEntity.setNomeCompleto("João da Silva");
        final Optional<PortadorEntity> expectedResult = Optional.of(portadorEntity);

        final PortadorEntity portadorEntity2 = new PortadorEntity();
        portadorEntity2.setCpf("47639537029");
        portadorEntity2.setNomeCompleto("João da Silva");
        final Optional<PortadorEntity> portadorEntity1 = Optional.of(portadorEntity2);
        when(mockPortadorRepository.findByCpf("47639537029")).thenReturn(portadorEntity1);

        final Optional<PortadorEntity> result = portadorServiceImplUnderTest.getPortadorByCpf("47639537029");

        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testGetPortadorByCpf_PortadorRepository() {

        when(mockPortadorRepository.findByCpf("23136168003")).thenReturn(Optional.empty());
        final Optional<PortadorEntity> result = portadorServiceImplUnderTest.getPortadorByCpf("23136168003");
        assertThat(result).isEmpty();
    }
}
