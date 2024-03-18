package za.ac.cput.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import za.ac.cput.DBC.DBConnection;
import za.ac.cput.Subject.Subject;

public class SubjectDAO {

    private Connection con;
    private Statement state;
    private PreparedStatement pState;
    private ResultSet rs;

    public SubjectDAO() {

        try {
            this.con = DBConnection.derbyConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public Subject save(Subject subject) {

        try {
            int ok;
            
            // Statement method 
            /*
            String insert = "Insert into Subject(Subject_Code, Subject_Description) Values ('%s', '%s')";
            insert = String.format(insert, subject.getSubjectCode(), subject.getDescription());
            state = this.con.createStatement();
            ok = state.executeUpdate(insert);
            */

            // Prepared statement method
            String insertSQL = "Insert into sujects values (?, ?)"; // Not case sensitive
            pState = this.con.prepareStatement(insertSQL);
            pState.setString(1, subject.getSubjectCode());
            pState.setString(2, subject.getDescription());
            ok = pState.executeUpdate();
            
            if (ok > 0) {
                return subject;
            } else {
                return null;
            }

        } //end try
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                // statement close
                /*
                if (state != null) {
                    state.close();
                } */
                
                // prepared statement close
                if(pState != null){
                pState.close();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        } // end finally

        return null;
    }

    public ArrayList<Subject> read() {

        ArrayList<Subject> subjects = new ArrayList<>();
        String select = ("Select * from Subject");
        
        // Select all the values from subject code that s == to ADP2
        // String select = ("Select * from Subject where subject_code ?");
        // pState.setString(1, "ADP2")

        try {
            
            // Statement method
            //state = this.con.createStatement();
            //rs = state.executeQuery(select);
            
            // prepared statement method
            pState = this.con.prepareStatement(select);
            rs = pState.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    //System.out.println("Database Records: " + rs.getString(1) + rs.getString(2)); Displays each row and column in database
                    subjects.add(new Subject(rs.getString("Subject_Code"), rs.getString("Subject_Description")));
                } // end while
                rs.close();

            } //end try

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (pState != null) {
                    pState.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        return subjects;
    }
    
    public void closeOperation(){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
