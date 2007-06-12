package example.org.seasar.buri.web.error;

import javax.faces.context.FacesContext;

public class ErrorResultPage {

	private static final String SERVLET_NAME = "javax.faces.webapp.FacesServlet";

	private FacesContext context;

	private String stackTrace;

	private Exception exception;

	public String prerender() {
		stackTrace = buildStackTrace(exception);
		return null;
	}

	protected String buildStackTrace(Throwable t) {
		if (t == null) {
			return "";
		}
		final StringBuffer buf = new StringBuffer();
		buf.append("<div style=\"font-size: x-small;"
				+ " font-family: monospace;\">");

		Throwable current = t;
		do {
			buf.append("<span style=\"color: red;\">");
			buf.append(current.toString());
			buf.append("</span><br/>");
			final StackTraceElement[] traces = current.getStackTrace();
			for (int i = 0; i < traces.length; i++) {
				final StackTraceElement elm = traces[i];
				if (elm.getClassName().startsWith(SERVLET_NAME)) {
					// Servlet より前は不要
					break;
				}
				buf.append("&nbsp;&nbsp;at ");
				buf.append(elm.getClassName());
				buf.append("#");
				buf.append(elm.getMethodName());
				buf.append(" (");
				buf.append(elm.getFileName());
				buf.append(lineString(elm.getLineNumber()));
				buf.append(")<br/>");
			}
			buf.append("<br/>");
		} while ((current = current.getCause()) != null);
		buf.append("</div>");

		return buf.toString();
	}

	protected String lineString(int lineNumber) {
		if (lineNumber >= 0) {
			return "#" + Integer.toString(lineNumber);
		}
		return "";
	}

	public FacesContext getFacesContext() {
		return context;
	}

	public void setFacesContext(FacesContext context) {
		this.context = context;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTace) {
		this.stackTrace = stackTace;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
