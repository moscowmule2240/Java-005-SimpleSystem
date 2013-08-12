/**
 * 簡易システム
 */
package moscowmule2240.java005.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moscowmule2240.java005.bean.User;
import moscowmule2240.java005.connection.DBUtil;
import moscowmule2240.java005.utility.Utility;

/**
 * UserストレージへアクセスするためのDAOインターフェースです。
 * 
 * @author moscowmule2240
 */
public class UserDaoImpl implements UserDao {

	/**
	 * コネクション。
	 */
	private Connection connection;

	/**
	 * コンストラクター。
	 * 
	 * @param connection
	 *            コネクション
	 */
	public UserDaoImpl(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("コネクションがnullです。");
		}
		this.connection = connection;
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#find()
	 */
	@Override
	public List<User> find() throws SQLException {
		return this.find(null, null, null, null);
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#find(int)
	 */
	@Override
	public User find(int id) throws SQLException {
		List<User> users = this.find(id, null, null, null);
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#find(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> find(Integer id, String name, String tel, String pass) throws SQLException {
		return this.execute(id, name, tel, pass, false);
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#findLike(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<User> findLike(Integer id, String name, String tel, String pass) throws SQLException {
		return this.execute(id, name, tel, pass, true);
	}

	/**
	 * 指定された検索条件で検索を行います。
	 * 検索対象の文字列が存在する場合絞込みを行います。
	 * 絞込みは条件文字列を使用します。
	 * ユーザーIDの昇順で検索結果を返します。
	 * 
	 * @param userId
	 *            検索対象userId
	 * @param userName
	 *            検索対象usreName
	 * @param tel
	 *            検索対象tel
	 * @param pass
	 *            検索対象pass
	 * @param isLike
	 *            あいまい検索を行うかどうか
	 * @return 検索結果
	 * @throws SQLException
	 *             データベース処理時に例外が発生した場合
	 */
	public List<User> execute(Integer userId, String userName, String tel, String pass, Boolean isLike) throws SQLException {

		PreparedStatement statement = null;
		List<User> list = new ArrayList<>();

		try {
			// 引数に入力がある場合、条件に追加
			List<String> whereList = new ArrayList<>();
			if (isLike) {
				if (userId != null) {
					whereList.add("userid like ?");
				}
				if ((userName != null) && !userName.isEmpty()) {
					whereList.add("userName like ?");
				}
				if ((tel != null) && !tel.isEmpty()) {
					whereList.add("tel like ?");
				}
			} else {
				if (userId != null) {
					whereList.add("userid = ?");
				}
				if ((userName != null) && !userName.isEmpty()) {
					whereList.add("userName = ?");
				}
				if ((tel != null) && !tel.isEmpty()) {
					whereList.add("tel = ?");
				}
			}
			if ((pass != null)) {
				whereList.add("pass = ?");
			}

			// 条件を結合
			StringBuilder builder = new StringBuilder();
			builder.append(UserDao.SEARCH_ALL_SQL);
			if (!whereList.isEmpty()) {
				builder.append(" where ");
				builder.append(Utility.join(whereList.toArray(new String[0]), " and "));
			}
			builder.append(" order by userid asc");
			String sql = builder.toString();
			System.out.println(this.getClass() + " sql [" + sql + "]");

			// 変数をバインド
			statement = this.connection.prepareStatement(sql);
			int index = 0;
			if (isLike) {
				if (userId != null) {
					statement.setString(++index, "%" + userId + "%");
				}
				if ((userName != null) && !userName.isEmpty()) {
					statement.setString(++index, "%" + userName + "%");
				}
				if ((tel != null) && !tel.isEmpty()) {
					statement.setString(++index, "%" + tel + "%");
				}
			} else {
				if (userId != null) {
					statement.setInt(++index, userId);
				}
				if ((userName != null) && !userName.isEmpty()) {
					statement.setString(++index, userName);
				}
				if ((tel != null) && !tel.isEmpty()) {
					statement.setString(++index, tel);
				}
			}
			if ((pass != null)) {
				statement.setString(++index, pass);
			}

			// 結果取得
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setUserName(resultSet.getString("username"));
				user.setTel(resultSet.getString("tel"));
				user.setPass(resultSet.getString("pass"));
				list.add(user);
			}
		} finally {
			DBUtil.close(statement);
		}

		return list;
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#insert(moscowmule2240.java005.bean.User)
	 */
	@Override
	public int insert(User user) throws NullPointerException, SQLException {
		PreparedStatement statement = null;

		try {
			String sql = UserDao.INSERT_SQL;
			System.out.println(this.getClass() + " sql [" + sql + "]");

			statement = this.connection.prepareStatement(sql);
			int index = 0;
			statement.setString(++index, user.getUserName());
			statement.setString(++index, user.getTel());
			statement.setString(++index, user.getPass());

			return statement.executeUpdate();
		} finally {
			DBUtil.close(statement);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#update(moscowmule2240.java005.bean.User)
	 */
	@Override
	public int update(User user) throws NullPointerException, SQLException {
		PreparedStatement statement = null;

		try {
			String sql = UserDao.UPDATE_SQL;
			System.out.println(this.getClass() + " sql [" + sql + "]");

			statement = this.connection.prepareStatement(sql);
			int index = 0;
			statement.setString(++index, user.getUserName());
			statement.setString(++index, user.getTel());
			statement.setString(++index, user.getPass());
			statement.setInt(++index, user.getUserId());

			return statement.executeUpdate();
		} finally {
			DBUtil.close(statement);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see moscowmule2240.java005.dao.UserDao#delete(int)
	 */
	@Override
	public int delete(int userId) throws SQLException {
		PreparedStatement statement = null;

		try {
			String sql = UserDao.DELETE_SQL;
			System.out.println(this.getClass() + " sql [" + sql + "]");

			statement = this.connection.prepareStatement(sql);
			int index = 0;
			statement.setInt(++index, userId);

			return statement.executeUpdate();
		} finally {
			DBUtil.close(statement);
		}
	}
}
