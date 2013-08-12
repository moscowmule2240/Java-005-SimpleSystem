/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet.update;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moscowmule2240.java005.bean.User;
import moscowmule2240.java005.connection.DBUtil;
import moscowmule2240.java005.dao.UserDao;
import moscowmule2240.java005.dao.UserDaoImpl;

/**
 * Servlet implementation class UpdateConfirmServlet
 * 
 * @author moscowmule2240
 */
public class UpdateConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS_TOP = "/jsp/single/update/update.jsp";
	private static final String PAGE_PREVIOUS = "/jsp/single/update/updateConfirm.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/update/updateResult.jsp";

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
		String baseUserName = request.getParameter("baseUserName");
		String baseTel = request.getParameter("baseTel");
		String basePassword = request.getParameter("basePassword");
		String passwordPrevious = request.getParameter("passwordPrevious");
		String message = null;
		String forward;
		Connection connection = null;

		User user = new User();
		user.setUserId(new Integer(userId));
		user.setUserName(userName);
		user.setTel(tel);
		user.setPass(passwordPrevious);

		User baseUser = new User();
		baseUser.setUserId(new Integer(userId));
		baseUser.setUserName(baseUserName);
		baseUser.setTel(baseTel);
		baseUser.setPass(basePassword);

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = UpdateConfirmServlet.PAGE_TOP;
		} else if (passwordPrevious.equals(password)) {
			user.setPass(password);

			try {
				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);
				int count = dao.update(user);
				connection.commit();
				if (count == 0) {
					message = "入力されたIDは既に削除されています。";
					forward = UpdateConfirmServlet.PAGE_PREVIOUS_TOP;
				} else {
					forward = UpdateConfirmServlet.PAGE_FOLLOWING;
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
				forward = UpdateConfirmServlet.PAGE_PREVIOUS;
			} finally {
				DBUtil.close(connection);
			}

			forward = UpdateConfirmServlet.PAGE_FOLLOWING;
		} else {
			message = "パスワードが一致しません。";
			forward = UpdateConfirmServlet.PAGE_PREVIOUS;
		}

		request.setAttribute("user", user);
		request.setAttribute("baseUser", baseUser);

		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " passwordPrevious [" + passwordPrevious + "]");
		System.out.println(this.getClass() + " userId [" + userId + "]");
		System.out.println(this.getClass() + " userName [" + userName + "]");
		System.out.println(this.getClass() + " tel [" + tel + "]");
		System.out.println(this.getClass() + " password [" + password + "]");
		System.out.println(this.getClass() + " baseUserName [" + baseUserName + "]");
		System.out.println(this.getClass() + " baseTel [" + baseTel + "]");
		System.out.println(this.getClass() + " basePassword [" + basePassword + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
