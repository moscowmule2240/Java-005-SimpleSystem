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
 * Servlet implementation class UpdateServlet
 * 
 * @author moscowmule2240
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS = "/jsp/single/update/update.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/update/updateInput.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String message = null;
		Connection connection = null;
		User user = null;
		String forward;

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = UpdateServlet.PAGE_TOP;
		} else if ((userId == null) || userId.isEmpty()) {
			message = "IDは必須です。";
			forward = UpdateServlet.PAGE_PREVIOUS;
		} else {
			try {
				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);
				user = dao.find(Integer.parseInt(userId));
				if (user == null) {
					message = "入力されたIDは登録されていません。";
					forward = UpdateServlet.PAGE_PREVIOUS;
				} else {
					forward = UpdateServlet.PAGE_FOLLOWING;
				}
			} catch (NumberFormatException e) {
				message = "IDには数値を入力してください。";
				forward = UpdateServlet.PAGE_PREVIOUS;
			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
				forward = UpdateServlet.PAGE_PREVIOUS;
			} finally {
				DBUtil.close(connection);
			}
		}

		if (user != null) {
			request.setAttribute("user", user);
		}
		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " userId [" + userId + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " user [" + user + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
