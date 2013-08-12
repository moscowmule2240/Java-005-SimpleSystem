/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet;

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
 * Servlet implementation class LoginServlet
 * 
 * @author moscowmule2240
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PREVIOUS = "/jsp/login.jsp";
	private static final String PAGE_FOLLOWING_SINGLE = "/jsp/single/menu.jsp";
	private static final String PAGE_FOLLOWING_MULTI = "/multi/lump";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		User user = null;
		String message = null;
		String forward;
		Connection connection = null;

		try {
			connection = DBUtil.getConnection();
			UserDao dao = new UserDaoImpl(connection);
			List<User> users = dao.find(Integer.parseInt(userId), null, null, password);
			if (users.isEmpty()) {
				message = "IDまたはパスワードが間違っています。";
				forward = LoginServlet.PAGE_PREVIOUS;
			} else {
				user = users.get(0);
				if ("single".equals(type)) {
					forward = LoginServlet.PAGE_FOLLOWING_SINGLE;
				} else {
					forward = LoginServlet.PAGE_FOLLOWING_MULTI;
				}
			}
		} catch (NumberFormatException e) {
			message = "IDまたはパスワードが間違っています。";
			forward = LoginServlet.PAGE_PREVIOUS;
		} catch (Exception e) {
			e.printStackTrace();
			message = "データベース処理中にエラーが発生しました。";
			forward = LoginServlet.PAGE_PREVIOUS;
		} finally {
			DBUtil.close(connection);
		}

		if (user != null) {
			request.getSession().setAttribute("loginUser", user);
		}
		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " userId [" + userId + "]");
		System.out.println(this.getClass() + " password [" + password + "]");
		System.out.println(this.getClass() + " type [" + type + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		if (user == null) {
			System.out.println(this.getClass() + " user [" + user + "]");
		} else {
			System.out.println(this.getClass() + " user.getUserId [" + user.getUserId() + "]");
			System.out.println(this.getClass() + " user.getUserName [" + user.getUserName() + "]");
			System.out.println(this.getClass() + " user.getTel [" + user.getTel() + "]");
			System.out.println(this.getClass() + " user.getPass [" + user.getPass() + "]");
		}
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
