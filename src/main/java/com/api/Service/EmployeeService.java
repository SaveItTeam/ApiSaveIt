package com.api.Service;

import com.api.Model.Employee;
import com.api.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    //    Métodos de busca
    public List<Employee> listarFuncionario(){return employeeRepository.findAll();}

    public Employee inserirFuncionario(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void excluirFuncionario(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        employeeRepository.deleteById(id);
        //        return;
    }
    public Employee atualizarFuncionario(Long id, Employee employeeAtualizado) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        employee.setName(employeeAtualizado.getName());
        employee.setEmail(employeeAtualizado.getEmail());
        employee.setPassword(employeeAtualizado.getPassword());
        employee.setEnterprise_id(employeeAtualizado.getEnterprise_id());

        return employeeRepository.save(employee);
    }


    public Employee atualizarFuncionarioParcial(Long id, Map<String, Object> updates) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        if (updates.containsKey("name")) {
            employee.setName((String) updates.get("name"));
        }
        if (updates.containsKey("email")) {
            employee.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("password")) {
            employee.setPassword((String) updates.get("password"));
        }
        if (updates.containsKey("enterprise_id")) {
            employee.setEnterprise_id((long) updates.get("enterprise_id"));
        }

        return employeeRepository.save(employee);
    }
}
