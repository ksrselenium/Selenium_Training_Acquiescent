package com.valtech.kgk.app.ak.test.shoppingCart;

import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.valtech.kgk.businessFun.Common;
import com.valtech.kgk.businessFun.SearchArticle;
import com.valtech.kgk.businessFun.ShoppingCartFun;
import com.valtech.kgk.config.BaseTest;
import com.valtech.kgk.utilities.DataReader;

/**
 * Company: Valtech
 * Date:
 * @author lavanya.idamakanti
 * Description: Importing of difference kinds of articles
 * TestCases : TC15, TC18 to TC22, TC33 and TC35
 */

public class SCImportArticles extends BaseTest 
{
	Common Cmn= new Common();
	SearchArticle Sa=new SearchArticle();
	ShoppingCartFun Sc=new ShoppingCartFun();
	DataReader readdata= new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();
	public Properties OR=readdata.readObjectRepository();
	public Properties CONFIG=readdata.readConfig();
	public mySoftAssert soft= gfun.myassert();

	@Test(priority=1)
	public void importStatus20Article() throws BiffException, IOException, InterruptedException
	{		
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\statsu20import.exe");
		//driver.findElement(By.xpath(OR.getProperty("scImportbutton"))).click();		
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\st20.txt");
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(3000);		
		gfun.verifytext(driver, OR.getProperty("scImportFileDialogHdrTxt"), "scImportFileDialogHdrValueTxt", true);
		gfun.verifytext(driver, OR.getProperty("scImportFileDialogHdrTxt1"), "scImportFileDialogHdrValueTxt1", true);
		gfun.verifytext(driver, OR.getProperty("scImportFileDialogHdrTxt2"), "scImportFileDialogHdrValueTxt2", true);
		gfun.verifytext(driver, OR.getProperty("ScimportSerialNumTxt"), "scVerifyImportSerialNumTxt", true);
		gfun.verifytext(driver, OR.getProperty("ScimportArticleNumTxt"), "scVerifyimportArticleNumTxt", true);
		gfun.verifytext(driver, OR.getProperty("ScimportBenamningTxt"), "scVerifyimportBenamningTxt", true);
		gfun.verifytext(driver, OR.getProperty("ScimportAntalTxt"), "scVerifyimportAntalTxt", true);
		//Thread.sleep(2000);
		gfun.verifytext(driver, OR.getProperty("ScimportStatusTxt"), "scVerifyimportStatusTxt", true);
		
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		driver.findElement(By.xpath(OR.getProperty("scAntalFieldEdtBox"))).clear();
		driver.findElement(By.xpath(OR.getProperty("scAntalFieldEdtBox"))).sendKeys("3");
		driver.findElement(By.xpath(OR.getProperty("ScuppdateraBtn"))).click();
		Thread.sleep(1000);
		Cmn.placeOrder(driver);
		deleteOrder(driver);
		Cmn.logout(driver);
		soft.assertAll();

	}		    
	
