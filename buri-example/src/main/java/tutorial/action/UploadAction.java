package tutorial.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.UploadUtil;

import tutorial.form.UploadForm;

public class UploadAction {

	@ActionForm
	@Resource
	protected UploadForm uploadForm;

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected ServletContext application;

	@Execute(validator = false)
	public String index() {
		UploadUtil.checkSizeLimit(request);
		return "index.html";
	}

	@Execute(input = "index.html")
	public String upload() {
		upload(uploadForm.formFile);
		for (FormFile file : uploadForm.formFiles) {
			upload(file);
		}
		return "index.html";
	}

	protected void upload(FormFile file) {
		String path = application.getRealPath("/WEB-INF/work/"
			+ file.getFileName());
		UploadUtil.write(path, file);
	}
}