import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Resume Analyzer");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea resumeArea = new JTextArea(10, 40);
        JTextArea jobArea = new JTextArea(10, 40);

        JButton loadFile = new JButton("Load Resume File");
        JButton analyze = new JButton("Analyze");

        JLabel resultLabel = new JLabel("Match: ");
        JTextArea keywordsArea = new JTextArea(5, 40);

        // Load file
        loadFile.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int res = chooser.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    resumeArea.setText(content.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Analyze
        analyze.addActionListener(e -> {
            String resume = resumeArea.getText();
            String job = jobArea.getText();

            String processedResume = TextProcessor.process(resume);
            String processedJob = TextProcessor.process(job);

            double score = SimilarityCalculator.calculate(processedResume, processedJob);

            resultLabel.setText("Match: " + (score * 100) + "%");

            keywordsArea.setText(SimilarityCalculator.commonWords(processedResume, processedJob));
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Resume"));
        panel.add(new JScrollPane(resumeArea));
        panel.add(loadFile);

        panel.add(new JLabel("Job Description"));
        panel.add(new JScrollPane(jobArea));

        panel.add(analyze);
        panel.add(resultLabel);

        panel.add(new JLabel("Common Keywords"));
        panel.add(new JScrollPane(keywordsArea));

        frame.add(panel);
        frame.setVisible(true);
    }
}