	@Test(priority=2)
	public void importNonBreableandBreakableArticle() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		//NonBreakeable and Breakable article
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\Breakable_and_nonbreakable.exe");
		//driver.findElement(By.xpath(OR.getProperty("scImportbutton"))).click();
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\Breakable_and_nonbreakable.txt");
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(3000); 
		gfun.verifytext(driver, OR.getProperty("scImportFileDialogHdrTxt2"), "ScimportWrongFormatFileErrorMsgValueTxt", true);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();		
		Thread.sleep(3000);
		Cmn.placeOrder(driver);
		deleteOrder(driver);
		Cmn.logout(driver);
		soft.assertAll();

	}	
	
	@Test(priority=3)
	public void importNonSellableAndClashingArticle() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Thread.sleep(3000);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		 //NonSellable and Clashing article
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000);
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\NonSellable_and_ClashingArticle.exe");
		
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\Nonsellable.txt");
	
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		gfun.verifytext(driver, OR.getProperty("scNonSellableErrorTxt"), "scNonSellableErrorValueTxt", true);
		gfun.verifytext(driver, OR.getProperty("scClashingErrorTxt"), "scClashingErrorValueTxt", true);
		driver.findElement(By.xpath(OR.getProperty("ScimportAvbrytBtn"))).click();
		Cmn.logout(driver);
		soft.assertAll();

		
	}
	
	@Test(priority=4)
	public void importSpecialFeeAndAlliasArticle() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Thread.sleep(3000);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		
		//	SpecialFee Article And Allias number
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		//Thread.sleep(3000);
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\Specialfee.exe");
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\Specialfee.txt");

		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		Thread.sleep(3000);
		Cmn.placeOrder(driver);
		deleteOrder(driver);
		Cmn.logout(driver);
		soft.assertAll();
	
	}
	
	@Test(priority=5)
	public void importAliasChkBoxValidation() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("ScincludeAlliasChkBox"))).click();
		Thread.sleep(3000);
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\alliasnumber.exe");
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\alliasnumber.txt");
	
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		gfun.verifytext(driver, OR.getProperty("scNonSellableErrorTxt"), "scAlliasNumber1ErrorTxt1", true);
		gfun.verifytext(driver, OR.getProperty("scClashingErrorTxt"), "scAlliasNumber1ErrorTxt", true);
		gfun.verifytext(driver, OR.getProperty("scClashingErrorTxt2"), "scAlliasNumber3ErrorTxt", true);
		driver.findElement(By.xpath(OR.getProperty("ScimportAvbrytBtn"))).click();						
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("ScincludeAlliasChkBox"))).click();
		driver.findElement(By.xpath(OR.getProperty("ScincludeAlliasChkBox"))).click();
		Thread.sleep(3000);
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\alliasnumber.exe");
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\alliasnumber.txt");
	
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		Thread.sleep(3000);
		Cmn.placeOrder(driver);
		deleteOrder(driver);
		Cmn.logout(driver);
		soft.assertAll();

	}
	
	@Test(priority=6)
	public void importLeveranceArticle() throws BiffException, IOException, InterruptedException
	{	
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000); 
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\leverancArticle.exe");
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\levarence.txt");
	
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		Thread.sleep(3000);
		//gfun.verifytext(driver, OR.getProperty("scImportFileDialogHdrTxt2"), "scleveransArticleMsgTxt", true);
		driver.findElement(By.xpath(OR.getProperty("ScvisaRestorderListaBtn"))).click();
		gfun.verifytext(driver, OR.getProperty("restorderHeader"), "scRestOrderHdrTxt", true);
		gfun.verifytext(driver, OR.getProperty("scRestOrderHdtTxt"), "scRestOrderHdtvalueTxt", true);
		gfun.verifytext(driver, OR.getProperty("scRestOrderHdr1Txt"), "scRestOrderHdr1valueTxt", true);
		gfun.verifytext(driver, OR.getProperty("scRestOrderHdr2Txt"), "scRestOrderHdr2ValueTxt", true);
		//driver.findElement(By.xpath(OR.getProperty("scVisaOrderPrintBtn"))).click();
		driver.findElement(By.xpath(OR.getProperty("visaRestoorderValdChkBox"))).click();
		driver.findElement(By.xpath(OR.getProperty("visaRestOrderTabortValdaBtn"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("restorderCloseBtn"))).click();
		Thread.sleep(5000);
		Cmn.placeOrder(driver);
		deleteOrder(driver);
		Cmn.logout(driver);
		soft.assertAll();

	}
	
	@Test(priority=7)
	public void importVolumeDiscountArticle() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Thread.sleep(3000);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000); 
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\VolumeDiscount.txt");
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		Thread.sleep(3000);

		flag = gfun.isElementPresent(driver, OR.getProperty("ScvolumeDiscountIcon"));
		if(flag==true)
			driver.findElement(By.xpath(OR.getProperty("ScvolumeDiscountIcon"))).click();
		Thread.sleep(2000);
		gfun.verifytextaftertrim(driver, OR.getProperty("ScvolumeDiscountHeaderTxt"), "ScvolumeDiscountHeaderTxt", true);
		gfun.verifytextaftertrim(driver, OR.getProperty("ScvolumeDiscountFirstValueHdrTxt"), "ScvolumeDiscountFirstValueHdrTxt1", true);
		gfun.verifytextaftertrim(driver, OR.getProperty("ScvolumeDiscountFirstValueTxt"), "ScvolumeDiscountFirstValueTxt1", true);
		gfun.verifytextaftertrim(driver, OR.getProperty("ScvolumeDiscountSecondValueHdrTxt"), "ScvolumeDiscountSecondValueHdrTxt1", true);
		gfun.verifytextaftertrim(driver, OR.getProperty("ScvolumeDiscountSecondValueTxt"), "ScvolumeDiscountSecondValueTxt1", true);			
		driver.findElement(By.xpath(OR.getProperty("ScAntaleditbox"))).click();
		Thread.sleep(2000);
		gfun.isElementPresent(driver, OR.getProperty("ScvolumeDiscountHeaderTxt"),false);
		Cmn.placeOrder(driver);
		deleteOrder(driver);	
		Cmn.logout(driver);
		soft.assertAll();
	}
	
	
	//S1236-Implementation
	@Test(priority=8)
	public void importSt8090Articles() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
	   Sc.createCart(driver, "AutoStandardSeleniumCart5", "Standardorder");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000); 
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\St8090.txt");			
		driver.findElement(By.xpath(OR.getProperty("scImportArtbutton"))).click();	
		Thread.sleep(5000);	
		List<WebElement> firstcolElements=driver.findElements(By.xpath(OR.getProperty("annulleraValdaTableCol2Value")));
		gfun.verifyvalue(driver, firstcolElements.get(0).getText(),TEXT.getProperty("ScStatus80Art2"));		
		gfun.verifyvalue(driver, firstcolElements.get(1).getText(),TEXT.getProperty("ScStatus90Art2"));		
		gfun.verifyvalue(driver, firstcolElements.get(2).getText(),TEXT.getProperty("ScStatus80Art1"));		
		gfun.verifyvalue(driver, firstcolElements.get(3).getText(),TEXT.getProperty("ScStatus90Art1"));		

		List<WebElement> secondcolElements=driver.findElements(By.xpath(OR.getProperty("annulleraValdaTableCol3Value")));
		gfun.verifyvalue(driver, secondcolElements.get(0).getText(),TEXT.getProperty("ScStatu80Desc2"));		
		gfun.verifyvalue(driver, secondcolElements.get(1).getText(),TEXT.getProperty("ScStatu90Desc2"));		
		gfun.verifyvalue(driver, secondcolElements.get(2).getText(),TEXT.getProperty("ScStatu80Desc1"));		
		gfun.verifyvalue(driver, secondcolElements.get(3).getText(),TEXT.getProperty("ScStatu90Desc1"));	
		
		List<WebElement> fourthcolElements=driver.findElements(By.xpath(OR.getProperty("annulleraValdaTableCol5Value")));
		gfun.verifyvalue(driver, fourthcolElements.get(0).getText(),TEXT.getProperty("ScStatus80StatsuTxt2"));		
		gfun.verifyvalue(driver, fourthcolElements.get(1).getText(),TEXT.getProperty("ScStatus90StatsuTxt2"));		
		gfun.verifyvalue(driver, fourthcolElements.get(2).getText(),TEXT.getProperty("ScStatus80StatsuTxt1"));		
		gfun.verifyvalue(driver, fourthcolElements.get(3).getText(),TEXT.getProperty("ScStatus90ReplacedinfoTxt1"));	
		
		//driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		Thread.sleep(3000);		
		Cmn.logout(driver);
		soft.assertAll();

	}

	@Test(priority=9)
	public void importSwedishCharactertArticle() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
				Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		driver.findElement(By.xpath(OR.getProperty("scOrderHistoryTab"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		Thread.sleep(3000); 
		//Runtime.getRuntime().exec(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\SwedishCharacters.exe");							
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\SwedishCharacters.txt");
	
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
		Thread.sleep(3000);		
		Cmn.placeOrder(driver);
		deleteOrder(driver);	
		Cmn.logout(driver);
		soft.assertAll();

	}
	//DE4716----- Not possible to import files containing articlenumbers with a plus sign
	@Test(priority=10)
	public void DE4716_importingArticlesWithPlusSign() throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");
		Cmn.loginToApp(driver,"Excelpath","Login",0,7);
		Sc.selectcart(driver, "AutoStandardSeleniumCart5");
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
		boolean flag = driver.findElement(By.xpath(OR.getProperty("scupdater"))).isEnabled();
		if (flag == true)
		{
			Sc.clearCart(driver);
			gfun.waitForPageToLoad(driver);
		}
		//articlenumbers with a plus sign
		driver.findElement(By.xpath(OR.getProperty("navigateToPo"))).click();
	    gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("scImportTab"))).click();
		
	//Importing file articles having plus sign
		driver.findElement(By.xpath(OR.getProperty("ImportField"))).sendKeys(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\Importfiles\\DE4716_ArticlesWithPlusSign.txt");
		Thread.sleep(3000);
		driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
	    gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(readdata.OR.getProperty("ScimportUpdateraBtn"))).click();	
		driver.findElement(By.xpath(readdata.OR.getProperty("ScstansaDialogLagTillGodkandaIVarukorgenBtn"))).click();		
		Thread.sleep(1000);
		Cmn.placeOrder(driver);
		deleteOrder(driver);
		Cmn.logout(driver);
		soft.assertAll();

	}	
	
	
	
   void deleteOrder(WebDriver driver) throws InterruptedException, IOException, BiffException
   { 
    	String orderNumberValue = driver.findElement(By.xpath(OR.getProperty("scOrderNumConfirmation"))).getText();
		System.out.println("Placed order number is "+ orderNumberValue);       	
    	driver.findElement(By.xpath(OR.getProperty("clickOnOrderHistoryTabOh"))).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		//due to the implementation of 1300 user story
		WebElement wb = driver.findElement(By.xpath(OR.getProperty("sokpaDd")));
		Select sel = new Select(wb);
		sel.selectByVisibleText("Ordernr");
		gfun.waitForPageToLoad(driver);
		
		driver.findElement(By.xpath(OR.getProperty("sokTextEdtBoxOh"))).sendKeys(orderNumberValue);
		driver.findElement(By.xpath(OR.getProperty("clickOnSokButtonOh"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("clickOnFirstOrderNumber"))).click();
		driver.findElement(By.xpath(OR.getProperty("orderDetailsDialogAnnulleraAlla"))).click();	
		Thread.sleep(5000);			
		driver.findElement(By.xpath(OR.getProperty("annulleraValdaAnnulleraBtn"))).click();	
		Thread.sleep(2000);
		soft.assertTrue(gfun.isElementPresent(driver, OR.getProperty("annulleraAllaConfirmationCancelBtn")),"Stang button is not displayed");
		driver.findElement(By.xpath(OR.getProperty("annulleraAllaConfirmationCancelBtn"))).click();   
		Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();		    
   } 
		
   void punchArticle(int Articlerow) throws BiffException, IOException, InterruptedException
   {
	    String ArtNumber = readdata.excel("Excelpath","ScArticlelist",0,Articlerow); 
	    driver.findElement(By.xpath(OR.getProperty("ScarticleNumberEdtBox"))).sendKeys(ArtNumber);
	    driver.findElement(By.xpath(OR.getProperty("SclagtillBtn"))).click();
	    Thread.sleep(5000);
	        
	                     
	}
   void importArticle(WebDriver driver,String pathOfExe) throws InterruptedException, IOException 
   {  
        driver.findElement(By.xpath(readdata.OR.getProperty("scImportTab"))).click();
        Runtime.getRuntime().exec(pathOfExe);
        driver.findElement(By.xpath(readdata.OR.getProperty("scImportbutton"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbutton"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(readdata.OR.getProperty("scImportArtbuttondialog"))).click();
   }
   
}	
