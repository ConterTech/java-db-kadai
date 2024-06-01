package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Go_takami88"
					);
			
			System.out.println("データベース接続成功：" + con);
				
			String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
						+"(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),"
						+"(1002, '2023-02-08', 'お疲れ様です！', 12),"
						+"(1003, '2023-02-09', '今日も頑張ります！', 18),"
						+"(1001, '2023-02-09', '無理は禁物ですよ！', 17),"
						+"(1002, '2023-02-10', '明日から連休ですね！', 20)";
			
			statement = con.createStatement();	
			System.out.println("レコード追加を実行します");
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが追加されました");
			
			
			String searchSql = "SELECT * FROM posts WHERE user_id = 1002;";
			ResultSet result = statement.executeQuery(searchSql);
			System.out.println("ユーザIDが1002のレコードを検索しました");
			
			int cnt = 1;
			while(result.next()) {
				Date postedAt = result.getDate("posted_at");
				String postContent = result.getString("post_content");
				int like = result.getInt("likes");
				System.out.println(cnt + "件目：投稿日時=" + postedAt + "/投稿内容=" + postContent + "/いいね数=" + like);
				cnt++;
			}		
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if(statement != null) {
				try {statement.close();} catch(SQLException ignore) {}
			}
			if(con != null) {
				try {con.close();} catch(SQLException ignore) {}
			}
		}

	}

}
