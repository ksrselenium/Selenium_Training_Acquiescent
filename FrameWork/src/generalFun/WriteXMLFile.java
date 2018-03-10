package generalFun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 *Company : Valtech
 *@author hemanth.kumar
 *Description: This program is used to generate the xml file required for execution controlled by controller.xls sheet under resource folder
 *@date Jul 1, 2014
 *@time 4:48:00 PM
 */
public class WriteXMLFile {


	public static void main(String argv[]) throws BiffException, IOException {

		GeneralFun gfun=new GeneralFun();

		 // To Fetch the current workspace path
		  String UserDirectoryPath= System.getProperty("user.dir");
		  
		  System.out.println("UserDirectory = " + UserDirectoryPath);
		
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			
			
			//FileInputStream f2=new FileInputStream(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\resource\\Controller.xls");
			FileInputStream f2=new FileInputStream(UserDirectoryPath+"\\resource\\Controller.xls");
			
			Workbook w2=Workbook.getWorkbook(f2);	             
			Sheet s2= w2.getSheet("controller");

			//Controller sheet loop- Modules to be executed

			for(int rowindex=1; rowindex<s2.getRows();rowindex++)
			{
				String retvalue=s2.getCell(2,rowindex).getContents();

				//Controller sheet loop- Modules to be executed marked Yes 

				if(retvalue.equals("Yes"))
				{					

					String Suitename=s2.getCell(0,rowindex).getContents();	
					String PackageName=s2.getCell(1,rowindex).getContents();
					String ParallelAttribue=s2.getCell(3,rowindex).getContents();
					String threadCount=s2.getCell(4,rowindex).getContents();
				
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("suite");
					rootElement.setAttribute("name", Suitename);	
					rootElement.setAttribute("parallel", ParallelAttribue);	
					rootElement.setAttribute("thread-count", threadCount);	
					doc.appendChild(rootElement);

					//Listener under Suite
					Element firstElement = doc.createElement("listeners");		
					firstElement.setAttribute("name", "listeners");
					rootElement.appendChild(firstElement);


					// defining listener elements
					Element staff = doc.createElement("listener");
					firstElement.appendChild(staff);

					Attr attr = doc.createAttribute("class-name");
					attr.setValue("com.valtech.kgk.utilities.ScreenShot");
					staff.setAttributeNode(attr);

					//Navigate to the sheet respective to module which has to be executed

					Sheet s3= w2.getSheet(Suitename);			
					//Loop in the Module sheet to execute those tests marked "Yes"
					for(int testcount=1;testcount<s3.getRows();testcount++)
					{	

						String testController=s3.getCell(2,testcount).getContents();
						String ExecutionBrowser=s3.getCell(3,testcount).getContents();
						
						
						if(testController.equalsIgnoreCase("YES"))
						{

							String testName=s3.getCell(0,testcount).getContents();	
							String ClassName=s3.getCell(1,testcount).getContents();	
							
							//test under suite

							Element test=doc.createElement("test");			
							test.setAttribute("name",testName );
							rootElement.appendChild(test);

							// parameter
							Element par1 = doc.createElement("parameter");
							test.appendChild(par1);
							
							// Set the name attribute
							Attr attr1 = doc.createAttribute("name");
							attr1.setValue("browser");
							par1.setAttributeNode(attr1);
							Attr attr1_1 = doc.createAttribute("value");
							attr1_1.setValue(ExecutionBrowser);
							par1.setAttributeNode(attr1_1);
							
							
							//creation of classes under test

							Element Reqclasses=doc.createElement("classes");
							test.appendChild(Reqclasses);							

							//creation of class under classes

							Element Reqclass=doc.createElement("class");
							Reqclasses.appendChild(Reqclass);
							Reqclass.setAttribute("name", "com.valtech.kgk.app.ak.test."+PackageName+"."+ClassName);


							// write the content into xml file
							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							DOMSource source = new DOMSource(doc);
							//StreamResult result = new StreamResult(new File(gfun.returnDrive()+"\\KGKProject_Workspace\\KGKTestAutomation\\testSuite\\"+Suitename+".xml"));
							StreamResult result = new StreamResult(new File( UserDirectoryPath+"\\testSuite\\"+Suitename+".xml"));
							
							
							// Output to console for testing
							// StreamResult result = new StreamResult(System.out);

							transformer.transform(source, result);
							
						}

					}


				} 
			}

		}

		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}