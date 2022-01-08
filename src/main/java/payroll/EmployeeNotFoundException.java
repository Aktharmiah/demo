package payroll;

class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(Integer id) {
    super("Could not find employee " + id);
  }
}