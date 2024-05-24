package com.dock.dock.controller;

import com.dock.dock.controller.dto.conta.ContaResponseDTO;
import com.dock.dock.service.ContaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> criarConta(@RequestParam String cpf) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(mapper.map(contaService.
                        criarConta(cpf), ContaResponseDTO.class));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ContaResponseDTO> consultarConta(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).
                body(mapper.map(contaService.
                        consultaContaPorCpf(cpf), ContaResponseDTO.class));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<String> alterarStatusDaConta(@PathVariable String cpf, @RequestParam Boolean statusConta) {
        ContaResponseDTO contaResponseDTO = mapper.map(contaService.alterarStatusDaConta(cpf, statusConta), ContaResponseDTO.class);
        String status = statusConta ? "desbloqueada" : "bloqueada";
        return ResponseEntity.ok(String.format("O Status da conta: [%s], vinculada ao CPF: [%s], foi alterada para: [%s].",
                contaResponseDTO.getNumero(), cpf, status));
    }


}
