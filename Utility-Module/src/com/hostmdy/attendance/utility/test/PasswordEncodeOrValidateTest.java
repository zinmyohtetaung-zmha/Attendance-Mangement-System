package com.hostmdy.attendance.utility.test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.jupiter.api.Test;

import com.hostmdy.attendance.utility.crypto.PasswordEncoder;
import com.hostmdy.attendance.utility.crypto.PasswordValidator;

class PasswordEncodeOrValidateTest{

	@Test
	void passwordEncodeNotNullTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String securedPassword = PasswordEncoder.encodePassword("mgmg1234");
		assertNotNull(securedPassword);
		System.out.println(securedPassword);
		System.out.println(securedPassword.length());
		
	}
	
	@Test
	void passwordEncodeNotEqualTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = "mgmg1234";
		String securedPassword = PasswordEncoder.encodePassword("mgmg1234");
		assertNotEquals(securedPassword,password);
		System.out.println("Original password is not equal hash password");
		
	}
	
	@Test
	void passwordValidateTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String password = "admin1234";
		String securedPassword = PasswordEncoder.encodePassword(password);
		assertTrue(PasswordValidator.validatePassword(password, securedPassword));
		
		System.out.println("Original password is equal to securedPassword");
	}

}

