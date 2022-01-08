package payroll;

public class ProjectNotFoundException extends Exception {

    ProjectNotFoundException(int id){

        super("Project not found with ID:"+id);
    }

}
