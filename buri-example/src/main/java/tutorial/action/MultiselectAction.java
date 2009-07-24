package tutorial.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tutorial.form.MultiselectForm;

public class MultiselectAction {

	public List<Map<String, String>> select2List;

	@ActionForm
	@Resource
	protected MultiselectForm multiselectForm;

	@Execute(validator = false)
	public String index() {
		multiselectForm.initialize();
		return "index.html";
	}

	@Execute(validator = false, reset = "reset")
	public String submit() {
		setupSelect2List();
		return "submit.html";
	}

	@Execute(validator = false, reset = "reset2")
	public String submit2() {
		return "submit2.html";
	}

	protected void setupSelect2List() {
		select2List = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= 3; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("value", String.valueOf(i));
			m.put("label", "label" + i);
			select2List.add(m);
		}
	}
}