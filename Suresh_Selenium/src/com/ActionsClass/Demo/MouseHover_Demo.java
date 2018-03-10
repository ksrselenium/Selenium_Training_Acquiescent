package com.ActionsClass.Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MouseHover_Demo {
	WebDriver driver;

@Test

public void Mousehover_SpiceJet_LoginBtn() throws InterruptedException
{

	System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.get("http://book.spicejet.com/?utm_source=Brand%20Campaign%20_&utm_medium=cpc_google&utm_term=O%26D&utm_campaign=Spicejet%20all%20route%20campaign&gclid=EAIaIQobChMIkvySqcCp2AIVGV4ZCh1w0QHnEAAYASAAEgIw-fD_BwE");
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	
	
	WebElement mosuseHover_LoginBtn=driver.findElement(By.id("Login"));
	
	Actions act = new Actions(driver);
	act.moveToElement(mosuseHover_LoginBtn).perform();
	Thread.sleep(1000);
	
	WebElement mosuseHover_SpiceCash_SpiceClubMembers=driver.findElement(By.partialLinkText("SpiceCash/SpiceClub Members"));
	
	act.moveToElement(mosuseHover_SpiceCash_SpiceClubMembers).perform();
	Thread.sleep(1000);
	
	WebElement Clickon_MemberLogin=driver.findElement(By.partialLinkText("Member Login"));
	
	act.moveToElement(Clickon_MemberLogin).click().perform();
	
	Thread.sleep(1000);
	
	



}


}
