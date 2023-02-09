package tech.crud.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.crud.employeemanager.exception.UserNotFoundException;
import tech.crud.employeemanager.model.Employee;
import tech.crud.employeemanager.repo.EmployeeRepo;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> findAll(){
        return employeeRepo.findAll();
    }

    public Employee findById(Long id){
        return employeeRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No employee found with id: " + id));
    }

    public Employee save(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public void delete(Long id){
        employeeRepo.deleteById(id);
    }

    public Employee update(Employee employee){
        return employeeRepo.save(employee);
    }
}
