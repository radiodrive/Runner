package testrunner1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author justin.mulcahy
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

/**
 * @se http://stackoverflow.com/a/20603012/230513
 * @see http://stackoverflow.com/a/17763395/230513
 */
public class RunTest {

    private final JLabel statusLabel = new JLabel("Status: ", JLabel.CENTER);
    private final JTextArea textArea = new JTextArea(40, 80);
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JProgressBar bar = new JProgressBar();
    private BackgroundTask backgroundTask;
    private final ActionListener buttonActions = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton source = (JButton) ae.getSource();
            if (source == startButton) {
                textArea.setText(null);
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                backgroundTask = new BackgroundTask();
                backgroundTask.execute();
                bar.setIndeterminate(true);
            } else if (source == stopButton) {
                backgroundTask.cancel(true);
                backgroundTask.done();
            }
        }
    };

    public void displayGUI() {
        JFrame frame = new JFrame("TestRunner");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(5, 5));

        JScrollPane sp = new JScrollPane();
        sp.setBorder(BorderFactory.createTitledBorder("Output: "));
        sp.setViewportView(textArea);

        startButton.addActionListener(buttonActions);
        stopButton.setEnabled(false);
        stopButton.addActionListener(buttonActions);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(bar);

        panel.add(statusLabel, BorderLayout.PAGE_START);
        panel.add(sp, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private class BackgroundTask extends SwingWorker<Integer, String> {

        private int status;

        public BackgroundTask() {
            statusLabel.setText((this.getState()).toString());
        }

        @Override
        protected Integer doInBackground() {
            try {
                ProcessBuilder pb = new ProcessBuilder(System.getProperty("user.dir")+"\\"+MainFrame.uuid+"\\Jar\\RunTest.bat");
                File userDir = new File(System.getProperty("user.dir") +"\\"+MainFrame.uuid+ "\\Jar");
                if (userDir.exists()) {
                	pb = pb.directory(userDir);
                    File temp = pb.directory();
                    String currentWorkingDirectory = "Results will be available from here: " + temp.toString();
                    JOptionPane.showMessageDialog(null, currentWorkingDirectory);
                    pb.start();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Can't find Standard Software directory!"+userDir);
                }
                pb.redirectErrorStream(true);
                Process p = pb.start();
                String s;
                BufferedReader stdout = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
                while ((s = stdout.readLine()) != null && !isCancelled()) {
                    publish(s);
                }
                if (!isCancelled()) {
                    status = p.waitFor();
                }
                
                p.getInputStream().close();
                p.getOutputStream().close();
                p.getErrorStream().close();
                p.destroy();
            } catch (IOException | InterruptedException ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex + "\n"+"Please contact your system administrator.","Error",JOptionPane.ERROR_MESSAGE);
            }
            return status;
        }

        @Override
        protected void process(java.util.List<String> messages) {
            statusLabel.setText((this.getState()).toString());
            for (String message : messages) {
                textArea.append(message + "\n");
            }
        }

        @Override
        protected void done() {
            statusLabel.setText((this.getState()).toString() + " " + status);
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            bar.setIndeterminate(false);
        }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RunTest().displayGUI();
            }
        });
    }
}