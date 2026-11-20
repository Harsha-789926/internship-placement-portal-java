
package com.careerbridge.careerbridge_backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hashedPassword = encoder.encode("Student@123");

        System.out.println("Encrypted password:");
        System.out.println(hashedPassword);
    }
}
