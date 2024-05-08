package AccountDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class DeleteUserInfo {
	protected final String FILE_PATH = "src/userData/member.txt";
	
	
//							TODO: 로그인한 계정의 회원번호 참조	
	public void deleteUser(String targetAccountNumber) {
		try (RandomAccessFile raf = new RandomAccessFile(FILE_PATH, "rw")) {
		    List<String> lines = new ArrayList<>();
		    String line;
		    while ((line = raf.readLine()) != null) {
		        String[] tempArray = line.split(",");
		        if (!tempArray[0].equals(targetAccountNumber)) {
		            lines.add(line);
		        } else {
		            System.out.println("회원정보 삭제 완료");
		        }
		        System.out.println("회원정보 삭제 실패");
		    }

		    // 파일 초기화
		    raf.setLength(0);

		    // 수정된 내용을 파일에 다시 쓰기
		    for (String l : lines) {
		        raf.writeBytes(l);
		        raf.writeBytes("\n");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	
}
