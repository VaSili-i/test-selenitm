package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class FirstTestInSelenium {

    public static void main(String[] args) {
// TODO Auto-generated method stub

//setting the driver executable
        System.setProperty("webdriver.chrome.driver", "/home/vasilii/Рабочий стол/utils/webix_java/demo/src/test/java/com/example/demo/chromedriver");

//Initiating your chromedriver
        WebDriver driver=new ChromeDriver();

//Applied wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();

//open browser with desried URL
        driver.get("https://www.google.com");

//closing the browser
        driver.close();

    }

}