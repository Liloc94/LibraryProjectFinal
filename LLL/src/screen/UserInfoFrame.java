package screen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

import AccountDB.UserInfo;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public class UserInfoFrame {
	
	private static final String FILE_PATH = "src/userData/member.txt";
	private JFrame myInfo;
	private UserInfo userInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoFrame window = new UserInfoFrame();
					window.myInfo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInfoFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		myInfo = new JFrame();
		myInfo.getContentPane().setBackground(new Color(230, 230, 250));
		myInfo.getContentPane().setFont(new Font("돋움체", Font.BOLD, 20));
		myInfo.setTitle("회원정보");
		myInfo.setResizable(false);
		myInfo.setBounds(100, 100, 830, 680);
		myInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myInfo.getContentPane().setLayout(null);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new MatteBorder(2, 2, 2, 2, (Color) new Color(211, 211, 211))));
		userInfoPanel.setBackground(new Color(255, 255, 255));
		userInfoPanel.setBounds(12, 10, 614, 623);
		myInfo.getContentPane().add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		JLabel AccNumLabel = new JLabel("회원번호");
		AccNumLabel.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		AccNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		AccNumLabel.setBounds(0, 62, 134, 53);
		userInfoPanel.add(AccNumLabel);
		
		JLabel IDLabel = new JLabel("회원 ID");
		IDLabel.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		IDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		IDLabel.setBounds(0, 161, 134, 53);
		userInfoPanel.add(IDLabel);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(0, 272, 134, 53);
		userInfoPanel.add(nameLabel);
		
		JLabel addressLabel = new JLabel("주소");
		addressLabel.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setBounds(0, 382, 134, 53);
		userInfoPanel.add(addressLabel);
		
		JLabel phoneNumLabel = new JLabel("전화번호");
		phoneNumLabel.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		phoneNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		phoneNumLabel.setBounds(0, 489, 134, 53);
		userInfoPanel.add(phoneNumLabel);
		
		JLabel displayAccountNum = new JLabel("");
		displayAccountNum.setHorizontalAlignment(SwingConstants.CENTER);
		displayAccountNum.setFont(new Font("바탕", Font.BOLD, 22));
		displayAccountNum.setBounds(173, 62, 355, 53);
		userInfoPanel.add(displayAccountNum);
		
		JLabel displayMemberID = new JLabel("");
		displayMemberID.setFont(new Font("바탕", Font.BOLD, 22));
		displayMemberID.setHorizontalAlignment(SwingConstants.CENTER);
		displayMemberID.setBounds(173, 161, 355, 53);
		userInfoPanel.add(displayMemberID);
		
		JLabel displayName = new JLabel("");
		displayName.setFont(new Font("바탕", Font.BOLD, 22));
		displayName.setHorizontalAlignment(SwingConstants.CENTER);
		displayName.setBounds(173, 272, 355, 53);
		userInfoPanel.add(displayName);
		
		JLabel displayAddress = new JLabel("");
		displayAddress.setFont(new Font("바탕", Font.BOLD, 22));
		displayAddress.setHorizontalAlignment(SwingConstants.CENTER);
		displayAddress.setBounds(173, 382, 355, 53);
		userInfoPanel.add(displayAddress);
		
		JLabel displayPhoneNum = new JLabel("");
		displayPhoneNum.setFont(new Font("바탕", Font.BOLD, 22));
		displayPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
		displayPhoneNum.setBounds(173, 489, 355, 53);
		userInfoPanel.add(displayPhoneNum);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		buttonPanel.setBounds(638, 10, 166, 623);
		myInfo.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton ModifyAccountInfoButton = new JButton("수정");
		ModifyAccountInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoModifierFrame modify = new InfoModifierFrame();
				modify.main(null);
			}
		});
		ModifyAccountInfoButton.setBounds(12, 467, 142, 60);
		buttonPanel.add(ModifyAccountInfoButton);
		ModifyAccountInfoButton.setForeground(new Color(0, 0, 0));
		ModifyAccountInfoButton.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		
		JButton cancleModify = new JButton("닫기");
		cancleModify.setBounds(12, 537, 142, 60);
		buttonPanel.add(cancleModify);
		cancleModify.setFont(new Font("휴먼편지체", Font.BOLD, 30));
		cancleModify.addActionListener(x->myInfo.dispose());
		myInfo.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
	                myInfo.dispose();
		}});

	}
}
