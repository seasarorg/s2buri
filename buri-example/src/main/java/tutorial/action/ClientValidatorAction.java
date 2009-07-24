package tutorial.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tutorial.form.ClientValidatorForm;

public class ClientValidatorAction {

	@ActionForm
	@Resource
	protected ClientValidatorForm clientValidatorForm;

	@Execute(validator = false)
	public String index() {
		return "index.html";
	}

	@Execute(input = "index.html")
	public String submit() {
		return "index.html";
	}

	@Execute(input = "index.html")
	public String submit2() {
		return "index.html";
	}
}