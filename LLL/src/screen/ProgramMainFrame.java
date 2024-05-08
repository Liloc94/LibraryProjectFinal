package screen;

import java.awt.EventQueue;
import javax.swing.JFrame;

import java.util.HashMap;
import java.util.Locale;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Canvas;
import java.awt.List;
import java.awt.Panel;
import javax.swing.JDesktopPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

import AccountDB.AccountManager;
import AccountDB.InputDocumentListener;
import AccountDB.LoginLogic;
import AccountDB.UserInfo;
import MainProgramFrame.SwingMain;

import javax.swing.JPasswordField;

public class ProgramMainFrame {
	private static final String FILE_PATH = "src/userData/";
	private JFrame frame;
	private JTextField idField;
	private JPasswordField passwordField;
	private LoginLogic login;
	private InputDocumentListener listener;
	
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramMainFrame window = new ProgramMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProgramMainFrame() {
		// 프로그램 실행 시 로그인 정보 불러올 객체생성
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setName("ImagePane");
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setTitle("도서관리 프로그램");
		frame.setLocale(Locale.KOREA);
		frame.setBounds(140, 30, 1680, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(0, 0, 1664, 1011);
		frame.getContentPane().add(imagePanel);
		imagePanel.setLayout(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.LIGHT_GRAY);
		loginPanel.setBounds(656, 634, 492, 170);
		imagePanel.add(loginPanel);
		loginPanel.setLayout(null);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.setBounds(320, 10, 162, 58);
		loginPanel.add(loginBtn);
		
		listener = new InputDocumentListener(idField, passwordField, loginBtn, login);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login = new LoginLogic();
				if (idField.getText().isEmpty() || passwordField.getPassword().length == 0) {
			        JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호 입력을 확인하세요");
			    } else {
			        // 로그인 입력값 검증을 위해 LoginLogic 으로 넘기기
			        login.setLoginParams(idField.getText(), passwordField.getPassword());
			    }
			        if (login.isCorrect()) {
			        	JOptionPane.showMessageDialog(null, "환영합니다.");
			        	SwingMain swingmain = new SwingMain(LoginLogic.getUserKeyValue());
			        	swingmain.main(null);
			            // TODO: 회원 로그인 연결 !!
			            // 회원용 메인프레임 연결
			        	System.out.println(LoginLogic.getUserKeyValue());
			        	frame.dispose();
			        	
			        } else if (login.isAdmin()) {
			        	JOptionPane.showMessageDialog(null, "관리자 로그인 성공");
			            // TODO: 관리자 로그인 연결 !!
			            // 관리자용 메인프레임 연결
			        	System.out.println(LoginLogic.getUserKeyValue());
			        	SwingMain.main(null);	 
			        	frame.dispose();
			        	
			        	
			        	
			        } else {
			            JOptionPane.showMessageDialog(null, "회원으로 등록되지 않은 정보입니다\n 아이디와 비밀번호를 다시 확인해주세요.");
			        }
			    }
			}
		);
		
		JButton RegisterBtn = new JButton("회원가입");
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame regist = new RegisterFrame();
				// 회원가입 프레임 팝업
				regist.main(null);
			}
		});
		RegisterBtn.setBounds(118, 83, 135, 51);
		loginPanel.add(RegisterBtn);
		
		JButton exitBtn = new JButton("프로그램 종료");
		exitBtn.addActionListener(x -> System.exit(0));
		exitBtn.setBounds(265, 83, 135, 51);
		loginPanel.add(exitBtn);
		
		JLabel registerNotice = new JLabel("처음이신가요 ??");
		registerNotice.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		registerNotice.setForeground(Color.BLUE);
		registerNotice.setBounds(128, 134, 125, 26);
		loginPanel.add(registerNotice);
		
		JLabel idLabel = new JLabel("아이디 :");
		idLabel.setBounds(20, 16, 49, 15);
		loginPanel.add(idLabel);
		
		JLabel pwLabel = new JLabel("비밀번호 :");
		pwLabel.setBounds(12, 53, 57, 15);
		loginPanel.add(pwLabel);
		
		idField = new JTextField();
		idField.setBounds(81, 10, 227, 28);
		loginPanel.add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(81, 43, 227, 28);
		loginPanel.add(passwordField);
		
		JLabel imgLabel = new JLabel("");
		imgLabel.setBounds(0, 0, 1664, 1011);
		imagePanel.add(imgLabel);
		imgLabel.setIcon(new ImageIcon(ProgramMainFrame.class.getResource("/image/Library.jpg")));

		}
	}
