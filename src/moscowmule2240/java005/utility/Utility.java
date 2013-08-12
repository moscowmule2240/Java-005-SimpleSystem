/**
 * 簡易システム
 */
package moscowmule2240.java005.utility;

/**
 * ユーティリティクラス。
 * 
 * @author moscowmule2240
 */
public class Utility {

	/**
	 * 文字列配列を区切り文字で連結します。
	 * 
	 * @param array
	 *            文字列配列
	 * @param with
	 *            区切り文字
	 * @return 文字列配列を区切り文字で連結した文字列
	 */
	public static String join(String[] array, String with) {
		StringBuilder builder = new StringBuilder();
		for (String string : array) {
			if (builder.length() > 0) {
				builder.append(with);
			}
			builder.append(string);
		}
		return builder.toString();
	}
}
