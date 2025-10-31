package com.api.controller;

import com.api.dto.admin.AdminRequestDTO;
import com.api.dto.admin.AdminResponseDTO;
import com.api.dto.employee.EmployeeRequestDTO;
import com.api.dto.employee.EmployeeResponseDTO;
import com.api.exception.GlobalException;
import com.api.openapi.AdminOpenApi;
import com.api.repository.AdminRepository;
import com.api.service.AdminService;
import com.api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController implements AdminOpenApi {
    private final AdminService adminService;
    private GlobalException ge;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/selecionar")
    public ResponseEntity<List<AdminResponseDTO>> listarAdmin() {
        List<AdminResponseDTO> admins = adminService.listAdmin();
        return ResponseEntity.ok(admins);
    }
    @PostMapping("/inserir")
    public ResponseEntity<?> inserirAdmin(@RequestBody AdminRequestDTO admin) {
        adminService.insertAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Admin inserido com sucesso!!");
    }
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin excluído com sucesso!");
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminRequestDTO adminAtualizado) {
        adminService.updateAdmin(id, adminAtualizado);
        return ResponseEntity.ok("Admin atualizado com sucesso!");
    }
    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarAdminParcial(@PathVariable Long id,@RequestBody Map<String, Object> updates) {
        adminService.updateAdminPartial(id, updates);
        return ResponseEntity.ok("Admin atualizado parcialmente com sucesso!");
    }
    @GetMapping("/buscar-por-email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        var adminOpt = adminRepository.findByEmail(email);

        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Admin não encontrado"));
        }

        var admin = adminOpt.get();

        return ResponseEntity.ok(Map.of(
                "name", admin.getName(),
                "email", admin.getEmail()
        ));
    }

}
