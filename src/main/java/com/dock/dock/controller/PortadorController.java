package com.dock.dock.controller;

import com.dock.dock.controller.dto.portador.PortadorRequestDTO;
import com.dock.dock.controller.dto.portador.PortadorResponseDTO;
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
    public ResponseEntity<PortadorResponseDTO> criarPortador(@RequestBody PortadorRequestDTO portadorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(mapper.map(portadorService.
                        criarPortador(mapper.map(portadorRequestDTO, PortadorEntity.class)), PortadorResponseDTO.class));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> removerPortador(@PathVariable String cpf) {
            portadorService.removerPortador(cpf);
            return ResponseEntity.ok("Operação realizada com sucesso.");
        }
    }


