package web.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class LoginServiceUnitTest {
	
	// Boundary Value Analysis for Username
    @Test
    public void testEmptyUsername() {
        assertFalse(LoginService.login("", "ahsan_pass", "07/27/1996"));
    }

    @Test
    public void testSingleCharacterUsername() {
        assertFalse(LoginService.login("a", "ahsan_pass", "07/27/1996"));
    }

    @Test
    public void testVeryLongUsername() {
        String longUsername = "a".repeat(100);
        assertFalse(LoginService.login(longUsername, "ahsan_pass", "07/27/1996"));
    }

    // Boundary Value Analysis for Password
    @Test
    public void testEmptyPassword() {
        assertFalse(LoginService.login("ahsan", "", "07/27/1996"));
    }

    @Test
    public void testVeryLongPassword() {
        String longPassword = "a".repeat(100);
        assertFalse(LoginService.login("ahsan", longPassword, "07/27/1996"));
    }

    // Boundary Value Analysis for Date of Birth (DoB)
    @Test
    public void testInvalidDobFormat() {
        assertFalse(LoginService.validateDob("27/07/1996"));
        assertFalse(LoginService.validateDob("1996/13/01"));
    }

    @Test
    public void testFutureDate() {
        String futureDate = LocalDate.now().plusDays(1).toString();
        assertFalse(LoginService.validateDob(futureDate));
    }

    // Equivalence Class Partitioning for Username
    @Test
    public void testValidUsername() {
        assertTrue(LoginService.login("ahsan", "ahsan_pass", "07/27/1996"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(LoginService.login("wrong_user", "ahsan_pass", "07/27/1996"));
    }

    // Equivalence Class Partitioning for Password
    @Test
    public void testValidPassword() {
        assertTrue(LoginService.login("ahsan", "ahsan_pass", "07/27/1996"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(LoginService.login("ahsan", "wrong_pass", "07/27/1996"));
    }

}
