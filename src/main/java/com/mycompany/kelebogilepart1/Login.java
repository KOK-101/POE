/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kelebogilepart1;
import java.util.regex.*;

/**
 *
 * @author K.O.K
 */
public class Login {
    
   // use the private fields to capture the users registration inputs
    
    private String name;
    private String surName;
    private String userName;
    private String passWord;
    private String cellNumber;
   
  // To keep of the most recent login attempt whether it was succesful or not
    
    private boolean loginSuccessFul;
 
 // Constructing to capture the details entered by the user
    
    public Login(String name, String surName, String userName, String passWord, String cellNumber){
        
        this.name = name;
        this.surName = surName;
        this.userName = userName;
        this.passWord = passWord;
        this.cellNumber = cellNumber;
        
    }
// Creating the username validation
    
     public static boolean checkUserName(String userName){
             
         if(userName == null){
             return false;
         }
         
        boolean underScore = userName.contains("_");
        boolean length = userName.length() <= 5;
        
        return underScore & length;
    }
//Creating the password validation
     
    public static boolean checkPasswordComplexity(String passWord){
        
        if(passWord == null){
             return false;
         }
        
        boolean length = passWord.length() >= 8;
        boolean capitalLetter = Pattern.compile("[A-Z]").matcher(passWord).find();
        boolean isTheNumber = Pattern.compile("[0-9]").matcher(passWord).find();
        boolean specialCharacter = Pattern.compile("[^a-zA-Z0-9]").matcher(passWord).find();
        
        return length & capitalLetter & isTheNumber & specialCharacter;
    }
 
 //Creating the cellphone number validation
    
    public static boolean checkCellPhoneNumber(String cellNumber){
        
        if(cellNumber == null){
             return false;
         }
        
        boolean phoneNumber = Pattern.matches("^\\+27[0-9]{1,10}$", cellNumber);
                
         return phoneNumber;
         
    }
    
//check if the user inputs match the method validations before login
   public String  registerUser(){
       
       if (checkUserName(this.userName)){
       }
       if (checkPasswordComplexity(this.passWord)){
       }
      if (checkCellPhoneNumber(this.cellNumber)){
       } 
    
      return "\n------------Registration SuccessFul-------------";
   }
   
//Checks if the user details match to confirm
   
   public boolean loginUser(String enteredUsername, String enteredPassword){
       
       this.loginSuccessFul = this.userName.equals(enteredUsername)& this.passWord.equals(enteredPassword);
       
       return this.loginSuccessFul;
   
   }

//This method returns the messages when the user input is correct and wrong
   
   public  String returnLoginStatus(){
       if (this.loginSuccessFul){
           
           return "\n----------Login successFul------------"+ "\n :)!! Welcome Back " + this.name +" "+ this.surName+" :)!!";
       }else{
       return "\n----Username and Password are incorrect please try again----";
       }
   }
  
}
