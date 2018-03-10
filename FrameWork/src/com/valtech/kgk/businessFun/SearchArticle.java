package com.valtech.kgk.businessFun;


import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.valtech.kgk.utilities.DataReader;



public class SearchArticle 

{   

	GeneralFun gfun=new GeneralFun();
	Common com=new Common();
	DataReader readdata=new DataReader();           
	public Properties CONFIG = readdata.readConfig();
	public Properties OR = readdata.readObjectRepository();
	public mySoftAssert soft= gfun.myassert();


	/**
	 * Method Name: clickSok
	 * Description: Click on search button
	 */

	public void clickSok(WebDriver driver)
	{

		System.out.println("Clicking on sok button");
		driver.findElement(By.xpath(OR.getProperty("sokBtn"))).click();
		gfun.waitForPageToLoad(driver);
		
	}

	/**
	 * Method Name: freeText
	 * Description: Enter data in free text search
	 */

	public void freeText(WebDriver driver,String Article) throws BiffException, IOException
	{

		System.out.println("entered Free text");
		System.out.println("Article=" + Article);
		driver.findElement(By.xpath(OR.getProperty("freeTextEdtBox"))).sendKeys(Article);		
	}

	/**
	 * Method Name: purchaseFromListing
	 * Description: Purchase first article in the listing
	 */

	public void purchaseFromListing(WebDriver driver)
	{

		System.out.println("Purchasing from listing page");
		driver.findElement(By.xpath(OR.getProperty("clickKopBtn"))).click();
		gfun.waitForPageToLoad(driver);
		
	}


	/**
	 * Method Name: changeQuantity
	 * Description: to change the quantity in listing 
	 */	

	public  void changeQuantity(WebDriver driver,String quant)
	{

		driver.findElement(By.xpath(OR.getProperty("articleQuantityEdtBox"))).sendKeys(quant);

	}
	/**
	 * Method Name: clearAntal
	 * Description: clearing the antal field
	 */

	public  void clearAntalArticlelisting (WebDriver driver)
	{

		driver.findElement(By.xpath(OR.getProperty("articleQuantityEdtBox"))).clear();
	}

	/**
	 * Method Name: clickOnNode
	 * Description: click on first node of registration number search
	 */

	public  void clickOnFirstNodeinLandingPage(WebDriver driver)
	{

		driver.findElement(By.xpath(OR.getProperty("clickLandingPageLinkNode"))).click();
		gfun.waitForPageToLoad(driver);
	}

	/**
	 * Method Name: navigateToCard
	 * Description: Navigation to article card page for the first article in listing
	 */

	public  void navigateToCard(WebDriver driver)
	{

		driver.findElement(By.xpath(OR.getProperty("clickFirstArticleLnk"))).click();
		gfun.waitForPageToLoad(driver);

	}
	/**
	 * Method Name: clickKop
	 * Description: Click on kop button 
	 */	

