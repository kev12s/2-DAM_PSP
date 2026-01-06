package com.example.employees.service;

import com.example.employees.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private ArrayList<Employee> arrayEmpleados = new ArrayList<>();

    public EmployeeService() {
        arrayEmpleados.add(new Employee(1L, "Salo", "Burgoa", "Encargado", 2000d, LocalDate.now()));
    }
    
    public List<Employee> getAll() {
        return arrayEmpleados;
    }
    
    public Employee getEmployee(long id) {
        for(Employee empleado: arrayEmpleados) {
            if (empleado.getId() == id)
                return empleado;
        }
        return null;
    }
    
    // Añadir nuevo 
    public Employee addEmployee(Employee newEmployee) {
        // Verificar que no exista ya
        if (getEmployee(newEmployee.getId()) != null) {
            throw new RuntimeException("El empleado ya existe");
        }
        
        arrayEmpleados.add(newEmployee);
        return newEmployee;
    }
    
    // Buscar por nombre y apellido
    public Employee findByFullName(String firstName, String lastName) {
        for(Employee empleado: arrayEmpleados) {
            if (empleado.getFirstName().equals(firstName) && 
                empleado.getLastName().equals(lastName)) {
                return empleado;
            }
        }
        return null;
    }
    
    // Actualizar empleado
    public Employee updateEmployee(long id, Employee updatedEmployee) {
        for(Employee empleado: arrayEmpleados) {
            if (empleado.getId() == id) {
                // Actualizar todos los campos
                empleado.setFirstName(updatedEmployee.getFirstName());
                empleado.setLastName(updatedEmployee.getLastName());
                empleado.setPosition(updatedEmployee.getPosition());
                empleado.setSalary(updatedEmployee.getSalary());
                empleado.setHireDate(updatedEmployee.getHireDate());
                return empleado;
            }
        }
        return null;
    }
    
    // Eliminar empleado
    public boolean deleteEmployee(long id) {
        for(int i = 0; i < arrayEmpleados.size(); i++) {
            if (arrayEmpleados.get(i).getId() == id) {
                arrayEmpleados.remove(i);
                return true;
            }
        }
        return false;
    }

}