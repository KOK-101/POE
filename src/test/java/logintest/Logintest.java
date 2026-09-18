/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package logintest;

import com.mycompany.kelebogilepart1.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author K.O.K
 */
public class Logintest {
    
  private Login login;
  
  @BeforeEach
  public void setup(){
      
 //All data used for registration and flow tests     
      login = new Login("Kyle", "Smith", "ky1_1", "Ch&&sec@ke99!", "+27838968976");
  }
   
//Testing checkUsername  
 
 @Test
public void testUsernameCorrect(){
    assertTrue(Login.checkUserName("ky1_1"));
} 
  
 @Test
public void testUsernameIncorrect(){
    assertFalse(Login.checkUserName("kyle!!!!!!!"));
}
  
//Testing checkPasswordComplexity

@Test
public void testPasswordComplexityRequirements(){
    assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
}

@Test
public void testPasswordComplexityRequirementsNotMeet(){
    assertFalse(Login.checkPasswordComplexity("password"));
}

//Testing checkCellPhoneNumber

@Test
 public void testPhoneNumberCorrect(){
    assertTrue(Login.checkCellPhoneNumber("+27838968976"));
 }
 
 @Test
 public void testPhoneNumberIncorrect(){
    assertFalse(Login.checkCellPhoneNumber("08966553"));
 }
 
 //Testing registerUser(assertEquals)
 
 @Test
 public void testRegisterUsercorrect(){
     assertEquals("\n------------Registration SuccessFul-------------", login.registerUser());
 }
 
 //Testing loginUser (assertTrue/ assertFalse)
 
 @Test
 public void testLoginSuccessful(){
     assertTrue(login.loginUser("ky1_1", "Ch&&sec@ke99!"));
 }
 
 @Test
 public void testLoginFail(){
     assertFalse(login.loginUser("ky1_1", "wrongPassword"));
 }
 
 //Testing returnLoginStatus(assertEquals)
 
 @Test
 public void testReturnLoginStatusSuccesful(){
     login.loginUser("ky1_1", "Ch&&sec@ke99!");
     assertEquals("\n----------Login successFul------------" + "\n Welcome Back " + "Ky1e"+" "+ "Smith"+ "" , login.returnLoginStatus());
 }
 
 @Test
 public void testReturnLoginStatusFail(){
     login.loginUser("ky1_1", "wrongPassword");
     assertEquals("\n----Username and Password are incorrect please try again----", login.returnLoginStatus());
 }
 
 
 
}
