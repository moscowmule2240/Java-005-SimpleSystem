/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet.update;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moscowmule2240.java005.bean.User;
import moscowmule2240.java005.connection.DBUtil;
import moscowmule2240.java005.dao.UserDao;
import moscowmule2240.java005.dao.UserDaoImpl;

/**
 * Servlet implementation class UpdateInputServlet
 * 
 * @author moscowmule2240
 */
public class UpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS_TOP = "/jsp/single/update/update.jsp";
	private static final String PAGE_PREVIOUS = "/jsp/single/update/updateInput.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/update/updateConfirm.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		String message = null;
		Connection connection = null;
		User baseUser = null;
		String forward;

		User user = new User();
		user.setUserId(new Integer(userId));
		user.setUserName(userName);
		user.setTel(tel);
		user.setPass(password);

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = UpdateInputServlet.PAGE_TOP;
		} else {
			try {
				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);
				baseUser = dao.find(Integer.parseInt(userId));
				if (baseUser == null) {
					message = "入力されたIDは既に削除されています。";
					forward = UpdateInputServlet.PAGE_PREVIOUS_TOP;
				} else {
					boolean isChange = false;
					if (!userName.equals(baseUser.getUserName())) {
						isChange = true;
					} else if (!tel.equals(baseUser.getTel())) {
						isChange = true;
					} else if (!password.equals(baseUser.getPass())) {
						isChange = true;
					}
					boolean isTellAllNumber = true;
					try {
						for (int i = 0; i < tel.length(); i++) {
							Integer.parseInt(String.valueOf(tel.charAt(i)));
						}
					} catch (NumberFormatException e) {
						isTellAllNumber = false;
					}
					List<User> updateUsers = dao.find(null, userName, tel, null);
					if (!isChange) {
						message = "名前・TEL・パスワードいずれかの値を変更してください。";
						forward = UpdateInputServlet.PAGE_PREVIOUS;
					} else if (!isTellAllNumber) {
						message = "TELには数値を入力してください。";
						forward = UpdateInputServlet.PAGE_PREVIOUS;
					} else if (!updateUsers.isEmpty() && (Integer.parseInt(userId) != updateUsers.get(0).getUserId())) {
						message = "入力された名前、TELの方は既に登録されています。";
						forward = UpdateInputServlet.PAGE_PREVIOUS;
					} else {
						forward = UpdateInputServlet.PAGE_FOLLOWING;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
				forward = UpdateInputServlet.PAGE_PREVIOUS;
			} finally {
				DBUtil.close(connection);
			}
		}

		request.setAttribute("user", user);
		if (baseUser != null) {
			request.setAttribute("baseUser", baseUser);
		}
		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " userId [" + userId + "]");
		System.out.println(this.getClass() + " userName [" + userName + "]");
		System.out.println(this.getClass() + " tel [" + tel + "]");
		System.out.println(this.getClass() + " password [" + password + "]");
		if (baseUser != null) {
			System.out.println(this.getClass() + " baseUser.userName [" + baseUser.getUserName() + "]");
			System.out.println(this.getClass() + " baseUser.tel [" + baseUser.getTel() + "]");
			System.out.println(this.getClass() + " baseUser.password [" + baseUser.getPass() + "]");
		}
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
