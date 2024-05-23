//package com.dock.dock.controller;
//
//import com.dock.dock.entity.ContaEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/contas")
//public class ContaController {
//
//
//    @PostMapping
//    public ResponseEntity<ContaEntity> criarConta(@RequestBody ContaEntity contaEntity) {
//        // Implemente a lógica para criar uma conta aqui
//        return ResponseEntity.ok(conta);
//    }
//
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
//}
