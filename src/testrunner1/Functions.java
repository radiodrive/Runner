/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner1;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 *
 * @author justin.mulcahy
 */
public class Functions {
    
     public void createTextXML(String ClassName) {
         
        try {
			
                    char quotes ='"';
                    String newline = System.getProperty("line.separator");

                   String AntScript = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+newline+
                                   "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">"+newline+
                                           "<suite name="+quotes+"Results\">"+newline+
                                           "<test verbose=\"2\" name="+quotes+ClassName+"\">"+newline+
                                   "<classes>"+newline+
                           "<class name="+quotes+ClassName+"\"/>-->"+newline+
                           "</classes>"+newline+
                           "</test> <!-- Default test -->"+newline+
                   "</suite> <!-- Default suite -->";


                    String Path = System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Test.xml";

                    FileUtils.writeStringToFile(new File(Path), AntScript);

	} catch (Exception ex) {
	JOptionPane.showMessageDialog(new JFrame(), ex + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
	}
        
    }

    public void createConfigCloud(String Browser ,String Version,String URL,String Logon, String ProductVersion,String Language) throws IOException {
      
        
                                        String newline = System.getProperty("line.separator");
					SecureRandom random = new SecureRandom();	
					String Username = new BigInteger(130, random).toString(32);
					String Firstname = "System";
					String Lastname = "Administrator";
					String Password = "Passw0rd1";
					String Email = Username+"@qasage.com";
					String Phone = "123456";
					String CompanyName = "SageAutomation";
					
				    //callURL("https://eu.ondemandstaging.com/internalautoreg/uk/automaticRegister.php?companyName="+CompanyName+"&email="+Email+"&password="+Password+"&fName="+Firstname+"&lName="+Lastname+"&phone="+Phone+"&version=2014R2");
					
					
				    String BrowserConfig =	
				
					"#Test Configuration (auatomatically read from TestRunner)"+newline+
					"Grid2On=True"+newline+
                                        "GridURL=http://192.168.118.148:4444/grid/console"+newline+
					"Browser="+Browser+newline+
					"CRM_Edition="+Version+newline+
					
					"CRM_Version="+ProductVersion+newline+
					"NoFrame=true"+newline+
					"Installation_Folder=NA"+newline+
					"Integration=NA"+newline+
					"ClientOS=ClientIP"+newline+
					"ServerOS=ServerIP"+newline+
					"Database_Version=sqlserver2008r2"+newline+
					"ERP_URL=ERP_URL"+newline+
					"Cloud_Essentials_UserName=essentials1@johnholland.ie"+newline+
					"Cloud_Professional_UserName="+Logon+newline+
					"#Test Parameters"+newline+
					"OutputDir="+newline+
					"WorkingFolder=c:/automation"+newline+
					"ResultFolder=\\\\192.168.118.210\\Result"+newline+
					"SiteURL="+URL+newline+
					"#Webservice logon and password"+newline+
					"wslogon=admin"+newline+
					"wspassword="+newline+
					"#Login and password for SageID logon"+newline+
					"sageidlogon=admin"+newline+
					"sageidpassword="+newline+
					"#SQL Server"+newline+
					"ServerName=jdbc:sqlserver://10.2.90.11:1433;databaseName=CRM;"+newline+
					"sqlUserName=sa"+newline+
					"sqlPassword=Passw0rd"+newline+
					"DriverName=com.microsoft.sqlserver.jdbc.SQLServerDriver"+newline+
					"#implicitly waiting time, in seconds"+newline+
					"implicitlyWait=10"+newline+
					"#verify not found waiting time in seconds"+newline+
					"notfoundWait=6"+newline+
					"Language="+Language+newline+
					"#slow down to make test stable, especially for firefox"+newline+
					"slowDown=0"+newline+
					"#Webservice logon and password"+newline+
					"wslogon=admin"+newline+
					"wspassword="+newline+
					"#reports output dir"+newline+
					"ReportTolerence = 0.05"+newline+
					"ReportBaselineDir=C:Workspace/Sel_Framework/data/ReportBaselines/baselineDir"+newline+
					"ReportOutputDir=C:/Workspace/Sel_Framework/ReportOutputDirectory"+newline+
					"#Login Timings - used by testLogon_010(). Writes login times to a file in this directory"+newline+
					"LoginTimings=C:/Workspace/Sel_Framework/data/LoginTimings"+newline+
					"#Snapshot settings"+newline+
					"SnapshotOnInfo=False"+newline+
					"SnapshotOnWarn=False"+newline+
					"SnapshotOnStep=False"+newline+
					"SnapShot=Grid"+newline;
				
				String Path = System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar\\configuration.properties";
				
				FileUtils.writeStringToFile(new File(Path), BrowserConfig);
                                
    }

    public void CreateDirectories() {
        
        
                
                File file = new File(System.getProperty("user.dir")+"\\"+MainFrame.uuid);
            	file.mkdir();
            	File Jar = new File(System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar");
            	Jar.mkdir();
            	File Results = new File(System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Results");
            	Results.mkdir();
                 
                 
    }
    
        public static void checkoutCloud() throws SVNException {
            
            
           // OutPut.append("Checking Out latest Code........\n");

            	final String url = "http://10.2.88.49:100/svn/qarepos/trunk/Sel_Framework/out";
        		final String destPath = System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar";
        		
        		SVNRepository repository = null;
        		
        		try{
        			//initiate the reporitory from the url
        			repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
        			//create authentication data
        			ISVNAuthenticationManager authManager =  SVNWCUtil.createDefaultAuthenticationManager("justin.mulcahy", "DevSvn3dge");
        			repository.setAuthenticationManager(authManager);
        			//output some data to verify connection
        			System.out.println( "Repository Root: " + repository.getRepositoryRoot( true ) );
        			System.out.println(  "Repository UUID: " + repository.getRepositoryUUID( true ) );
        			//need to identify latest revision
        			long latestRevision = repository.getLatestRevision();
        			System.out.println(  "Repository Latest Revision: " + latestRevision);
        			
        			//create client manager and set authentication
        			SVNClientManager ourClientManager = SVNClientManager.newInstance();
        			ourClientManager.setAuthenticationManager(authManager);
        			//use SVNUpdateClient to do the export
        			SVNUpdateClient updateClient = ourClientManager.getUpdateClient( );
        			updateClient.setIgnoreExternals( false );
        			updateClient.doExport( repository.getLocation(), new File(destPath), 
        					SVNRevision.create(latestRevision), SVNRevision.create(latestRevision), 
        					null, true, SVNDepth.INFINITY);
        			
        		} catch (SVNException e) {
        			JOptionPane.showMessageDialog(new JFrame(), e + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
        		}finally {
        			System.out.println("Done");
        		}
            }
        
         public static void checkoutOnPrem() throws SVNException {
            
            
           // OutPut.append("Checking Out latest Code........\n");

            	final String url = "http://10.2.88.49:100/svn/qarepos/branches/2014NavigationRedesign/Sel_Framework/out";
        		final String destPath = System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar";
        		
        		SVNRepository repository = null;
        		
        		try{
        			//initiate the reporitory from the url
        			repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
        			//create authentication data
        			ISVNAuthenticationManager authManager =  SVNWCUtil.createDefaultAuthenticationManager("justin.mulcahy", "DevSvn3dge");
        			repository.setAuthenticationManager(authManager);
        			//output some data to verify connection
        			System.out.println( "Repository Root: " + repository.getRepositoryRoot( true ) );
        			System.out.println(  "Repository UUID: " + repository.getRepositoryUUID( true ) );
        			//need to identify latest revision
        			long latestRevision = repository.getLatestRevision();
        			System.out.println(  "Repository Latest Revision: " + latestRevision);
        			
        			//create client manager and set authentication
        			SVNClientManager ourClientManager = SVNClientManager.newInstance();
        			ourClientManager.setAuthenticationManager(authManager);
        			//use SVNUpdateClient to do the export
        			SVNUpdateClient updateClient = ourClientManager.getUpdateClient( );
        			updateClient.setIgnoreExternals( false );
        			updateClient.doExport( repository.getLocation(), new File(destPath), 
        					SVNRevision.create(latestRevision), SVNRevision.create(latestRevision), 
        					null, true, SVNDepth.INFINITY);
        			
        		} catch (SVNException e) {
        			JOptionPane.showMessageDialog(new JFrame(), e + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
        		}finally {
        			System.out.println("Done");
        		}
            }
        
        
        public void CreateBatchFile() throws IOException {
        
	 	
        String Batch = "java -jar "+System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar\\Autostart.jar "+System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Test.xml "+System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Results Cloud(Professional)";

        
        String Path = System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar\\RunTest.bat";

        FileUtils.writeStringToFile(new File(Path), Batch);
    }

    public void executeTest() {
        
      
		//String command = "\"cmd\", \"/c\", \"Batch.bat\" ";
		//File dir = new File("C:\\Justin\\"+uuid+"\\Jar");
		try 
        { 
            Process p =  Runtime.getRuntime().exec("cmd /c RunTest.bat", null, new File(System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar"));
             //String[] command = {"cmd.exe", "/C", "Start", "C:\\Justin\\"+uuid+"\\Jar\\RunTest.bat"};
           // Process p =  Runtime.getRuntime().exec(command); 
            
            
        
			

        }
        catch(IOException e1) {
        
        JOptionPane.showMessageDialog(new JFrame(), e1 + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);} 
 
    }
    
    
            public   String createCloudAccount() throws IOException {

        

        

                                        String newline = System.getProperty("line.separator");

                                         SecureRandom random = new SecureRandom();             

                                         String Username = new BigInteger(130, random).toString(32);

                                        String Firstname = "System";

                                         String Lastname = "Administrator";

                                          String Password = "Passw0rd1";

                                           String Email = Username+"@qasage.com";

                                          String Phone = "123456";

                                                                                String CompanyName = "SageAutomation";

                                                                               

                                                                    callURL("https://eu.ondemandstaging.com/internalautoreg/uk/automaticRegister.php?companyName="+CompanyName+"&email="+Email+"&password="+Password+"&fName="+Firstname+"&lName="+Lastname+"&phone="+Phone+"&version=2015R1");

                                                                               

                                                                    return Email;    

                                                                 
                             }
            
             public   String createCloudAccount14() throws IOException {

        

        

                                        String newline = System.getProperty("line.separator");

                                         SecureRandom random = new SecureRandom();             

                                         String Username = new BigInteger(130, random).toString(32);

                                        String Firstname = "System";

                                         String Lastname = "Administrator";

                                          String Password = "Passw0rd1";

                                           String Email = Username+"@qasage.com";

                                          String Phone = "123456";

                                                                                String CompanyName = "SageAutomation";

                                                                               

                                                                    callURL("https://eu.ondemandstaging.com/internalautoreg/uk/automaticRegister.php?companyName="+CompanyName+"&email="+Email+"&password="+Password+"&fName="+Firstname+"&lName="+Lastname+"&phone="+Phone+"&version=2014R2");

                                                                               

                                                                    return Email;    

                                                                 
                             }
    
    
    
    
    
    
    public static String callURL(String myURL) {
    System.out.println("Requeted URL:" + myURL);
    StringBuilder sb = new StringBuilder();
    URLConnection urlConn = null;
    InputStreamReader in = null;
    try {
        URL url = new URL(myURL);
        urlConn = url.openConnection();
        if (urlConn != null)
            urlConn.setReadTimeout(60 * 1000);
        if (urlConn != null && urlConn.getInputStream() != null) {
            in = new InputStreamReader(urlConn.getInputStream(),
                    Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(in);
            if (bufferedReader != null) {
                int cp;
                while ((cp = bufferedReader.read()) != -1) {
                    sb.append((char) cp);
                }
                bufferedReader.close();
            }
        }
    in.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), e + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
    } 

    return sb.toString();
}

    void reset() {
        
    }
    
    
    
    
    
    public static void PostURL(String myURL) throws IOException {
 
            URL url = new URL(myURL);
           Map<String,Object> params = new LinkedHashMap<>();

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for (int c; (c = in.read()) >= 0; System.out.print((char)c));
        }
    
    
    public static void openWebpage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}

public static void openWebpage(URL url) {
    try {
        openWebpage(url.toURI());
    } catch (URISyntaxException e) {
       JOptionPane.showMessageDialog(new JFrame(), e + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
    }
}
}
