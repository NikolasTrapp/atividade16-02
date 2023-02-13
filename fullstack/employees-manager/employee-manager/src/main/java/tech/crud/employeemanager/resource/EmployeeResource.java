package tech.crud.employeemanager.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.crud.employeemanager.model.Employee;
import tech.crud.employeemanager.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeResource {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee){
        Employee newEmployee = employeeService.save(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/addal")
    public ResponseEntity<List<Employee>> add(@RequestBody List<Employee> employees){
        List<Employee> newEmployee = employeeService.saveAll(employees);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
        Employee updateEmployee = employeeService.update(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED);
    }
}
