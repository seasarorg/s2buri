package org.escafe.buri.event.boot.caller.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.event.boot.BuriCompileEvent;
import org.escafe.buri.event.boot.BuriComplieEventListener;
import org.escafe.buri.event.boot.BuriCompileEvent.CompileTargetType;
import org.escafe.buri.event.boot.caller.BuriComplieEventCaller;

public class BuriComplieEventCallerImpl implements BuriComplieEventCaller {
	private List<BuriComplieEventListener> compileListeners = new ArrayList<BuriComplieEventListener>();
	
	public void addComplieEventListener(BuriComplieEventListener listener) {
		compileListeners.add(listener);
	}
	
	public void callStartReadXpdl(Object compiler,CompileTargetType compileTargetType,String compileTargetName) {
		BuriCompileEvent event = new BuriCompileEvent();
		event.setCompiler(compiler);
		event.setCompileTargetName(compileTargetName);
		event.setCompileTargetType(compileTargetType);
		event.setResult(null);
		for (BuriComplieEventListener listener : compileListeners) {
			listener.startReadXpdl(event);
		}
	}
	
	public void callEndReadXpdl(Object compiler,CompileTargetType compileTargetType,String compileTargetName,Object result) {
		BuriCompileEvent event = new BuriCompileEvent();
		event.setCompiler(compiler);
		event.setCompileTargetName(compileTargetName);
		event.setCompileTargetType(compileTargetType);
		event.setResult(result);
		for (BuriComplieEventListener listener : compileListeners) {
			listener.endReadXpdl(event);
		}
	}
	
	public void callStartCompile(Object compiler,CompileTargetType compileTargetType,String compileTargetName) {
		BuriCompileEvent event = new BuriCompileEvent();
		event.setCompiler(compiler);
		event.setCompileTargetName(compileTargetName);
		event.setCompileTargetType(compileTargetType);
		for (BuriComplieEventListener listener : compileListeners) {
			listener.startCompile(event);
		}
	}
	
	public void callEndCompile(Object compiler,CompileTargetType compileTargetType,String compileTargetName,Object result) {
		BuriCompileEvent event = new BuriCompileEvent();
		event.setCompiler(compiler);
		event.setCompileTargetName(compileTargetName);
		event.setCompileTargetType(compileTargetType);
		event.setResult(result);
		for (BuriComplieEventListener listener : compileListeners) {
			listener.endCompile(event);
		}
	}
	
	public void callStartObjectInit(Object compiler,CompileTargetType compileTargetType,String compileTargetName) {
		BuriCompileEvent event = new BuriCompileEvent();
		event.setCompiler(compiler);
		event.setCompileTargetName(compileTargetName);
		event.setCompileTargetType(compileTargetType);
		for (BuriComplieEventListener listener : compileListeners) {
			listener.startObjectInit(event);
		}
	}
	
	public void callEndObjectInit(Object compiler,CompileTargetType compileTargetType,String compileTargetName,Object result) {
		BuriCompileEvent event = new BuriCompileEvent();
		event.setCompiler(compiler);
		event.setCompileTargetName(compileTargetName);
		event.setCompileTargetType(compileTargetType);
		event.setResult(result);
		for (BuriComplieEventListener listener : compileListeners) {
			listener.endObjectInit(event);
		}
	}
}
