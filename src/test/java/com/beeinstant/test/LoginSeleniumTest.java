package com.beeinstant.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class LoginSeleniumTest {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        Random rn = new Random();
         List<String> usernames = Arrays.asList("in28Minutes", "in29Minutes");
        for (int i = 0; i < 20; i++) {
            int randomSelection = rn.nextInt(1);
            loginAndLogout(usernames.get(randomSelection));
            Thread.sleep(60000);
        }

    }

    private void loginAndLogout(String username) throws InterruptedException {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("dummy");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("Logout")).click();
        Thread.sleep(1000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
