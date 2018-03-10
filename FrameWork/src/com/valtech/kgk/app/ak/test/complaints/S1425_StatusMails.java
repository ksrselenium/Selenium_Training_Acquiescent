package com.valtech.kgk.app.ak.test.complaints;

import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.Test;



import com.valtech.kgk.businessFun.Common;
import com.valtech.kgk.businessFun.Complaints;
import com.valtech.kgk.businessFun.ShoppingCartFun;
import com.valtech.kgk.config.BaseTest;
import com.valtech.kgk.utilities.DataReader;

/** 
 * Company: valtech
 * Date: 30-Jul-2015
 * @author :Suresh k 
 *  Description:s1425---- 
 *This story is about enhancing the customer information when we are making changes to a claim at KGK. 
 *Better information to the customer in KGK-layout when a claim is either stopped or decided.
 
 */


public class S1425_StatusMails extends BaseTest{

	Common Cmn = new Common();
	GeneralFun gfun = new GeneralFun();
	ShoppingCartFun sc= new ShoppingCartFun();
	Complaints comp=new Complaints();
	DataReader readdata = new DataReader();
	public Properties TEXT=readdata.readText();
	public Properties OR=readdata.readObjectRepository();
	public mySoftAssert soft= gfun.myassert();
	Calendar cal=Calendar.getInstance();                               
	int date = cal.get(Calendar.DATE);


	@Test(priority=1)
	public void S1425_Stopped_StatusMailVerifycation() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();	
		// Method to file new complaint
		S1425_FileNewCmlt();
		String lopnr=driver.findElement(By.xpath(OR.getProperty("ComplaintNo"))).getText();
		System.out.println("lopno=" + lopnr);
		Cmn.logout(driver);
	
	// login as a adimn user (kgk.new/test123)---changing status of complaint
		Cmn.loginToApp(driver,"Excelpath","Login",0,1);            
		Cmn.selectCustomer(driver,"Excelpath","Customer",0, 17);
	//Navigating to complaint admin page 
		comp.navigatetoAdminstreraReklamationerpage(driver);
	// Verifying lopnr search
		WebElement complaintsearchoptionlop= driver.findElement(By.xpath(OR.getProperty("Cmp_SearchFieldDropdown")));
		Select select1= new Select(complaintsearchoptionlop);
		select1.selectByIndex(0);
		
	//valid lopnr search		
		driver.findElement(By.xpath(OR.getProperty("Cmp_NummerTextBox"))).sendKeys(lopnr);
		driver.findElement(By.xpath(OR.getProperty("Cmp_SearchBtn"))).click();
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("Cmp_Row1LopNoValueLnk"))).click();
		gfun.waitForPageToLoad(driver);
	//Verifying lopnr in admin edite page
		gfun.verifytext(driver, OR.getProperty("CmpAdminEditLopnummerValue"), lopnr, false);
	// Verifying status dropdown
	gfun.verifytextaftertrim(driver,OR.getProperty("Cmp_AdminStatus_fieldlabel"),"Comp_Admin_Status",true);
		
	// Changing status to CLOSED (Avslutad) and verifying-----Ansvarig tekniker: (Technician)
		WebElement CmpAdminCardStatusDd= driver.findElement(By.xpath(OR.getProperty("CmpAdminCardStatusDd")));
	    Select	select= new Select(CmpAdminCardStatusDd);
		
		// Changing status to CLOSED (Avslutad) and verifying-----Ansvarig tekniker: (Technician)
		select.selectByValue("CLOSED");
		Thread.sleep(1000);
		gfun.verifytext(driver, OR.getProperty("cmpntTectn"), "kgk.new", false);
		gfun.isElementPresent(driver, OR.getProperty("cmpntTectn"), true);
		driver.findElement(By.xpath(OR.getProperty("TabrtTectn"))).click();
		gfun.waitForPageToLoad(driver);
						
		// Changing status to decided (Beslutad) and verifying-----Ansvarig tekniker: (Technician)
		select.selectByValue("DECIDED");
		Thread.sleep(1000);
		gfun.isElementPresent(driver, OR.getProperty("cmpntTectn"), true);
		gfun.verifytext(driver, OR.getProperty("cmpntTectn"), "kgk.new", false);
			
		// Changing status to Blocked (stapppad) and verifying-----Ansvarig tekniker: (Technician)
		select.selectByValue("BLOCKED");
			gfun.waitForPageToLoad(driver);
	//selecting options ----unser Saknas: section

	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasKGKArtiklelnrChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknas_KopiaarbfakturaChkbox"))).click();
  	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasLevdatumChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasSkade_datumChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasFeletsartChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasRegnrfordonChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasMätarställningChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasKörsträckaChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasFotoChkbox"))).click();
	driver.findElement(By.xpath(OR.getProperty("CompAdminCardSaknasKvittoChkbox"))).click();
	//Entering text in Statusmeddelande till kund:
	driver.findElement(By.xpath(OR.getProperty("CmpAdminCardStatusNotefield"))).sendKeys("Verifying stopped complaint mails");
		
