package com.api.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthService {

    public String createUser(String email, String password) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setEmailVerified(false)
                    .setPassword(password)
                    .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("✅ Usuário criado no Firebase com UID: " + userRecord.getUid());
            return userRecord.getUid();

        } catch (FirebaseAuthException e) {
            System.err.println("❌ Erro FirebaseAuth: " + e.getErrorCode() + " - " + e.getMessage());
            throw new RuntimeException("Erro ao criar usuário no Firebase: " + e.getMessage(), e);

        } catch (Exception e) {
            System.err.println("❌ Erro inesperado ao criar usuário: " + e.getMessage());
            throw new RuntimeException("Erro inesperado ao criar usuário: " + e.getMessage(), e);
        }
    }
}
