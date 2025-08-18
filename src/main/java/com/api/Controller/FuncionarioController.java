package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Employee;
import com.api.Service.EmployeeService;
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
    private final EmployeeService employeeService;
    private GlobalException ge;

    @Autowired
    public FuncionarioController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<Employee>> listarFuncionarios() {
        // Se lançar uma RuntimeException aqui, o Spring vai chamar o método do GlobalException automaticamente
        List<Employee> employees = employeeService.listarFuncionario();
        return ResponseEntity.ok(employees);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirFuncionario(@RequestBody Employee employee) {
        Employee employeeSalvo = employeeService.inserirFuncionario(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionario inserido com sucesso! ID: " + employeeSalvo.getId());
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
        employeeService.excluirFuncionario(id);
        return ResponseEntity.ok("Funcionario excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Employee employeeAtualizado) {
        employeeService.atualizarFuncionario(id, employeeAtualizado);
        return ResponseEntity.ok("Funcionario atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarFuncionarioParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        employeeService.atualizarFuncionarioParcial(id, updates);
        return ResponseEntity.ok("Funcionario atualizado parcialmente com sucesso!");
    }

}
