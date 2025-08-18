package com.api.Service;

import com.api.Model.Employee;
import com.api.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }


    //    Métodos de busca
    public List<Employee> listarFuncionario(){return funcionarioRepository.findAll();}

    public Employee inserirFuncionario(Employee employee) {
        return funcionarioRepository.save(employee);
    }

    public void excluirFuncionario(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        funcionarioRepository.deleteById(id);
        //        return;
    }
    public Employee atualizarFuncionario(Long id, Employee employeeAtualizado) {
        Employee employee = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        employee.setName(employeeAtualizado.getName());
        employee.setEmail(employeeAtualizado.getEmail());
        employee.setPassword(employeeAtualizado.getPassword());
        employee.setEnterprise_id(employeeAtualizado.getEnterprise_id());

        return funcionarioRepository.save(employee);
    }


    public Employee atualizarFuncionarioParcial(Long id, Map<String, Object> updates) {
        Employee employee = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionario com ID " + id + " não encontrado"));

        if (updates.containsKey("nome")) {
            employee.setName((String) updates.get("nome"));
        }
        if (updates.containsKey("email")) {
            employee.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("senha")) {
            employee.setPassword((String) updates.get("senha"));
        }
        if (updates.containsKey("empresa_id")) {
            employee.setEnterprise_id((long) updates.get("empresa_id"));
        }

        return funcionarioRepository.save(employee);
    }
}
