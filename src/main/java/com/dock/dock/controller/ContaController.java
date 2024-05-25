package com.dock.dock.controller;

import com.dock.dock.controller.dto.conta.ContaResponseDTO;
import com.dock.dock.service.ContaService;
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
@RequestMapping("/api/contas")
@Tag(name = "Contas", description = "API cadastro de contas.")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    @Operation(summary = "Cria uma nova conta.", tags = "Contas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta criada com sucesso",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "Conta criada", value = "{\"numero\": \"456723\", \"saldo\": 1000.0, \"agencia\": \"0001\"}")
                    }))
    })
    public ResponseEntity<ContaResponseDTO> criarConta(@Parameter(description = "CPF do portador já existente.", example = "50729057054")
                                                       @RequestParam String cpf) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.map(contaService.criarConta(cpf), ContaResponseDTO.class));
    }


    @GetMapping("/{cpf}")
    @Operation(summary = "Retorna as informações da conta.", tags = "Contas")
    @ApiResponse(responseCode = "200", description = "Informações da conta retornadas com sucesso",
            content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "Conta encontrada", value = "{\"numero\": \"456723\", \"saldo\": 1000.0, \"agencia\": \"0001\"}")
            }))
    public ResponseEntity<ContaResponseDTO> consultarConta(@Parameter(description = "CPF do portador já com a conta criada.",
            example = "50729057054")
                                                           @PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).
                body(mapper.map(contaService.
                        consultaContaPorCpf(cpf), ContaResponseDTO.class));
    }

    @PutMapping("/{cpf}")
    @Operation(summary = "Altera o status da conta, para bloqueada ou desbloqueada.", tags = "Contas")
    @ApiResponse(responseCode = "200", description = "Status da conta alterado com sucesso",
            content = @Content(mediaType = "text/plain", examples = {
                    @ExampleObject(name = "Status alterado", value = "O Status da conta: [12345], vinculada ao CPF: [50729057054], foi alterada para: [desbloqueada].")
            }))
    public ResponseEntity<String> alterarStatusDaConta(@Parameter(description = "CPF do portador já com a conta criada.", example = "50729057054")
                                                       @PathVariable String cpf,
                                                       @Parameter(description = "Status que deseja alterar.", example = "true")
                                                       @RequestParam Boolean statusConta) {
        ContaResponseDTO contaResponseDTO = mapper.map(contaService.alterarStatusDaConta(cpf, statusConta), ContaResponseDTO.class);
        String status = statusConta ? "desbloqueada" : "bloqueada";
        return ResponseEntity.ok(String.format("O Status da conta: [%s], vinculada ao CPF: [%s], foi alterada para: [%s].",
                contaResponseDTO.getNumero(), cpf, status));
    }


}