////Entering text in Interna anteckningar:
	
	driver.findElement(By.xpath(OR.getProperty("CmpAdminCardInternalnotesbox"))).sendKeys("Verifying stopped complaint mails");
	
//Saving the stopped status complaint
	driver.findElement(By.xpath(OR.getProperty("CmpAdminCardSecondCompalintSaveBtn"))).click();
	Alert StoppadStatusWarnWithoutReason= driver.switchTo().alert();
	gfun.verifyvalue(driver, StoppadStatusWarnWithoutReason.getText(), TEXT.getProperty("CmpAdminCardStoppadPrinttext"));
	StoppadStatusWarnWithoutReason.dismiss();
	gfun.waitForPageToLoad(driver);
	
	Cmn.logout(driver);
		
	
	//verify the mail in outlook
	driver.get("https://webmail.kgk.se/CookieAuth.dll?GetLogon?curl=Z2FowaZ2F&reason=0&formdir=6");
	gfun.waitForPageToLoad(driver);
	//enter the credientials
	driver.findElement(By.xpath(OR.getProperty("ERUserName"))).sendKeys("ztest");
	driver.findElement(By.xpath(OR.getProperty("ERPassword"))).sendKeys("kHgjvwJg");
	driver.findElement(By.xpath(OR.getProperty("ERLogOn"))).click();
	gfun.waitForPageToLoad(driver);
	//click on the mail
	String Emai_stpdlopnr=driver.findElement(By.xpath("(//div[contains(text(),'"+lopnr+"')])[3]")).getText().substring(12, 17);
	System.out.println("Lopnumber in mail ="+ Emai_stpdlopnr);
	gfun.verifyvalue(driver, lopnr, Emai_stpdlopnr);
	driver.findElement(By.xpath("(//div[contains(text(),'"+lopnr+"')])[3]")).click();
	Thread.sleep(1000);
	
// Verifying email content--for stopped complaints

	gfun.verifytextaftertrim(driver,OR.getProperty("MailHeading"),"Reklamation stoppad",false);
	gfun.verifytextaftertrim(driver,OR.getProperty("AtagardHeadng"),"Åtgärd",false);
	gfun.verifytextaftertrim(driver,OR.getProperty("MeddelandeHedng"),"Meddelande",false);
	
		
	//Deletnng abobve complaint in application

	gfun.navigateUrl(driver, "AkUrl"); 	
	Cmn.loginToApp(driver,"Excelpath","Login",0,1);            
	Cmn.selectCustomer(driver,"Excelpath","Customer",0, 17);
//Navigating to complaint admin page 
	comp.navigatetoAdminstreraReklamationerpage(driver);

	// Verifying lopnr search
	
	WebElement complaintsearchoptionlop1= driver.findElement(By.xpath(OR.getProperty("Cmp_SearchFieldDropdown")));
	Select select2= new Select(complaintsearchoptionlop1);
	select2.selectByIndex(0);
	//valid lopnr search		
	driver.findElement(By.xpath(OR.getProperty("Cmp_NummerTextBox"))).sendKeys(lopnr);
	driver.findElement(By.xpath(OR.getProperty("Cmp_SearchBtn"))).click();
	gfun.waitForPageToLoad(driver);
	Thread.sleep(3000);
	driver.findElement(By.xpath(OR.getProperty("Cmp_Row1TabortImg"))).click();
	Alert alert4 = driver.switchTo().alert();
	alert4.accept();
	Cmn.logout(driver);
	soft.assertAll();

	
	}	
	
	
	@Test(priority=2)
	public void S1425_Beslutad_StatusMailVerifycation() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();	
		// Method to file new complaint
		S1425_FileNewCmlt();
		String lopnr=driver.findElement(By.xpath(OR.getProperty("ComplaintNo"))).getText();
		System.out.println("lopno=" + lopnr);
		
		Cmn.logout(driver);
	
	// login as a adimn user (kgk.new/test123)---changing status of complaint
		Cmn.loginToApp(driver,"Excelpath","Login",0,1);            
		Cmn.selectCustomer(driver,"Excelpath","Customer",0, 17);
	//Navigating to complaint admin page 
		comp.navigatetoAdminstreraReklamationerpage(driver);
	// Verifying lopnr search
		WebElement complaintsearchoptionlop= driver.findElement(By.xpath(OR.getProperty("Cmp_SearchFieldDropdown")));
		Select select1= new Select(complaintsearchoptionlop);
		select1.selectByIndex(0);
		
	//valid lopnr search		
		driver.findElement(By.xpath(OR.getProperty("Cmp_NummerTextBox"))).sendKeys(lopnr);
		driver.findElement(By.xpath(OR.getProperty("Cmp_SearchBtn"))).click();
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("Cmp_Row1LopNoValueLnk"))).click();
		gfun.waitForPageToLoad(driver);
	//Verifying lopnr in admin edite page
		gfun.verifytext(driver, OR.getProperty("CmpAdminEditLopnummerValue"), lopnr, false);
	// Verifying status dropdown
	gfun.verifytextaftertrim(driver,OR.getProperty("Cmp_AdminStatus_fieldlabel"),"Comp_Admin_Status",true);
		
