package screen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import AccountDB.AccountManager.MemberProcessor;
import AccountDB.LoginLogic;
import AccountDB.UserInfo;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class InfoModifierFrame {
	private static final String FILE_PATH = "src/userData/member.txt";
	private JFrame ModifyingFrame;
	private JTextField idInputField;
	private JTextField nameInputField;
	private JTextField addressInputField;
	private JTextField phoneNumInputField;
	private JPasswordField passwordField;
	private String comboValue;
	static JButton modifyConfirmButton;
	private MemberProcessor mp; // MemberProcessor 객체화
	private UserInfo ui;
	
	
	public JTextField getIdInputField() {
		return idInputField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JTextField getNameInputField() {
		return nameInputField;
	}
	public JTextField getAddressInputField() {
		return addressInputField;
	}
	public JTextField getPhoneNumInputField() {
		return phoneNumInputField;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoModifierFrame window = new InfoModifierFrame();
					window.ModifyingFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InfoModifierFrame() {
		ui = new UserInfo();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ModifyingFrame = new JFrame();
		ModifyingFrame.setBackground(new Color(128, 128, 128));
		ModifyingFrame.setResizable(false);
		ModifyingFrame.setType(Type.POPUP);
		ModifyingFrame.setTitle("회원 정보 수정");
		ModifyingFrame.getContentPane().setBackground(new Color(192, 192, 192));
		ModifyingFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(12, 10, 504, 343);
		ModifyingFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel IDLabel = new JLabel("ID");
		IDLabel.setFont(new Font("함초롬바탕", Font.BOLD, 27));
		IDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		IDLabel.setBounds(12, 20, 120, 45);
		panel.add(IDLabel);
		
		JLabel NameLabel = new JLabel("이름");
		NameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		NameLabel.setFont(new Font("함초롬바탕", Font.BOLD, 27));
		NameLabel.setBounds(12, 143, 120, 45);
		panel.add(NameLabel);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("함초롬바탕", Font.BOLD, 27));
		passwordLabel.setBounds(12, 75, 120, 45);
		panel.add(passwordLabel);
		
		JLabel addressLabel = new JLabel("주소");
		addressLabel.setFont(new Font("함초롬바탕", Font.BOLD, 27));
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setBounds(12, 206, 120, 45);
		panel.add(addressLabel);
		
		JLabel phoneNumLabel = new JLabel("전화번호");
		phoneNumLabel.setFont(new Font("함초롬바탕", Font.BOLD, 27));
		phoneNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNumLabel.setBounds(12, 272, 120, 45);
		panel.add(phoneNumLabel);
		
		idInputField = new JTextField();
		idInputField.setBounds(144, 20, 313, 45);
		panel.add(idInputField);
		idInputField.setColumns(10);
		
		nameInputField = new JTextField();
		nameInputField.setColumns(10);
		nameInputField.setBounds(144, 150, 313, 45);
		panel.add(nameInputField);
		
		addressInputField = new JTextField();
		addressInputField.setColumns(10);
		addressInputField.setBounds(144, 213, 313, 45);
		panel.add(addressInputField);
		
		phoneNumInputField = new JTextField();
		phoneNumInputField.setColumns(10);
		phoneNumInputField.setBounds(218, 281, 239, 42);
		panel.add(phoneNumInputField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 82, 313, 45);
		panel.add(passwordField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"통신사", "SKT", "KT", "LG"}));
		comboBox.setBounds(144, 282, 62, 38);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboValue = (String)comboBox.getSelectedItem();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(528, 229, 156, 124);
		ModifyingFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton modifyCancleBtn = new JButton("취소");
		modifyCancleBtn.setFont(new Font("한컴 고딕", Font.BOLD, 30));
		modifyCancleBtn.setBounds(12, 67, 132, 47);
		panel_1.add(modifyCancleBtn);
		ModifyingFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ModifyingFrame.dispose();
			}
		});
		modifyCancleBtn.addActionListener(x->ModifyingFrame.dispose());
		// 수정값 넘겨줄 ArrayList
		ArrayList<String> tempArray = new ArrayList<String>();
		
		JButton modifyConfirmBtn = new JButton("적용");
		modifyConfirmBtn.setFont(new Font("한컴 고딕", Font.BOLD, 30));
		modifyConfirmBtn.setBounds(12, 10, 132, 47);
		panel_1.add(modifyConfirmBtn);
		
		JTextField[] inputFields = 
			{idInputField, passwordField, nameInputField, addressInputField, phoneNumInputField};
		
		modifyConfirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isValid = true; // 입력값이 유효한지 여부를 나타내는 플래그
		        ArrayList<String> tempArray = new ArrayList<>();

		        // 각 입력 필드의 값을 확인하고 비어 있는지 검증
		        for (JTextField field : inputFields) {
		            if (field.getText().isEmpty()) {
		                isValid = false;
		                break;
		            }
		        }
		        if(isValid) {
//TODO:			수정할값 넘기기 + 타겟특정용 ID값
				tempArray.add(idInputField.getText());
				String tempString = new String(passwordField.getPassword());
				tempArray.add(tempString);
				tempArray.add(nameInputField.getText());
				tempArray.add(addressInputField.getText());
				tempArray.add(comboValue + phoneNumInputField.getText());
//TODO: 		회원 로그인 이후 회원번호값 받아서 넣기
				ui.setAccountInfo(tempArray, LoginLogic.getUserKeyValue());
				JOptionPane.showMessageDialog(null, "회원정보 수정이 완료되었습니다.");
				ModifyingFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "수정할 값이 존재하지 않습니다 !!");
				}
			}
		});
		
		JLabel noticeLabel = new JLabel("입력한 정보로 수정됩니다.");
		noticeLabel.setForeground(new Color(255, 0, 0));
		noticeLabel.setBackground(new Color(255, 255, 255));
		noticeLabel.setVerticalAlignment(SwingConstants.TOP);
		noticeLabel.setFont(new Font("함초롬돋움", Font.BOLD, 13));
		noticeLabel.setBounds(524, 200, 172, 19);
		ModifyingFrame.getContentPane().add(noticeLabel);
		ModifyingFrame.setBounds(100, 100, 710, 400);
		ModifyingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
