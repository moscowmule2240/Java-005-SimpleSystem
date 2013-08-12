/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet.insert;

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
 * Servlet implementation class InsertConfirmServlet
 * 
 * @author moscowmule2240
 */
public class InsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS_TOP = "/jsp/single/insert/insert.jsp";
	private static final String PAGE_PREVIOUS = "/jsp/single/insert/insertConfirm.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/insert/insertResult.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String passwordPrevious = request.getParameter("passwordPrevious");
		String password = request.getParameter("password");
		String message = null;
		User user = null;
		String forward;
		Connection connection = null;

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = InsertConfirmServlet.PAGE_TOP;
		} else if (!passwordPrevious.equals(password)) {
			message = "パスワードが一致しません。";
			forward = InsertConfirmServlet.PAGE_PREVIOUS;
		} else {
			try {
				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);

				// 存在確認
				List<User> users = dao.find(null, name, tel, null);

				if (users.isEmpty()) {
					// 登録
					user = new User();
					user.setUserName(name);
					user.setTel(tel);
					user.setPass(password);
					dao.insert(user);
					connection.commit();

					// 登録データ取得
					user = dao.find(null, name, tel, null).get(0);
					forward = InsertConfirmServlet.PAGE_FOLLOWING;
				} else {
					message = "入力された名前、TELの方は登録済みです。";
					forward = InsertConfirmServlet.PAGE_PREVIOUS_TOP;
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
				forward = InsertConfirmServlet.PAGE_PREVIOUS;
			} finally {
				DBUtil.close(connection);
			}
		}

		if (message != null) {
			request.setAttribute("message", message);
		}
		if (user != null) {
			request.setAttribute("insertUser", user);
		}

		System.out.println(this.getClass() + " name [" + name + "]");
		System.out.println(this.getClass() + " tel [" + tel + "]");
		System.out.println(this.getClass() + " passwordPrevious [" + passwordPrevious + "]");
		System.out.println(this.getClass() + " password [" + password + "]");
		if (user != null) {
			System.out.println(this.getClass() + " insertUserid [" + user.getUserId() + "]");
		}
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
