/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kelebogilepart1;
import java.util.Scanner;
import java.util.regex.*;

/**
 *
 * @author K.O.K
 */
public class KelebogilePart1 {

    public static void main(String[] args) {
        // Scanner 
        
        Scanner myInput = new Scanner(System.in);
        
        //Declarations
        
        String name;
        String surName;
        String userName;
        String passWord;
        String cellNumber;
        
        //Prompting the user to register
        
        System.out.println("Enter your First Name: ");
        name = myInput.next();
        System.out.println("Enter your Surname: ");
        surName = myInput.next();
        System.out.println("Enter your Username(Must contain an underscore and be less than 5 characters): ");
        userName = myInput.next();
        System.out.println("Enter your Password(Must contain 8+ character,a capital letter,a number and a special character): ");
        passWord = myInput.next();
        System.out.println("Enter your Cellphone Number(+27): ");
        cellNumber = myInput.next();
        
        // Displaying the conditions and results
        
       while(checkUserName(userName)){
            
            if(checkUserName(userName)){
                System.out.println("Username succesfully captured.");
            }else{
                System.out.print("Username is not correctly formatted; "
                        + "please ensure that your username contains an underscore"
                        + "and is no more than five characters in length");
            }      
        }
        while(checkPasswordComplexity(passWord)){
            
          if( checkPasswordComplexity(passWord)){
                System.out.println("Password succesfully captured.");
            }else{
                System.out.print("Password is not correctly formatted; "
                        + "please ensure that your password contains at least eight characters, "
                        + "a capital letter, a number, and a special character");
            }  
        }
        while(checkCellPhoneNumber(cellNumber)){
            
            if(checkCellPhoneNumber(cellNumber)){
                System.out.println("Cell phone number succesfully added.");
            }else{
                System.out.print("Cell phone number is incorrectly formatted; "
                        + "or does not contain international code");
            }
        }
            
    }
    
    //Methods for the conditions
    
     public static boolean checkUserName(String userName){
             
        boolean underScore = userName.contains("_");
        boolean length = userName.length() <= 5;
        
        return underScore & length;
    }
    public static boolean checkPasswordComplexity(String passWord){
        
        boolean length = passWord.length() >= 8;
        boolean capitalLetter = Pattern.compile("[A-Z]").matcher(passWord).find();
        boolean isTheNumber = Pattern.compile("[0-9]").matcher(passWord).find();
        boolean specialCharacter = Pattern.compile("[^a-zA-Z0-9]").matcher(passWord).find();
        
        return length & capitalLetter & isTheNumber & specialCharacter;
    }
    public static boolean checkCellPhoneNumber(String cellNumber){
        
        boolean phoneNumber = Pattern.matches("[^\\+27[0-9]{1,10}$]", cellNumber);
                
         return phoneNumber;
    }
   
        
 }
