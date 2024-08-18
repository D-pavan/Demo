import com.employeeservices.controllers.CLIController;
public class Main {
    public static  void main(String args[]){
        CLIController cliController=CLIController.getInstance();
        cliController.startOperations();
    }
}