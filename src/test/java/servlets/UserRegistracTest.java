package servlets;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserRegistracTest {

    //inspect existance mail for registracii
     @Test
   public void testProverkaMail() throws Exception {
        UserRegistrac u = new UserRegistrac();
        boolean b = u.proverkaMail("chentaichiquan@gmail.com");
        assertTrue(b);
     }

    //inspect regex for login
    @Test
    public void testProverkaRegexLogin() throws Exception {
        boolean b = UserRegistrac.test("[].");
        assertEquals(false, b);
    }
}