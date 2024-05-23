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

//    @GetMapping("/{id}")
//    public ResponseEntity<Conta> consultarConta(@PathVariable Long id) {
//        // Implemente a lógica para consultar uma conta aqui
//        return ResponseEntity.ok(new Conta());
//    }
//
//    @GetMapping("/{id}/extrato")
//    public ResponseEntity<List<Transacao>> consultarExtrato(@PathVariable Long id, @RequestParam("inicio") String inicio, @RequestParam("fim") String fim) {
//        // Implemente a lógica para consultar o extrato de uma conta aqui
//        return ResponseEntity.ok(new ArrayList<>());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> fecharConta(@PathVariable Long id) {
//        // Implemente a lógica para fechar uma conta aqui
//        return ResponseEntity.noContent().build();
//    }
}
