package net.java.springbackend.controller;

import net.java.springbackend.Repo.EmployeeRepo;
import net.java.springbackend.exeption.ResoureNotFoundExeption;
import net.java.springbackend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    //Get_ALL
    @GetMapping("employees")
    public List<Employee> getALL(){

        return employeeRepo.findAll();

    }
    //Create new employee
    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeRepo.save(employee);


    }

    // Get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity <Employee> GetEmployeeById(@PathVariable long id){

        Employee employee = employeeRepo.findById(id).orElseThrow(()-> new ResoureNotFoundExeption("EmployeeID Not Fount"+id));

        return ResponseEntity.ok(employee);

    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeRepo.findById(id).orElseThrow(()-> new ResoureNotFoundExeption("EmployeeID Not Fount"+id));
         employee.setFirstName(employeeDetails.getFirstName());
         employee.setLastName(employeeDetails.getLastName());
         employee.setEmail(employeeDetails.getEmail());
         Employee updateEmployee = employeeRepo.save(employee);
         return ResponseEntity.ok(updateEmployee);


    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepo.findById(id).orElseThrow(()-> new ResoureNotFoundExeption("EmployeeID Not Fount"+id));
        employeeRepo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);



    }

}
