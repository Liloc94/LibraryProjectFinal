package AccountDB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import AccountDB.AccountManager.MemberProcessor;

public class LoginLogic {
	private static final String FILE_PATH = "src/userData/member.txt";
	private static Map<String, String> LoginParams = new HashMap<>();
	private static Map<String, String> dbInfo = new HashMap();
	private static Map<String, String> administrator = new HashMap<>();
	private static Map<String, String> accountNumberInfo = new HashMap<>();
	private static String userKeyValue;
	
	static {
        // 관리자 계정 정보 초기화
        administrator.put("admin", "admin");
    }
	
	public static String getUserKeyValue() {
		return userKeyValue;
	}
	
	public static void setUserKeyValue(String userKeyValue) {
		LoginLogic.userKeyValue = userKeyValue;
	}
	
	public LoginLogic() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while((line = br.readLine()) != null) {
				String[] tempArray = line.split(",");
				// 회원정보 읽어온 값에서 id,pw 추출 후 Map 에 저장
				if (tempArray.length >= 3) {
                    accountNumberInfo.put(tempArray[1], tempArray[0]);
                    dbInfo.put(tempArray[1], tempArray[2]);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	// 회원등록정보에서 아이디, 비밀번호 추출 후 Map LoginParams 에 저장
	public void setLoginParams(String id, char[] password) {
		String pw = new String(password);
		LoginParams.put(id, pw);
	}
	
//	--------------------- setter , Field instance ------------------------------

	public boolean isCorrect() {		
		String enteredId = LoginParams.keySet().iterator().next();
	    String enteredPassword = LoginParams.get(enteredId);
	    try {
	        if (!enteredId.isEmpty() || !enteredPassword.isEmpty()) {
	            if (dbInfo.containsKey(enteredId)) {
	                String storedPassword = dbInfo.get(enteredId);
	                if (enteredPassword.equals(storedPassword)) {
	                    // 로그인 성공 시 회원번호 설정
	                	setUserKeyValue(accountNumberInfo.get(enteredId));
	                    return true;
	                }
	            }
	        } 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;           
	}
	
	// 관리자 계정인지 검증
	public boolean isAdmin() {
		String enteredId = LoginParams.keySet().iterator().next();
	    String enteredPassword = LoginParams.get(enteredId);
	    try {
	        if (!enteredId.isEmpty() && !enteredPassword.isEmpty()) {
	            if (enteredId.equals("admin") && enteredPassword.equals("admin")) {
	                    setUserKeyValue("000000");
	                    return true;
	                }
	            } 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;           
	}

	
}
	
