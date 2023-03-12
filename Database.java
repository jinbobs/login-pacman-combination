import java.sql.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	
	 
	
	Database() {	//데이터베이스에 연결한다.
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver"); //클래스의 정보를 얻기위한 클래스
	            con = DriverManager.getConnection(
	            		"jdbc:mysql://localhost:3306/login", "root", "mite"); //데이터베이스와 연결하는 객체.
	            System.out.println("데이터베이스에 접속했습니다.");
	            
	        }
	        catch (ClassNotFoundException cnfe) {
	            System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
	        }
	        catch (SQLException se) {
	            System.out.println("SQL에러입니다. " + se.getMessage());
	        }
	    }

	

	// 로그인 정보를 확인 
	boolean logincheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
		
		try {
			stmt = con.createStatement(); //SQL문을 데이터베이스에 보내기위한 객체이다.
			String checkingStr = "SELECT password FROM register WHERE id ='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr); //SQL문장(SELECT)을 실행하고 그결과를 resultset으로 맅턴한다.
			
			
			int count = 0;
			while(result.next()) { //next를 이용하여 커서를 이동시킨다. 패스워드의 일치여부를 알기 위한 코딩.
				if(pw.equals(result.getString("password"))) {
					flag = true;
					System.out.println("로그인 성공");
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}
	
	boolean joinCheck(String _i, String _p) { //회원가입 체크를 위한 코딩.
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
			
		try {
			String insertStr = "INSERT INTO register VALUES('" + id + "', '" + pw + "')";
			stmt.executeUpdate(insertStr); // INSERT,UPATE,DELETE SQL문을 쓰기위해 excuteUpdate를 사용
				
			flag = true;
			System.out.println("회원가입 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("회원가입 실패 > " + e.toString());
					}
			
		return flag;
	}
	
}