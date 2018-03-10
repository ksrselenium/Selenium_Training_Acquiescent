package com.valtech.kgk.businessFun;

import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.valtech.kgk.utilities.DataReader;

public class Complaints {


	GeneralFun gfun=new GeneralFun();
	Common com=new Common();
	DataReader readdata=new DataReader();       
	public Properties CONFIG=readdata.readConfig();
	public Properties OR =readdata.readObjectRepository();
	public Properties TEXT=readdata.readText();
	Calendar cal=Calendar.getInstance();                               
	int date = cal.get(Calendar.DATE);
	public mySoftAssert soft= gfun.myassert();

	/**
	 * Method Name: navigatetoAdminstreraReklamationerpage
	 * Description: This function takes the user to Administrera Reklamationer page.
	 */

	public void navigatetoAdminstreraReklamationerpage(WebDriver driver) throws InterruptedException
	{
		soft.clearMaps();
		driver.findElement(By.xpath(OR.getProperty("minasidorTab"))).click();
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("Cmp_AdministreraReklamationerTab"))).click();
		gfun.waitForPageToLoad(driver);
        soft.assertAll();
	}

	/**
	 * Method Name: navigateToReklamationerTab
	 * Description: This function takes the user to Reklamationer Tab page.
	 */
	public void navigateToReklamationerTab(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click(); 
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("Cmp_ReklamationerTab"))).click(); 
	}




	/**
	 * Method Name: enterContactPersonDetails
	 * Description: Entering Mandatory Contact person name,and clicking on E-post and Telnr link
	 */
	public void enterContactPersonDetails(WebDriver driver, int val) throws InterruptedException, BiffException, IOException
	{
		soft.clearMaps();
		gfun.verifytext(driver,OR.getProperty("RegCmp_KundLabel"),"Kund",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_KundValue"),"Kund\nBilomatic Bil AB (Citroën)",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_LeveransadressLabel"),"Leveransadress",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_LeveransaddressValue"),"Stångjärnsgatan 3, 753 23 UPPSALA",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_KundnrLabel"),"Kundnr",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_KundnrValue"),"Kundnr\n21224",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_KontaktPersonLabel"),"Kontaktperson *",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_VaransAgareLabel"),"Kontaktinformation varans ägare",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_ArtikelBenamningLabel"),"Artikelbenämning",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_LopnummerIannatLabel"),"Löpnummer i annat system",false);

		String Spec = driver.findElement(By.xpath(OR.getProperty("RegCmp_EPostLabel"))).getText();
		String Spec1[] = Spec.split("\n");
		System.out.println(Spec1[0].trim());
		gfun.verifyvalue(driver, Spec1[0].trim(),TEXT.getProperty("RegCmp_EPostLabel"));
		String Spec2 = driver.findElement(By.xpath(OR.getProperty("RegCmp_TelnrLabel"))).getText();
		String Spec3[] = Spec2.split("\n");
		System.out.println(Spec3[0].trim());
		gfun.verifyvalue(driver, Spec3[0].trim(),TEXT.getProperty("RegCmp_TelnrLabel"));

		gfun.verifytext(driver,OR.getProperty("RegCmp_SkapadLabel"),"Skapad",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_LopnummerLabel"),"Löpnummer",false);

		String contactPerson=readdata.excel("Excelpath","Complaints", 0,val);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_KontaktPersonTextBox"))).sendKeys(contactPerson);            
		Thread.sleep(2000);

		// validate E-post wrong format
		driver.findElement(By.xpath(OR.getProperty("RegCmp_EPostTextBox"))).sendKeys("testing emailformat");            
		
		
		driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).click();
		Alert alert = driver.switchTo().alert();
		String alertmsg7=alert.getText();
		gfun.verifyvalue(driver, alertmsg7, TEXT.getProperty("alertmsg7"));
		alert.accept();	 
		
		driver.findElement(By.xpath(OR.getProperty("RegCmp_EPostLnk"))).click();
		Thread.sleep(1000);
		//Validate Telephone number 
		driver.findElement(By.xpath(OR.getProperty("RegCmp_TelnrTextBox"))).sendKeys("char in phonefield");            
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).click();
		alert = driver.switchTo().alert();
		String alertmsg8=alert.getText();
		gfun.verifyvalue(driver, alertmsg8, TEXT.getProperty("alertmsg8"));
		alert.accept();			
		driver.findElement(By.xpath(OR.getProperty("RegCmp_TelnrLnk"))).click();
	gfun.waitForPageToLoad(driver);


	}

