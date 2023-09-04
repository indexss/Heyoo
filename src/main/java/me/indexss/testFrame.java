package me.indexss;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;

import javax.swing.*;

public class testFrame extends JFrame {
    public void testFrame(){
        FlatSolarizedDarkIJTheme.setup();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlatArcDarkIJTheme.setup();
        testFrame x = new testFrame();
        x.setVisible(true);
    }
}
