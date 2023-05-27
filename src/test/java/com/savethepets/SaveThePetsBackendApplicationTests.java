package com.savethepets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SaveThePetsBackendApplicationTests {

	@Test
	void contextLoads() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Oracle JDBC 드라이버 클래스를 로드합니다.
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Oracle DB에 연결합니다.
            String url = "jdbc:oracle:thin:@110.8.166.180:1521/xepdb1";  // 호스트, 포트, SID에 맞게 수정해야 합니다.
            String username = "SCOTT";  // Oracle DB 사용자 이름
            String password = "tiger";  // Oracle DB 비밀번호
            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM student";

            // PreparedStatement를 생성합니다.
            preparedStatement = connection.prepareStatement(sql);

            // 쿼리를 실행하고 결과를 가져옵니다.
            resultSet = preparedStatement.executeQuery();

            // 결과를 순회하며 값을 출력합니다.
            while (resultSet.next()) {
            	int studentId = resultSet.getInt("sid");
                String name = resultSet.getString("sname");

                System.out.println("Student ID: " + studentId);
                System.out.println("Name: " + name);
                System.out.println("---------------------------");
            }
                
            // 연결이 성공적으로 이루어진 경우 메시지를 출력합니다.
            System.out.println("Oracle DB에 연결되었습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Oracle DB 연결 중 오류가 발생하였습니다.");
            e.printStackTrace();
        } finally {
            // 연결을 닫습니다.
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}