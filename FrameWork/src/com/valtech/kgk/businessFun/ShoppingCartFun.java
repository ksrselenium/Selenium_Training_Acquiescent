package com.valtech.kgk.businessFun;

import generalFun.GeneralFun;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.valtech.kgk.utilities.DataReader;


public class ShoppingCartFun {
                
                DataReader readdata=new DataReader();           
                public Properties CONFIG = readdata.readConfig();
    public Properties OR = readdata.readObjectRepository();
   
    
    GeneralFun gfun= new GeneralFun();
    
 
    /**
        * Method name: selectcart
        * Method Description: Selecting the cart
     * @throws IOException 
     * @throws TimeoutException 
        */
       public void selectcart(WebDriver driver,String cartname) throws InterruptedException, TimeoutException, IOException
    {
        WebElement shoppingCartDd = driver.findElement(By.xpath(readdata.OR.getProperty("cartDd")));
        Select select = new Select(shoppingCartDd);
        try{
               select.selectByVisibleText(cartname);
        }
        catch(NoSuchElementException e)
        {
               driver.findElement(By.xpath(OR.getProperty("createCartLnk"))).click();
              gfun.waitForElement(driver, 35, OR.getProperty("cartNameEdtBox"));
               
               driver.findElement(By.xpath(OR.getProperty("cartNameEdtBox"))).sendKeys(cartname);
               WebElement OrderTypDd = driver.findElement(By.xpath(readdata.OR.getProperty("scOrderTypeDd")));
               Select select1 = new Select(OrderTypDd);
               select1.selectByVisibleText("Standardorder");                 
               driver.findElement(By.xpath(OR.getProperty("createShoppingCartBtn"))).click();
               
        }
       System.out.println("Selecting cart is done");

    }                                       
                                                
        /**
        * Method name: checkExistsArticleNumInPO
        * Method Description: Verifying the article number in managed/not managed/Problem tab
        */
        public  Boolean checkExistsArticleNumInPO(WebDriver driver,String articlenumber) throws NoSuchElementException, InterruptedException
        {     
                                       
        	Boolean flag;
        	flag=true;
        	// checks for ordernumber passed in argument in each page of afwadmin
                              
                try 
                	{
                		if ((driver.findElement(By.xpath("//*[contains(text(),'"+articlenumber+"')]")).isDisplayed())==true)
                          
                          {   
                                 System.out.println("articlenumber:"+articlenumber+" found");
                                 flag=true;                                      
                          }     
                	}
           catch (NoSuchElementException exception) 
                      {
                              System.out.println("articlenumber:"+articlenumber+" not found");
                              flag=false;//returns false if the article is found
                                                                
                       }
                                                                                        return flag;
        }
                /**
                * Method name: navigatetoPOAK
                * Method Description: Navigating to place order screen in all applications
                 * @throws InterruptedException 
                */
                public void navigatetoPOAK(WebDriver driver) throws InterruptedException
                {
                                driver.findElement(By.xpath(OR.getProperty("NavigateToScLnk"))).click(); 
                              Thread.sleep(2000);
                                //driver.findElement(By.xpath(OR.getProperty("NavigateToScLnk"))).click(); 
                                System.out.println("Navigate to PO screen");
                            	gfun.waitForPageToLoad(driver);
               
               
                }
                /**
                * Method name: verifyPurchaseOrderNum
                * Method Description: Verifying the purchase order number in Place order screen in AK application
                */
                public void verifyPurchaseOrderNum(WebDriver driver,String purchaseNum)
                {
                
                                System.out.println("Verifying the purchase order number");
                                System.out.println("purchaseNum="+purchaseNum);
                                String purchase=driver.findElement(By.xpath(OR.getProperty("valuePlaceorderbestallningrsEdtBox"))).getAttribute("value");
                
                                System.out.println("purchaseNumAttribute="+purchase);
                                if(purchase.equals(purchaseNum))
                                {
                                                Reporter.log("Purchase order number is correct");
                                                
                                }
                                else
                                                Reporter.log("Purchase order number is wrong");
                                
                }
        
