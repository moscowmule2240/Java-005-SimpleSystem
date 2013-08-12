/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServlet
 * 
 * @author moscowmule2240
 */
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_PREVIOUS = "/jsp/single/insert/insert.jsp";
	private static final String PAGE_FOLLOWING = "/jsp/single/insert/insertConfirm.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		String message = null;
		String forward;

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = InsertServlet.PAGE_TOP;
		} else if ((password == null) || password.isEmpty()) {
			message = "パスワードが入力されていません。";
			forward = InsertServlet.PAGE_PREVIOUS;
		} else {
			try {
				for (int i = 0; i < tel.length(); i++) {
					Integer.parseInt(String.valueOf(tel.charAt(i)));
				}
				forward = InsertServlet.PAGE_FOLLOWING;
			} catch (NumberFormatException e) {
				message = "TELには数値を入力してください。";
				forward = InsertServlet.PAGE_PREVIOUS;
			}
		}

		if (message != null) {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " name [" + name + "]");
		System.out.println(this.getClass() + " tel [" + tel + "]");
		System.out.println(this.getClass() + " password [" + password + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}
}
