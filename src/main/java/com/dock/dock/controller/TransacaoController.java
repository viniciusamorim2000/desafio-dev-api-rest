package com.dock.dock.controller;

import com.dock.dock.controller.dto.extrato.ExtratoResponseDTO;
import com.dock.dock.controller.dto.transacao.TransacaoRequestDTO;
import com.dock.dock.controller.dto.transacao.TransacaoResponseDTO;
import com.dock.dock.domain.entity.TransacaoEntity;
import com.dock.dock.service.TransacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/extrato/{cpf}")
    public ResponseEntity<List<ExtratoResponseDTO>> consultarExtrato(@PathVariable String cpf,
                                                                     @RequestParam LocalDateTime dataIncio,
                                                                     @RequestParam LocalDateTime dataFim) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonList(mapper.map(transacaoService.
                        consultarExtrato(cpf, dataIncio, dataFim), ExtratoResponseDTO.class)));


    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> efetuarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(mapper.map(transacaoService.
                        efetuarTransacao(mapper.map(transacaoRequestDTO, TransacaoEntity.class)), TransacaoResponseDTO.class));
    }

}
