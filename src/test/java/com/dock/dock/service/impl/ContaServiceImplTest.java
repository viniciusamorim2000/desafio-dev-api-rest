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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContaServiceImplTest {

    @Mock
    private PortadorServiceImpl mockPortadorService;
    @Mock
    private ContaRepository mockContaRepository;

    @InjectMocks
    private ContaServiceImpl contaServiceImplUnderTest;

    @Test
    void testCriarConta_PortadorServiceImplValidaCPFReturnsFalse() {
        // Setup
        when(mockPortadorService.validaCPF("47639537029")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> contaServiceImplUnderTest.criarConta("47639537029"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testCriarConta_PortadorServiceImplGetPortadorByCpf() {

        when(mockPortadorService.validaCPF("47639537029")).thenReturn(true);
        when(mockPortadorService.getPortadorByCpf("47639537029")).thenReturn(Optional.empty());


        assertThatThrownBy(() -> contaServiceImplUnderTest.criarConta("47639537029"))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void testConsultaContaPorCpf() {

        final ContaEntity expectedResult = new ContaEntity();
        expectedResult.setNumero(12345);
        expectedResult.setAgencia(678);
        expectedResult.setSaldo(new BigDecimal("1000.00"));
        expectedResult.setAtiva(true);
        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setCpf("123.456.789-00");
        portadorEntity.setNomeCompleto("João da Silva");
        expectedResult.setPortadorEntity(portadorEntity);

        when(mockPortadorService.validaCPF("123.456.789-00")).thenReturn(true);


        final PortadorEntity portadorEntity2 = new PortadorEntity();
        portadorEntity2.setCpf("123.456.789-00");
        portadorEntity2.setNomeCompleto("João da Silva");
        final Optional<PortadorEntity> portadorEntity1 = Optional.of(portadorEntity2);
        when(mockPortadorService.getPortadorByCpf("123.456.789-00")).thenReturn(portadorEntity1);


        final ContaEntity contaEntity1 = new ContaEntity();
        contaEntity1.setNumero(12345);
        contaEntity1.setAgencia(678);
        contaEntity1.setSaldo(new BigDecimal("1000.00"));
        contaEntity1.setAtiva(true);
        final PortadorEntity portadorEntity3 = new PortadorEntity();
        portadorEntity3.setCpf("123.456.789-00");
        portadorEntity3.setNomeCompleto("João da Silva");
        contaEntity1.setPortadorEntity(portadorEntity3);
        final Optional<ContaEntity> contaEntity = Optional.of(contaEntity1);
        final PortadorEntity portadorEntity4 = new PortadorEntity();
        portadorEntity4.setCpf("123.456.789-00");
        portadorEntity4.setNomeCompleto("João da Silva");
        when(mockContaRepository.findByPortadorEntity(portadorEntity4)).thenReturn(contaEntity);


        final ContaEntity result = contaServiceImplUnderTest.consultaContaPorCpf("123.456.789-00");


        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testConsultaContaPorCpf_PortadorServiceImplValidaCPFReturnsFalse() {

        when(mockPortadorService.validaCPF("90862973074")).thenReturn(false);

        assertThatThrownBy(() -> contaServiceImplUnderTest.consultaContaPorCpf("90862973074"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConsultaContaPorCpf_PortadorServiceImplGetPortadorByCpfReturnsAbsent() {

        when(mockPortadorService.validaCPF("90862973074")).thenReturn(true);
        when(mockPortadorService.getPortadorByCpf("90862973074")).thenReturn(Optional.empty());


        assertThatThrownBy(() -> contaServiceImplUnderTest.consultaContaPorCpf("90862973074"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testConsultaContaPorCpf_ContaRepositoryReturnsAbsent() {

        when(mockPortadorService.validaCPF("90862973074")).thenReturn(true);

        final PortadorEntity portadorEntity1 = new PortadorEntity();
        portadorEntity1.setCpf("90862973074");
        portadorEntity1.setNomeCompleto("João da Silva");
        final Optional<PortadorEntity> portadorEntity = Optional.of(portadorEntity1);
        when(mockPortadorService.getPortadorByCpf("90862973074")).thenReturn(portadorEntity);

        final PortadorEntity portadorEntity2 = new PortadorEntity();
        portadorEntity2.setCpf("90862973074");
        portadorEntity2.setNomeCompleto("João da Silva");
        when(mockContaRepository.findByPortadorEntity(portadorEntity2)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> contaServiceImplUnderTest.consultaContaPorCpf("90862973074"))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void testAlterarStatusDaConta() {

        final ContaEntity expectedResult = new ContaEntity();
        expectedResult.setNumero(12345);
        expectedResult.setAgencia(678);
        expectedResult.setSaldo(new BigDecimal("1000.00"));
        expectedResult.setAtiva(false);
        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setCpf("35413175078");
        portadorEntity.setNomeCompleto("João da Silva");
        expectedResult.setPortadorEntity(portadorEntity);

        when(mockPortadorService.validaCPF("35413175078")).thenReturn(true);


        final PortadorEntity portadorEntity2 = new PortadorEntity();
        portadorEntity2.setCpf("35413175078");
        portadorEntity2.setNomeCompleto("João da Silva");
        final Optional<PortadorEntity> portadorEntity1 = Optional.of(portadorEntity2);
        when(mockPortadorService.getPortadorByCpf("35413175078")).thenReturn(portadorEntity1);


        final ContaEntity contaEntity1 = new ContaEntity();
        contaEntity1.setNumero(12345);
        contaEntity1.setAgencia(678);
        contaEntity1.setSaldo(new BigDecimal("1000.00"));
        contaEntity1.setAtiva(true);
        final PortadorEntity portadorEntity3 = new PortadorEntity();
        portadorEntity3.setCpf("35413175078");
        portadorEntity3.setNomeCompleto("João da Silva");
        contaEntity1.setPortadorEntity(portadorEntity3);
        final Optional<ContaEntity> contaEntity = Optional.of(contaEntity1);
        final PortadorEntity portadorEntity4 = new PortadorEntity();
        portadorEntity4.setCpf("35413175078");
        portadorEntity4.setNomeCompleto("João da Silva");
        when(mockContaRepository.findByPortadorEntity(portadorEntity4)).thenReturn(contaEntity);


        final ContaEntity result = contaServiceImplUnderTest.alterarStatusDaConta("35413175078", false);


        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testAlterarStatusDaConta_PortadorServiceImplValidaCPFReturnsFalse() {

        when(mockPortadorService.validaCPF("54103202041")).thenReturn(false);


        assertThatThrownBy(() -> contaServiceImplUnderTest.alterarStatusDaConta("54103202041", false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAlterarStatusDaContaPortadorServiceImplGetPortadorByCpf() {
        // Setup
        when(mockPortadorService.validaCPF("54103202041")).thenReturn(true);
        when(mockPortadorService.getPortadorByCpf("54103202041")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> contaServiceImplUnderTest.alterarStatusDaConta("54103202041", false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAlterarStatusDaConta_ContaRepository() {
        // Setup
        when(mockPortadorService.validaCPF("54103202041")).thenReturn(true);

        // Configure PortadorServiceImpl.getPortadorByCpf(...).
        final PortadorEntity portadorEntity1 = new PortadorEntity();
        portadorEntity1.setCpf("54103202041");
        portadorEntity1.setNomeCompleto("João Silva");
        final Optional<PortadorEntity> portadorEntity = Optional.of(portadorEntity1);
        when(mockPortadorService.getPortadorByCpf("54103202041")).thenReturn(portadorEntity);

        // Configure ContaRepository.findByPortadorEntity(...).
        final PortadorEntity portadorEntity2 = new PortadorEntity();
        portadorEntity2.setCpf("54103202041");
        portadorEntity2.setNomeCompleto("João Silva");
        when(mockContaRepository.findByPortadorEntity(portadorEntity2)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> contaServiceImplUnderTest.alterarStatusDaConta("54103202041", false))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void testConsultaContaPorNumeroConta() {
        final ContaEntity expectedResult = new ContaEntity();
        expectedResult.setNumero(12345);
        expectedResult.setAgencia(678);
        expectedResult.setSaldo(new BigDecimal("1000.00"));
        expectedResult.setAtiva(true);
        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setNomeCompleto("João Silva");
        expectedResult.setPortadorEntity(portadorEntity);

        final ContaEntity contaEntity1 = new ContaEntity();
        contaEntity1.setNumero(12345);
        contaEntity1.setAgencia(678);
        contaEntity1.setSaldo(new BigDecimal("1000.00"));
        contaEntity1.setAtiva(true);
        final PortadorEntity portadorEntity1 = new PortadorEntity();
        portadorEntity1.setNomeCompleto("João Silva");
        contaEntity1.setPortadorEntity(portadorEntity1);
        final Optional<ContaEntity> contaEntity = Optional.of(contaEntity1);
        when(mockContaRepository.findByNumero(12345)).thenReturn(contaEntity);

        final ContaEntity result = contaServiceImplUnderTest.consultaContaPorNumeroConta(12345);

        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testSalvarConta() {
        final ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNumero(12345);
        contaEntity.setAgencia(678);
        contaEntity.setSaldo(new BigDecimal("1000.00"));
        contaEntity.setAtiva(true);
        final PortadorEntity portadorEntity = new PortadorEntity();
        portadorEntity.setNomeCompleto("João Silva");
        contaEntity.setPortadorEntity(portadorEntity);

        final ContaEntity expectedResult = new ContaEntity();
        expectedResult.setNumero(12345);
        expectedResult.setAgencia(678);
        expectedResult.setSaldo(new BigDecimal("1000.00"));
        expectedResult.setAtiva(true);
        final PortadorEntity portadorEntity1 = new PortadorEntity();
        portadorEntity1.setNomeCompleto("João Silva");
        expectedResult.setPortadorEntity(portadorEntity1);

        final ContaEntity contaEntity1 = new ContaEntity();
        contaEntity1.setNumero(12345);
        contaEntity1.setAgencia(678);
        contaEntity1.setSaldo(new BigDecimal("1000.00"));
        contaEntity1.setAtiva(true);
        final PortadorEntity portadorEntity2 = new PortadorEntity();
        portadorEntity2.setNomeCompleto("João Silva");
        contaEntity1.setPortadorEntity(portadorEntity2);
        when(mockContaRepository.save(contaEntity)).thenReturn(contaEntity1);

        final ContaEntity result = contaServiceImplUnderTest.salvarConta(contaEntity);

        assertThat(result).isEqualTo(expectedResult);
    }


}
