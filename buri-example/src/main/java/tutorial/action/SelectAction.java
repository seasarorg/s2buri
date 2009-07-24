package tutorial.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tutorial.form.SelectForm;

public class SelectAction {

	@ActionForm
	@Resource
	protected SelectForm selectForm;
	
	@Execute(validator = false)
	public String index() {

		return "index.html";
	}

	@Execute(validator = false)
	public String submit() {
		return "index.html";
	}
}