	public  void purchaseFromCard(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("buyBtn"))).click();
	}
	/**
	 * Method Name: clickTilbaka
	 * Description: Click on tillbaka link
	 */

	public  void clickTilbakalink(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("tillbakaFromArtCardlinkText"))).click();
	}


	/**
	 * Method Name: Rensa
	 * Description: Click on Rensa Button
	 */

	public void Rensa(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("rensaBtn"))).click();
		gfun.waitForPageToLoad(driver);
	}
	/**
	 * Method Name: clickOnKontrolleraAlternativaLagerstallen
	 * Description:To click on kontrolleralagerstallen link and verify the text
	 */

	public  void clickOnKontrolleraAlternativaLagerstallen(WebDriver driver) throws InterruptedException
	{

		String articledescription=driver.findElement(By.xpath(OR.getProperty("clickFirstArticleLnk"))).getText();
		String fullarticledescription=driver.findElement(By.xpath(OR.getProperty("articleNameNumberbrand"))).getText();
		String lines[] = fullarticledescription.split("\\r?\\n");
		String articlenumber= lines[1];
		System.out.println("articlenumber is: "+articlenumber);

		driver.findElement(By.xpath(OR.getProperty("kontrollerAlternativaLnk"))).click();
		gfun.waitForPageToLoad(driver);
		String Lagerstallenlink=driver.findElement(By.xpath(OR.getProperty("kontrollerAlternativaHeaderTxt"))).getText();
		System.out.println(Lagerstallenlink);
		if(Lagerstallenlink.equals("Kontrollerar alternativa lagerställen 26 av 26 för artikel '"+articledescription+", "+articlenumber+"'"))
		{
			Reporter.log("Kontrollera alternativa lagerstallen dialog is working fine");
		}
		else
		{
			Reporter.log("kontrollera alternativa lagerstallen link header is not correct");
		}
		driver.findElement(By.xpath(OR.getProperty("kontrollerAlternativaCloseBtn"))).click();

	}
	/*
	 * Method name=clickOnPassarTillTab
	 * Description=Clicking on Passartill tab in the article card page
	 */

	public  void clickOnPassarTillTab(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("passerTillTab"))).click();
		System.out.println("Passartill tab is clicked");
	}
	
	
	/*
	 * Method name=clickOnAliasTab
	 * Description=Clicking on Alias tab in the article card page
	 */

	public  void clickOnAliasTab(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.xpath(OR.getProperty("aliasTab"))).click();
		Thread.sleep(2000);
		System.out.println("aliasTab tab is clicked");
	}

	/*
	 * Method name=clickOnZoom
	 * Description= Clicking on zoom dialog in the article card page
	 * to zoom the article image 
	 */

	public  void clickOnZoom(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("zoomDialogArtImg"))).click();
		gfun.waitForPageToLoad(driver);
		System.out.println("zoom dialog is clicked");
	}

	/*
	 * Method name=clickOnReservdelar
	 * Description=Clicking on Reservdelar tab in the article card page
	 */

	public  void clickOnReservdelar(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("reservDelarTab"))).click();
		gfun.waitForPageToLoad(driver);
		System.out.println("Reservdelar tab is clicked");
	}

	/*
	 * Method name=navigateToArticlePrint
	 * Description= Clicking on print preview button in the 
	 * article card page.
	 */

	public  void navigateToArticlePrint(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("printBtn"))).click();
		gfun.waitForPageToLoad(driver);
		System.out.println("Entered Article print page");
	}

	/*
	 * Method name= navigateBackToArticleCard
	 * Description= Clicking on Tillbaka link in print preview page to  
	 * navigate back to the article card page
	 */

	public  void navigateBackToArticleCard(WebDriver driver)
	{
		System.out.println("Navigate to Article card page");
		driver.findElement(By.linkText("Tillbaka till artikelkortet")).click();
		gfun.waitForPageToLoad(driver);
	}

	/*
	 * Method name=purchaseFromReservdelar
	 * Description= Clicking on KOP button for the First article in Reservdelar tab
	 */

	public  void purchaseFromReservdelar(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("reserveDelarBuyBtn"))).click();
		gfun.waitForPageToLoad(driver);
	}

	/*
	 *Method name=navigateToManualVehicleSearch
	 *description= clicking on manual vehicle link to navigate to manual
	 *vehicle search page
	 */

	public  void navigateToManualVehicleSearch(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("valjModellLnk"))).click();
		gfun.waitForPageToLoad(driver);
		System.out.println("Clicked on manual vehicle link");
	}

	/*
	 *    Method name:regnumbersearch
	 *    Description: To enter a regno in the registration number text field
	 */

	public  void regnumbersearch(WebDriver driver,String Regnum) throws BiffException, IOException
	{ 
		System.out.println("entered Regno");
		System.out.println("Regno=" + Regnum);
		driver.findElement(By.xpath(OR.getProperty("registrationNumberEdtBox"))).sendKeys(Regnum);    
	}

	/*
	 * Method name:clickonmorfordonlink
	 * Description: Clicking on morfordon link in the post it note
	 * 
	 */

	public  void clickonmorfordonlink(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("moreInfoLnk"))).click();
		gfun.waitForPageToLoad(driver);
	}


	/*
	 * Method name:closemorefordondialog
	 * Description: Closing the Morfordon dialog
	 */

	public  void closemorefordondialog(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("moreInfoStangBtn"))).click();
		gfun.waitForPageToLoad(driver);
	}


	/*
	 * Method name:selectPriceDropDown
	 * Description: selecting options from price drop down
	 * 
	 */

	public  void selectPriceDropDown(WebDriver driver,String pricetype)
	{
		WebElement pricedropdown = driver.findElement(By.xpath(OR.getProperty("priceDd")));
		Select select = new Select(pricedropdown);
		select.selectByVisibleText(pricetype);
               gfun.waitForPageToLoad(driver);

	}

	/*
	 * Method name:selectPriceDropDown
	 * Description: selecting options from price drop down
	 * 
	 */

	public  void selectSortingDropDown(WebDriver driver,String sortType)
	{
		WebElement SortingDropDown = driver.findElement(By.xpath(OR.getProperty("sortingDd")));
		Select select = new Select(SortingDropDown);
		select.selectByVisibleText(sortType);

	}

	/**
	 * Method Name: Registration history dialog
	 * Description: This function will verify whether the registration number searched are stored
	 */
	public Boolean regnohistory(WebDriver driver, String value) throws InterruptedException, BiffException, IOException
	{
		driver.findElement(By.xpath(OR.getProperty("registrationHistoryImg"))).click();
		gfun.waitForPageToLoad(driver);
		String reghis=driver.findElement(By.xpath(OR.getProperty("registrationHistoryList"))).getText();
		Boolean verifyVal=reghis.contains(value);
		return verifyVal;   

	}  

	/*
	 * Method name:verifyUnusualModelChkBox
	 * Description: Verifying whether the unusual model checkbox is enabled or disabled
	 * 
	 */

	public boolean verifyUnusualModelChkBox(WebDriver driver) throws IOException
	{
		gfun.verifytext(driver,OR.getProperty("unusualModelTxt"), "unusualModelTxt", true);
		boolean flag=driver.findElement(By.xpath(OR.getProperty("unusualModelChkBox"))).isSelected();
		return flag;

	}

	/*
	 * Method selectMakeModelManualVehicle
	 * Description: selecting Make, model and first link from manual vehicle search page for personbil
	 * 
	 */

		public  void selectMakeModelManualVehicle(WebDriver driver, int mak,int mod) throws BiffException, IOException, InterruptedException
	{
		WebElement makeDd = driver.findElement(By.xpath(OR.getProperty("selectMakeDdId")));
		Select select = new Select(makeDd);
		String make=readdata.excel("Excelpath","Manualvehicle",0,mak);
		select.selectByVisibleText(make); 

		WebElement modelDd = driver.findElement(By.xpath(OR.getProperty("selectModelDdId")));
		Select select1 = new Select(modelDd);
		String model=readdata.excel("Excelpath","Manualvehicle",0,mod);
		System.out.println("Model="+model);
		select1.selectByVisibleText(model);

		System.out.println("Make and model drop downs are selected");

		driver.findElement(By.xpath(OR.getProperty("firstTypLnk"))).click();
		gfun.waitForPageToLoad(driver);

	}


	/*
	 * Method name: verifyDividers
	 * Description: Verifying whether the dividers displays the unlinked node or not
	 * 
	 */
	public void verifyDividers(WebDriver driver) throws IOException
	{
	      soft.clearMaps();
              //Verifying whether the unlinked articles , dividers are displayed 
		boolean node;   
		List<WebElement> dividersList=driver.findElements(By.xpath(OR.getProperty("allDividersListing")));
		int divno=dividersList.size();
		String firstnode=driver.findElement(By.xpath(OR.getProperty("accessoriesNodeLnk"))).getText();
		System.out.println("First Unlinked node:" +firstnode);

		for(int i=0;i<divno;i++)
		{
			String a=dividersList.get(i).getText();
			System.out.println(a);
			if(a.contains(firstnode))
			{
				System.out.println("Divider contains the unlinked node");
				node=true;
				soft.assertFalse(node);//DE3192
			}
			else
			{
				System.out.println("Divider not matched with unlinked node");
				node=false;
			}
		}
		soft.assertAll();
      }

	/*
	 * Method name: verifyKontrolleraLagerPopup
	 * Description: Verifying the kontrollera lager link and content in popup
	 * 
	 */
	public void verifyKontrolleraLagerPopup(WebDriver driver) throws IOException
	{
		driver.findElement(By.xpath(OR.getProperty("kontrolleraLagerLnk"))).click();      
		boolean flag=gfun.isElementPresent(driver, "kontrolleraLagerTooltip");
		soft.assertFalse(flag);
		gfun.verifytext(driver,OR.getProperty("kontrolleraLagerTooltipTxt"), "kontrolleraLagerTooltipTxt", true);
		flag=gfun.isElementPresent(driver, "kontrolleraLagerValueTxt");
		soft.assertFalse(flag);
		driver.findElement(By.xpath(OR.getProperty("closeKontrolleraLagerImg"))).click();  
		gfun.waitForPageToLoad(driver);
	}

	/*
	 * Method name: navigatePurchaseStatisticsTab
	 * Description: Navigate the user to the purchase statistics page by clicking on the tab
	 * 
	 */
	public void navigatePurchaseStatisticsTab(WebDriver driver) throws IOException
	{
		driver.findElement(By.xpath(OR.getProperty("orderTab"))).click(); 
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("purchaseStatisticsTab"))).click(); 
		gfun.waitForPageToLoad(driver);
	}  




}













