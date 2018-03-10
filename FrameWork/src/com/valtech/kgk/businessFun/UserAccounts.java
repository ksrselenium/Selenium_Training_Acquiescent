package com.valtech.kgk.businessFun;

import generalFun.GeneralFun;

import java.io.IOException;
import java.util.Properties;

import jxl.read.biff.BiffException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.valtech.kgk.utilities.DataReader;

public class UserAccounts {


	GeneralFun gfun=new GeneralFun();
	Common com=new Common();
	DataReader readdata=new DataReader();       
	public Properties CONFIG=readdata.readConfig();
	public Properties OR =readdata.readObjectRepository();
	public Properties TEXT=readdata.readText();


	/**
	 * Method Name: navigateToAnvandarKonton
	 * Description: This function takes the user to Customer/KGK user search page from Search article page.
	 */

	public void navigateToAnvandarKonton(WebDriver driver) throws InterruptedException
	{
		driver.findElement(By.xpath(OR.getProperty("minasidorTab"))).click();
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("AnvandarkontonTab"))).click();
		gfun.waitForPageToLoad(driver);

	}

	/**
	 * Method Name: navigateToCustomerUserCreationPage
	 * Description: default user should be in search page, this function takes the user to customer user creation page.
	 */
	public void navigateToCustomerUserCreationPage(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		driver.findElement(By.xpath(OR.getProperty("uaAnvandridEdtBox"))).clear();
		//Valid email address  search
		String validemailid=readdata.excel("Excelpath","UAValidations", 0, 13);
		driver.findElement(By.xpath(OR.getProperty("uaEpostEdtBox"))).sendKeys(validemailid);
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("uaSOkBtn"))).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(OR.getProperty("uaCreatenewUserBtn"))).click();
		Thread.sleep(2000);
	}

	/**
	 * Method Name: navigateToKGKUserCreationPage
	 * Description: default user should be in search page, this function takes the user to customer user creation page.
	 */
	public void navigateToKgkUserCreationPage(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		WebElement Typkund = driver.findElement(By.xpath(OR.getProperty("uaTypDd")));
		Select select = new Select(Typkund);
		select.selectByValue("1");           
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("uaCreatenewUserBtn"))).click(); 
		gfun.waitForPageToLoad(driver);      
	}


	/**
	 * Method Name: deleteCustomerUser
	 * Description:To delete the customer user
	 */
	public void deleteCustomerUser(WebDriver driver,String Userid,String firstname,String lastname) throws InterruptedException, BiffException, IOException
	{

		driver.findElement(By.xpath(OR.getProperty("uaAnvandridEdtBox"))).sendKeys(Userid);

		driver.findElement(By.xpath(OR.getProperty("uaSOkBtn"))).click();
		gfun.waitForPageToLoad(driver);	
		driver.findElement(By.xpath(OR.getProperty("uaSrchCustomerresultCol1Row1DeleteImg"))).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		gfun.waitForPageToLoad(driver);
		String actualdeleteUserConfirmMsg=driver.findElement(By.xpath(OR.getProperty("uadeleteuserConfirmMsgTxt"))).getText().trim();
		String expecteddeleteUserConfirmMsg="Användare "+firstname+" "+lastname+" är borttagen.";
		gfun.verifyvalue(driver, actualdeleteUserConfirmMsg,expecteddeleteUserConfirmMsg);

	}


	/**
	 * Method Name: deletekgkUser
	 * Description: to delete the kgk user
	 */
	public void deleteKgkUser(WebDriver driver,String Userid,String firstname,String lastname) throws InterruptedException, BiffException, IOException
	{

		driver.findElement(By.xpath(OR.getProperty("uaAnvandridEdtBox"))).sendKeys(Userid);

		driver.findElement(By.xpath(OR.getProperty("uaSOKKgkuserBtn"))).click();
		gfun.waitForPageToLoad(driver);	
		driver.findElement(By.xpath(OR.getProperty("uaSrchCustomerresultCol1Row1DeleteImg"))).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		gfun.waitForPageToLoad(driver);
		String actualdeleteUserConfirmMsg=driver.findElement(By.xpath(OR.getProperty("uadeleteuserConfirmMsgTxt"))).getText().trim();
		String expecteddeleteUserConfirmMsg="Användare "+firstname+" "+lastname+" är borttagen.";
		gfun.verifyvalue(driver, actualdeleteUserConfirmMsg,expecteddeleteUserConfirmMsg);

	}

	/**
	 * Method Name: verifyNettoprisMinicart
	 * Description: Verifying the selected netprice option in dropdown is displayed in Minicart area.
	 */

	public Boolean verifyNettoprisMinicart(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String minicart= driver.findElement(By.xpath(OR.getProperty("cartDetailsTxt"))).getText();
		System.out.println(minicart);
		Thread.sleep(2000);
		boolean netprice2=minicart.endsWith("(netto)");
		return netprice2;

	}




	/**
	 * Method Name: verifyNettoprisDropdown
	 * Description: Verifying whether the dropdown contains Nettopris option
	 */

	public Boolean verifyNettoprisDropdown(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String pricedd = driver.findElement(By.xpath(OR.getProperty("priceDd"))).getText();
		String netprice="Nettopris";
		Boolean verifynet=pricedd.contains(netprice);
		return verifynet; 	   
	}




	/**
	 * Method Name: verifyDeliveryAddress
	 * Description: Verify whether the Delivery address dropdown is in enabled state or disabled state.
	 */
	public Boolean verifyDeliveryAddress(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		gfun.verifytext(driver, OR.getProperty("PlaceorderLeveransadressTxt"), "PlaceorderLeveransadressTxt", true);
		boolean address=driver.findElement(By.xpath(OR.getProperty("valueplaceorderleveransadressDd"))).isEnabled();
		return address;
	}


	/**
	 * Method Name: verifyNettoprisShoppingCart
	 * Description: To verify netprice in shopping cart
	 */

	public Boolean verifyNettoprisShoppingCart(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String shoppingCart= driver.findElement(By.xpath(OR.getProperty("tableNettoprisTxt"))).getText();
		System.out.println(shoppingCart);
		String netprice="Nettopris";
		boolean netprice2=shoppingCart.contains(netprice);
		return netprice2;
	}

	/**
	 * Method Name: verifyTotalNetprisShoppingCart
	 * Description: To verify Total netprice in shopping cart
	 */

	public Boolean verifyTotalNetprisShoppingCart(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String shoppingCart= driver.findElement(By.xpath(OR.getProperty("tableTotalNettoprisTxt"))).getText();
		System.out.println(shoppingCart);
		String netprice="Total netto:";
		boolean netprice2=shoppingCart.contains(netprice);
		return netprice2;
	}

	/**
	 * Method Name: verifyPlaceOrderBtn
	 * Description: To verify Place order button existence in shopping cart
	 */

	public Boolean verifyPlaceOrderBtn(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, OR.getProperty("placeorderskicksBtn"));

		return flag;
	}


	/**
	 * Method Name: verifyNettoprisArticleCard
	 * Description: To verify Netprice in article card
	 */

	public Boolean verifyNettoprisArticleCard(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, OR.getProperty("netPriceLabel"));
		if(flag==true)
		{
			String card_net=driver.findElement(By.xpath(OR.getProperty("netPriceLabel"))).getText();
			System.out.println(card_net);
			String netprice1="Nettopris (exkl. moms)";
			Boolean verifynet1=card_net.contains(netprice1);
			return verifynet1;

		}
		else
			return flag;

	}


	/**
	 * Method Name: verifyNettoprisInRestOrderDlg
	 * Description: To verify Netprice in restorder dialog
	 */

	public Boolean verifyNettoprisInRestOrderDlg(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String pricedd = driver.findElement(By.xpath(OR.getProperty("placeOrderVisaOrderNettoprisTxt"))).getText();
		String netprice="Nettopris";
		Boolean verifynet=pricedd.contains(netprice);
		return verifynet;       
	}

	/**
	 * Method Name: verifyNettoprisInOrderHistDlg
	 * Description: To verify Netprice in orderhistory dialog
	 */
	public Boolean verifyNettoprisInOrderHistDlg(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		String pricedd = driver.findElement(By.xpath(OR.getProperty("OrderHistoryDetailsThridColmn"))).getText();
		String netprice="Pris";
		Boolean verifynet=pricedd.contains(netprice);
		return verifynet;       
	}

	/**
	 * Method Name: EditTelephonenummer
	 * Description: To verify editing telephone number in customer profile
	 */
	public Boolean EditTelephonenummer(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("CustomerProfilTelefon")));
		if(flag==true)
		{
			driver.findElement(By.xpath(OR.getProperty("CustomerProfilTelefon"))).clear();
			driver.findElement(By.xpath(OR.getProperty("CustomerProfilTelefon"))).sendKeys("08-50569100");
			return flag;
		}
		else
			return flag;

	}

	/**
	 * Method Name: EditFaxnummer
	 * Description:To verify editing fax number in customer profile
	 */
	public Boolean EditFaxnummer(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("CustomerProfilFaxnr")));
		if(flag==true)
		{
			driver.findElement(By.xpath(OR.getProperty("CustomerProfilFaxnr"))).clear();
			driver.findElement(By.xpath(OR.getProperty("CustomerProfilFaxnr"))).sendKeys("0850569099");
			return flag;
		}
		else
			return flag;        

	}


	/**
	 * Method Name: AddDeliveryAddress
	 * Description: To verify delivery address can be added
	 */

	public Boolean AddDeliveryAddress(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, OR.getProperty("GatuadressEdtBox"));
		if(flag==true)
		{
			driver.findElement(By.xpath(OR.getProperty("GatuadressEdtBox"))).sendKeys("Webdriver");
			driver.findElement(By.xpath(OR.getProperty("PostnrEdtBox"))).sendKeys("212 14");
			driver.findElement(By.xpath(OR.getProperty("postAddressEdtBox"))).sendKeys("Automation");
			driver.findElement(By.xpath(OR.getProperty("AddTemporaryAddressBtn"))).click();
                         gfun.waitForPageToLoad(driver);
			return flag;
		}
		else
			return flag;

	}

	/**
	 * Method Name: DeleteDeliveryAddress
	 * Description: To verify delivery address can be deleted
	 */

	public Boolean DeleteDeliveryAddress(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, OR.getProperty("GatuadressEdtBox"));
		if(flag==true)
		{
			driver.findElement(By.xpath(OR.getProperty("DeletetemporaryAdress"))).click();
		       gfun.waitForPageToLoad(driver);
                   return flag;
		}
		else
			return flag;
	}
	/**
	 * Method Name: createKGKUser
	 * Description: To create the kgk user for the argumnets passed
	 */


	public void createKGKUser(WebDriver driver,int typ,String Fornamn,String Efternamn,String userID,String mail) throws InterruptedException
	{
		driver.findElement(By.xpath(OR.getProperty("minasidorTab"))).click();
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("AnvandarkontonTab"))).click();
		gfun.waitForPageToLoad(driver);
		WebElement Typkund = driver.findElement(By.xpath(OR.getProperty("uaTypDd")));
		Select select = new Select(Typkund);
		select.selectByIndex(typ);           
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("uaCreatenewUserBtn"))).click(); 
		gfun.waitForPageToLoad(driver);      
		driver.findElement(By.xpath(OR.getProperty("Fornamn"))).sendKeys(Fornamn);
		driver.findElement(By.xpath(OR.getProperty("Efternamn"))).sendKeys(Efternamn);
		driver.findElement(By.xpath(OR.getProperty("userIDAK"))).sendKeys(userID);
		//driver.findElement(By.xpath(OR.getProperty("pwd"))).sendKeys(pwd);
	//	driver.findElement(By.xpath(OR.getProperty("pwd2AK"))).sendKeys(pwd);
		driver.findElement(By.xpath(OR.getProperty("mail"))).sendKeys(mail);                     
             	driver.findElement(By.xpath(OR.getProperty("spar"))).click();
	        gfun.waitForPageToLoad(driver);

                }

	/**
	 * Method Name: deleteKGKUser
	 * Description: To delete KGK user for the userid passed as an argument
	 */

	public void deleteKGKUser(WebDriver driver,String Userid,int typ) throws InterruptedException, BiffException, IOException
	{                    
		driver.findElement(By.xpath(OR.getProperty("minasidorTab"))).click();   
		driver.findElement(By.xpath(OR.getProperty("AnvandarkontonTab"))).click();
		gfun.waitForPageToLoad(driver);
		WebElement Typkund = driver.findElement(By.xpath(OR.getProperty("uaTypDd")));
		Select select = new Select(Typkund);
		select.selectByIndex(typ);           
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("userIdEdtBx"))).sendKeys(Userid);
		driver.findElement(By.xpath(OR.getProperty("KgkSokBtn"))).click();
		gfun.waitForPageToLoad(driver);
                driver.findElement(By.xpath(OR.getProperty("delBtn"))).click();
		Alert alert1=driver.switchTo().alert();
		alert1.accept();
	}
	/**
	 * Method Name: VerifySelectCustomer
	 * Description: To delete select customer button exists
	 */
	public Boolean VerifySelectCustomer(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("selectCustomerBtn")));
		if(flag==true)
		{   driver.findElement(By.xpath(OR.getProperty("selectCustomerBtn"))).click();
		System.out.println("enetered select customer dialog");
		gfun.waitForPageToLoad(driver);

		driver.findElement(By.xpath(OR.getProperty("enterCustEdtBox"))).sendKeys("21214");

		System.out.println("Entering the data");
		driver.findElement(By.xpath(OR.getProperty("enterCustSearchBtn"))).click();
		gfun.waitForPageToLoad(driver);
		System.out.println("Selecting customer is done");
		return flag;
		}
		else
			return flag;        

	}
	/**
	 * Method Name: VerifyKopButton
	 * Description: To verify kop button exists in article listing page
	 */
	public Boolean VerifyKopButton(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("clickKopBtn")));
		if(flag==true)
		{   driver.findElement(By.xpath(OR.getProperty("clickKopBtn"))).click();
		gfun.waitForPageToLoad(driver);
		return flag;
		}
		else
			return flag;        

	}

	/**
	 * Method Name: VerifyMinicartArea
	 * Description: To verfiy minicart existence
	 */
	public Boolean VerifyMinicartArea(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("cartDd")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;        

	}


	/**
	 * Method Name: VerifyMinicartAreadisabled
	 * Description: To verfiy minicart is disabled
	 */
	public Boolean VerifyMinicartAreadisabled(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("shoppingcartdisabled")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;        

	}
	/**
	 * Method Name: Verifyordertab
	 * Description: To verfiy ordertab existence
	 */
	public Boolean VerifyOrderTab(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("orderTab")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;        

	}


	/**
	 * Method Name: VerifyKundProfilTab
	 * Description: To verify kund profile tab existence
	 */


	public Boolean VerifyKundProfilTab(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("kundprofilTab")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;        

	}

	/**
	 * Method Name: VerifyAnvandarkonton
	 * Description: To verify Anvandarkonton tab existence
	 */

	public Boolean VerifyAnvandarkontonTab(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("AnvandarkontonTab")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;        

	}

	/**
	 * Method Name: VerifyMinasidorTab
	 * Description: To verify MinasidorTab tab existence
	 */

	public Boolean VerifyMinasidorTab(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("minasidorTab")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;        

	}

	/**
	 * Method Name: VerifyKOPBtnInCardPage
	 * Description: to verify kop button in card page
	 */
	public Boolean VerifyKOPBtnInCardPage(WebDriver driver) throws InterruptedException, BiffException, IOException
	{
		Boolean flag=gfun.isElementPresent(driver, (OR.getProperty("buyBtn")));
		if(flag==true)
		{   
			return flag;
		}
		else
			return flag;       

	}

	/**
	 * Method Name: sellerIdValidation
	 * Description: To validate seller id
	 */
	public void sellerIdValidation(WebDriver driver, int id) throws BiffException, IOException, InterruptedException
	{
		String sellerid = readdata.excel("Excelpath","UAValidations", 0, id);
		gfun.waitForPageToLoad(driver);
		driver.findElement(By.xpath(OR.getProperty("uaEditUserProfileSelleridEdtBox"))).sendKeys(sellerid);      
		driver.findElement(By.xpath(OR.getProperty("uaEditUserProfileSaveBtn"))).click();

		//Verify sellerid value after saving
		String defaultEdtBox1=driver.findElement(By.xpath(OR.getProperty("uaEditUserProfileSelleridEdtBox"))).getAttribute("value");
		int val=Strlength(defaultEdtBox1);
		if(val>4)
		{
			System.out.println(defaultEdtBox1);
			gfun.verifytext(driver, OR.getProperty("usrNameOrPwdErrMsg"), "usrNameOrPwdErrMsg", true);
		}
		else
			System.out.println(defaultEdtBox1);

	}

	private int Strlength(String defaultEdtBox1)
	{
		// TODO Auto-generated method stub
		return 0;
	}



}
