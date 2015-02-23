/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunner1;

/**
 *
 * @author justin.mulcahy
 */
/**
 *
 * @author Justin Mulcahy
 */
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a pie chart using 
 * data from a {@link DefaultPieDataset}.
 */
public class PieChart extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public PieChart(String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
        String connectionURL = "jdbc:sqlserver://10.2.65.22:1433;databaseName=TestRunner;user=sa;password=Passw0rd";
// Change the connection string according to your db, ip, username and password
 DefaultPieDataset dataset = new DefaultPieDataset();
try {
    String date = new SimpleDateFormat("").format(new Date());
    // Load the Driver class. 
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    // If you are using any other database then load the right driver here.
 
    //Create the connection using the static getConnection method
    Connection con = DriverManager.getConnection (connectionURL);
 
    //Create a Statement class to execute the SQL statement
    java.sql.Statement stmt = con.createStatement();
 
    //Execute the SQL statement and get the results in a Resultset
   ResultSet rs = stmt.executeQuery("SELECT Max([Product]) as Product,[TestName],[MethodName],Max([Result])as Result,Max([Version])as Version,Max([Date]) as Date FROM [TestRunner].[dbo].[Results] where Date>'"+date+" 23:00:11.353' GROUP BY [TestName],[MethodName]");

 
    // Iterate through the ResultSet, displaying two values
    // for each row using the getString method
 while (rs.next()){
 //   Test.addItem(rs.getString("ClassName"));
     
     
       dataset.setValue("Pass",rs.getInt("Pass"));
      dataset.setValue("Fail",rs.getInt("Fail"));
      dataset.setValue("Skip",rs.getInt("Skip"));
 }
      
     // System.out.println("Name= " + rs.getString("ClassName"));
}

  catch (SQLException e) {
    e.printStackTrace();
}
catch (Exception e) {
    e.printStackTrace();
}      
        
      return dataset;        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Test Results",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void DispalyResults() {

        PieChart demo = new PieChart("TestRunner Results");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}