/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.kelebogilepart1.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author K.O.K
 */
public class messageTest {
    
    //Data to be tested
    private static final String recipient1 = "+27718693002";
    private static final String message1 = "Hi Mike, can you join us for dinner tonight?";
    private static final String recipient2 = "08575975889";
    private static final String message2 = "Hi Keegan, did you recieve the payment?";
    private static final String knownID = "0012345678";
    
    //set to reset the counter back to zero after every test
    @BeforeEach
    public void setUp(){
        Messages.resetMessages();
    }
    
    
    //Testing the message length
    @Test
    public void testMessagelengthIfSuccess(){
        
        Messages m = new Messages(recipient1, message1);
        assertEquals("Message ready to be send ", m.checkMessageLength());  
    }
    
    @Test
    public void testMessagelengthIfFailure(){
        
        String tooLong = "a".repeat(255);
        Messages m = new Messages(recipient1, tooLong);
        assertEquals("Message exceeds 250 characters by 5, please reduce the number of characters ", m.checkMessageLength());
    }
    
    //Testing if the recipients number is correctly formatted
    @Test
    public void testRecipientNumberIfSuccess(){
        
        Messages m = new Messages(recipient1, message1);
        assertEquals("Cellphone number succesfully captured :)", m.checkRecipientNumber());
    }
    
    @Test
    public void testRecipientNumberIfFailure(){
        
        Messages m = new Messages(recipient2, message2);
        assertEquals("Cell phone number incorrectly formatted "
                + "or does not contain international code", m.checkRecipientNumber());
    }
    
    //Testing if message hash is correct
    @Test
    public void testMessageHashCase1(){
        
        Messages m = new Messages(recipient1, message1);
        
        String actualHash = m.messageHashCreate();
        String firstTwoDigits = actualHash.substring(0, 2);
        String expectedHash = firstTwoDigits + ":0:HITONIGHT";
        assertEquals(expectedHash,actualHash);
        
    }
    
    @Test
    public void testMessageHashRemainingInLoop(){
        
        String[] messages ={
           "Hi Mike, can you join us for dinner tonight?",
           "Hi Keegan, did you recieve the payment?"
        };
        
        String[] expectedWords ={
            "HITONIGHT",
            "HIPAYMENT"
        };
        
        String[] recipients ={
            "+27718693002",
            "08575975889"
        };
        
        for (int i = 0; i < messages.length; i++) {

        int messageNumber = Messages.returnTotalMessages();

        Messages m = new Messages(
                recipients[i],
                messages[i]
        );

        String actualHash = m.messageHashCreate();
        String idStatus = m.getMessageIDStatus();
        String messageID = idStatus.replace(
                "Message ID generated: ", ""
        );

        String firstTwoDigits = messageID.substring(0, 2);
        String expectedHash = firstTwoDigits + ":"
                + messageNumber + ":"
                + expectedWords[i];
        
        assertEquals(expectedHash, actualHash);
        }
    }
    
    //Testing the message ID created
    @Test
    public void testMessageIDCreated(){
        
        Messages m = new Messages(recipient1, message1);
        assertTrue( m.getMessageIDStatus().matches("Message ID generated: \\d{10}"));
        assertTrue(m.checkMessageID());
    }
    
    //Testing the selection of the message sent
    @Test
    public void testMessageSentSend(){
        
        Messages m = new Messages(recipient1, message1);
        assertEquals("\n----- Message successfully sent :) -----", m.sentMessages(1));
    }
    
    @Test
    public void testMessageSentDisregard(){
        
        Messages m = new Messages(recipient1, message1);
        assertEquals("Press 0 to delete the message:  ", m.sentMessages(2));
    }
    
    @Test
     public void testMessageSentStore(){
        
        Messages m = new Messages(recipient1, message1);
        assertEquals("Message successfully stored :) ", m.sentMessages(3));
    }
     
    //Testing the data to bring the total messages
    @Test
     public void testReturnTotalMessagesTestData(){
         
         Messages first = new Messages(recipient1, message1);
         first.sentMessages(1);
         
         Messages second = new Messages(recipient1, message2);
         second.sentMessages(2);
         
         assertEquals(1, Messages.returnTotalMessages());
    }   
    
}
