package com.easybuy.backend.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MD5PasswordEncoder implements PasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encode((String) rawPassword));
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encode((String) rawPassword);

    }
}