
package autos;

import com.formdev.flatlaf.FlatDarkLaf;


public class Main {

     public static void main(String[] args) {

        FlatDarkLaf.setup();

        java.awt.EventQueue.invokeLater(() -> {
            new crud();
        });
     }
}
