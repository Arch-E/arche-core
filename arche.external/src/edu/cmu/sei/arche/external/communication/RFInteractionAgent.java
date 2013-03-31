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
 * â€œNeither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this softwareâ€�
 *
 * 4. The names â€œCarnegie Mellon University,â€� and/or â€œSoftware Engineering
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
 * IS FURNISHED ON AN â€œAS-ISâ€� BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package edu.cmu.sei.arche.external.communication;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlBlaster.client.I_Callback;
import org.xmlBlaster.client.I_XmlBlasterAccess;
import org.xmlBlaster.client.key.EraseKey;
import org.xmlBlaster.client.key.PublishKey;
import org.xmlBlaster.client.key.SubscribeKey;
import org.xmlBlaster.client.key.UnSubscribeKey;
import org.xmlBlaster.client.key.UpdateKey;
import org.xmlBlaster.client.qos.ConnectQos;
import org.xmlBlaster.client.qos.ConnectReturnQos;
import org.xmlBlaster.client.qos.DisconnectQos;
import org.xmlBlaster.client.qos.EraseQos;
import org.xmlBlaster.client.qos.EraseReturnQos;
import org.xmlBlaster.client.qos.PublishQos;
import org.xmlBlaster.client.qos.SubscribeQos;
import org.xmlBlaster.client.qos.SubscribeReturnQos;
import org.xmlBlaster.client.qos.UnSubscribeQos;
import org.xmlBlaster.client.qos.UnSubscribeReturnQos;
import org.xmlBlaster.client.qos.UpdateQos;
import org.xmlBlaster.client.qos.UpdateReturnQos;
import org.xmlBlaster.util.Global;
import org.xmlBlaster.util.MsgUnit;
import org.xmlBlaster.util.XmlBlasterException;
import org.xmlBlaster.util.qos.ClientProperty;

import edu.cmu.sei.arche.external.execution.ArchEApplySuggestedTactic;
import edu.cmu.sei.arche.external.execution.ArchEDescribeTacticToUser;
import edu.cmu.sei.arche.external.execution.RawArchETryTacticResult;
import edu.cmu.sei.arche.external.execution.RawArchEUserQuestion;
import edu.cmu.sei.arche.external.reasoningframework.ArchEAnalysisResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEEvaluationResult;
import edu.cmu.sei.arche.external.reasoningframework.ArchEReasoningFramework;

/**
 * This is an agent that helps ArchE interact with an external reasoning
 * framework(RF). It has the following responsibilities:
 * 1. Establishes a remote connection to a MOM server, say, xmlBlaster.
 * 2. Advertises RF commands that the external RF can provide.
 * 3. Listens to a RF command request.
 * 4. Has the external RF respond to it.
 * 5. Periodically reports its progress to the MOM server.
 * 6. Has the external RF stop its execution if a cancellation is requested from ArchE.  
 *    
 * @see RFCommand
 * @see RFCommandListener
 * @see For more details about XmlBlaster: http://www.xmlblaster.org/
 * 
 * @author Hyunwoo Kim
 */
public class RFInteractionAgent {

    private static final String PROPERTYFILE_NAME = "properties_xmlBlaster.xml";
    private static final String QUESTIONS_FILE_EXTENTION = ".questions";
    
    // Use abstract implementation classes, not interfaces, in order to hide the details
    // about how to make an external reasoning framework interact with ArchE via this agent class.
    // In this way, external RF developers are free from knowing about the connection protocol
    // implemented in this class.     
    private ArchEReasoningFramework eRF = null;	
    private RFCommandListener listener = null;   
    
	private Logger log = null; 

	private Global glob = null;
	private I_XmlBlasterAccess clientHandle = null;
    private XmlBlasterConfig xmlBlasterConfig = null;
	private String registerKey =null;
	private String cancelKey =null;
	private String progressKey =null;
	private String returnKey =null;	
	private SubscribeReturnQos srServiceKey =null;
	private SubscribeReturnQos srCancelKey =null;
	private boolean canceled = false;
	private boolean running = false;
	
	private RFCommand rfCommand = null;	

