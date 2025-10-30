package com.api.Util;

import com.api.Util.PasswordUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SHA256PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtils.hashPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashed = PasswordUtils.hashPassword(rawPassword.toString());
        return hashed.equals(encodedPassword);
    }
}
