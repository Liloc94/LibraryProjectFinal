package MainProgramFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class BookInfoPanel extends JPanel {
    private JTextField textField;

    public BookInfoPanel(String labelName) {
        setLayout(new GridLayout(2, 1, 0, 5)); // 2행 1열의 그리드 레이아웃 설정

        JLabel memberInformation = new JLabel(labelName);
        textField = new JTextField(20); // 텍스트 필드 길이를 20으로 설정

        // JLabel과 JTextField를 패널에 추가
        add(memberInformation);
        add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }
}