package com.api.Controller;

import com.api.Exception.GlobalException;
import com.api.Model.Employee;
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
@RequestMapping("/api/funcionario")
public class EmployeeController {
    private final EmployeeService employeeService;
    private GlobalException ge;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Listar todos os funcionários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<EmployeeResponseDTO>> listarFuncionarios() {
        List<EmployeeResponseDTO> employees = employeeService.listEmployee();
        return ResponseEntity.ok(employees);
    }



    @PostMapping("/inserir")
    @Operation(summary = "Inserir um novo funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário inserido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> inserirFuncionario(@RequestBody EmployeeRequestDTO employee) {
        employeeService.insertEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionario inserido com sucesso!!");
    }



    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Funcionario excluído com sucesso!");
    }



    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO employeeAtualizado) {
        employeeService.updateEmployee(id, employeeAtualizado);
        return ResponseEntity.ok("Funcionario atualizado com sucesso!");
    }



    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente um funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> atualizarFuncionarioParcial(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) {
        employeeService.updateEmployeePartial(id, updates);
        return ResponseEntity.ok("Funcionario atualizado parcialmente com sucesso!");
    }

}