// selecting Beslutad option in status drop down
		WebElement CmpAdminCardStatusDd= driver.findElement(By.xpath(OR.getProperty("CmpAdminCardStatusDd")));
	    Select	select= new Select(CmpAdminCardStatusDd);
		select.selectByValue("DECIDED");
// selecting GodKand Option in Besult drop down

		WebElement CmpAdminCardBeslutDd= driver.findElement(By.xpath(OR.getProperty("CmpAdminCardBeslutDd")));
		select= new Select(CmpAdminCardBeslutDd);
		select.selectByValue("APPROVED");
	
	  //Entering text in 	Beslutsmeddelande till kund: text box
		driver.findElement(By.xpath(OR.getProperty("CmpAdminCardDecisionNotefield"))).sendKeys("verifying decided status mails");	
		//Entering text in 	Interna anteckningar: text box		
		driver.findElement(By.xpath(OR.getProperty("CmpAdminCardInternalnotesbox"))).sendKeys("verifying decided status mails");
		Thread.sleep(2000);
			
		
//Saving the Beslutad status complaint
	driver.findElement(By.xpath(OR.getProperty("CmpAdminCardSecondCompalintSaveBtn"))).click();
	 gfun.waitForPageToLoad(driver);
	Cmn.logout(driver);
	
	//verify the mail in outlook
	driver.get("https://webmail.kgk.se/CookieAuth.dll?GetLogon?curl=Z2FowaZ2F&reason=0&formdir=6");
	gfun.waitForPageToLoad(driver);
	//enter the credientials
	driver.findElement(By.xpath(OR.getProperty("ERUserName"))).sendKeys("ztest");
	driver.findElement(By.xpath(OR.getProperty("ERPassword"))).sendKeys("kHgjvwJg");
	driver.findElement(By.xpath(OR.getProperty("ERLogOn"))).click();
	gfun.waitForPageToLoad(driver);
	//click on the mail
	String Emai_Bsltdlopnr=driver.findElement(By.xpath("(//div[contains(text(),'"+lopnr+"')])[3]")).getText().substring(12, 17);
	System.out.println("Lopnumber in mail ="+ Emai_Bsltdlopnr);
	gfun.verifyvalue(driver, lopnr, Emai_Bsltdlopnr);
	driver.findElement(By.xpath("(//div[contains(text(),'"+lopnr+"')])[3]")).click();
	Thread.sleep(1000);
	
// Verifying email content--for stopped complaints

	gfun.verifytextaftertrim(driver,OR.getProperty("MailHeading_Besult"),"Reklamation beslutad",false);
	gfun.verifytextaftertrim(driver,OR.getProperty("GodkändHeadng"),"Godkänd",false);
	gfun.verifytextaftertrim(driver,OR.getProperty("MeddelandeHedng"),"Meddelande",false);
	//gfun.verifytextaftertrim(driver,OR.getProperty("SpoddedCmntText"),"Verifying stopped complaint mails",false);
			
	gfun.navigateUrl(driver, "AkUrl"); 	
	Cmn.loginToApp(driver,"Excelpath","Login",0,1);            
	Cmn.selectCustomer(driver,"Excelpath","Customer",0, 17);
