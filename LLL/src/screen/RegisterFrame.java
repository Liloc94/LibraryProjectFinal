package screen;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import AccountDB.AccountManager;
import AccountDB.AccountManager.MemberProcessor;
import AccountDB.InputDocumentListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Color;

public class RegisterFrame {
	private JFrame RegisterFrame;
	private JTextField idInput;
	private JPasswordField pwInput;
	private JPasswordField pwCheckField;
	private JTextField nameInput;
	private JTextField addressInput;
	private JTextField phoneNumInput;
	private JComboBox<String> comboBox;
	private String comboValue;
	private MemberProcessor mp; // MemberProcessor 객체화
	private AccountManager am;
	
	/**
	 * Launch the application.
	 */
	
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    RegisterFrame window = new RegisterFrame();
	                    window.RegisterFrame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	
	/**
	 * Create the application.
	 */
	public RegisterFrame() {
		mp = new MemberProcessor();
		am = new AccountManager();
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 * @param <E>
	 */
	private <E> void initialize() {
		RegisterFrame = new JFrame();
		RegisterFrame.setTitle("신규회원 등록");
		RegisterFrame.setBounds(750, 200, 440, 625);
		RegisterFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		RegisterFrame.getContentPane().setLayout(null);
		RegisterFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 586);
		RegisterFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(12, 10, 403, 566);
		panel.add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		JButton RegisterConfirm = new JButton("신규회원 등록");
		RegisterConfirm.setBounds(47, 518, 141, 43);
		ButtonPanel.add(RegisterConfirm);
		RegisterConfirm.setEnabled(false);
		
		// 회원정보 파라미터 전달할 ArrayList 객체화
		ArrayList<String> save = new ArrayList<>();
		
		// 회원등록 버튼 이벤트 할당
		RegisterConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MemberProcessor.getisIdDupChecked()) {
					RegisterConfirm.setEnabled(true);
				// char 배열 getPassword()를 String으로 변환
			    String temp = new String(pwInput.getPassword());
			    save.add(idInput.getText());
				// char[] password -> String으로 바꿔서 save에 전달
				save.add(temp);
				save.add(nameInput.getText());
				save.add(addressInput.getText());
				
				// JComboBox 값 더해서 전달
				save.add(comboValue + phoneNumInput.getText());
				
				// AccountManager로 입력값 전부 넘기기
				am.addMember(save);
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				RegisterFrame.dispose();
				} else if(!MemberProcessor.getisIdDupChecked()) {
					RegisterConfirm.setEnabled(false);
					JOptionPane.showMessageDialog(null, "중복된 아이디를 사용할 수 없습니다 !!");
				}
				}
			});
		
		JButton RegisterCancleBtn = new JButton("취소");
		RegisterCancleBtn.setBounds(200, 518, 141, 43);
		ButtonPanel.add(RegisterCancleBtn);
		RegisterCancleBtn.addActionListener(x -> RegisterFrame.dispose());
		RegisterFrame.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
	                RegisterFrame.dispose();
		}});
		
		JPanel accInfoPanel = new JPanel();
		accInfoPanel.setBounds(-12, -12, 425, 520);
		ButtonPanel.add(accInfoPanel);
		accInfoPanel.setLayout(null);
		
		JLabel idInputLabel = new JLabel("아이디   : ");
		idInputLabel.setBounds(44, 181, 60, 35);
		accInfoPanel.add(idInputLabel);
		
		idInput = new JTextField();
		idInput.setBounds(109, 181, 200, 35);
		accInfoPanel.add(idInput);
		idInput.setColumns(10);
		
		
		JButton isDupBtn = new JButton("중복 확인");
		isDupBtn.setBounds(321, 181, 92, 35);
		accInfoPanel.add(isDupBtn);
		
		// 아이디 중복확인 로직
		isDupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 중복확인 클래스 객체화
				MemberProcessor mp = new MemberProcessor();
				mp.checkDup(idInput.getText());
			}
		});
		
		JLabel nameInputLabel = new JLabel("이름  : ");
		nameInputLabel.setBounds(58, 133, 60, 15);
		accInfoPanel.add(nameInputLabel);
		
		pwInput = new JPasswordField();
		pwInput.setBounds(109, 238, 200, 35);
		accInfoPanel.add(pwInput);
		
		JLabel pwInputLabel = new JLabel("비밀번호 : ");
		pwInputLabel.setBounds(44, 248, 74, 15);
		accInfoPanel.add(pwInputLabel);
		
		nameInput = new JTextField();
		nameInput.setBounds(109, 123, 200, 35);
		accInfoPanel.add(nameInput);
		nameInput.setColumns(10);
		
		JLabel addInputLabel = new JLabel("주소   : ");
		addInputLabel.setBounds(61, 374, 57, 15);
		accInfoPanel.add(addInputLabel);
		
		JLabel pNumInputLabel = new JLabel("연락처 : ");
		pNumInputLabel.setBounds(58, 440, 60, 15);
		accInfoPanel.add(pNumInputLabel);
		
		addressInput = new JTextField();
		addressInput.setBounds(109, 364, 200, 35);
		accInfoPanel.add(addressInput);
		addressInput.setColumns(10);
		
		phoneNumInput = new JTextField();
		phoneNumInput.setBounds(191, 430, 118, 35);
		accInfoPanel.add(phoneNumInput);
		phoneNumInput.setColumns(10);

		
		JComboBox phonecomboBox = new JComboBox();
		// 콤보박스 값 반환받기 -> 회원DB 저장용
		String[] items = {"통신사","SKT", "KT", "LG"};
		phonecomboBox.setModel(new DefaultComboBoxModel<>(items));
		phonecomboBox.setBounds(109, 430, 70, 35);
		accInfoPanel.add(phonecomboBox);
		phonecomboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboValue = (String)phonecomboBox.getSelectedItem();
			}
		});
		
		JLabel welcomeImg = new JLabel("");
		welcomeImg.setIcon(new ImageIcon(RegisterFrame.class.getResource("/image/welcome (1).jpg")));
		welcomeImg.setBounds(12, 10, 401, 86);
		accInfoPanel.add(welcomeImg);
		
		JLabel onlyNumber = new JLabel("숫자만 입력하세요 !!");
		onlyNumber.setFont(new Font("돋움체", Font.PLAIN, 12));
		onlyNumber.setForeground(Color.GRAY);
		onlyNumber.setBounds(191, 475, 121, 15);
		accInfoPanel.add(onlyNumber);
		
		JLabel pwIsDupCheck = new JLabel("비밀번호 확인 :");
		pwIsDupCheck.setBounds(12, 311, 92, 15);
		accInfoPanel.add(pwIsDupCheck);
		
		pwCheckField = new JPasswordField();
		pwCheckField.setBounds(109, 301, 200, 35);
		accInfoPanel.add(pwCheckField);
		pwCheckField.setColumns(10);
		
		JLabel pwCheck = new JLabel("비밀번호가 일치하지 않습니다 !!");
		pwCheck.setVisible(false);
		
		
		// 패스워드 중복확인 타이핑체크 
		pwInput.getDocument().addDocumentListener(new DocumentListener() {
		    public void changedUpdate(DocumentEvent e) {
		        checkPassword();
		    }
		    public void removeUpdate(DocumentEvent e) {
		        checkPassword();
		    }
		    public void insertUpdate(DocumentEvent e) {
		        checkPassword();
		    }
		    private void checkPassword() {
		        String password = new String(pwInput.getPassword());
		        String passwordCheck = new String(((JPasswordField) pwCheckField).getPassword());
		        if (password.equals(passwordCheck)) {
		            pwCheck.setVisible(false);
		        } else {
		            pwCheck.setVisible(true);
		        }
		    }
		});

		pwCheckField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkPassword();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkPassword();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkPassword();
			}
			
			private void checkPassword() {
		        String password = new String(pwInput.getPassword());
		        String passwordCheck = new String(((JPasswordField) pwCheckField).getPassword());
		        if (password.equals(passwordCheck)) {
		            pwCheck.setVisible(false);
		        } else {
		            pwCheck.setVisible(true);
		        }
		    }
		});
		
		InputDocumentListener listener = new InputDocumentListener(new JTextField[]{idInput, pwInput, nameInput, addressInput, phoneNumInput}, RegisterConfirm, mp);
		idInput.getDocument().addDocumentListener(listener);
		pwInput.getDocument().addDocumentListener(listener);
		nameInput.getDocument().addDocumentListener(listener);
		addressInput.getDocument().addDocumentListener(listener);
		phoneNumInput.getDocument().addDocumentListener(listener);

		
		pwCheck.setFont(new Font("돋움", Font.PLAIN, 12));
		pwCheck.setForeground(Color.RED);
		pwCheck.setBounds(112, 339, 197, 15);
		accInfoPanel.add(pwCheck);
	}
	

	
	
}
