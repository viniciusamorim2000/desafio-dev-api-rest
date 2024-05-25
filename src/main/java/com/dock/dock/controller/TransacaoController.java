package com.dock.dock.controller;

import com.dock.dock.controller.dto.transacao.TransacaoRequestDTO;
import com.dock.dock.controller.dto.transacao.TransacaoResponseDTO;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacao")
@Tag(name = "Transações", description = "API cadastro de transações.")
public class TransacaoController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/extrato/{numeroConta}")
    @Operation(summary = "Consulta extrato de portador por número da conta entre um período e outro.", tags = "Transações")
    public ResponseEntity<List<TransacaoResponseDTO>> consultarExtrato(@Parameter(description = "Número da conta", example = "275824")
                                                                           @PathVariable Integer numeroConta,
                                                                       @Parameter(description = "Data inicial", example = "2024-01-01")
                                                                       @RequestParam LocalDate dataInicio,
                                                                       @Parameter(description = "Data fim", example = "2024-04-29")
                                                                           @RequestParam LocalDate dataFim) {
        List<TransacaoEntity> transacoes = transacaoService.consultarExtrato(numeroConta, dataInicio, dataFim);
        List<TransacaoResponseDTO> dtoList = transacoes.stream()
                .map(transacao -> mapper.map(transacao, TransacaoResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping
    @Operation(summary = "Efetua transações de saque ou deposito.", tags = "Transações")
    public ResponseEntity<TransacaoResponseDTO> efetuarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(mapper.map(transacaoService.
                        efetuarTransacao(mapper.map(transacaoRequestDTO, TransacaoEntity.class)), TransacaoResponseDTO.class));
    }

}
