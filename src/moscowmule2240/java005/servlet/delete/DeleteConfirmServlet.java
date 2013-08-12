/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet.delete;

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
 * Servlet implementation class DeleteConfirmServlet
 * 
 * @author moscowmule2240
 */
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS_TOP = "/jsp/single/delete/delete.jsp";
	private static final String PAGE_PREVIOUS = "/jsp/single/delete/deleteConfirm.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/delete/deleteResult.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String message = null;
		String forward;
		Connection connection = null;

		User user = new User();
		user.setUserId(Integer.parseInt(userId));
		user.setUserName(userName);
		user.setTel(tel);

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = DeleteConfirmServlet.PAGE_TOP;
		} else {
			try {
				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);
				int count = dao.delete(Integer.parseInt(userId));
				connection.commit();
				if (count == 0) {
					message = "入力されたIDは既に削除されています。";
					forward = DeleteConfirmServlet.PAGE_PREVIOUS_TOP;
				} else {
					forward = DeleteConfirmServlet.PAGE_FOLLOWING;
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
				forward = DeleteConfirmServlet.PAGE_PREVIOUS;
			} finally {
				DBUtil.close(connection);
			}
		}

		request.setAttribute("user", user);
		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " userId [" + userId + "]");
		System.out.println(this.getClass() + " user [" + user + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
