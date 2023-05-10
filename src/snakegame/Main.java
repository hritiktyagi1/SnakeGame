
package snakegame;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame("Snake game");
        frame.setBounds(10,10,905,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePannel pannel = new GamePannel();
        pannel.setBackground(Color.DARK_GRAY);
        frame.add(pannel);
        frame.setVisible(true);
    }
    
    
}
