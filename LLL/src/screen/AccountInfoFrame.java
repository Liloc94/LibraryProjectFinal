package screen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import AccountDB.DeleteUserInfo;
import AccountDB.LoginLogic;
import AccountDB.UserInfo;
import MainProgramFrame.SwingMain;

import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class AccountInfoFrame {

	private JFrame UserInfoFrame;
	private UserInfo userInfo;
	private AccountInfoFrame aif;
	protected String userAccNum;
	protected String userID;
	protected String userPW;
	protected String userName;
	protected String userAddress;
	protected String userPhoneNum;
	
	public String getUserAccNum() {
		return userAccNum;
	}
	public String getUserID() {
		return userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountInfoFrame window = new AccountInfoFrame();
					window.UserInfoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccountInfoFrame() {
		initialize();	

	}
	
	private void loadUserInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(UserInfo.getFilePath()))) {
            String line;
            String[] tempArray;
            while ((line = reader.readLine()) != null) {
                // 파일에서 읽은 회원정보 -> 필요한 형태로 파싱 -> userInfo에 저장
                // 각 필드 추출 -> UserInfo 객체 생성 -> userInfo 필드에 저장
            	
            	tempArray = line.split(",");
            	// 로그인한 회원의 회원번호 참조 후 해당 회원정보 정적변수에 대입
            	if(tempArray[0].equals(LoginLogic.getUserKeyValue())){            		
            	userAccNum = tempArray[0];
            	userID = tempArray[1];
            	userPW = tempArray[2];
            	userName = tempArray[3];
            	userAddress = tempArray[4];
            	userPhoneNum = tempArray[5];
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loadUserInfoFromFile();
		UserInfoFrame = new JFrame();
		UserInfoFrame.setResizable(false);
		UserInfoFrame.setTitle("회원 정보");
		UserInfoFrame.setBounds(100, 100, 1000, 650);
		UserInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserInfoFrame.getContentPane().setLayout(null);
		UserInfoFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
                UserInfoFrame.dispose();
			}});

		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 986, 613);
		UserInfoFrame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JPanel displayLabelPanel = new JPanel();
		displayLabelPanel.setBackground(new Color(0, 0, 0));
		displayLabelPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		displayLabelPanel.setBounds(12, 10, 764, 593);
		panel.add(displayLabelPanel);
		displayLabelPanel.setLayout(null);
		
		JLabel accountNumLabel = new JLabel("Account Num :");
		accountNumLabel.setForeground(new Color(0, 255, 0));
		accountNumLabel.setBounds(12, 5, 194, 46);
		displayLabelPanel.add(accountNumLabel);
		accountNumLabel.setFont(new Font("Poor Richard", Font.BOLD, 30));
		
		JLabel NameLabel = new JLabel("이름 :");
		NameLabel.setForeground(new Color(0, 255, 0));
		NameLabel.setBounds(67, 93, 139, 60);
		displayLabelPanel.add(NameLabel);
		NameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		NameLabel.setFont(new Font("돋움체", Font.BOLD, 30));
		
		JLabel AddressLabel = new JLabel("주소 :");
		AddressLabel.setForeground(new Color(0, 255, 0));
		AddressLabel.setBackground(new Color(255, 255, 255));
		AddressLabel.setBounds(67, 387, 139, 60);
		displayLabelPanel.add(AddressLabel);
		AddressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		AddressLabel.setFont(new Font("돋움체", Font.BOLD, 30));
		
		JLabel IDLabel = new JLabel("아이디 :");
		IDLabel.setForeground(new Color(0, 255, 0));
		IDLabel.setBounds(67, 186, 139, 60);
		displayLabelPanel.add(IDLabel);
		IDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		IDLabel.setFont(new Font("돋움체", Font.BOLD, 30));
		
		JLabel PhoneNumLabel = new JLabel("전화번호 :");
		PhoneNumLabel.setForeground(new Color(0, 255, 0));
		PhoneNumLabel.setBounds(26, 483, 180, 60);
		displayLabelPanel.add(PhoneNumLabel);
		PhoneNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		PhoneNumLabel.setFont(new Font("돋움체", Font.BOLD, 30));
		
		JLabel PWLabel = new JLabel("비밀번호 :");
		PWLabel.setForeground(new Color(0, 255, 0));
		PWLabel.setBounds(26, 286, 180, 60);
		displayLabelPanel.add(PWLabel);
		PWLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		PWLabel.setFont(new Font("돋움체", Font.BOLD, 30));
		
		JLabel displayAccountNum = new JLabel(getUserAccNum());
		displayAccountNum.setForeground(new Color(50, 205, 50));
		displayAccountNum.setFont(new Font("휴먼편지체", Font.BOLD | Font.ITALIC, 22));
		displayAccountNum.setHorizontalAlignment(SwingConstants.CENTER);
		displayAccountNum.setBounds(218, 10, 194, 41);
		displayLabelPanel.add(displayAccountNum);
		
		JLabel showNameLabel = new JLabel(getUserName());
		showNameLabel.setForeground(new Color(255, 255, 255));
		showNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		showNameLabel.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		showNameLabel.setBounds(218, 93, 347, 60);
		displayLabelPanel.add(showNameLabel);
		
		JLabel showIDLabel = new JLabel(getUserID());
		showIDLabel.setForeground(new Color(255, 255, 255));
		showIDLabel.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		showIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		showIDLabel.setBounds(218, 186, 347, 60);
		displayLabelPanel.add(showIDLabel);
		
		JLabel pwLabel_NoShow = new JLabel("--- Can't display in public ---");
