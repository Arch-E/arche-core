/*
 * ArchE
 * Copyright (c) 2012 Carnegie Mellon University.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following acknowledgments and disclaimers.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. All advertising materials for third-party software mentioning features or
 * use of this software must display the following disclaimer:
 *
 * “Neither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this software”
 *
 * 4. The names “Carnegie Mellon University,” and/or “Software Engineering
 * Institute" shall not be used to endorse or promote products derived from
 * this software without prior written permission. For written permission,
 * please contact permission@sei.cmu.edu.
 *
 * 5. Redistributions of any form whatsoever must retain the following
 * acknowledgment:
 *
 * Copyright 2012 Carnegie Mellon University.
 *
 * This material is based upon work funded and supported by the United States
 * Department of Defense under Contract No. FA8721-05-C-0003 with Carnegie
 * Mellon University for the operation of the Software Engineering Institute, a
 * federally funded research and development center.
 *
 * NO WARRANTY
 *
 * THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE MATERIAL
 * IS FURNISHED ON AN “AS-IS” BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package edu.cmu.sei.designview.layouts;

import java.awt.Dimension;

import edu.uci.ics.jung.visualization.Layout;
import edu.uci.ics.jung.visualization.StatusCallback;

/**
 * @author Hyunwoo Kim
 */

public abstract class ViewLayout {

	
	public static String DECOMPOSITION_VIEW = "DecompositionView";
	public static String DEPENDENCY_VIEW = "DependencyView";	
	public static String CONCURRENCY_VIEW = "ConcurrencyView";
	// TODO: [Extension Point] for additional views
	
    /**
     * a callback called during relaxer iteration
     */
	protected StatusCallback statusCallback;

    /**
	 * the thread that applies the current layout algorithm
	 */
	Thread relaxer;
	
	/**
	 * when <code>true</code>, the relaxer thread will enter a wait state
	 * until unsuspend is called
	 */
	boolean manualSuspend;
	
	/**
	 * the layout algorithm currently in use
	 */
	protected Layout layout;
	
	/**
	 * how long the relaxer thread pauses between iteration loops.
	 */
	protected long relaxerThreadSleepTime = 100L;	
		
	
	public Object pauseObject = new String("PAUSE OBJECT");

	long[] relaxTimes = new long[5];
	long[] paintTimes = new long[5];
	int relaxIndex = 0;
	int paintIndex = 0;
	double paintfps, relaxfps;


	boolean stop = false;

	boolean visRunnerIsRunning = false; 
	
	
	/**
	 * the relaxer thread that applies the Layout algorithm to the graph
	 *
	 */
	protected class VisRunner extends Thread {
		public VisRunner() {
			super("Relaxer Thread");
		}

		public void run() {
		    visRunnerIsRunning = true;
		    try {
		        while (!layout.incrementsAreDone() && !stop) {
		            synchronized (pauseObject) {
		                while (manualSuspend && !stop) {
		                    try {
		                        pauseObject.wait();
		                    } catch (InterruptedException e) {
//		                        System.err.println("vis runner wait interrupted");
		                    }
		                }
		            }
		            long start = System.currentTimeMillis();
		            layout.advancePositions();
		            long delta = System.currentTimeMillis() - start;
		            
		            if (stop)
		                return;
		            
		            String status = layout.getStatus();
		            if (statusCallback != null && status != null) {
		                statusCallback.callBack(status);
		            }
		            
		            if (stop)
		                return;
		            
		            relaxTimes[relaxIndex++] = delta;
		            relaxIndex = relaxIndex % relaxTimes.length;
		            relaxfps = average(relaxTimes);
		            
		            if (stop)
		                return;
		            
		            if (stop)
		                return;
		            
		            try {
		                sleep(relaxerThreadSleepTime);
		            } catch (InterruptedException ie) {
		                System.err.println("vis runner sleep insterrupted");
		            }
		        }
		    } finally {
		        visRunnerIsRunning = false;
		    }
		}
	}	
	
	/**
	 * Returns the double average of a number of long values.
	 * @param paintTimes	an array of longs
	 * @return the average of the doubles
	 */
	protected double average(long[] paintTimes) {
		double l = 0;
		for (int i = 0; i < paintTimes.length; i++) {
			l += paintTimes[i];
		}
		return l / paintTimes.length;
	}	
	
	/**
	 * Removes the current graph layout, and adds a new one.
	 * @param layout   the new layout to use
	 * @param viewSize the size of the View that will display this layout
	 */
	public void setGraphLayout(Layout layout, Dimension viewSize) {
        if(viewSize == null) {
            viewSize = new Dimension(600,600);
        }
		suspend();
		Dimension layoutSize = layout.getCurrentSize();
		// if the layout has NOT been initialized yet, initialize it
		// now to the size of the VisualizationViewer window
		if(layoutSize == null) {
		    layout.initialize(viewSize);
		} else {
		    layout.restart();      
        }
		layoutSize = layout.getCurrentSize();

		this.layout = layout;

		prerelax();
		unsuspend();
	}	
	
	/**
	 * Pre-relaxes and starts a visRunner thread
	 */
	public synchronized void init() {
	    if (visRunnerIsRunning ) {
	        stop();
	       // throw new FatalException("Can't init while a visrunner is running");
	    }
		prerelax();
		
        while (!layout.incrementsAreDone() && !stop) {
            synchronized (pauseObject) {
                while (manualSuspend && !stop) {
                    try {
                        pauseObject.wait();
                    } catch (InterruptedException e) {
//                        System.err.println("vis runner wait interrupted");
                    }
                }
            }
            long start = System.currentTimeMillis();
            layout.advancePositions();
            long delta = System.currentTimeMillis() - start;
                       
            String status = layout.getStatus();
            if (statusCallback != null && status != null) {
                statusCallback.callBack(status);
            }
                       
            relaxTimes[relaxIndex++] = delta;
            relaxIndex = relaxIndex % relaxTimes.length;
            relaxfps = average(relaxTimes);
            
        }		
		
//		relaxer = new VisRunner();
//		relaxer.start();
	}
	
	
	/**
	 * Runs the visualization forward a few hundred iterations (for half a 
	 * second)
	 */
	public void prerelax() {
		suspend();

		int i = 0;
		if (layout.isIncremental()) {
			// then increment layout for half a second
			long timeNow = System.currentTimeMillis();
			while (System.currentTimeMillis() - timeNow < 500 && !layout.incrementsAreDone()) {
				i++;
				layout.advancePositions();
			}
		}
		unsuspend();
	}	
	
	/**
	 * set a flag to suspend the relaxer thread
	 */
	public synchronized void suspend() {
		manualSuspend = true;
	}

	/**
	 * un-set the suspend flag for the relaxer thead
	 */
	public synchronized void unsuspend() {
		manualSuspend = false;
		synchronized (pauseObject) {
			pauseObject.notifyAll();
		}
	}	
	
	/**
	 * set a flag to stop the VisRunner relaxer thread
	 */
	public synchronized void stop() {
		manualSuspend = false;
		stop = true;
		// interrupt the relaxer, in case it is paused or sleeping
		// this should ensure that visRunnerIsRunning gets set to false
		try { relaxer.interrupt(); }
        catch(Exception ex) {
            // the applet security manager may have prevented this.
            // just sleep for a second to let the thread stop on its own
            System.err.println("got "+ex);
            try { Thread.sleep(1000); }
            catch(InterruptedException ie) {} // swallow
        }
		synchronized (pauseObject) {
			pauseObject.notifyAll();
		}
	}
}
