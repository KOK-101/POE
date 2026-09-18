/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kelebogilepart1;
import java.util.Scanner;

/**
 *
 * @author K.O.K
 */
public class KelebogilePart1 {

    public static void main(String[] args) {
        
        // Scanner.
        
        Scanner myInput = new Scanner(System.in);
        
        //Declarations.
        
        String name;
        String surName;
        String userName;
        String passWord;
        String cellNumber;
       
        //Prompting user to register an account. 
        
        System.out.println("\n----------Registration----------");
        
        System.out.println("\nEnter your First Name: ");
        name = myInput.nextLine();
        System.out.println("Enter your Surname: ");
        surName = myInput.nextLine();
        
     
       //login object used to check. 
        
       Login check = new Login("","","","",""); 
        
     // Prompting the user and looping if conditions are not met.
     //userName input looping
     
        System.out.println("Enter your Username(Must contain an underscore and be less than 5 characters): ");
        userName = myInput.nextLine();
        while(!check.checkUserName(userName)){
         
        System.out.println("Username is not correctly formatted; "
                   + "please ensure that your username contains an underscore"
                   + "and is no more than five characters in length");
        
        System.out.println("Enter your Username(Must contain an underscore and be less than 5 characters): ");
        userName = myInput.nextLine();
        }
        System.out.println("Username successfully captured :)!!!");
        
     //passWord input looping
     
        System.out.println("Enter your Password(Must contain 8+ character,a capital letter,a number and a special character): ");
        passWord = myInput.nextLine();
        while(!check.checkPasswordComplexity(passWord)){
         
        System.out.println("Password is not correctly formatted; "
                   + "please ensure that your password contains at least eight characters"
                   + "a capital letter, a number and a special character");
            
        System.out.println("Enter your Password(Must contain 8+ character,a capital letter,a number and a special character): ");
        passWord = myInput.nextLine();
        }
        System.out.println("Password successfully captured :)!!!");
      
      //cellphone number input looping
      
        System.out.println("Enter your Cellphone Number e.g.(+27956891234): ");
        cellNumber = myInput.nextLine();
        while(!check.checkCellPhoneNumber(cellNumber)){
    
        System.out.println("Cell phone number incorrectly formatted "
                   + "or does not contain international code");
            
        System.out.println("Enter your Cellphone Number e.g.(+27956891234): ");
        cellNumber = myInput.nextLine();
        }
        System.out.println("Cell phone number successfully added :)!!!");
        
        //This is the real login object
        
        Login register = new Login(name, surName, userName, passWord, cellNumber);
        
        //Using the login object to register the account
      
        System.out.println(register.registerUser());
        
     // boolean to make sure the inputs match  
        
        boolean registeredSuccessfully = register.checkUserName(userName)&& register.checkPasswordComplexity(passWord)&& register.checkCellPhoneNumber(cellNumber);
       
     // Creating the Login 
     
        if(registeredSuccessfully){
            
            System.out.println("\n--------------LOGIN------------- ");
         
         //if Login credentials are wrong we loop using a while loop and a boolean 
         // boolean to help make the loop a success
         
            boolean loginSuccessful = false;
            
            while(!loginSuccessful){
            
            System.out.println("\nEnter your username: ");
            String enteredUsername = myInput.nextLine();
            System.out.println("Enter your password: ");
            String enteredPassword = myInput.nextLine();
            
          //Verifying credentials before print the success message 
          
            loginSuccessful = register.loginUser(enteredUsername, enteredPassword);
            register.returnLoginStatus();
            
            System.out.println(register.returnLoginStatus());
            }
        }
        myInput.close();
    }   
}   
   
   
        
 
