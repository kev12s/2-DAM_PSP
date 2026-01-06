package com.example.employees.controller;

import com.example.employees.model.Employee;
import com.example.employees.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
      
            return ResponseEntity.ok(service.getAll());
        
    }
 @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build(); // 400
        }
        
        Employee employee = service.getEmployee(id);
        
        if (employee == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        
        return ResponseEntity.ok(employee); // 200
    }
    
    // POST: Añadir nuevo empleado
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
        if (newEmployee == null || newEmployee.getId() == null) {
            return ResponseEntity.badRequest().build(); // 400
        }
        
        // Verificar si ya existe
        if (service.getEmployee(newEmployee.getId()) != null) {
            return ResponseEntity.status(409).build(); // 409 Conflict
        }
        
        Employee addedEmployee = service.addEmployee(newEmployee);
        return ResponseEntity.status(201).body(addedEmployee); // 201 Created
    }
    
    // GET: Buscar empleado por nombre y apellido
    @GetMapping("/search")
    public ResponseEntity<Employee> searchEmployee(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        
        if (firstName == null || lastName == null) {
            return ResponseEntity.badRequest().build(); // 400
        }
        
        Employee employee = service.findByFullName(firstName, lastName);
        
        if (employee == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        
        return ResponseEntity.ok(employee); // 200
    }
    
    // PUT: Modificar empleado existente
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {
        
        if (id == null || updatedEmployee == null) {
            return ResponseEntity.badRequest().build(); // 400
        }
        
        // Uso metodo de service paraerificar si existe
        if (service.getEmployee(id) == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        
        Employee result = service.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(result); // 200
    }
    
    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build(); // 400
        }
        
        // Verificar si existe
        if (service.getEmployee(id) == null) {
            return ResponseEntity.notFound().build(); // 404
        }
        
        boolean deleted = service.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.status(500).build(); 
        }
    }
}