//											or getUserPW(); --> 비밀번호 표시
		pwLabel_NoShow.setForeground(new Color(255, 0, 0));
		pwLabel_NoShow.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel_NoShow.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		pwLabel_NoShow.setBounds(218, 286, 546, 60);
		displayLabelPanel.add(pwLabel_NoShow);
		
		JLabel showAddressLabel = new JLabel(getUserAddress());
		showAddressLabel.setForeground(new Color(255, 255, 255));
		showAddressLabel.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		showAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		showAddressLabel.setBounds(218, 387, 347, 60);
		displayLabelPanel.add(showAddressLabel);
		
		JLabel showPhoneNum = new JLabel(getUserPhoneNum());
		showPhoneNum.setForeground(new Color(255, 255, 255));
		showPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
		showPhoneNum.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		showPhoneNum.setBounds(218, 483, 347, 60);
		displayLabelPanel.add(showPhoneNum);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.DARK_GRAY);
		btnPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPanel.setBounds(788, 335, 186, 268);
		panel.add(btnPanel);
		btnPanel.setLayout(null);
		
		JButton modifyButton = new JButton("수정");
		modifyButton.setForeground(Color.BLACK);
		modifyButton.setFont(new Font("휴먼편지체", Font.BOLD, 32));
		modifyButton.setBounds(12, 10, 162, 78);
		btnPanel.add(modifyButton);
		modifyButton.setEnabled(true);
		if(LoginLogic.getUserKeyValue().equals("000000")) {
			modifyButton.setEnabled(false);
		}
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoModifierFrame iff = new InfoModifierFrame();
				iff.main(null);
			}
		});
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.setFont(new Font("휴먼편지체", Font.BOLD, 32));
		deleteButton.setBounds(12, 98, 162, 78);
		btnPanel.add(deleteButton);
		deleteButton.setEnabled(false);
		if(LoginLogic.getUserKeyValue().equals("000000")) {
			deleteButton.setEnabled(true);
		}
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteUserInfo dui = new DeleteUserInfo();
				String accountNum = JOptionPane.showInputDialog("삭제할 계정의 회원번호를 입력하세요.");
				dui.deleteUser(accountNum);
			}
		});
		
		JButton cancleButton = new JButton("취소");
		cancleButton.setFont(new Font("휴먼편지체", Font.BOLD, 32));
		cancleButton.setBounds(12, 186, 162, 73);
		btnPanel.add(cancleButton);
		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfoFrame.dispose();
			}
		});
		
	}
}
