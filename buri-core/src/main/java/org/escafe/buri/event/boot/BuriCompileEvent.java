package org.escafe.buri.event.boot;

public class BuriCompileEvent {
	public enum CompileTargetType {XPDL,Package,Process,DataField,Preprocessor };
	private Object compiler;
	private CompileTargetType compileTargetType;
	private String compileTargetName;
	private Object result;
	
	public Object getCompiler() {
		return compiler;
	}
	public void setCompiler(Object compiler) {
		this.compiler = compiler;
	}
	public CompileTargetType getCompileTargetType() {
		return compileTargetType;
	}
	public void setCompileTargetType(CompileTargetType compileTargetType) {
		this.compileTargetType = compileTargetType;
	}
	public String getCompileTargetName() {
		return compileTargetName;
	}
	public void setCompileTargetName(String compileTargetName) {
		this.compileTargetName = compileTargetName;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer("[");
		buffer.append("compiler=").append(compiler);
		buffer.append("/compileTargetType=").append(compileTargetType);
		buffer.append("/compileTargetName=").append(compileTargetName);
		buffer.append("/result=").append(result);
		buffer.append("]");
		return buffer.toString();
	}
}
