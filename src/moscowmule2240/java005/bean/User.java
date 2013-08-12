/**
 * 簡易システム
 */
package moscowmule2240.java005.bean;

/**
 * ユーザー情報。
 * 
 * @author moscowmule2240
 */
public class User {

	/**
	 * userId。
	 */
	private int userId;

	/**
	 * userName。
	 */
	private String userName;

	/**
	 * tel。
	 */
	private String tel;

	/**
	 * pass。
	 */
	private String pass;

	/**
	 * userIdを返します。
	 * 
	 * @return userId
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * userIdを設定します。
	 * 
	 * @param userId
	 *            userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * userNameを返します。
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * userNameを設定します。
	 * 
	 * @param userName
	 *            userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * telを返します。
	 * 
	 * @return tel
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * telを設定します。
	 * 
	 * @param tel
	 *            tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * passを返します。
	 * 
	 * @return pass
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * passを設定します。
	 * 
	 * @param pass
	 *            pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
}
