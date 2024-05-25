package com.dock.dock.controller;

import com.dock.dock.controller.dto.portador.PortadorRequestDTO;
import com.dock.dock.controller.dto.portador.PortadorResponseDTO;
import com.dock.dock.domain.entity.PortadorEntity;
import com.dock.dock.service.PortadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portador")
@Tag(name = "Portadores", description = "API cadastro de portadores.")
public class PortadorController {
    @Autowired
    private PortadorService portadorService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    @Operation(summary = "Cria um novo portador.", tags = "Portadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Portador criado com sucesso.",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "Portador criado", value = "{\"nome\": \"João Ribeiro\", \"cpf\": \"55727146070\"}")
                    }))})
    public ResponseEntity<PortadorResponseDTO> criarPortador(@Parameter(description = "Dados do portador a ser criado")
                                                                 @RequestBody PortadorRequestDTO portadorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(mapper.map(portadorService.
                        criarPortador(mapper.map(portadorRequestDTO, PortadorEntity.class)), PortadorResponseDTO.class));
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Remove um portador existente, desde que não haja conta vinculada a ele.", tags = "Portadores")
    @ApiResponse(responseCode = "200", description = "Portador removido com sucesso.",
            content = @Content(mediaType = "text/plain", examples = {
                    @ExampleObject(name = "Portador removido", value = "Operação realizada com sucesso.")
            }))
    public ResponseEntity<String> removerPortador(@Parameter(description = "CPF do portador a ser removido", example = "50729057054")
                                                      @PathVariable String cpf) {
            portadorService.removerPortador(cpf);
            return ResponseEntity.ok("Operação realizada com sucesso.");
        }
    }


