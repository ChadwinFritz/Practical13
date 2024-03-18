package za.ac.cput.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import za.ac.cput.DAO.SubjectDAO;
import za.ac.cput.Subject.Subject;

public class UniversityGUI extends JFrame {
    
    private JPanel panelW, panelE, panelS, panelBox;
    private JLabel lblCode, lblDes;
    private JTextField txtCode, txtDes;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox comBox;
    private JButton btnSave, btnRead, btnExit;
    private Subject subject;
    private SubjectDAO sql;
    private ArrayList<Subject> subjectList = new ArrayList<>();
    
    public UniversityGUI(){
        
        super("Add a subject");
        
        panelW = new JPanel();
        panelE = new JPanel();
        panelS = new JPanel();
        panelBox = new JPanel();
        
        lblCode = new JLabel("Subject Code ");
        lblDes = new JLabel("Description:");
        
        txtCode = new JTextField();
        txtDes = new JTextField();
        
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Subject Code");
        tableModel.addColumn("Description");
        //table.setPreferredSize(new Dimension(100,100));
        
        comBox = new JComboBox();
        //comBox.setPreferredSize(new Dimension(100,100));
        
        btnSave = new JButton("Save");
        btnRead = new JButton("Read");
        btnExit = new JButton("Exit");
        
        sql = new SubjectDAO();
    }
    
    public void setGui(){
        
        panelW.setLayout(new GridLayout(2,2));
        panelW.setPreferredSize(new Dimension(400,100));
        panelW.add(lblCode);
        panelW.add(txtCode);
        panelW.add(lblDes);
        panelW.add(txtDes);
        this.add(panelW, BorderLayout.WEST);
        
        panelE.setLayout(new GridLayout(1,1));
        panelE.setPreferredSize(new Dimension(500,500));
        panelE.add(new JScrollPane(table));
        panelBox.add(comBox);
        panelE.add(panelBox);
        this.add(panelE, BorderLayout.EAST);
        
        panelS.setLayout(new GridLayout(1,3));
        panelS.add(btnSave);
        panelS.add(btnRead);
        panelS.add(btnExit);
        this.add(panelS, BorderLayout.SOUTH);
        
        btnSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = txtCode.getText();
                String des = txtDes.getText();
                
                Subject s = new Subject(code, des);
                
                subject = sql.save(s);
                if(subject.equals(s)){
                    JOptionPane.showMessageDialog(null, "Success");
                    txtCode.setText("");
                    txtDes.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
            
            
        });
        
        btnRead.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                subjectList = sql.read();
                
                // Sets table to empty
                table.setModel(tableModel);
                tableModel = (DefaultTableModel) table.getModel();
                tableModel.setRowCount(0);
                
                for (int i = 0; i < subjectList.size(); i++){
                    String code = subjectList.get(i).getSubjectCode();
                    String des = subjectList.get(i).getDescription();
                    Object[] subList = {code, des};
                    tableModel.addRow(subList);
                    
                    // Another way of doing it
                    // tableModel.addRow(new Object[]{code, des});
                }
            }
            
        });
        
        btnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sql.closeOperation();
                System.exit(0);
            }
        });
        
    }
    
}
