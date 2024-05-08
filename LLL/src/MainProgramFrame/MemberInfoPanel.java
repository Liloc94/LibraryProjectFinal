package MainProgramFrame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

class MemberInfoPanel extends JPanel {
    private JTextField textField;

    public MemberInfoPanel(String labelName) {
        setLayout(new GridLayout(2, 1, 3, 2)); // 2행 1열의 그리드 레이아웃 설정

        JLabel memberInformation = new JLabel(labelName);
        textField = new JTextField(5);

        // JLabel과 JTextField를 패널에 추가
        add(memberInformation);
        add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }
}