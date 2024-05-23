package com.dock.dock.controller;

import com.dock.dock.entity.PortadorEntity;
import com.dock.dock.service.PortadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portador")
public class PortadorController {
    @Autowired
    private PortadorService portadorService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<PortadorEntity> criarPortador(@RequestBody PortadorEntity portadorEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(portadorService.criarPortador(portadorEntity));
    }

    @DeleteMapping
    public ResponseEntity<String> removerPortador(@PathVariable String cpf) {
        try {
            portadorService.removerPortador(cpf);
            return ResponseEntity.ok("Operação realizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar a operação.");
        }
    }

}
