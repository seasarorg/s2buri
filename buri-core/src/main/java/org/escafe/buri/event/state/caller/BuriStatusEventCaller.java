package org.escafe.buri.event.state.caller;

import java.util.Date;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.event.state.BuriStatusEventListener;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriStatusEventCaller {
	void addStatusEventListener(BuriStatusEventListener listener);
	void saveState(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker);
	void processed(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker);
	void abortState(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker);
	void createBranch(BuriSystemContext sysContext, BranchWalker walker);
	void abortBranch(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker);
	void setAutorun(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker,Date autorun);
}
