package com.in28minutes.springboot.web.service;

import com.beeinstant.metrics.MetricsLogger;
import com.beeinstant.metrics.MetricsManager;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    private static final MetricsLogger metricsLogger = MetricsManager.getMetricsLogger("service=LoginService");
    public boolean validateUser(String user, String password) {
        metricsLogger.incCounter("userLoginCounter", 1);
        return user.equalsIgnoreCase("in28Minutes") && password.equals("dummy");
    }
}
