package tutorial.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tutorial.form.SessionForm;

public class SessionAction {

	@ActionForm
	@Resource
	protected SessionForm sessionForm;

	@Execute(validator = false)
	public String index() {
		return "index.html";
	}

	@Execute(input = "index.html")
	public String goSecond() {
		return "second.html";
	}

	@Execute(validator = false)
	public String backSecond() {
		return "second.html";
	}

	@Execute(input = "second.html")
	public String goThird() {
		return "third.html";
	}

	// TODO SAStruts - Version 1.0.4-rc1で修正されているバグのため、クリアをするとエラーになります。
	@Execute(validator = false, removeActionForm = true)
	public String clear() {
		return "index.html";
	}
}