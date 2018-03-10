package generalFun;

import java.util.Map;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

public class mySoftAssert extends Assertion {
	  // LinkedHashMap to preserve the order
	  private Map<AssertionError, IAssert> m_errors = Maps.newLinkedHashMap();

	  @Override
	  public void executeAssert(IAssert a) {
	   try {
	      a.doAssert();
	      
	   } catch(AssertionError ex) {
	    	onAssertFailure(a, ex);
	    	m_errors.put(ex, a);
	    	
	    	Reporter.log("###################################################################################<br>");
	    	System.out.println("################################################");	    	
	    	System.out.println("ExpectedValue is: "+ a.getExpected());
	    	System.out.println("ActualValue   is: "+ a.getActual());	
	    	Reporter.log("ExpectedValue is: "+ a.getExpected());
	    	Reporter.log("ActualValue is: "+ a.getActual());
	    	Reporter.log("###############################################<br>");
	    	System.out.println("##############################################");
	    	Reporter.log(ex.getMessage()+"<br>");
	    	StackTraceElement[] trace=ex.getStackTrace();

	    	for (StackTraceElement traceElement : trace)
	    	{
	    	Reporter.log(""+traceElement);
	    	System.out.println(""+traceElement);
	    	}
	    	Reporter.log("<br>");
	    	Reporter.log("###############################################<br>");
	    	System.out.println("##############################################");

	    }
	  }

	  public void assertAll() {
	    if (! m_errors.isEmpty()) {
	      StringBuilder sb = new StringBuilder("The following asserts failed:\n");
	      boolean first = true;
	      for (Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet()) {
	        if (first) {
	          first = false;
	        } else {
	          sb.append(", ");
	        }
	        sb.append(ae.getValue().getMessage());
	        
	      }
	      throw new AssertionError(sb.toString());
	    }
	  }
	  
	  public void clearMaps() {		  
		  m_errors.clear();
	}
	  
	  
	  
	}
