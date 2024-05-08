package MainProgramFrame;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MenuButtons extends JPanel {
    public MenuButtons() {
    	 JButton button1 = new JButton("대여");
         JButton button2 = new JButton("도서");
         JButton button3 = new JButton("회원");
         JButton button4 = new JButton("종료");

         add(button1);
         add(button2);
         add(button3);
         add(button4);
    }
}


