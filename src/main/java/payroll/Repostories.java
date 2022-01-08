package payroll;

import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import payroll.models.Employee;
import payroll.models.Project;

// interface EmployeeRepository extends JpaRepository<Employee, Long> {}

interface ProjectRepository extends CrudRepository<Project, Integer> {}

interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    //Parameterised query using :id alias and the @Param annotation
    @Query("SELECT u FROM Employee u WHERE u.id = :id")
    Iterable<Employee> findAllActiveUsers(@Param("id") int id);

    //Depending on the left/right join, the where condition will apply the joined side
    @Query("select e from Employee as e left join e.project as p")
    Iterable<Employee> findAllWithRelatedData(@Param("id") int id);    
}