package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class KirbyTest {
  static WebDriver driver = new FirefoxDriver();
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    //driver = new HtmlUnitDriver();
    driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    
  }

 //User Scenario:
  //Given on WiKirby homepage,
  //When I click on the 'Enemies' link,
  //Then I should be taken to the 'Category: Enemies' page
  @Test
  public void testEnemies() throws Exception {
	driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    driver.findElement(By.cssSelector("a[title=\"Visit the main page\"]")).click();
    driver.findElement(By.linkText("Enemies")).click();
    assertEquals("Category:Enemies - WiKirby", driver.getTitle());
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
