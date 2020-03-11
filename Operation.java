
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Operation {
	
	double result;
	
	public Operation(String content) {
		int i;
		for (i = content.length() - 1; i >= 0; i--) {
			if (content.charAt(i) <= '9' && content.charAt(i) >= '0') {
				break;
			}
		}
		content = content.substring(0, i + 1);
		if (content.length() == 0) {
			content = "0";
		}
		// TODO Auto-generated constructor stub
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine se = manager.getEngineByName("js");
		try {
			result = (int)se.eval(content);
		} catch (ClassCastException e) {
			// TODO: handle exception
			try {
				result = (double)se.eval(content);
			} catch (ScriptException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return (String.format("%.4f", (double)result));
	}
}
