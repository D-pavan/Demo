package com.employeeservices.controllers;

import com.employeeservices.utils.InputUtil;

public class CLIController implements Controllers
{
    private static CLIController cliController;
    private final InputUtil inputUtil;
    private final EmployeeInMemoryController employeeInMemoryController;
    private  final  EmployeeFileSystemController employeeFileSystemController;
    private CLIController(){
        inputUtil=InputUtil.getInstance();
        employeeInMemoryController=EmployeeInMemoryController.getInstance();
        employeeFileSystemController=EmployeeFileSystemController.getInstance();
    }
    public static  CLIController getInstance(){
        if(cliController==null){
            cliController=new CLIController();
        }
        return cliController;
    }
    @Override
    public void startOperations(){
        try {
            while (true) {
                System.out.println("Enter a number from menu : \n  1.InMemory \n  2.FileSystem\n  3.Exit");
                int menuNumber = Integer.parseInt(inputUtil.readString());
                switch (menuNumber) {
                    case 1:
                        employeeInMemoryController.startOperations();
                        break;
                    case 2:
                        employeeFileSystemController.startOperations();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Invalid Menu Option !!!");
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println(e);
        }

    }
}
