package com.itg.restApiClient.controller;

import com.itg.restApiClient.model.Employee;
import com.itg.restApiClient.util.CustomErrorType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class RestServices {

    private static final String REST_SERVICE_URI ="http://localhost:8080/api";

    public int userRequest(int a){
        return a;
    }



    /*GET*/
    @SuppressWarnings("unchecked")
    public static void listAllUsers(){
        System.out.println("Testing listAllUsers API.............");

        RestTemplate restTemplate = new RestTemplate();

        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/",List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("Employee ID : " + map.get("id")+", Name : "+map.get("name")+", Age : "+map.get("age")+", Salary : "+map.get("salary"));
            }
        }else{
            System.out.println("No users exists..........");
        }

    }

    //GET
    public static void getUser(){
        System.out.println("Testing getUser API..................");
        RestTemplate restTemplate = new RestTemplate();
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the id of the employee : ");
        long id = scanner.nextLong();


            Employee employee = restTemplate.getForObject(REST_SERVICE_URI+"/user/"+id,Employee.class);
            if( employee != null) {
                System.out.println("Employee ID : " + employee.getId() + ", Name : " + employee.getName() + ", Age : " + employee.getAge() + ", Salary : " + employee.getSalary());
            } else {
                CustomErrorType errorMessage = new CustomErrorType("User with id : "+id+" not found.");
                System.out.println(errorMessage.getErrorMessage());
            }
    }

    //POST
    public static void createUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Testing createUser API................");
        RestTemplate restTemplate = new RestTemplate();
        System.out.print("Enter the name of the Employee : ");
        String name = scanner.next();
        System.out.print("Enter the age of the Employee : ");
        int age = scanner.nextInt();
        System.out.print("Enter the salary of the Employee : ");
        double salary = scanner.nextDouble();
        Employee employee = new Employee(0,name, age, salary);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/",employee,Employee.class);
        System.out.println("Location = "+uri.toASCIIString());
    }

    //PUT
    public static void updateUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Testing updateUser API................");
        RestTemplate restTemplate = new RestTemplate();
        System.out.print("Enter the ID of the employee : ");
        int id = scanner.nextInt();
        System.out.print("Enter new name : ");
        String name = scanner.next();
        System.out.print("Enter new age : ");
        int age = scanner.nextInt();
        System.out.print("Enter new salary : ");
        double salary = scanner.nextDouble();
        Employee employee = new Employee(id, name, age, salary);
        restTemplate.put(REST_SERVICE_URI+"/user/"+id,employee);
        System.out.println(employee);

    }

    //DELETE
    public static void deleteUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Testing deleteUser API............");
        RestTemplate restTemplate = new RestTemplate();
        System.out.print("Enter the ID of the employee to delete : ");
        int id = scanner.nextInt();
        restTemplate.delete(REST_SERVICE_URI+"/user/"+id);
        System.out.println("Successfully deleted the employee with ID : "+id);

    }



}
