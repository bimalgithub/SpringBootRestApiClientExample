package com.itg.restApiClient.view;

import java.util.Scanner;
import com.itg.restApiClient.controller.RestServices;

public class EmployeeView {



    public void menu(){
        int choice=-1;
        boolean flag=true;

        do {
            System.out.println("Choose one of the following options: ");
            System.out.println("1. Type '1' to add new Employee.");
            System.out.println("2. Type '2' to get an Employee detail.");
            System.out.println("3. Type '3' to update an Employee record.");
            System.out.println("4. Type '4' to remove an Employee from the record.");
            System.out.println("5. Type '5' to view all Employees list.");
            System.out.println("0. Type '0' to exit.");
            System.out.print("What do you want ? --> ");

            Scanner scan = new Scanner(System.in);
            boolean flag1 = true;

            while (flag1) {
                choice = scan.nextInt();
                if (choice >= 0 && choice < 6) {
                    //flag = selection(s);
                    break;
                } else
                    System.out.print("Incorrect choice!! Please provide the correct input -->");
            }

            switch (choice){

                case 1:
                    RestServices.createUser();
                    break;
                case 2:
                    RestServices.getUser();
                    break;
                case 3:
                    RestServices.updateUser();
                    break;
                case 4:
                    RestServices.deleteUser();
                    break;
                case 5:
                    RestServices.listAllUsers();
                    break;
                case 0:
                    System.exit(0);
                default:

            }
        } while(flag);
    }
}