	public RFInteractionAgent(ArchEReasoningFramework rf){		
		// Set ArchE reasoning framework
		this.eRF = rf;		
				
		// Initialize event keys
		this.registerKey = "MetaData_" + eRF.getID();			
		this.cancelKey = eRF.getID() + ":Cancel";
		this.returnKey = eRF.getID() + ":Return";
		this.progressKey = eRF.getID() + ":Progress";			
				
		String installPathName = null;
		// Put xmlBlaster.jar into classpath		
		try {     	   
			URL url = FileLocator.find(Platform.getBundle("SEI.ArchE.External"), new Path("/"), null);
			installPathName = FileLocator.resolve(url).getPath();
			URL newURL = new URL("file", "", installPathName + "lib/xmlBlaster.jar");
			ClassPathHacker.addURL(newURL);
		} catch (MalformedURLException e1){
			e1.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		}                             
		
         // Prepare for a connection to xmlBlaster server
		String configFolderPath = installPathName + "config";
        initialize(configFolderPath);
	}
	
	public Logger getLogger(){
		if(log == null)
			log = Logger.getLogger(eRF.getID());
		return log;
	}

	public ArchEReasoningFramework getExternalReasoningFramework(){
		return this.eRF;		
	}	

    public void printLog(int depth, Level level, String msg){
    	String taps = "";
    	for (int i=0; i < depth; i++)
    		taps += "\t";
    	
    	if(level == Level.INFO)
    		getLogger().info(taps + msg);
    	else if(level == Level.WARNING)
    		getLogger().warning(taps + msg);
    	else if(level == Level.SEVERE)
    		getLogger().severe(taps + msg);
    }
    
    
    /////// RFCommandListener Methods ////////////
    /**
     * Add a listener if you want to change the default command listener (ReasoningFrameworkExecutor)
     * @param listener the listener added
     */
    public void addRFCommandListener(RFCommandListener listener) {
 	   this.listener = listener;
    }

    /**
     * Remove a listener
     * @param listener the listener removed
     */
    public void removeRFCommandListener(RFCommandListener listener) {
 	   if(this.listener == listener)
 		   this.listener = null;
    }
    
    /**
     * Initialize global resources for the following remote connection to xmlBlaster server.
     */
	private void initialize(String configFolderPath){								
		if(glob != null){
        	getLogger().warning("Global resources are already initialized");
			return;			
		}
		
		glob = new Global();
		
		
        // initialize XmlBlaster configuration object
        xmlBlasterConfig = new XmlBlasterConfig();
              
        // load XmlBlaster configuration
        if (loadProperties(configFolderPath) == false) {
        	getLogger().severe("Failed to load properties");
        }
		
    	String[] argsTemp = null;
    	if(xmlBlasterConfig.getHostName().equals("127.0.0.1")){
    	    String[] argsTemp1 = { "-protocol", "SOCKET",
    	    		  "-dispatch/connection/plugin/socket/port", xmlBlasterConfig.getPort()};
    	    argsTemp = argsTemp1;
    	}
    	else{
    	    String[] argsTemp1 = { "-protocol", "SOCKET",
    	              "-dispatch/connection/plugin/socket/hostname", xmlBlasterConfig.getHostName(),
    	              "-dispatch/connection/plugin/socket/port", xmlBlasterConfig.getPort()};
      	    argsTemp = argsTemp1;    		
    	}
    	
		if (glob.init(argsTemp) != 0) {   // this execution leads to some changes of the log object 
			getLogger().severe(glob.usage());
        	getLogger().severe("Failed to connect to xmlBlaster ");
		}		
	}
	
   /**
    * Establish a remote connection to xmlBlaster.
    */
	protected void connect() throws RFAgentException{

        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        }
        
	    clientHandle = glob.getXmlBlasterAccess();
		    
	    if(clientHandle.isConnected() && clientHandle.isAlive()){
	    	getLogger().warning("RF Agent already connected to xmlBlaster");
	        return;	    	
	    }	    	
		      