                /**
                * Method Name: clearCart
                * Description: clicking on Clear cart button in the place order screen in AK
                * @throws InterruptedException 
                 * @throws IOException 
                 */

                public  void clearCart(WebDriver driver) throws InterruptedException, IOException
                {
                	 boolean flag= driver.findElement(By.xpath(OR.getProperty("SctomVarukorgBtn"))).isEnabled();
                	 if (flag==true)
                	 {
                		   driver.findElement(By.xpath(OR.getProperty("SctomVarukorgBtn"))).click();
                		   gfun.waitForPageToLoad(driver);
                           driver.findElement(By.xpath(OR.getProperty("placeOrderDialogTomvarukorgOkBtn"))).click();
                            gfun.waitForElementtillinvisible(driver,10, OR.getProperty("placeOrderDialogTomvarukorgOkBtn"));

                	 }
                             
                              
                }
                
                /**
                 * Method Name: clickOnUppdaterra
                 * Description: Click on update button in shopping cart
                  */

                public  void clickOnUppdaterra(WebDriver driver) throws InterruptedException
                	{
                                driver.findElement(By.xpath(OR.getProperty("placeOrderUpdateBtn"))).click();
                                gfun.waitForPageToLoad(driver);
                	}
                

                  /**   * Method name: delete cart
                     * Method Description: Deleting the selected cart 
                      * @throws InterruptedException 
                 * @throws IOException 
                 * @throws TimeoutException 
                      */
                     public void deleteCart(WebDriver driver) throws InterruptedException, TimeoutException, IOException 
                     {

                     driver.findElement(By.xpath(OR.getProperty("deleteCartLnk"))).click();
                     gfun.waitForElement(driver, 10, OR.getProperty("scDeleteCartBtn"));
                     driver.findElement(By.xpath(OR.getProperty("scDeleteCartBtn"))).click();
                     gfun.waitForElementtillinvisible(driver, 10, OR.getProperty("scDeleteCartBtn"));
                     }     




                /**
                 * Method Name: ClickOnOrderTab
                 * Description: Click on Order Tab in Search article page
                  */

                public  void ClickOnOrderTab(WebDriver driver) throws InterruptedException
                	{
                                driver.findElement(By.xpath(OR.getProperty("orderTab"))).click();
                                gfun.waitForPageToLoad(driver);
                	}
                /**
                 * Method Name: ClickOnReturnTab
                 * Description: Click on Return Tab in Shopping Cart Page
                  */

                public  void ClickOnReturnTab(WebDriver driver) throws InterruptedException
                	{
                                driver.findElement(By.xpath(OR.getProperty("returnTab"))).click();
                                gfun.waitForPageToLoad(driver);
              }




           /**
                 * Method Name: createCart
                 * Description: To Create cart 
		 * @throws InterruptedException 
		 * @throws IOException 
		 * @throws TimeoutException 
                  */

                        public void createCart(WebDriver driver,String cartname, String orderType) throws InterruptedException, TimeoutException, IOException
    			 {
            			WebElement shoppingCartDd = driver.findElement(By.xpath(readdata.OR.getProperty("cartDd")));
           			 Select select = new Select(shoppingCartDd);
      				try{
            				 select.selectByVisibleText(cartname);
      				    }
      				catch(NoSuchElementException e)
     				 {
          				 
      					driver.findElement(By.xpath(OR.getProperty("SccreateCartLnk"))).click();
        			      gfun.waitForPageToLoad(driver)  ;				 
          				  driver.findElement(By.xpath(OR.getProperty("createCart_NameEdtBox"))).sendKeys(cartname);
            
           				 WebElement scOrderTypeDd = driver.findElement(By.xpath(OR.getProperty("scOrderTypeDd")));                                            
          				  Select select1 = new Select(scOrderTypeDd);
           			        select1.selectByVisibleText(orderType);  
                                      driver.findElement(By.xpath(OR.getProperty("createShoppingCartBtn"))).click();
                                      Thread.sleep(3000);

				}
			}      
                                
}
