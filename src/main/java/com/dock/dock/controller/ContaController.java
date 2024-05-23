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


//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> fecharConta(@PathVariable Long id) {
//        // Implemente a lógica para fechar uma conta aqui
//        return ResponseEntity.noContent().build();
//    }
}
