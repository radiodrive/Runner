/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner1;

/**
 *
 * @author justin.mulcahy
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Justin Mulcahy
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class CreateXML {
    
    
 
	public static void main(String argv[]) throws FileNotFoundException, IOException {
 
	  try {
               ArrayList<String> MethodName= new ArrayList <String>();
            FileReader fr= new FileReader("C:\\Users\\justin.mulcahy\\Documents\\NetBeansProjects\\TestRunner1\\Test.txt");
            BufferedReader reader= new BufferedReader(fr);
            String line;
            
            ArrayList<String> className= new ArrayList <String>();
            FileReader fr1= new FileReader("C:\\Users\\justin.mulcahy\\Documents\\NetBeansProjects\\TestRunner1\\Test2.txt");
            BufferedReader reader1= new BufferedReader(fr1);
            String line2;
                //String[] name = {"Test1", "Test2", "Test3","Test4"};
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("suite");
		doc.appendChild(rootElement);
                
                // set attribute to Suite element
		Attr SuiteName = doc.createAttribute("Name");
		SuiteName.setValue("Test");
		rootElement.setAttributeNode(SuiteName);
 
		// staff elements
		Element staff = doc.createElement("test");
		rootElement.appendChild(staff);
 
		// set attribute to staff element
		Attr attr = doc.createAttribute("Name");
		attr.setValue("Test");
		staff.setAttributeNode(attr);
                
                
                Element Classes = doc.createElement("classes");
		staff.appendChild(Classes);
               
              while ((line2=reader1.readLine()) !=null)
            {
                className.add(line2);
                Element Class = doc.createElement("Class");
		Classes.appendChild(Class);
                
            
                
                Attr ClassName = doc.createAttribute("Name");
		ClassName.setValue(line2);
		Class.setAttributeNode(ClassName);
            }
                
                Element Method = doc.createElement("methods");
		Classes.appendChild(Method);
                
                //for (int i = 0; i < name.length; i++) {
                while ((line=reader.readLine()) !=null)
            {
                MethodName.add(line);
                Element Include = doc.createElement("include");
		Method.appendChild(Include);
                Attr IncludeName = doc.createAttribute("Name");
		IncludeName.setValue(line);
		Include.setAttributeNode(IncludeName);
                }
               
                Element Include2 = doc.createElement("include");
		Method.appendChild(Include2);
                Attr IncludeName2 = doc.createAttribute("Name");
		IncludeName2.setValue("InitalBeforeMethod");
		Include2.setAttributeNode(IncludeName2);
               
                Element Include3 = doc.createElement("include");
		Method.appendChild(Include3);
                Attr IncludeName3 = doc.createAttribute("Name");
		IncludeName3.setValue("checkResult");
		Include3.setAttributeNode(IncludeName3);
                
                 Element Include4 = doc.createElement("include");
		Method.appendChild(Include4);
                Attr IncludeName4 = doc.createAttribute("Name");
		IncludeName4.setValue("SuperTearDown");
		Include4.setAttributeNode(IncludeName4);
                
          
                
                 Element Include6 = doc.createElement("include");
		Method.appendChild(Include6);
                Attr IncludeName6 = doc.createAttribute("Name");
		IncludeName6.setValue("tearDown");
		Include6.setAttributeNode(IncludeName6);
                
                Element Include7 = doc.createElement("include");
		Method.appendChild(Include7);
                Attr IncludeName7 = doc.createAttribute("Name");
		IncludeName7.setValue("setup");
		Include7.setAttributeNode(IncludeName7);
                
                
                
		// shorten way
		// staff.setAttribute("id", "1");
 
		// firstname elements
		/*Element firstname = doc.createElement("firstname");
		firstname.appendChild(doc.createTextNode("yong"));
		staff.appendChild(firstname);*/
 
		
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("C:\\Users\\justin.mulcahy\\Documents\\NetBeansProjects\\TestRunner1\\Testfile.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
        
        
}