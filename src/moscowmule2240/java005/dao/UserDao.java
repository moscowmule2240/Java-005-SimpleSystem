/**
 * 簡易システム
 */
package moscowmule2240.java005.dao;

import java.sql.SQLException;
import java.util.List;

import moscowmule2240.java005.bean.User;

/**
 * UserストレージへアクセスするためのDAOインターフェースです。
 * 
 * @author moscowmule2240
 */
public interface UserDao {
	/** 全検索を行うためのSQLを示す定数です。 */
	final String SEARCH_ALL_SQL = "select * from users";
	/** 登録を行うためのSQLを示す定数です。 */
	final String INSERT_SQL = "insert into users (username,tel,pass)values(?,?,?)";
	/** 更新を行うためのSQLを示す定数です。 */
	final String UPDATE_SQL = "update users set username = ? ,tel = ? , pass = ? where userid = ?";
	/** 削除を行うためのSQLを示す定数です。 */
	final String DELETE_SQL = "delete from users where userid = ?";

	/**
	 * 指定された条件を満たすUserを返します。
	 * 
	 * @param id
	 *            Userのid
	 * @param name
	 *            Userのname
	 * @param tel
	 *            Userのtel
	 * @param pass
	 *            Userのpass
	 * @return 指定された条件を満たすUser
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	List<User> find(Integer id, String name, String tel, String pass) throws SQLException;

	/**
	 * 指定された条件を含むUserを返します。
	 * 
	 * @param id
	 *            Userのid
	 * @param name
	 *            Userのname
	 * @param tel
	 *            Userのtel
	 * @param pass
	 *            Userのpass
	 * @return 指定された条件を満たすUser
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	List<User> findLike(Integer id, String name, String tel, String pass) throws SQLException;

	/**
	 * Userを全件取得します。 1件もデータが存在しない場合はサイズ０のListを返します。
	 * 
	 * @return 全件データ
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	List<User> find() throws SQLException;

	/**
	 * 指定されたidを持つUserを返します。 一致するUserが存在しない場合、デフォルト値がセットされたUserオブジェクトを返します。
	 * 
	 * @param id
	 *            Userのid
	 * @return 一致したUser
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	User find(int id) throws SQLException;

	/**
	 * 指定されたUserを登録します。 また登録成功時に自動で設定されたidをセットします。
	 * 
	 * @param user
	 *            登録するUser
	 * @return 登録した件数
	 * @throws NullPointerException
	 *             userがnullの場合
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	int insert(User user) throws NullPointerException, SQLException;

	/**
	 * 指定されたUserをidに基づいて更新します。
	 * 
	 * @param user
	 *            更新するUser
	 * @return 更新した件数
	 * @throws NullPointerException
	 *             userがnullの場合
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	int update(User user) throws NullPointerException, SQLException;

	/**
	 * 指定されたidを持つUserを削除します。
	 * 
	 * @param id
	 *            削除するid
	 * @return 削除した件数
	 * @throws SQLException
	 *             データベース処理中に例外が発生した場合
	 */
	int delete(int id) throws SQLException;
}
