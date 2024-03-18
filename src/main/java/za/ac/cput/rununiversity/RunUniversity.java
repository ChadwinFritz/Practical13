package za.ac.cput.rununiversity;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import za.ac.cput.GUI.UniversityGUI;

/**
 *
 * @author User
 */
public class RunUniversity {

    public static void main(String[] args) {
        
        UniversityGUI display = new UniversityGUI();
        display.pack();
        display.setSize(1000,700);
        display.setVisible(true);
        display.setDefaultCloseOperation(EXIT_ON_CLOSE);
        display.setGui();
        
    }
}