		try {
	         // Check if other login name or password was given on command line:
	         // (This is redundant as it is done by ConnectQos already)
	         String name = glob.getProperty().get("session.name", "RFAgent");
	         String passwd = glob.getProperty().get("passwd", "RFAgent");

	         ConnectQos qos = new ConnectQos(glob, name, passwd);                 
             ConnectReturnQos conRetQos = clientHandle.connect(qos, new I_Callback() {
					public String update(String cbSessionId, UpdateKey updateKey, byte[] content,UpdateQos updateQos){
	        			if (updateKey.isInternal()) {
	        				getLogger().warning("Received unexpected internal message '" +
	        						updateKey.getOid() + " from xmlBlaster");
	        				return "";
	        			}
	        		
	        			getLogger().warning("Received asynchronous message '" + updateKey.getOid() +
	        		            "' state=" + updateQos.getState() +
	        		            " content=" + new String(content) + " from xmlBlaster");
	        		
	        			UpdateReturnQos uq = new UpdateReturnQos(clientHandle.getGlobal());
	        			return uq.toXml();
	        		}
	         });// Login to xmlBlaster, default handler for updates             
             getLogger().warning("RF Agent connected to xmlBlaster " + conRetQos.getSessionName().getRelativeName());	             
		}
		catch (XmlBlasterException e) {
		   getLogger().severe(e.getMessage());
		   throw new RFAgentException("Failed to connect to xmlBlaster ");
		}
		catch (Throwable e) {
		   getLogger().severe(e.toString());
		   e.printStackTrace();
		   throw new RFAgentException("Failed to connect to xmlBlaster ");
		}		
	}

   /**
    * Advertise ArchE commands that the external RF can provide.
    */
	protected void advertise() throws RFAgentException{
		
		try {
			clientHandle.getQueue().clear();
			         
			PublishKey pk = new PublishKey(glob, registerKey, "text/plain", "1.0");
			// Add meta data specific to this external reasoning framework
			String rfTags = "<ArchE>" +
								"<RF type='Tactical' quality='" + eRF.getQuality() + "'>" +
								"</RF>" +
							"</ArchE>";
			
			pk.setClientTags(rfTags);  
			PublishQos pq = new PublishQos(glob);

			ByteArrayOutputStream rfconfigStream = new ByteArrayOutputStream();			
            String rfconfigFullPath = eRF.getInstallPathName() + eRF.getRFConfigFileName();

            ObjectOutputStream oos = new ObjectOutputStream(rfconfigStream);
            
			// Read and encode each line from the RF configuration file before sending the file across networks
            File file = new File(rfconfigFullPath);            
			BufferedReader in = new BufferedReader(new FileReader(file));

			String strline;
			int numofLines = 0;
		    in.mark((int)file.length()+1);			    
		    while ((strline = in.readLine()) != null){
		    	numofLines++;			    	
		    }
		    
		    in.reset();
            oos.writeInt(numofLines);			    	
		    while ((strline = in.readLine()) != null){
	            oos.writeUTF(strline);   // encoded			    	
		    }
	        in.close();				
	        
 			// Read and encode each line from the questions.properties file before sending the file across networks
            String questionFileFullPath = eRF.getInstallPathName() 
            				+ eRF.getRFConfigFileName().substring(0, eRF.getRFConfigFileName().indexOf(".xml"))
            				+ QUESTIONS_FILE_EXTENTION;
            file = new File(questionFileFullPath);
            if(file.exists()){
				in = new BufferedReader(new FileReader(file));

				numofLines = 0;
			    in.mark((int)file.length()+1);			    
			    while ((strline = in.readLine()) != null){
			    	numofLines++;			    	
			    }
			    
			    in.reset();
	            oos.writeInt(numofLines);			    	
			    while ((strline = in.readLine()) != null){
		            oos.writeUTF(strline);   // encoded			    	
			    }
		        in.close();					            	
            }		     
            
            oos.close();	            
			
			MsgUnit msgUnit = new MsgUnit(pk, rfconfigStream.toByteArray(), pq);
			clientHandle.publish(msgUnit);
			getLogger().warning("Published message '" + pk.getOid() + "'");				
		}
	    catch (XmlBlasterException e) {
	       getLogger().severe("We have a problem: " + e.getMessage());
		   throw new RFAgentException("Failed to advertise this external reasoning framework ");
		} catch (IOException e) {
	       getLogger().severe("Failed to load the RF configuration file or questions file" + e.getMessage());
		   throw new RFAgentException("Failed to advertise this external reasoning framework ");			
		}			
	}	

   /**
    * Start listening to RF command requests.
    */
	protected void subscribe() throws RFAgentException{
		try {
			SubscribeKey sk = new SubscribeKey(glob, eRF.getID());
			SubscribeQos sq = new SubscribeQos(glob);
			sq.setWantInitialUpdate(false);
			srServiceKey = clientHandle.subscribe(sk, sq, new I_Callback() {
				public String update(String cbSessionId, UpdateKey updateKey, byte[] content, UpdateQos updateQos) {
					if (updateKey.getOid().equals(eRF.getID())){
						getLogger().warning("Receiving asynchronous message '" + updateKey.getOid() +
								"' state=" + updateQos.getState() + " in RFAgent handler");
						if(updateQos.isOk() == true){						
							ClientProperty cp = updateQos.getClientProperty("CommID");
							int rfCommandID = cp.getIntValue();		        		   
							getLogger().warning("[Requested Command] " + RFCommand.getCommandNameByID(rfCommandID));
			        		   
							if(deserializeParametersForTactical(rfCommandID,content) == true){		        	
								canceled = false;
								if(updateQos.getState().equals("OK")){
									fireCommandRequestedForTactical();		        		   		        		   
								}		        			   
							}
							else
								getLogger().severe("Failed to parse command parameters!!!");					        	
				        }
					}
					else
						getLogger().severe("Receiving unexpected asynchronous message '" + updateKey.getOid() +
		                           "' with state '" + updateQos.getState() + "' in RFAgent handler");
					return "";
				}
			});  // subscribe with our specific update handler
			getLogger().warning("Subscribed on message " + sk.getOid());
	        
			sk = new SubscribeKey(glob, cancelKey);
			sq = new SubscribeQos(glob);
			sq.setWantInitialUpdate(false);
			srCancelKey = clientHandle.subscribe(sk, sq, new I_Callback() {
				public String update(String cbSessionId, UpdateKey updateKey, byte[] content, UpdateQos updateQos) {
					if (updateKey.getOid().equals(cancelKey)) {
						getLogger().warning("Receiving asynchronous message '" + updateKey.getOid() +
				                  "' state=" + updateQos.getState() + " in RFAgent handler");
						if(updateQos.isOk() == true){
							if(updateQos.getState().equals("OK")){
								canceled = true;
								getLogger().warning("Cancellation is requested");
							}
						}
					}
					else
						getLogger().severe("Receiving unexpected asynchronous message '" + updateKey.getOid() +
			                   "' with state '" + updateQos.getState() + "' in RFAgent handler");
					return "";
				}
			});  // subscribe with our specific update handler
			getLogger().warning("Subscribed on message " + sk.getOid());	        	        
		}
	    catch (XmlBlasterException e) {
	    	getLogger().severe("RFAgent initialization failed: " + e.getMessage());
	    	throw new RFAgentException("Failed to start listening to xmlBlaster ");
	    }		
	}
	
   /**
    * Stop listening to Rf command requests.
    */
	protected void unsubscribe() throws RFAgentException{
		try {
			UnSubscribeKey uk = new UnSubscribeKey(glob, srServiceKey.getSubscriptionId());
			UnSubscribeQos uq = new UnSubscribeQos(glob);
			UnSubscribeReturnQos[] urq = clientHandle.unSubscribe(uk, uq);
			if (urq.length > 0) 
				getLogger().warning("Unsubscribed on message " + uk.getOid());
	         	         
			uk = new UnSubscribeKey(glob, srCancelKey.getSubscriptionId());
			uq = new UnSubscribeQos(glob);
			urq = clientHandle.unSubscribe(uk, uq);
			if (urq.length > 0) 
				getLogger().warning("Unsubscribed on message " + uk.getOid());
		}
		catch (XmlBlasterException e) {
			getLogger().severe(e.getMessage());
			throw new RFAgentException("Failed to stop listening to xmlBlaster ");
		}
		catch (Throwable e) {
			getLogger().severe(e.toString());
			e.printStackTrace();
			throw new RFAgentException("Failed to stop listening to xmlBlaster ");
		}
	}	
	
    /**
    * Erase published keys.
    */
	protected void erase() throws RFAgentException{
		try {
		     EraseKey ek = new EraseKey(glob, registerKey);
		     EraseQos eq = new EraseQos(glob);
		     EraseReturnQos[] eraseArr = clientHandle.erase(ek, eq);
		     if (eraseArr.length  == 1) 
		    	 getLogger().warning("Erased message " + eraseArr[0].getKeyOid());
		     else
		    	 getLogger().warning("Erased " + eraseArr.length + " messages of " + registerKey);
		     
		     ek = new EraseKey(glob, progressKey);
		     eq = new EraseQos(glob);
		     eraseArr = clientHandle.erase(ek, eq);
		     if (eraseArr.length  == 1) 
		    	 getLogger().warning("Erased message " + eraseArr[0].getKeyOid());
		     else
		    	 getLogger().warning("Erased " + eraseArr.length + " messages of " + progressKey);
		     
		     ek = new EraseKey(glob, returnKey);
		     eq = new EraseQos(glob);
		     eraseArr = clientHandle.erase(ek, eq);
		     if (eraseArr.length  == 1) 
		    	 getLogger().warning("Erased message " + eraseArr[0].getKeyOid());
		     else
		    	 getLogger().warning("Erased " + eraseArr.length + " messages of " + returnKey);
		     		     
		     ek = new EraseKey(glob, eRF.getID());
		     eq = new EraseQos(glob);
		     eraseArr = clientHandle.erase(ek, eq);
		     if (eraseArr.length  == 1) 
		    	 getLogger().warning("Erased message " + eraseArr[0].getKeyOid());
		     else
		    	 getLogger().warning("Erased " + eraseArr.length + " messages of " + eRF.getID());
		     
		     ek = new EraseKey(glob, cancelKey);
		     eq = new EraseQos(glob);
		     eraseArr = clientHandle.erase(ek, eq);
		     if (eraseArr.length  == 1) 
		    	 getLogger().warning("Erased message " + eraseArr[0].getKeyOid());
		     else
		    	 getLogger().warning("Erased " + eraseArr.length + " messages of " + cancelKey);		     		     
		  }
		  catch (XmlBlasterException e) {
		     getLogger().severe(e.getMessage());
	         throw new RFAgentException("Failed to erase related messages");
		  }
		  catch (Throwable e) {
		     getLogger().severe(e.toString());
		     e.printStackTrace();
	         throw new RFAgentException("Failed to erase related messages");
		  }		
	}
	
    /**
    * Disconnect from xmlBlaster.
    */
	protected void disconnect() throws RFAgentException{
	     DisconnectQos dq = new DisconnectQos(glob);
	     clientHandle.disconnect(dq);
	}

    /**
    * Free global resources
    */
	protected void uninitialize(){
	     glob.shutdown(); // free resources
	     glob = null;
	}
	
    /**
    * Publish a return message.
    */
	public void publishReturn(List resultList, List extraResultList,int rfCommandID) {
	   ByteArrayOutputStream parameters = new ByteArrayOutputStream();
	   PublishKey pk = new PublishKey(glob, returnKey, "text/plain", "1.0");
	   PublishQos pq = new PublishQos(glob);
	   pq.setState("Error");
	   
	   try {
		   ObjectOutputStream oos = new ObjectOutputStream(parameters);
	    		   
		   if(rfCommandID == RFCommand.ID_COMMAND_APPLYTACTICS){
			   // Do nothing
		   }
		   else if(rfCommandID == RFCommand.ID_COMMAND_ANALYZEANDSUGGEST){
			   // List of Analysis Result
			   oos.writeObject(resultList);

			   // List of TryTacticResult			   
			   oos.writeObject(extraResultList);			   
		   }
		   else if(rfCommandID == RFCommand.ID_COMMAND_APPLYSUGGESTEDTACTIC){
			   // Do nothing			   
		   }
		   else if(rfCommandID == RFCommand.ID_COMMAND_ANALYZE){
			   // List of Evaluation Result
			   oos.writeObject(resultList);
		   }
		   else if(rfCommandID == RFCommand.ID_COMMAND_DESCRIBETACTIC){
			   // List of User Question
			   oos.writeObject(resultList);			   
		   }
		   		   		   
		   oos.close();
		   pq.setState("OK");
		   
	   }catch (IOException e){
		   e.printStackTrace();
	   }finally{
	       MsgUnit msgUnit = new MsgUnit(pk, parameters.toByteArray(), pq);	       
	       try {
	    	   clientHandle.publish(msgUnit);
		       printLog(1, Level.WARNING, "Published message '" + pk.getOid() + "'");		                	 
		   } catch (XmlBlasterException e) {
				e.printStackTrace();
     	   }
	   }
	}
	
   /**
    * Start this agent's services
    */
	public void start() throws RFAgentException{
		if(this.eRF!=null && running == false){
			connect();
			advertise();
			subscribe();
			running = true;			
		}
    }
	  	   
   /**
    * Stop this agent's services
    */
	public void stop() throws RFAgentException{
		if(this.eRF!=null && running == true){
			unsubscribe();
			erase();
			disconnect();
			running = false;
		}
    }
	   

   /**
    * Publish the amount of work done at this time.
    */
	public void worked(int work){
	}
	
   /**
    * Check if it is canceled.
    * @return true if not canceled, otherwise false.
    */
	public boolean isCanceled(){
		return canceled;
	}
	   
   /**
    * Fire the requested command to a "tactical" reasoning framework. First, it creates a thread in which
    * the requested command will be processed. Second, in the thread, the reasoning framework will
    * execute the requested command. The result is that two threads are running concurrently:
    * the current thread (which returns from the call to the start methods) and
    * the other thread (which executes its run method).
    * 
    */		
	private void fireCommandRequestedForTactical() {   
		Thread t = new Thread(new Runnable() {
			public void run() {
		        getLogger().warning("[SQL URL] " + rfCommand.getDBCConfig().getSQL_URL());
		        getLogger().warning("[SQL User] " + rfCommand.getDBCConfig().getSQL_User());
		        getLogger().warning("[SQL Pwd] " + rfCommand.getDBCConfig().getSQL_Pwd());
		        
				if(rfCommand.getCommandID() == RFCommand.ID_COMMAND_APPLYTACTICS){
					printLog(0, Level.INFO, "[ApplyTactic starting...]");
					printLog(1, Level.INFO, "Current Project Name = " + ((RFApplyTactics)rfCommand).getCurrentProjectName());
					printLog(1, Level.INFO, "Current Architecture Name = " + ((RFApplyTactics)rfCommand).getArchitectureName());						
					
					listener.applyTactics((RFApplyTactics)rfCommand);
					if(!canceled){
						publishReturn(null,null,RFCommand.ID_COMMAND_APPLYTACTICS);
						printLog(1, Level.INFO, "Tactic is applied!!!");					
					}
					else
						printLog(1, Level.INFO, "Command canceled!!!");										
				}
				else if(rfCommand.getCommandID() == RFCommand.ID_COMMAND_ANALYZEANDSUGGEST){
					printLog(0, Level.INFO, "[AnalyzeAndSuggest starting...]");
					printLog(1, Level.INFO, "Current Project Name = " + ((RFAnalyzeAndSuggest)rfCommand).getCurrentProjectName());
					printLog(1, Level.INFO, "Current Architecture Name = " + ((RFAnalyzeAndSuggest)rfCommand).getArchitectureName());						
					
					List<RawArchETryTacticResult> outRawTactics = new ArrayList<RawArchETryTacticResult>();					
					List<ArchEAnalysisResult> resultList = 
							listener.analyzeAndSuggest((RFAnalyzeAndSuggest)rfCommand, outRawTactics);				
					if(!canceled){
						publishReturn(resultList,outRawTactics, RFCommand.ID_COMMAND_ANALYZEANDSUGGEST);
						printLog(1, Level.INFO, "Sent analysis results and suggested tactics!!!");					
					}
					else
						printLog(1, Level.INFO, "Command canceled!!!");					
				}
				else if(rfCommand.getCommandID() == RFCommand.ID_COMMAND_APPLYSUGGESTEDTACTIC){
					printLog(0, Level.INFO, "[ApplySuggestedTactic starting...]");
					printLog(1, Level.INFO, "Current Project Name = " + ((RFApplySuggestedTactic)rfCommand).getCurrentProjectName());
					printLog(1, Level.INFO, "Current Architecture Name = " + ((RFApplySuggestedTactic)rfCommand).getArchitectureName());						
					printLog(1, Level.INFO, "Candidate name to create = " + ((RFApplySuggestedTactic)rfCommand).getCandidateName());						
					listener.applySuggestedTactic((RFApplySuggestedTactic)rfCommand);				
					if(!canceled)
						publishReturn(null,null,RFCommand.ID_COMMAND_APPLYSUGGESTEDTACTIC);
					else
						printLog(1, Level.INFO, "Command canceled!!!");					
				}
				else if(rfCommand.getCommandID() == RFCommand.ID_COMMAND_ANALYZE){					
					printLog(0, Level.INFO, "[Analyze starting...]");
					printLog(1, Level.INFO, "Current Project Name = " + ((RFAnalyze)rfCommand).getCurrentProjectName());
					printLog(1, Level.INFO, "Candidate Name = " + ((RFAnalyze)rfCommand).getCandidateName());
					
					List<ArchEEvaluationResult> evaluationResultList =
							listener.analyze((RFAnalyze)rfCommand);				
					if(!canceled){
						publishReturn(evaluationResultList,null,RFCommand.ID_COMMAND_ANALYZE);
						printLog(1, Level.INFO, "Sent analysis results!!!");						
					}
					else
						printLog(1, Level.INFO, "Command canceled!!!");					
				}
				else if(rfCommand.getCommandID() == RFCommand.ID_COMMAND_DESCRIBETACTIC){
					printLog(0, Level.INFO, "[DescribeTactic starting...]");
					printLog(1, Level.INFO, "Current Project Name = " + ((RFDescribeTactic)rfCommand).getCurrentProjectName());
					printLog(1, Level.INFO, "Current Architecture Name = " + ((RFDescribeTactic)rfCommand).getArchitectureName());						
					List<RawArchEUserQuestion> rawUserQuestionList = 
							listener.describeTactics((RFDescribeTactic)rfCommand);				
					if(!canceled){
						publishReturn(rawUserQuestionList,null,RFCommand.ID_COMMAND_DESCRIBETACTIC);
						printLog(1, Level.INFO, "Sent a user question list!!!");						
					}
					else
						printLog(1, Level.INFO, "Command canceled!!!");					
				}										        	
	        }
		});
		t.start();	   
		
		// wait a second for the new thread to make a value copy of the RFCommand object.
		// The RFCommand object will be used for the next command request
		try { Thread.sleep(1000); } catch(Exception e) { }				 		
   }	
   
	protected boolean deserializeParametersForTactical(int commandID, byte[] parameters){
		boolean ret = false;
        DBCConfig dbConfig=null;
        String SQL_URL=null;
        String SQL_User=null;
        String SQL_Pwd=null;
        String projectName=null;
		
		if(commandID == RFCommand.ID_COMMAND_APPLYTACTICS){			
			ByteArrayInputStream bais = new ByteArrayInputStream(parameters);        	
            ObjectInputStream ois;
            String architectureName=null;
            List<RawArchEUserQuestion> listOfUserQuestion=null;
            
			try {
				ois = new ObjectInputStream(bais);
				SQL_URL = ois.readUTF();
				SQL_User = ois.readUTF();
				SQL_Pwd = ois.readUTF();
	            projectName = ois.readUTF();
				architectureName = ois.readUTF();
	            listOfUserQuestion = (List<RawArchEUserQuestion>) ois.readObject();
	            ois.close();            
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
						
			dbConfig = new DBCConfig();
			dbConfig.setSQL_URL(SQL_URL);
			dbConfig.setSQL_User(SQL_User);
			dbConfig.setSQL_Pwd(SQL_Pwd);
	        
			rfCommand = new RFApplyTactics(this,commandID, dbConfig, projectName, architectureName, listOfUserQuestion);
			ret = true;			
		}
		else if(commandID == RFCommand.ID_COMMAND_ANALYZEANDSUGGEST){
			ByteArrayInputStream bais = new ByteArrayInputStream(parameters);        	
            ObjectInputStream ois;
            String architectureName=null;
			try {
				ois = new ObjectInputStream(bais);
				SQL_URL = ois.readUTF();
				SQL_User = ois.readUTF();
				SQL_Pwd = ois.readUTF();
	            projectName = ois.readUTF();
				architectureName = ois.readUTF();
	            ois.close();            
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			dbConfig = new DBCConfig();
			dbConfig.setSQL_URL(SQL_URL);
			dbConfig.setSQL_User(SQL_User);
			dbConfig.setSQL_Pwd(SQL_Pwd);
			
			rfCommand = new RFAnalyzeAndSuggest(this,commandID, dbConfig, projectName, architectureName);
			ret = true;			
		}
		else if(commandID == RFCommand.ID_COMMAND_APPLYSUGGESTEDTACTIC){
			ByteArrayInputStream bais = new ByteArrayInputStream(parameters);        	
            ObjectInputStream ois;
            String architectureName=null;
            String candidateName=null;
            ArchEApplySuggestedTactic applySuggestedTactic=null;
			try {
				ois = new ObjectInputStream(bais);
				SQL_URL = ois.readUTF();
				SQL_User = ois.readUTF();
				SQL_Pwd = ois.readUTF();
	            projectName = ois.readUTF();
				architectureName = ois.readUTF();
				candidateName = ois.readUTF();
				applySuggestedTactic = (ArchEApplySuggestedTactic) ois.readObject();
	            ois.close();            
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			dbConfig = new DBCConfig();
			dbConfig.setSQL_URL(SQL_URL);
			dbConfig.setSQL_User(SQL_User);
			dbConfig.setSQL_Pwd(SQL_Pwd);
			
			rfCommand = new RFApplySuggestedTactic(this,commandID, dbConfig, projectName, architectureName, candidateName, applySuggestedTactic);
			ret = true;
		}
		else if(commandID == RFCommand.ID_COMMAND_ANALYZE){
			ByteArrayInputStream bais = new ByteArrayInputStream(parameters);        	
            ObjectInputStream ois;
            String candidateName=null;
			try {
				ois = new ObjectInputStream(bais);
				SQL_URL = ois.readUTF();
				SQL_User = ois.readUTF();
				SQL_Pwd = ois.readUTF();
	            projectName = ois.readUTF();
				candidateName = ois.readUTF();
	            ois.close();            
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			dbConfig = new DBCConfig();
			dbConfig.setSQL_URL(SQL_URL);
			dbConfig.setSQL_User(SQL_User);
			dbConfig.setSQL_Pwd(SQL_Pwd);
			
			rfCommand = new RFAnalyze(this,commandID, dbConfig, projectName, candidateName);
			ret = true;
		}
		else if(commandID == RFCommand.ID_COMMAND_DESCRIBETACTIC){
			ByteArrayInputStream bais = new ByteArrayInputStream(parameters);        	
            ObjectInputStream ois;
            String architectureName=null;
            List<ArchEDescribeTacticToUser> listOfDescribeTacticToUser=null;
			try {
				ois = new ObjectInputStream(bais);
				SQL_URL = ois.readUTF();
				SQL_User = ois.readUTF();
				SQL_Pwd = ois.readUTF();
	            projectName = ois.readUTF();
				architectureName = ois.readUTF();
	            listOfDescribeTacticToUser = (List<ArchEDescribeTacticToUser>) ois.readObject();
	            ois.close();            
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			dbConfig = new DBCConfig();
			dbConfig.setSQL_URL(SQL_URL);
			dbConfig.setSQL_User(SQL_User);
			dbConfig.setSQL_Pwd(SQL_Pwd);
			
			rfCommand = new RFDescribeTactic(this,commandID, dbConfig, projectName, architectureName, listOfDescribeTacticToUser);
			ret = true;
		}
		else { // Unknown command
            getLogger().severe("Unknown command");			
		}	
		return ret;
	}   

    /*
     * Read the XmlBlaster configuration from the xml file.
     */
    private boolean loadProperties(String configFolderPath) {
        XMLReader xmlReader = null;
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            spfactory.setValidating(false);
            
            // get the SAX paser and its xml reader
            SAXParser saxParser = spfactory.newSAXParser();
            xmlReader = saxParser.getXMLReader();

            xmlReader.setContentHandler(new XmlBlasterPropertyLoader(xmlBlasterConfig));
            String path = configFolderPath + "/" + PROPERTYFILE_NAME;
            InputSource source = new InputSource(path);
            xmlReader.parse(source);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }	
}
