/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet.select;

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
 * Servlet implementation class SelectServlet
 * 
 * @author moscowmule2240
 */
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS = "/jsp/single/select/select.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/select/selectResult.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String message = null;
		List<User> users = null;
		String forward;
		Connection connection = null;

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = SelectServlet.PAGE_TOP;
		} else {
			try {
				Integer userIdInteger;
				if ((userId == null) || userId.isEmpty()) {
					userIdInteger = null;
				} else {
					userIdInteger = new Integer(userId);
				}

				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);
				users = dao.findLike(userIdInteger, name, tel, null);

				if (users.isEmpty()) {
					message = "入力されたデータはありませんでした。";
					forward = SelectServlet.PAGE_PREVIOUS;
				} else {
					forward = SelectServlet.PAGE_FOLLOWING;
				}

			} catch (NumberFormatException e) {
				message = "入力されたデータはありませんでした。";
				forward = SelectServlet.PAGE_PREVIOUS;
			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
				forward = SelectServlet.PAGE_PREVIOUS;
			} finally {
				DBUtil.close(connection);
			}
		}

		if ((users != null) && !users.isEmpty()) {
			request.setAttribute("users", users);
		}
		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " userId [" + userId + "]");
		System.out.println(this.getClass() + " name [" + name + "]");
		System.out.println(this.getClass() + " tel [" + tel + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		if (users == null) {
			System.out.println(this.getClass() + " users [" + users + "]");
		} else {
			System.out.println(this.getClass() + " users.size [" + users.size() + "]");
		}
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
