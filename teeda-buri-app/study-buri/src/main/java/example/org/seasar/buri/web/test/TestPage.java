package example.org.seasar.buri.web.test;

public class TestPage {

	public Class doError() {
		throw new RuntimeException("AAA");
	}

	public Class initialize() {
		throw new RuntimeException("aaa");
	}

	public Class prerender() {
		return null;
	}

}
