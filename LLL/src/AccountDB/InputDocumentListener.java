package AccountDB;

import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import AccountDB.AccountManager.MemberProcessor;

public class InputDocumentListener implements DocumentListener {
    private JTextField[] fields;
    private JTextField idfield;
    private JPasswordField PW;
    private JPasswordField[] pwfield;
    private JButton registerConfirmButton;
    private JButton loginButton;
    private MemberProcessor mp;
    private LoginLogic login;
    private HashMap<String, String> AccountInfo;
    
    public InputDocumentListener(JTextField[] fields, JButton registerConfirmButton, MemberProcessor mp) {
        this.fields = fields;
        this.registerConfirmButton = registerConfirmButton;
        this.mp = mp; // MemberProcessor 객체 주입
    }
    
    public InputDocumentListener(JTextField id, JPasswordField pw, JButton loginBtn, LoginLogic login2) {
    	this.idfield = id;
    	this.PW = pw;
    	this.loginButton = loginBtn;
    	this.login = login; // LoginLogic 객체 주입
	}

	public void extractLoginInfo(JTextField[] fileds) {
    	this.AccountInfo.put(fields[0].getText(), fields[1].getText());
    }
    
    public JTextField[] getFields() {
		return fields;
	}

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkFields();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    	checkFields();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    	checkFields();
    }

    public void checkFields() {
        boolean allFieldsFilled = true; // 초기값을 true로 설정
        
        // 각 텍스트 필드의 상태를 확인하고, 비어있는 필드가 있으면 allFieldsFilled 값을 false로 변경
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                allFieldsFilled = false;
                break; // 하나라도 비어있다면 더 이상 확인할 필요 없으므로 반복문 종료
            }
        }

        // 모든 필드가 채워져 있고 중복 확인이 완료되면 버튼을 활성화
        if (allFieldsFilled) {
            registerConfirmButton.setEnabled(true);
        } else {
            registerConfirmButton.setEnabled(false);
        }
    }
    
}