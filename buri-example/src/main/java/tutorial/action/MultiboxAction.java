package tutorial.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import tutorial.form.MultiboxForm;

public class MultiboxAction {

	public List<Map<String, String>> check2List;

	@ActionForm
	@Resource
	protected MultiboxForm multiboxForm;

	@Execute(validator = false)
	public String index() {
		multiboxForm.initialize();
		return "index.html";
	}

	@Execute(validator = false, reset = "reset")
	public String submit() {
		setupCheck2List();
		return "submit.html";
	}

	@Execute(validator = false, reset = "reset2")
	public String submit2() {
		return "submit2.html";
	}

	protected void setupCheck2List() {
		check2List = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= 3; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("value", String.valueOf(i));
			m.put("label", "label" + i);
			check2List.add(m);
		}
	}
}