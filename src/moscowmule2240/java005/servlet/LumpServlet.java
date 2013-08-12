/**
 * 簡易システム
 */
package moscowmule2240.java005.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
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
 * Servlet implementation class LumpServlet
 *
 * @author moscowmule2240
 */
public class LumpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_TOP = "/";
	private static final String PAGE_FOLLOWING = "/jsp/multi/lump.jsp";

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<String> userIds = LumpServlet.asList(request.getParameterValues("userIds"));
		List<String> userNames = LumpServlet.asList(request.getParameterValues("userNames"));
		List<String> tels = LumpServlet.asList(request.getParameterValues("tels"));
		List<String> passes = LumpServlet.asList(request.getParameterValues("passes"));
		List<String> deletes = LumpServlet.asList(request.getParameterValues("deletes"));
		String newUserName = request.getParameter("newUserName");
		String newTel = request.getParameter("newTel");
		String newPass = request.getParameter("newPass");

		String message = null;
		String forward = LumpServlet.PAGE_FOLLOWING;
		Connection connection = null;
		List<User> users = null;
		int insertCount = 0;
		int updateCount = 0;
		int deleteCount = 0;

		if (request.getSession().getAttribute("loginUser") == null) {
			message = "サーバーから切断されました。もう一度ログインしてください。";
			forward = LumpServlet.PAGE_TOP;
		} else {
			try {
				connection = DBUtil.getConnection();
				UserDao dao = new UserDaoImpl(connection);

				// 登録
				if (((newUserName != null) && !newUserName.isEmpty()) ||
						((newTel != null) && !newTel.isEmpty()) ||
						((newPass != null) && !newPass.isEmpty())) {
					User insertUser = new User();
					insertUser.setUserName(newUserName);
					insertUser.setTel(newTel);
					insertUser.setPass(newPass);
					insertCount += dao.insert(insertUser);
				}

				if (userIds != null) {
					List<User> searchUsers = dao.find();
					for (User item : searchUsers) {
						// 削除
						if (deletes.contains(String.valueOf(item.getUserId()))) {
							deleteCount += dao.delete(item.getUserId());
							continue;
						}

						// 更新
						for (int i = 0; i < userIds.size(); i++) {
							if (item.getUserId() == Integer.parseInt(userIds.get(i))) {
								if (!item.getUserName().equals(userNames.get(i)) ||
										!item.getTel().equals(tels.get(i)) ||
										!item.getPass().equals(passes.get(i))) {
									User updateUser = new User();
									updateUser.setUserId(Integer.parseInt(userIds.get(i)));
									updateUser.setUserName(userNames.get(i));
									updateUser.setTel(tels.get(i));
									updateUser.setPass(passes.get(i));
									updateCount += dao.update(updateUser);
								}
								break;
							}
						}
					}
				}

				connection.commit();

				// 表示データ取得
				users = dao.find();

			} catch (Exception e) {
				e.printStackTrace();
				message = "データベース処理中にエラーが発生しました。";
			} finally {
				DBUtil.close(connection);
			}
		}

		if (message == null) {
			request.setAttribute("users", users);
			request.setAttribute("insertCount", insertCount);
			request.setAttribute("updateCount", updateCount);
			request.setAttribute("deleteCount", deleteCount);
		} else {
			request.setAttribute("message", message);
		}

		System.out.println(this.getClass() + " users [" + users + "]");
		System.out.println(this.getClass() + " message [" + message + "]");
		System.out.println(this.getClass() + " insertCount [" + insertCount + "]");
		System.out.println(this.getClass() + " updateCount [" + updateCount + "]");
		System.out.println(this.getClass() + " deleteCount [" + deleteCount + "]");
		System.out.println(this.getClass() + " forward [" + forward + "]");

		request.getRequestDispatcher(forward).forward(request, response);
	}

	/**
	 * 配列をリストに変換します。配列がNullの場合、空のリストを返します。
	 *
	 * @param arrays
	 *            配列
	 * @return リスト
	 */
	private static List<String> asList(String[] arrays) {
		List<String> data;
		if (arrays == null) {
			data = new ArrayList<>();
		} else {
			data = Arrays.asList(arrays);
		}
		return data;
	}
}
