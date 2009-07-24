package tutorial.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.TokenProcessor;
import org.seasar.struts.annotation.Execute;

public class TokenAction {

	@Resource
	protected HttpServletRequest request;

	@Execute(validator = false)
	public String index() {
		TokenProcessor.getInstance().saveToken(request);
		return "index.html";
	}

	@Execute(validator = false, validate = "validate", input = "index.html")
	public String result() {
		return "result.html";
	}

	public ActionMessages validate() {
		ActionMessages errors = new ActionMessages();
		if (!TokenProcessor.getInstance().isTokenValid(request, true)) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"errors.invalid",
				"Token"));
		}
		return errors;
	}
}