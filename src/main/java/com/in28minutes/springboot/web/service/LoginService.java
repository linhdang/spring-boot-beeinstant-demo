package com.in28minutes.springboot.web.service;

import com.beeinstant.metrics.MetricsLogger;
import com.beeinstant.metrics.MetricsManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class LoginService implements UserDetailsService {
    private static final MetricsLogger metricsLogger = MetricsManager.getMetricsLogger("class=LoginService");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("in28Minutes")) {
            metricsLogger.incCounter("LoginSucceed", 1);
            return new UserDetails() {

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Arrays.asList((GrantedAuthority) () -> "ROLE_USER");
                }

                @Override
                public String getPassword() {
                    return "dummy";
                }

                @Override
                public String getUsername() {
                    return "in28Minutes";
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
        metricsLogger.incCounter("LoginFailed", 1);
        return null;
    }
}