package payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Collection;

import javax.xml.bind.PrintConversionEvent;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import payroll.models.*;

@RestController
class EmployeeController {

  private final EmployeeRepository repository;
  private final ProjectRepository pRepo;

  //The EmployeeRepository here is a @bean code injection
  EmployeeController(EmployeeRepository repository, ProjectRepository pRepo) {
    this.repository = repository;
    this.pRepo = pRepo;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/projects")
  Iterable<Project> allProjects() {

    Iterable<Project> res = pRepo.findAll();

    return res;
  }
  

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/projects/{id}")
  EntityModel<Project> singleProject(@PathVariable int id ){

      Project project = pRepo.findById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));

    return EntityModel.of(project, 
      linkTo(methodOn(EmployeeController.class).singleProject(id)).withRel("project"),
      linkTo(methodOn(EmployeeController.class).allProjects()).withRel("all_projects") 
      
    );

  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  Iterable<Employee> all() {

    return repository.findAll();
  }
  
  
  // end::get-aggregate-root[]

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  // @GetMapping("/employees/{id}")
  // Employee one(@PathVariable Long id) {
    
  //   return repository.findById(id).orElseThrow( () -> new EmployeeNotFoundException(id) );
  // }

  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Integer id) {
  
    Employee employee = repository.findById(id) //
        .orElseThrow(() -> new EmployeeNotFoundException(id));
  
    return EntityModel.of(employee, //
        linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }


  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {
    

    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Integer id) {
    repository.deleteById(id);
  }
}