//Navigating to complaint admin page 
	comp.navigatetoAdminstreraReklamationerpage(driver);

	// Verifying lopnr search
	
	WebElement complaintsearchoptionlop1= driver.findElement(By.xpath(OR.getProperty("Cmp_SearchFieldDropdown")));
	Select select2= new Select(complaintsearchoptionlop1);
	select2.selectByIndex(0);
	//valid lopnr search		
	driver.findElement(By.xpath(OR.getProperty("Cmp_NummerTextBox"))).sendKeys(lopnr);
	driver.findElement(By.xpath(OR.getProperty("Cmp_SearchBtn"))).click();
	gfun.waitForPageToLoad(driver);
	Thread.sleep(3000);
	driver.findElement(By.xpath(OR.getProperty("Cmp_Row1TabortImg"))).click();
	Alert alert4 = driver.switchTo().alert();
	alert4.accept();
	Cmn.logout(driver);
	soft.assertAll();

	
	}	
	
// Below method used to file the new complaint	
	
	public void S1425_FileNewCmlt() throws BiffException, IOException, InterruptedException
	{
			
		gfun.navigateUrl(driver, "AkUrl"); 	
		Cmn.loginToApp(driver,"Excelpath","Login", 0, 26);
		comp.navigateToReklamationerTab(driver);
		//Some times if previously complaint is stopped in batch page ,then we get continue and discard button , so If discard button exists , click on discard button		
		boolean batchCompcheck=gfun.isElementPresent(driver, OR.getProperty("complantdiscardBtn"));
		if (batchCompcheck==true)
		{
			driver.findElement(By.xpath(OR.getProperty("complantdiscardBtn"))).click();
			gfun.waitForPageToLoad(driver);
		}

		driver.findElement(By.xpath(OR.getProperty("Cmp_SkapanyReklamationBtn"))).click();
     	gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("Cmp_FortsattBtn"))).click();
		gfun.waitForPageToLoad(driver);
		//Verifying ANNAT radio button --it should select by default
		boolean flag_1=driver.findElement(By.xpath(OR.getProperty("RegCmp_AnnatRadioBtn"))).isSelected();
		soft.assertTrue(flag_1);
										
		//6 is value for contact person from testdata sheet	
		comp.enterContactPersonDetails1(driver,6);
							
		//Regno section while filing a complaint 
		gfun.verifytext(driver,OR.getProperty("RegCmp_RegnrLabel"),"Regnr",false);
				
		driver.findElement(By.xpath(OR.getProperty("RegCmp_RegnrTextBox"))).sendKeys("AAA001");
		driver.findElement(By.xpath(OR.getProperty("RegCmp_RegnrSokBtn"))).click();
		gfun.waitForElement(driver, 10, OR.getProperty("RegCmp_vehicleIdentifiedMsgTxt"));
		driver.findElement(By.xpath(OR.getProperty("RegCmp_MatarstallningTextBox"))).sendKeys("1");	
       driver.findElement(By.xpath(OR.getProperty("RegCmp_Annat_KorstrackaTextBox"))).sendKeys("1");
		String articlenumber=readdata.excel("Excelpath","Complaints", 0,7);//7 is the article number from testdata 
		driver.findElement(By.xpath(OR.getProperty("RegCmp_ArtikelnummerTextBox"))).sendKeys(articlenumber); 
		driver.findElement(By.xpath(OR.getProperty("RegCmp_AntalTextBox"))).sendKeys("1"); 
		driver.findElement(By.xpath(OR.getProperty("RegCmp_SkadedatumTextBox"))).click();
		driver.findElement(By.xpath("//a[contains(text(),'"+date+"') and @href='#']")).click();

		WebElement dd2 = driver.findElement(By.xpath(OR.getProperty("RegCmp_VidEjGodkandDropdown")));
		Select select = new Select(dd2);
		select.selectByVisibleText("Kostnadsförslag för reparation");
		driver.findElement(By.xpath(OR.getProperty("RegCmp_OtherInfoTextBox"))).sendKeys("Automation Testing ");
	 // Entering---	work performed detailes (Utfört arbete och arbetsrelaterat materiel )
		
	//	comp.RegCmp_utfortarbete(driver);	
	
		//Uploading FilE	
		comp.uploadDocComplaints(driver,gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\12104_12244_Supervinch_61-1219-13281_188.jpg");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Doc_LaddaUppBtn"))).click();		
      	gfun.waitForPageToLoad(driver);
	  	
		System.out.println(driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).isDisplayed());
		System.out.println(driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).isEnabled());
		driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).click();
        Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("cmpSaved_SparaOchSkicka"))).click();
		Thread.sleep(1000);
		Alert alert7msg=driver.switchTo().alert();
		String alert7msg1=alert7msg.getText();
		gfun.verifyvalue(driver, alert7msg1, TEXT.getProperty("alert7msg"));
		alert7msg.dismiss() ;

		
	}
	
	
	
}
