package com.dock.dock.controller;

import com.dock.dock.controller.dto.transacao.TransacaoRequestDTO;
import com.dock.dock.controller.dto.transacao.TransacaoResponseDTO;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.service.TransacaoService;
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
public class TransacaoController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/extrato/{numeroConta}")
    public ResponseEntity<List<TransacaoResponseDTO>> consultarExtrato(@PathVariable Integer numeroConta,
                                                                     @RequestParam LocalDate dataInicio,
                                                                     @RequestParam LocalDate dataFim) {
        List<TransacaoEntity> transacoes = transacaoService.consultarExtrato(numeroConta, dataInicio, dataFim);
        List<TransacaoResponseDTO> dtoList = transacoes.stream()
                .map(transacao -> mapper.map(transacao, TransacaoResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> efetuarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(mapper.map(transacaoService.
                        efetuarTransacao(mapper.map(transacaoRequestDTO, TransacaoEntity.class)), TransacaoResponseDTO.class));
    }

}
