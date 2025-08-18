package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Employee;
import com.api.Service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    private GlobalException ge;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Employee>> listarFuncionarios() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Employee> employees = funcionarioService.listarFuncionario();
        return ResponseEntity.ok(employees);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirFuncionario(@RequestBody Employee employee) {
        Employee employeeSalvo = funcionarioService.inserirFuncionario(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionario inserido com sucesso! ID: " + employeeSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
        return ResponseEntity.ok("Funcionario excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Employee employeeAtualizado) {
        funcionarioService.atualizarFuncionario(id, employeeAtualizado);
        return ResponseEntity.ok("Funcionario atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarFuncionarioParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        funcionarioService.atualizarFuncionarioParcial(id, updates);
        return ResponseEntity.ok("Funcionario atualizado parcialmente com sucesso!");
    }

}
