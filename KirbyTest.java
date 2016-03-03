package com.example.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class KirbyTest {
  static WebDriver driver = new HtmlUnitDriver();

  //start at the homepage for each WiKirby test
  @Before
  public void setUp() throws Exception {
    //driver = new HtmlUnitDriver();
    driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    
  }
  
////////////////////////////////////////////////////////////////
  /*User Story 1:
  As a user, I would like to browse Kirby's different types of enemies,
  so that I can learn about Kirby's enemies.
  */
 ///////////////////////////////////////////////////////////////
  
 /*Scenario 1-1:
  *Given on WiKirby homepage,
  *When I click on the 'Enemies' link,
  *Then I should be taken to the 'Category: Enemies' page
  */
  @Test
  public void testEnemies() throws Exception {
    driver.findElement(By.linkText("Enemies")).click();
    assertEquals("Category:Enemies - WiKirby", driver.getTitle());
  }
  
  /*Scenario 1-2:
   *Given on WiKirby 'Enemies' page,
   *When I click on the 'Horror Tramp' link,
   *Then I should be taken to the 'WiKirby Horror Tramp' page
   */
  @Test
  public void testHorrorTramp() throws Exception {
    driver.get("http://wikirby.com/wiki/Category:Enemies");
    driver.findElement(By.linkText("Horror Tramp")).click();
    String newPageTitle = driver.getTitle();
	assertTrue(newPageTitle.contains("Horror Tramp"));
  }
  
  /*Scenario 1-3:
   *Given on WiKirby 'Enemies' page,
   *When I click on the 'Broom Hatter' link,
   *Then I should be taken to the 'WiKirby Broom Hatter' page
   */
  @Test
  public void testBroomHatter() throws Exception {
	    driver.get("http://wikirby.com/wiki/Category:Enemies");
	    driver.findElement(By.linkText("Broom Hatter")).click();
	    String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Broom Hatter"));
	  }
  
  /*Scenario 1-4:
   *Given on WiKirby 'Enemies' page,
   *When I click on the 'Waddle Doo' link,
   *Then I should not be taken to the 'WiKirby Waddle Dee' page
   */
  @Test
  public void testWaddleDoo() throws Exception {
    driver.get("http://wikirby.com/wiki/Category:Enemies");
    driver.findElement(By.linkText("Waddle Doo")).click();
    String newPageTitle = driver.getTitle();
	assertTrue(!newPageTitle.contains("Waddle Dee"));
  }
  
  /*Scenario 1-5:
   *Given on WiKirby home page,
   *When I click on the 'Enemies' link,
   *Then I should see listed the 'Pages in category "Enemies"'
   */
  @Test
  public void testEnemiesPages() throws Exception {
    driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    driver.findElement(By.linkText("Enemies")).click();
    boolean pages = driver.getPageSource().contains("Pages in category \"Enemies\"");
	assertTrue(pages);
  }
  
  
 ////////////////////////////////////////////////////////////////
 /*User Story 2:
 *As a user,
 *I would like to log in to WiKirby,
 *so that I can access the site with member privileges.
 */
 ///////////////////////////////////////////////////////////////

 /*Scenario 2-1:
 *Given on WiKirby home page,
 *and not logged in,
 *When I try to log in with an invalid user name,
 *Then I should be prompted to create an account
 */
  @Test
  public void testLogin() throws Exception {
    driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.id("wpName1")).clear();
    driver.findElement(By.id("wpName1")).sendKeys("meow");
    driver.findElement(By.id("wpPassword1")).clear();
    driver.findElement(By.id("wpPassword1")).sendKeys("meow");
    driver.findElement(By.id("wpLoginAttempt")).click();
    WebElement signUp = driver.findElement(By.linkText("create a new account"));
	assertTrue(signUp.isDisplayed());
  }
  
  /*Scenario 2-2:
   *Given on WiKirby home page,
   *When I click on 'create account' and try to create an account with a user name matching the password,
   *Then an error should appear
   */
  @Test
  public void testSamePassword() throws Exception {
    driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.id("wpName1")).clear();
    driver.findElement(By.id("wpName1")).sendKeys("meow");
    driver.findElement(By.id("wpPassword1")).clear();
    driver.findElement(By.id("wpPassword1")).sendKeys("meow");
    driver.findElement(By.id("wpLoginAttempt")).click();
    driver.findElement(By.linkText("Create account")).click();
    driver.findElement(By.id("wpName2")).clear();
    driver.findElement(By.id("wpName2")).sendKeys("meow");
    driver.findElement(By.id("wpPassword2")).clear();
    driver.findElement(By.id("wpPassword2")).sendKeys("meow");
    driver.findElement(By.id("wpRetype")).clear();
    driver.findElement(By.id("wpRetype")).sendKeys("meow");
    driver.findElement(By.id("wpCreateaccount")).click();
    assertTrue(driver.findElement(By.className("errorbox")).isDisplayed());
  }
  
  /*Scenario 2-3:
   *Given on WiKirby home page,
   *When I click on 'create account' and try to create an account with the two lines for password not matching,
   *Then an error should appear
   */
  @Test
  public void testMismatchedPasswords() throws Exception {
	driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    driver.findElement(By.linkText("Create account")).click();
    driver.findElement(By.id("wpName2")).clear();
    driver.findElement(By.id("wpName2")).sendKeys("meow");
    driver.findElement(By.id("wpPassword2")).clear();
    driver.findElement(By.id("wpPassword2")).sendKeys("cat");
    driver.findElement(By.id("wpRetype")).clear();
    driver.findElement(By.id("wpRetype")).sendKeys("cats");
    driver.findElement(By.id("wpCreateaccount")).click();
    assertTrue(driver.findElement(By.className("errorbox")).isDisplayed());
  }
  
  /*Scenario 2-4:
   *Given on WiKirby home page,
   *When I click on 'create account' and try to create an account without a user name,
   *Then an error should appear
   */
  @Test
  public void testNoUsername() throws Exception {
	driver.get("http://wikirby.com/wiki/Kirby_Wiki");
    driver.findElement(By.linkText("Create account")).click();
    driver.findElement(By.id("wpName2")).clear();
    driver.findElement(By.id("wpName2")).sendKeys("");
    driver.findElement(By.id("wpPassword2")).clear();
    driver.findElement(By.id("wpPassword2")).sendKeys("");
    driver.findElement(By.id("wpRetype")).clear();
    driver.findElement(By.id("wpRetype")).sendKeys("");
    driver.findElement(By.id("wpCreateaccount")).click();
    assertTrue(driver.findElement(By.className("errorbox")).isDisplayed());
  }
  
  /*Scenario 2-5:
   *Given on WiKirby home page,
   *and not logged in,
   *When I try to log in with a valid user name and incorrect password,
   *Then I should get a login error
   */
    @Test
    public void testIncorrectPassword() throws Exception {
      driver.get("http://wikirby.com/wiki/Kirby_Wiki");
      driver.findElement(By.linkText("Log in")).click();
      driver.findElement(By.id("wpName1")).clear();
      driver.findElement(By.id("wpName1")).sendKeys("Fubaka");
      driver.findElement(By.id("wpPassword1")).clear();
      driver.findElement(By.id("wpPassword1")).sendKeys("meow");
      driver.findElement(By.id("wpLoginAttempt")).click();
  	  assertTrue(driver.getPageSource().contains("Login error"));
    }
    
    
  
  

}
