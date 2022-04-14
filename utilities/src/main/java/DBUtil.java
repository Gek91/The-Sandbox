/**
 * Utilities for Database management
 * @author gek
 *
 */
public class DBUtil {

	public static String escapeSpecialCharacters(String s, boolean search) {
	    if (s == null) {
	        return s;
	    }
	    String[][] chars;
	    if(!search) {
	        chars = new String[][ ]{
	                {"\\",  "\\\\"},
	                {"\0", "\\0"},
	                {"'", "\\'"}, 
	                {"\"",  "\\\""},
	                {"\b",  "\\b"},
	                {"\n",  "\\n"},
	                {"\r",  "\\r"},
	                {"\t",  "\\t"},
	                {"\\Z", "\\\\Z"}, 
	                {"%", "\\%"},     
	                {"_", "\\_"}
	        };
	    } else {
	        chars = new String[][ ]{
	                {"\\",  "\\\\"},
	                {"\0", "\\0"},
	                {"'", "\\'"}, 
	                {"\"",  "\\\""},
	                {"\b",  "\\b"},
	                {"\n",  "\\n"},
	                {"\r",  "\\r"},
	                {"\t",  "\\t"},
	                {"\\Z", "\\\\Z"}, 
	        };
	    }
	    for (String[] c : chars) {
	        s = s.replace(c[0], c[1]);
	    }
	    return s;
	}
}
