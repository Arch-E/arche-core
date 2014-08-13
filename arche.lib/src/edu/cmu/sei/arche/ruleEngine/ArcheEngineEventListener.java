package edu.cmu.sei.arche.ruleengine;

import java.util.EventListener;
import java.util.EventObject;


public interface ArcheEngineEventListener extends EventListener {
	public void handleFactEvent(EventObject e);
}

