package com.valtech.kgk.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;


import com.valtech.kgk.config.BaseTest;


public class ScreenShot extends TestListenerAdapter
{
	BaseTest obj = new BaseTest();
                public static String dir3;
                File dir5;
                // Start for date display
                static Date today = Calendar.getInstance().getTime();
                static// create our "formatter" (our custom format)
                String pattern = "MM/dd/yyyy";
                static SimpleDateFormat formatter = new SimpleDateFormat(
                                                "dd_MMM_yyyy_hh_mm_ss");
                // End for date display
                Date date = new Date();
                long a = date.getTime();
                String screenshots = null;//Initializing the screenshots folder empty

                @Override
                public void onTestFailure(ITestResult result) 
                {
                	// Getting the instance of BaseTest
                	Object currentClass = result.getInstance();
	WebDriver driver1 = ((BaseTest) currentClass).getDriver();	
					
	// augmentedDriver to be used to take screenshot when using remotewebdriver					
	//WebDriver augmentedDriver = new Augmenter().augment(driver1);

                                System.out.println("Entered on test failure");
                                //Getting the root directory 
                                File dir = new File("");
                                String dir1 = dir.getAbsolutePath();
                                Reporter.log("Directory Path" + dir1);
                                File dir2 = new File(dir1);
                                //In dir3 root directory will be stored ie KGKProject_Workspace
                                dir3 = dir2.getParent();
                                String sysCurrentDate = formatter.format(today);
                                // START
                                String[] chiildDir = dir2.list();
                                for (int i = 0; i < chiildDir.length; i++) 
                                {
                                                Reporter.log("Child Dirs/Files" + chiildDir[i]);
                                }
                                // END
                                Reporter.setCurrentTestResult(result);
                                Reporter.log("Class Name IS:" + result.getTestClass().getName());
                                // System.out.println(dir.getAbsolutePath());
                                Reporter.log("Directory Path" + dir2.getAbsolutePath());
                                // Reporter.log("Directory Path"+ dir.getCanonicalPath());
                                Reporter.log("Directory Parent Path" + dir3);

                                Reporter.log("screenshot saved at ScreenShots folder" + dir3
                                                                + "\\screenshots\\" + result.getName() + sysCurrentDate
                                                                + ".png");
                                Reporter.log("<br />"
                                                                + "<a href=\""
                                                                + dir3
                                                                + "\\screenshots\\"
                                                                + result.getName()
                                                                + sysCurrentDate
                                                                + ".png\" class=\"highslide\" rel=\"highslide\">"
                                                                + " <img src=\""
                                                                + dir3
                                                                + "\\screenshots\\"
                                                                + result.getName()
                                                                + sysCurrentDate
                                                                + ".png\"  alt=\"Highslide JS\" title=\"Click to enlarge\" hight=\'100\' width=\'100\'/> </a>"
                                                                + "<br/>");
                                Reporter.log("<a href=\"" + dir3 + "\\screenshots\\"
                                                                + result.getName() + sysCurrentDate + ".png\">"
                                                                + result.getName() + sysCurrentDate + " </a>" + "<br/>");
                                // the below line is capture the screnshot on failure 
                                File scrFile = ((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);
                                
                                Calendar calendar = Calendar.getInstance();
                                Date date = calendar.getTime();
                                DateFormat stringDate = new SimpleDateFormat("ddMMyyhhmmss");
                                stringDate.format(date);
                                this.getClass().getName();

                                String c = getClass().getName(); // prints Package name + class name.
                                String d = getClass().getSimpleName(); // prints only class name.
                                String e = getClass().getCanonicalName(); // prints Package name + class name.

                                System.out.println(c);
                                System.out.println(d);
                                System.out.println(e);
                                // The captured screenshot should be copied into root directory folder(dir3)
                                try {
                                                FileUtils.copyFile(scrFile, new File(dir3 + "\\screenshots\\"+ result.getName() + sysCurrentDate + ".png"));

                                } 
                                catch (IOException e1) 
                                {
                                                e1.printStackTrace();
                                }
                                
                                Reporter.setCurrentTestResult(null);
                }

                @Override
                public void onTestSkipped(ITestResult result) 
                {
                                // will be called after test will be skipped
                }

                @Override
                public void onTestSuccess(ITestResult result) 
                {
                                // will be called after test will be Passed
                }

}
