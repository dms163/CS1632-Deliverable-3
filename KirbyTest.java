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
  
  

}
