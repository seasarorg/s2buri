package org.escafe.buri.event.boot.caller;

import org.escafe.buri.event.boot.BuriComplieEventListener;
import org.escafe.buri.event.boot.BuriCompileEvent.CompileTargetType;

public interface BuriComplieEventCaller {
	void addComplieEventListener(BuriComplieEventListener listener);
	void callStartReadXpdl(Object compiler,CompileTargetType compileTargetType,String compileTargetName);
	void callEndReadXpdl(Object compiler,CompileTargetType compileTargetType,String compileTargetName,Object result);
	void callStartCompile(Object compiler,CompileTargetType compileTargetType,String compileTargetName);
	void callEndCompile(Object compiler,CompileTargetType compileTargetType,String compileTargetName,Object result);
	void callStartObjectInit(Object compiler,CompileTargetType compileTargetType,String compileTargetName);
	void callEndObjectInit(Object compiler,CompileTargetType compileTargetType,String compileTargetName,Object result);
}
