/**
 * 簡易システム
 */
package moscowmule2240.java005.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * データベースコネクションを簡易的に扱うユーティリティーメソッドを提供します。
 * 
 * @author moscowmule2240
 */
public class DBUtil {
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/java005db";
	private static final String ID = "java005user";
	private static final String PASS = "java005java";

	/**
	 * インスタンス化の禁止。
	 */
	private DBUtil() {
		// インスタンス化の禁止
	}

	/**
	 * データベースへのコネクションを取得します。
	 * 
	 * @return DBへのコネクション
	 * @throws ClassNotFoundException
	 *             JDBCドライバが見つからなかった場合
	 * @throws SQLException
	 *             コネクションが取得できなかった場合
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DBUtil.DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.ID, DBUtil.PASS);
		connection.setAutoCommit(false);
		return connection;
	}

	/**
	 * 開いているデータベースコネクションを閉じます。
	 * 
	 * @param connection
	 *            コネクション
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// close失敗時続行
				e.printStackTrace();
			}
		}
	}

	/**
	 * 開いているステートメントを閉じます。
	 * 
	 * @param statement
	 *            ステートメント
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// close失敗時続行
				e.printStackTrace();
			}
		}
	}
}
