package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.OpenAPI.EmployeeOpenApi;
import com.api.Service.EmployeeService;
import com.api.dto.employee.EmployeeRequestDTO;
import com.api.dto.employee.EmployeeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController implements EmployeeOpenApi {
    private final EmployeeService employeeService;
    private GlobalException ge;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<EmployeeResponseDTO>> listarFuncionarios() {
        List<EmployeeResponseDTO> employees = employeeService.listEmployee();
        return ResponseEntity.ok(employees);
    }



    @PostMapping("/inserir")
    public ResponseEntity<?> inserirFuncionario(@RequestBody EmployeeRequestDTO employee) {
        employeeService.insertEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionario inserido com sucesso!!");
    }



    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Funcionario excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO employeeAtualizado) {
        employeeService.updateEmployee(id, employeeAtualizado);
        return ResponseEntity.ok("Funcionario atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarFuncionarioParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        employeeService.updateEmployeePartial(id, updates);
        return ResponseEntity.ok("Funcionario atualizado parcialmente com sucesso!");
    }

}
