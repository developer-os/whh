package com.whh.tools;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Login {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.gecko.driver", "D:\\developmentDirectory\\framework\\test_framework\\selenium\\geckodriver-v0.23.0-win64\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogin() throws Exception {
    driver.get("https://jy.xzsec.com/Login?el=1&clear=&returl=%2fTrade%2fBuy");
    driver.findElement(By.id("txtZjzh")).click();
    driver.findElement(By.id("txtZjzh")).clear();
    driver.findElement(By.id("txtZjzh")).sendKeys("232132");//账户
    driver.findElement(By.id("panelLoginHS")).click();
    driver.findElement(By.id("txtPwd")).click();
    driver.findElement(By.id("txtPwd")).clear();
    driver.findElement(By.id("txtPwd")).sendKeys("213123");//密码
    driver.findElement(By.id("txtValidCode")).click();
    driver.findElement(By.id("txtValidCode")).clear();
    driver.findElement(By.id("txtValidCode")).sendKeys("5554");//验证码https://jy.xzsec.com/Login/YZM?randNum=0.4252330389745018
    driver.findElement(By.id("btnConfirm")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
