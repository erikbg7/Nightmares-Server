package edu.upc.dsa;


import edu.upc.dsa.exceptions.NameAlreadyInUseException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class InsertTest {
    private GameManager GM;

    @Test
    public void insertUser2DDBB() throws NameAlreadyInUseException, UserNotFoundException {
        this.GM = GameManagerImpl.getInstance();
        //this.GM.signUp("LoLito4444", "lolo");
        Assert.assertEquals(true, this.GM.logIn("LoLito4444", "lolo"));
        this.GM.clear();
    }
}
