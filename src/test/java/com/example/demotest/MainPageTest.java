package com.example.demotest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Add AllureSelenide listener for logging
        io.qameta.allure.selenide.AllureSelenide allureSelenide = new io.qameta.allure.selenide.AllureSelenide();
        com.codeborne.selenide.logevents.SelenideLogger.addListener("allure", allureSelenide);

        // Set headless mode and timeout
        com.codeborne.selenide.Configuration.headless = true;
        com.codeborne.selenide.Configuration.timeout = 60000;

        // Test case1 : sign Up as a new user
        driver.navigate().to("http://15.206.69.9:81/signup");
        driver.findElement(By.name("name")).sendKeys("Urooj");
        driver.findElement(By.name("email")).sendKeys("Urooj123@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Urooj@123");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/main/div[3]/form/button")).click();
        String AccountCreated = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/main/div[1]")).getText();
        assertEquals("New account is created. Please signin", AccountCreated);
        driver.manage().deleteAllCookies();

        // Test case 2: sign in
        driver.navigate().to("http://15.206.69.9:81/sign in");
        driver.findElement(By.name("email")).sendKeys("Urooj123@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Urooj@123");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/main/div[3]/form/button")).click();
        String url = driver.getCurrentUrl();
        assertEquals("http://15.206.69.9:81/", url);
        driver.manage().deleteAllCookies();



        // Test case 3 : Password length should be 6 or greater
        driver.navigate().to("http://15.206.69.9:81/signup");
        driver.findElement(By.name("name")).sendKeys("Urooj");
        driver.findElement(By.name("email")).sendKeys("Urooj23@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/main/div[3]/form/button")).click();
        String error = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/main/div[2]")).getText();
        assertEquals("Password must contain at least 6 charactersn", error);
        driver.manage().deleteAllCookies();

        // Test case 4: buy a product and place order
        driver.navigate().to("http://15.206.69.9:81/sign in");
        driver.findElement(By.name("email")).sendKeys("Urooj123@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Urooj@123");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/main/div[3]/form/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[1]/div[1]/div/div/div/div/div[2]/span[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[3]/div/div[3]/div/div/div[1]/textarea")).sendKeys("Pakistan");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[3]/div/div[3]/div/div/button")).click();
        String OrderConfirm = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[3]/div/div[1]")).getText();
        assertEquals("Thanks! Your Order is Placed Successfully!", OrderConfirm);
        driver.manage().deleteAllCookies();

        // Test case 5: edit your name
        driver.navigate().to("http://15.206.69.9:81/user/dashboard");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/ul/li[2]/a")).click();
        driver.findElement(By.name("name")).sendKeys("Urooj Shaukat");
        url = driver.getCurrentUrl();
        assertEquals("http://15.206.69.9:81/user/dashboard", url);
        driver.manage().deleteAllCookies();


        
        driver.close();
    }
}
