package com.wpp.greenorange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private MyUserDetailService myUserDetailService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这个获取表单输入中的用户名
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = myUserDetailService.loadUserByUsername(userName);
        String encodePassword = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password,encodePassword)) {
            throw new UsernameNotFoundException("用户名或者密码不正确");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(userDetails, encodePassword, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