public void enterContactPersonDetails1(WebDriver driver, int val) throws InterruptedException, BiffException, IOException
	{
		soft.clearMaps();
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_KundLabel"),"Kund",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_KundValue"),"Kund\nPerformance Products Ltd",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_LeveransadressLabel"),"Leveransadress",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_LeveransaddressValue"),"Stuart Rd, Manor Park, GB-WA71UL RUNCOM",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_KundnrLabel"),"Kundnr",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_KundnrValue"),"Kundnr\n20218",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_KontaktPersonLabel"),"Kontaktperson *",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_VaransAgareLabel"),"Kontaktinformation varans ägare",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_ArtikelBenamningLabel"),"Artikelbenämning",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_LopnummerIannatLabel"),"Löpnummer i annat system",false);

		String Spec = driver.findElement(By.xpath(OR.getProperty("RegCmp_EPostLabel"))).getText();
		String Spec1[] = Spec.split("\n");
		System.out.println(Spec1[0].trim());
		gfun.verifyvalue(driver, Spec1[0].trim(),TEXT.getProperty("RegCmp_EPostLabel"));
		String Spec2 = driver.findElement(By.xpath(OR.getProperty("RegCmp_TelnrLabel"))).getText();
		String Spec3[] = Spec2.split("\n");
		System.out.println(Spec3[0].trim());
		gfun.verifyvalue(driver, Spec3[0].trim(),TEXT.getProperty("RegCmp_TelnrLabel"));

		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_SkapadLabel"),"Skapad",false);
		gfun.verifytextaftertrim(driver,OR.getProperty("RegCmp_LopnummerLabel"),"Löpnummer",false);

		String contactPerson=readdata.excel("Excelpath","Complaints", 0,val);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_KontaktPersonTextBox"))).sendKeys(contactPerson);            
		Thread.sleep(2000);

		// validate E-post wrong format
		driver.findElement(By.xpath(OR.getProperty("RegCmp_EPostTextBox"))).sendKeys("testing emailformat");            
		
		
		driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		String alertmsg7=alert.getText();
		gfun.verifyvalue(driver, alertmsg7, TEXT.getProperty("alertmsg7"));
		alert.accept();	 
		
		driver.findElement(By.xpath(OR.getProperty("RegCmp_EPostLnk"))).click();
		Thread.sleep(1000);
		//Validate Telephone number 
		driver.findElement(By.xpath(OR.getProperty("RegCmp_TelnrTextBox"))).sendKeys("char in phonefield");            
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_LaggtillReklameradArtikelBtn"))).click();
		Thread.sleep(1000);
		alert = driver.switchTo().alert();
		String alertmsg8=alert.getText();
		gfun.verifyvalue(driver, alertmsg8, TEXT.getProperty("alertmsg8"));
		alert.accept();			
		driver.findElement(By.xpath(OR.getProperty("RegCmp_TelnrLnk"))).click();
	   gfun.waitForPageToLoad(driver);


	}


	/**
	 * Method Name: enterDackRadioBtnPanel
	 * Description: Entering Mandatory details while clicking on Dack radio button (Article number,Skadedatum, 
	 * Leveransdatum, Monteringsdatum
	 */
	public void enterDackRadioBtnPanel(WebDriver driver, int val) throws InterruptedException, BiffException, IOException
	{
		String articlenumber=readdata.excel("Excelpath","Complaints", 0,val);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_ArtikelnummerTextBox"))).sendKeys(articlenumber);           
		driver.findElement(By.xpath(OR.getProperty("RegCmp_SkadedatumTextBox"))).click();
		driver.findElement(By.xpath("//a[contains(text(),'"+date+"') and @href='#']")).click();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_LeveransdatumTextBox"))).click();
		driver.findElement(By.xpath("//a[contains(text(),'"+date+"') and @href='#']")).click();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_MonteringsdatumTextBox"))).click();
		driver.findElement(By.xpath("//a[contains(text(),'"+date+"') and @href='#']")).click();
		Thread.sleep(2000);

	}

	/**
	 * Method Name: DackValidData_Varuspecifikt
	 * Description: Entering Mandatory details in Varuspecifikt tab while clicking on Dack radio button 
	 * Fabrikat, Modell, Storlek, Belastnings, Tillverkningsvecka, Korstracka, Monsterdjup, Specific rekl., ,Dack position
	 */
	public void DackValidData_Varuspecifikt(WebDriver driver, int fab,int mod,int stor,int till, int kor,int mons) throws InterruptedException, BiffException, IOException
	{
		String fabrikat=readdata.excel("Excelpath","Complaints", 0,fab);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_FabrikatTextBox"))).sendKeys(fabrikat);            
		String modell=readdata.excel("Excelpath","Complaints", 0,mod);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_ModellTextBox"))).sendKeys(modell);            
		String storlek=readdata.excel("Excelpath","Complaints", 0,stor);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_StorlekTextBox"))).sendKeys(storlek);            
		WebElement belastningsdDD = driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_BelastningsDropdown")));
		Select select1 = new Select(belastningsdDD);
		select1.selectByVisibleText("R");  
		String Tillverkningsvecka =readdata.excel("Excelpath","Complaints", 0,till);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_TillverkningsveckaTextBox"))).sendKeys(Tillverkningsvecka);            
		String korstracka=readdata.excel("Excelpath","Complaints", 0,kor);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_KorstrackaTextBox"))).sendKeys(korstracka);            
		String monsterdjup=readdata.excel("Excelpath","Complaints", 0,mons);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_MonsterdjupTextBox"))).sendKeys(monsterdjup); 
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_SpecificReklListBoxVal11"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_HFRadioBtn"))).click();
		Thread.sleep(1000);
		
		
	}

	/**
	 * Method Name: ClearDackFields
	 * Description: This function will clear the text fields under dack Varuspecifikt tab..
	 * (Fabrikat, Modell, Storlek, Belastnings, Tillverkningsvecka, Korstracka, Monsterdjup, Specific rekl., ,Dack position)
	 */
	public void ClearDackFields(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_FabrikatTextBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_ModellTextBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_StorlekTextBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_TillverkningsveckaTextBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_KorstrackaTextBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Dack_MonsterdjupTextBox"))).clear();
	}

	/**
	 * Method Name: uploadDocComplaints
	 * Description: This function will upload documents for a complaints
	 */
	public void uploadDocComplaints(WebDriver driver,String exepath) throws InterruptedException, BiffException, IOException
	{
		gfun.verifytext(driver,OR.getProperty("RegCmp_Doc_UploadDocumentTab"),"RegCmp_Doc_UploadDocumentTab",true);
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Doc_UploadDocumentTab"))).click();
		gfun.verifytext(driver,OR.getProperty("RegCmp_Doc_Header"),"RegCmp_Doc_Header",true);            
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Doc_BrowseBtn"))).sendKeys(exepath);
		gfun.waitForPageToLoad(driver);
		

	}


	/**
	 * Method Name: RegCmp_utfortarbete
	 * Description: This function will verify the functionality of work performed and rebatt calculation complaints
	 */
	public void RegCmp_utfortarbete(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
	
		soft.clearMaps();
		//driver.findElement(By.xpath(OR.getProperty("RegCmp_UtfortArbeteTab"))).click(); 
		
		driver.findElement(By.xpath(OR.getProperty("RegCmp_VisaLinkWorkdetails"))).click();
		gfun.waitForPageToLoad(driver);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_Header"),"Alla fält på respektive rad måste fyllas i för att raden ska sparas. Töm alla fält på en rad för att ta bort raden.",false);
		gfun.waitForPageToLoad(driver);
	
		//gfun.verifytext(driver,,"Utfört arbete",false);
		gfun.verifytextaftertrim(driver, OR.getProperty("RegCmp_Utfort_UtfortArbeteLabel"),  "Utfört arbete"  , false);
		
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_TimmarLabel"),"Timmar",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_TimprisLabel"),"Timpris",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_BeloppLabel1"),"Belopp",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_BeviljatLabel1"),"Beviljat",false);
		gfun.verifytextaftertrim(driver, OR.getProperty("RegCmp_Utfort_UtbyttDelLabel"),  "Arbetsrelaterat materiel"  , false);
				
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_AntalLabel"),"Antal",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_APrisLabel"),"Á pris",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_ArticleLabel"),"Artikelnummer",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_BeloppLabel2"),"Belopp",false);
		gfun.verifytext(driver,OR.getProperty("RegCmp_Utfort_BeviljatLabel2"),"Beviljat",false);
		
		//First section
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_UtfortArbete_Value"))).sendKeys("work performed");
		Actions act = new Actions(driver);
		act.doubleClick(driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_Timmar_Value")))).perform();
		Alert alert = driver.switchTo().alert();
		String Cmp_Timmaralert=alert.getText();
		gfun.verifyvalue(driver, Cmp_Timmaralert, TEXT.getProperty("Cmp_Timmaralert"));
		alert.accept();

		Actions act1 = new Actions(driver);
		act1.doubleClick(driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_Belopp1_Value")))).perform();
		alert = driver.switchTo().alert();
		String Cmp_Beloppalert=alert.getText();
		gfun.verifyvalue(driver, Cmp_Beloppalert, TEXT.getProperty("Cmp_Beloppalert"));
		alert.accept();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_Timmar_Value"))).sendKeys("2,1");
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_Timpris_Value"))).sendKeys("3,1");

		//second section
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_UtbyttDel_Value"))).sendKeys("rebatt calculation");
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_Antal_Value"))).sendKeys("2,1");
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Utfort_APris_Value"))).sendKeys("3,1");
		driver.findElement(By.xpath(OR.getProperty("Cmplnt_Utfort_Articlenumbr"))).sendKeys("dp 4");
		soft.assertAll();
	}
	/**
	 * Method Name: verifyLopnummerDropdown
	 * Description: This function will verify lopnr dropdown present or not
	 */
	public Boolean verifyLopnummerDropdown(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String dd = driver.findElement(By.xpath(OR.getProperty("Cmp_SearchFieldDropdown"))).getText();
		String value="Löpnummer";
		Boolean verifyVal=dd.contains(value);
		return verifyVal;     
	}  
	/**
	 * Method Name: verifyKundensLopnummerDropdown
	 * Description: This function will verify kundenslopnr dropdown present or not
	 */
	public Boolean verifyKundensLopnummerDropdown(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		
		String dd1 = driver.findElement(By.xpath(OR.getProperty("Cmp_SearchFieldDropdown"))).getText();
		String value1="Kundens löpnummer";
		Boolean verifyVal1=dd1.contains(value1);
		return verifyVal1;     
		
	}

	/**
	 * Method Name: validateKorstrackaStartBatteri
	 * Description: This function will validate the korstracka field by giving different inputs
	 */
	public void validateKorstrackaStartBatteri(WebDriver driver, String val) throws InterruptedException, BiffException, IOException
	{     
		soft.clearMaps();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Star_KorstrackaTextBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("RegCmp_Star_KorstrackaTextBox"))).sendKeys(val);            
		driver.findElement(By.xpath(OR.getProperty("CmpAdminCardadminSaveBtn"))).click();		
		Alert alert4 = driver.switchTo().alert();
		String alertmsg=alert4.getText();
		gfun.verifyvalue(driver, alertmsg, "Artikel finns ej i orderhistoriken: 572409068 3132. Vill du fortsätta?");
		alert4.accept();
	    Thread.sleep(3000);
		Alert alert7 = driver.switchTo().alert();
		String alertmsg1=alert7.getText();
		gfun.verifyvalue(driver, alertmsg1, "Fältet körsträcka måste vara ett positivt heltal eller 0.");
		alert7.accept();
		soft.assertAll();
	}






}
