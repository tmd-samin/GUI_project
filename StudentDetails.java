import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class StudentDetails extends JFrame implements ActionListener {
  // GUI components
  private JTextField nameField;
  private JTextField rollField;
  private JComboBox<String> batchCombo;
  private JCheckBox genderBox;
  private JButton saveButton;
  private JButton showButton;
  private JTextArea displayArea;

  // File to store student details
  private File studentFile;

  public StudentDetails() {
    // Set up GUI
    super("Student Details");
    setLayout(new FlowLayout());

    nameField = new JTextField(20);
    add(new JLabel("Name:"));
    add(nameField);

    rollField = new JTextField(20);
    add(new JLabel("Roll Number:"));
    add(rollField);

    String[] batches = {"2017", "2018", "2019", "2020", "2021", "2022", "2023"};
    batchCombo = new JComboBox<>(batches);
    add(new JLabel("Batch:"));
    add(batchCombo);
    

    add(new JLabel("Gender:"));
    genderBox = new JCheckBox("male");
    add(genderBox);
    genderBox = new JCheckBox("Female");
    add(genderBox);

    saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    add(saveButton);

    showButton = new JButton("Show");
    showButton.addActionListener(this);
    add(showButton);

    displayArea = new JTextArea(10, 40);
    add(displayArea);

    // Set up file to store student details
    studentFile = new File("students.txt");
  }

  public void actionPerformed(ActionEvent event) {
    // Save button clicked
    if (event.getSource() == saveButton) {
      String name = nameField.getText();
      String roll = rollField.getText();
      String batch = (String) batchCombo.getSelectedItem();
      String gender = genderBox.isSelected() ? "Female" : "Male";

      try (PrintWriter output = new PrintWriter(new FileWriter(studentFile, true))) {
        output.println(name + "," + roll + "," + batch + "," + gender);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    // Show button clicked
    else if (event.getSource() == showButton) {
      displayArea.setText("");
        try (Scanner input = new Scanner(studentFile)) {
            while (input.hasNextLine()) {
              String line = input.nextLine();
              String[] details = line.split(",");
              String name = details[0];
              String roll = details[1];
              String batch = details[2];
              String gender = details[3];
              displayArea.append("Name: " + name + "\n");
              displayArea.append("Roll Number: " + roll + "\n");
              displayArea.append("Batch: " + batch + "\n");
              displayArea.append("Gender: " + gender + "\n");
              displayArea.append("\n");
            }
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
    }
    
    public static void main(String[] args) {
    StudentDetails frame = new StudentDetails();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    }
}    
          

