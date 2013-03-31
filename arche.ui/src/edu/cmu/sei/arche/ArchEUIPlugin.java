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

package edu.cmu.sei.arche;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.navigator.ResourceNavigator;
import org.xml.sax.SAXException;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.designconfig.DesignReader;
import edu.cmu.sei.arche.designconfig.TreeReader;
import edu.cmu.sei.arche.ui.views.DesignElementsView;
import edu.cmu.sei.arche.ui.views.DesignRelationsView;
import edu.cmu.sei.arche.ui.views.EvaluationResultsView;
import edu.cmu.sei.arche.ui.views.FunctionRespMappingView;
import edu.cmu.sei.arche.ui.views.FunctionsView;
import edu.cmu.sei.arche.ui.views.JessConsole;
import edu.cmu.sei.arche.ui.views.ModelElementsView;
import edu.cmu.sei.arche.ui.views.ModelRelationsView;
import edu.cmu.sei.arche.ui.views.QuestionsView;
import edu.cmu.sei.arche.ui.views.RelationshipsView;
import edu.cmu.sei.arche.ui.views.ResponsibilitiesView;
import edu.cmu.sei.arche.ui.views.ScenarioRespMappingView;
import edu.cmu.sei.arche.ui.views.ScenariosView;
import edu.cmu.sei.arche.ui.views.TrafficLightView;
import edu.cmu.sei.arche.ui.views.designtree.ui.DesignTreeView;
import edu.cmu.sei.arche.vo.AnalysisResultVO;
import edu.cmu.sei.arche.vo.ViewElementTypeVO;
import edu.cmu.sei.arche.vo.ViewElementVO;
import edu.cmu.sei.arche.vo.DesignFilterVO;
import edu.cmu.sei.arche.vo.ViewRelationTypeVO;
import edu.cmu.sei.arche.vo.ViewRelationVO;
import edu.cmu.sei.arche.vo.EvaluationResultVO;
import edu.cmu.sei.arche.vo.FunctionResponsibilityMapVO;
import edu.cmu.sei.arche.vo.FunctionVO;
import edu.cmu.sei.arche.vo.ModelElementVO;
import edu.cmu.sei.arche.vo.ModelRelationVO;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.RelationshipVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;
import edu.cmu.sei.arche.vo.ScenarioPartVO;
import edu.cmu.sei.arche.vo.ScenarioResponsibilityMapVO;
import edu.cmu.sei.arche.vo.ScenarioVO;
import edu.cmu.sei.arche.vo.TreeVO;

/**
 * The main plugin class for SEI.ArchE.UI. Every Eclipse plugin that interacts with the platform UI
 * has to define a class like this. It provides access to various services, such as a registry of
 * images, persistent user preferences, current open project, resource bundles etc.
 * <p>
 * In particular, this plugin class is responsible for managing the life cycle of ArchE projects,
 * with respect to the peculiarity that ArchE allows only one project to be open at a time.
 * 
 * @see org.eclipse.ui.plugin.AbstractUIPlugin
 * @author Paulo Merson
 */
public class ArchEUIPlugin extends AbstractUIPlugin implements IRefreshableUI,
        IResourceChangeListener, IPropertyChangeListener, IWindowListener {

    /** Shared instance that is used all over */
    private static ArchEUIPlugin      plugin;

    /**
     * Hashtable that maps DesignFilterVO objects to Boolean objects. It shall contain as keys all
     * DesignFilterVOs that the DesignReader found as available from the designconfig.xml file. The
     * Boolean value is set to true. It is however dertermined by the user preferences set for the
     * eclipse workbench and is true if that design filter is active for and false otherwise.
     */
    private Hashtable                 availableDFs;
    private Hashtable                 availableTRs;

    //
    // BIT CONSTANTS FOR THE VIEWS
    //
    public static final int           NAVIGATOR             = 1 << 0;

    public static final int           SCENARIOS             = 1 << 1;

    public static final int           SCENARIO_RESP_MAPPING = 1 << 2;

    public static final int           FUNCTIONS             = 1 << 3;

    public static final int           FUNCTION_RESP_MAPPING = 1 << 4;

    public static final int           RESPONSIBILITIES      = 1 << 5;

    public static final int           RELATIONSHIPS         = 1 << 6;

    public static final int           MODEL_ELEMENTS        = 1 << 7;

    public static final int           MODEL_RELATIONS       = 1 << 8;

    public static final int           QUESTIONS             = 1 << 9;

    public static final int           JESS_CONSOLE          = 1 << 10;

    public static final int           DESIGN_ELEMENTS       = 1 << 11;

    public static final int           DESIGN_RELATIONS      = 1 << 12;

    public static final int           TRAFFIC_LIGHT         = 1 << 13;
    
    public static final int           DESIGN_TREE           = 1 << 14;
    
    public static final int           EVALUATION_RESULT     = 1 << 15;
    /**
     * session property that stores the ProjectVO instance associated with the workspace project
     */
    public static final QualifiedName PROJECTVO             = new QualifiedName("SEI.ArchE.UI",
                                                                    "ProjectVO");

    /**
     * session property that stores the ArchEFacade instance associated with the workspace project
     */
    public static final QualifiedName FACADE                = new QualifiedName("SEI.ArchE.UI",
                                                                    "ArchEFacade");

    /**
     * Contains the static mapping from VO class to the views that need to be refreshed when an
     * object of that VO class is created, updated or deleted. The set of views to be udpated is
     * actually a bitmap, so this Hashtable has:
     * <ul>
     * <li>Class as the key (the VO class)
     * <li>Ingeger as the object (the bitmap of the views)
     * </ul>
     */
    private static Hashtable          voClassToViews;

    /** Bitmap indicating what views are flagged to be refreshed at the moment. */
    private int                       viewsToRefreshBitmap;

    /**
     * Hashtable (ReasoningFrameworkVO --> Boolean) with all RFs loaded from all RF plugins found,
     * The value is true if that RF is active (user preference)
     */
    private Hashtable                 availableRFs;

    /**
     * Previously opened project. Used to identify when we open a different project
     */
    private IProject                  prevOpenProject;

    /**
     * Instance of QuestionParser that is maintained by ArchEUIPlugin and used everywhere to avoid
     * loading the questions.properties file every time we need a question parser. Every time we
     * open a project, we have to set the facade property of the questionParser.
     */
    private QuestionParser            questionParser;
    private ResourceBundle            resourceBundle;
    /**
     * Call superclass constructor; populates voClassToViews, reset viewsToRefreshBitmap, load RF
     * configurations.
     * 
     * @param descriptor object passed by the platform when it loads/creates the plugin
     */
    public ArchEUIPlugin(IPluginDescriptor descriptor) {
        super(descriptor);
        plugin = this;
		try {
			resourceBundle= ResourceBundle.getBundle("edu.cmu.sei.ArchEUIPluginResources");
//			System.out.println("+" + resourceBundle.toString() + "-");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}

        this.questionParser = new QuestionParser();

        
        viewsToRefreshBitmap = 0;
        //
        // Populate the voClassToViews hashtable
        //
        voClassToViews = new Hashtable();
        voClassToViews.put(ScenarioVO.class, new Integer(SCENARIOS | SCENARIO_RESP_MAPPING | TRAFFIC_LIGHT));
        voClassToViews.put(ScenarioPartVO.class, new Integer(SCENARIOS | TRAFFIC_LIGHT));
        voClassToViews.put(FunctionVO.class, new Integer(FUNCTIONS | FUNCTION_RESP_MAPPING));
        voClassToViews.put(ScenarioResponsibilityMapVO.class, new Integer(SCENARIO_RESP_MAPPING));
        voClassToViews.put(FunctionResponsibilityMapVO.class, new Integer(FUNCTION_RESP_MAPPING
                | RELATIONSHIPS));
        voClassToViews.put(ResponsibilityVO.class, new Integer(RESPONSIBILITIES
                | SCENARIO_RESP_MAPPING | FUNCTION_RESP_MAPPING | RELATIONSHIPS | DESIGN_TREE));
        voClassToViews.put(ParameterVO.class, new Integer(RESPONSIBILITIES | RELATIONSHIPS));
        voClassToViews.put(RelationshipVO.class, new Integer(RELATIONSHIPS));
        voClassToViews.put(AnalysisResultVO.class, new Integer(SCENARIOS | TRAFFIC_LIGHT));
        voClassToViews.put(QuestionToUserVO.class, new Integer(QUESTIONS | RESPONSIBILITIES
                | SCENARIOS | FUNCTIONS | RELATIONSHIPS | MODEL_ELEMENTS | MODEL_RELATIONS
                | DESIGN_ELEMENTS | DESIGN_RELATIONS | TRAFFIC_LIGHT | EVALUATION_RESULT));
        voClassToViews.put(ModelElementVO.class, new Integer(MODEL_ELEMENTS | MODEL_RELATIONS));
        voClassToViews.put(ModelRelationVO.class, new Integer(MODEL_RELATIONS));
        voClassToViews.put(ViewElementVO.class, new Integer(DESIGN_ELEMENTS | DESIGN_RELATIONS | DESIGN_TREE));
        voClassToViews.put(ViewRelationVO.class, new Integer(DESIGN_RELATIONS | DESIGN_TREE));
        voClassToViews.put(EvaluationResultVO.class, new Integer(EVALUATION_RESULT));
        voClassToViews.put(ReasoningFrameworkVO.class, new Integer(SCENARIOS | RESPONSIBILITIES | RELATIONSHIPS | MODEL_ELEMENTS | MODEL_RELATIONS | DESIGN_ELEMENTS | DESIGN_RELATIONS));        
       
        try {
            availableRFs = RFLoader.loadReasoningFrameworks(getPreferenceStore());
        } catch (Exception e) {
            // TODO: probably it could add a problem to the problems view here (when problems view
            // is used in subsequent versions).
            throw new RuntimeException("Could not load reasoning framework plug-ins.", e);
        }

        try {
            availableDFs = new Hashtable();
            availableTRs = new Hashtable();
            loadDesignFilters();
        } catch (Exception e) {
            // TODO: probably it could add a problem to the problems view here (when problems view
            // is used in subsequent versions).
            throw new RuntimeException("Could not load Design Filters configuration.", e);
        }

        int eventFilter = IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE
                | IResourceChangeEvent.POST_BUILD;
        getWorkspace().addResourceChangeListener(this, eventFilter);

        prevOpenProject = getOpenProject();

        PlatformUI.getWorkbench().addWindowListener(this);
    }

    /**
     * This method is called by many other classes in the plug-in to access the plugin instance.
     * 
     * @return the shared instance.
     */
    public static ArchEUIPlugin getDefault() {
        return plugin;
    }

    /**
     * Just calls ResourcesPlugin.getWorkspace()
     * 
     * @return the workspace instance
     */
    public static IWorkspace getWorkspace() {
        return ResourcesPlugin.getWorkspace();
    }

    public void startup() throws CoreException {
        super.startup();
        this.getPreferenceStore().addPropertyChangeListener(this);

    }

    public void shutdown() throws CoreException {
        this.getPreferenceStore().removePropertyChangeListener(this);
        super.shutdown();
    }

    /**
     * A property in the ArchE user preferences has changed. Here we pay heed to the property that
     * defines the RFs that are active. If an RF was activated/deactivated, we updated that
     * information in ProjectVO of the currently opened project and then refresh the views. Note
     * that we make the RF id the id of the preference property itself.
     * 
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        boolean needsRefresh = false;
        String propertyName = event.getProperty();
        IPreferenceStore prefStore = getPreferenceStore();
        for (Enumeration rfs = availableRFs.keys(); rfs.hasMoreElements();) {
            ReasoningFrameworkVO rf = (ReasoningFrameworkVO) rfs.nextElement();
            if (propertyName.equals(rf.getId())) {
                // The user activated/deactivated an RF indeed
                ProjectVO project = getProjectVo();
                if (project != null) {
                    project.getActiveRFs().put(rf, event.getNewValue());
                    needsRefresh = true;
                }
            }
        }
        if (needsRefresh) {
            refreshAllViews();
        }
    }

    /**
     * Eclipse by default allows several projects to be open at the same time. In ArchE that can
     * cause problems. Therefore, as an invariant, ONLY ONE PROJECT CAN BE OPEN AT THE SAME TIME.
     * This method returns the one project that is open now.
     * 
     * @return project that is open or null if there's no open project at the moment.
     */
    public IProject getOpenProject() {
        IProject projects[] = getWorkspace().getRoot().getProjects();
        for (int i = 0; i < projects.length; i++) {
            try {
                if (projects[i].isOpen() && projects[i].hasNature("SEI.ArchE.UI.ArchENature")) {
                    return projects[i];
                }
            } catch (CoreException e) {
                // Won't happen. Would only happen if project didn't exist or
                // was not open.
            }
        }
        return null;
    }

    /**
     * There's at most one open project. This method returns the ProjectVO object associated with
     * that project.
     * 
     * @return ProjectVO object of the currently open project or null if there's no open project or
     *         a problem occured.
     */
    public ProjectVO getProjectVo() {
        IProject project = getOpenProject();
        if (project != null) {
            try {
                ProjectVO projectVo = (ProjectVO) project.getSessionProperty(PROJECTVO);
                if (projectVo == null) {
                    // Probably it's launching ArchE and this project was opened
                    // in the last
                    // session, but the session property is null because it's
                    // volatile. We have
                    // to initialize the project.
                    initProject(project);
                    projectVo = (ProjectVO) project.getSessionProperty(PROJECTVO);
                    refreshAllViews();
                }
                return projectVo;
            } catch (Exception e) {
                // Could not initialize project for some reason. As a
                // precaution, try to shutdown
                // project (i.e. shutdown core) and close workspace project. If
                // another exception
                // occurs, don't bother because we'll propagate the original
                // exception to Eclipse
                // anyway.
                try {
                    shutdownProject(project);
                } catch (Exception e2) {
                }
                try {
                    project.close(null);
                } catch (Exception e3) {
                }
                throw new RuntimeException(e.getMessage(), e);
            }
        } else {
            return null;
        }
    }

    /**
     * There's at most one open project. This method returns the ArchEFacade object associated with
     * that project.
     * 
     * @return ArchEFacade object of the currently open project or null if there's no open project
     *         or a problem occured.
     */
    public ArchEFacade getArchEFacade() {
        IProject project = getOpenProject();
        ArchEFacade facade = null;
        if (project != null) {
            try {
                facade = (ArchEFacade) project.getSessionProperty(FACADE);
                if (facade == null) {
                    // Probably it's launching ArchE and this project was opened
                    // in the last
                    // session, but the session property is null because it's
                    // volatile. We have
                    // to initialize the project.
                    initProject(project);
                    facade = (ArchEFacade) project.getSessionProperty(FACADE);
                    refreshAllViews();
                }
            } catch (Exception e) {
                // Could not initialize project for some reason. As a
                // precaution, try to shutdown
                // project (i.e. shutdown core) and close workspace project. If
                // another exception
                // occurs, don't bother because we'll propagate the original
                // exception to Eclipse
                // anyway.
                try {
                    shutdownProject(project);
                } catch (Exception e2) {
                }
                try {
                    project.close(null);
                } catch (Exception e3) {
                }
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return facade;
    }

    /**
     * Set the views to be refreshed looking up the specified class in voClassToViews and OR'ing the
     * result with the current value in viewsToRefreshBitmap;
     * 
     * @param clazz VO class that changed and is requiring the views to be flagged for refresh.
     * @see edu.cmu.sei.arche.corebridge.IRefreshableUI#flagViewsToRefresh(java.lang.Class)
     */
    public void flagViewsToRefresh(Class clazz) {
        viewsToRefreshBitmap |= ((Integer) voClassToViews.get(clazz)).intValue();
    }

    /**
     * For each view, check if it needs refresh and if so call refresh on it. This method may be
     * called in a parallel thread (not the UI thread). Therefore, we refresh views calling
     * Display.syncExec(), which will permit the refresh calls to be executed by the UI thread
     * whenever possible, while this method is blocked waiting until the UI thread does it.
     * 
     * @see edu.cmu.sei.arche.corebridge.IRefreshableUI#refreshViews()
     */
    public void refreshViews() {
    	
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                IWorkbenchWindow wbw = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (wbw != null) {
                    try {
                        IWorkbenchPage activePage = wbw.getActivePage();
                        if (activePage != null) {
                            // Check if Scenarios view needs to be refreshed
                            if ((viewsToRefreshBitmap & SCENARIOS) > 0) {
                                ScenariosView view = (ScenariosView) activePage
                                        .findView("SEI.ArchE.UI.ScenariosView");
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            
//                          Check if Scenarios view needs to be refreshed
                            if ((viewsToRefreshBitmap & TRAFFIC_LIGHT) > 0) {
                                TrafficLightView view = (TrafficLightView) activePage
                                        .findView("SEI.ArchE.UI.TrafficLightView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            // Check if Functions view needs to be refreshed
                            if ((viewsToRefreshBitmap & FUNCTIONS) > 0) {
                                FunctionsView view = (FunctionsView) activePage
                                        .findView("SEI.ArchE.UI.FunctionsView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            // Check if Responsibilities view needs to be
                            // refreshed
                            if ((viewsToRefreshBitmap & RESPONSIBILITIES) > 0) {
                                ResponsibilitiesView view = (ResponsibilitiesView) activePage
                                        .findView("SEI.ArchE.UI.ResponsibilitiesView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            // Check if Relationships view needs to be refreshed
                            if ((viewsToRefreshBitmap & RELATIONSHIPS) > 0) {
                                RelationshipsView view = (RelationshipsView) activePage
                                        .findView("SEI.ArchE.UI.RelationshipsView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            // Check if ScenarioRespMapping view needs to be
                            // refreshed
                            if ((viewsToRefreshBitmap & SCENARIO_RESP_MAPPING) > 0) {
                                ScenarioRespMappingView view = (ScenarioRespMappingView) activePage
                                        .findView("SEI.ArchE.UI.ScenarioRespMappingView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }

                            // Check if FunctionRespMapping view needs to be
                            // refreshed
                            if ((viewsToRefreshBitmap & FUNCTION_RESP_MAPPING) > 0) {
                                FunctionRespMappingView view = (FunctionRespMappingView) activePage
                                        .findView("SEI.ArchE.UI.FunctionRespMappingView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }

                            // Check if Navigator view needs to be refreshed
                            if ((viewsToRefreshBitmap & NAVIGATOR) > 0) {
                                ResourceNavigator view = (ResourceNavigator) activePage
                                        .findView("org.eclipse.ui.views.ResourceNavigator");
                                
                                if (view != null) {
                                    view.getViewer().refresh();
                                }
                            }
                            if ((viewsToRefreshBitmap & JESS_CONSOLE) > 0) {
                                JessConsole view = (JessConsole) activePage
                                        .findView("SEI.ArchE.UI.JessConsole");
                                if (view != null) {
                                    view.hookUpToJess();
                                }
                            }
                              // TODO: findView currently returns an ErrorViewPart
                              // which cannot be parsed. 
//                            // Check if Questions view needs to be refreshed
//                            if ((viewsToRefreshBitmap & QUESTIONS) > 0) {
//                                QuestionsView view = (QuestionsView) activePage
//                                        .findView("SEI.ArchE.UI.QuestionsView");
//                                if (view != null) {
//                                    view.setInputAndRefresh();
//                                }
//                            }
                            // Check if ModelElementsView needs to be refreshed
                            if ((viewsToRefreshBitmap & MODEL_ELEMENTS) > 0) {
                                ModelElementsView view = (ModelElementsView) activePage
                                        .findView("SEI.ArchE.UI.ModelElementsView");
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            // Check if ModelRelationsView needs to be refreshed
                            if ((viewsToRefreshBitmap & MODEL_RELATIONS) > 0) {
                                ModelRelationsView view = (ModelRelationsView) activePage
                                        .findView("SEI.ArchE.UI.ModelRelationsView");
                                
                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }

                            // Check if DesignElementsView needs to be refreshed
                            if ((viewsToRefreshBitmap & DESIGN_ELEMENTS) > 0) {
                                DesignElementsView view = (DesignElementsView) activePage
                                        .findView("SEI.ArchE.UI.DesignElementsView");

                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }

                            // Check if DesignRelationsView needs to be
                            // refreshed
                            if ((viewsToRefreshBitmap & DESIGN_RELATIONS) > 0) {
                                DesignRelationsView view = (DesignRelationsView) activePage
                                        .findView("SEI.ArchE.UI.DesignRelationsView");

                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }
                            
                            // Check if DesignRelationsView needs to be
                            // refreshed
                            if ((viewsToRefreshBitmap & DESIGN_TREE) > 0) {
                                DesignTreeView view = (DesignTreeView) activePage
                                        .findView("SEI.ArchE.UI.DesignTreeView");

                                if (view != null) {
                                    view.refresh();
                                }
                            }
                            // Check if EvaluationResult view needs to be
                            // refreshed
                            if ((viewsToRefreshBitmap & EVALUATION_RESULT) > 0) {
                                EvaluationResultsView view = (EvaluationResultsView) activePage
                                        .findView("SEI.ArchE.UI.EvaluationResultsView");

                                if (view != null) {
                                    view.setInputAndRefresh();
                                }
                            }                            
                        }
                    } catch (Exception e) {
                        // A problem occurred while refreshing a view. Likely
                        // not all views were
                        // properly refreshed. Notify user.
                        MessageDialog
                                .openError(null, "Error",
                                           "A problem occurred while refreshing the views.\n"
                                                   + "Please close the project and open it again." );
                        e.printStackTrace();                        
                        throw new RuntimeException("Error while refreshing ArchE views", e);
                    }
                }
                // reset bitmap at the end
                viewsToRefreshBitmap = 0;
            }
        });
    }

    /**
     * Call the Design (config) reader that reads the design configuration file and makes available
     * the set of available DF data is then stored in availableDFs
     */
    private void loadDesignFilters() throws SAXException, IOException, ArchEException {

        availableDFs = DesignReader.parse();
        availableTRs = TreeReader.parse();

    }

    /**
     * @return Returns the Hashtable (ReasoningFrameworkVO --> Boolean) with all RFs loaded from all
     *         RF plugins found, The Boolean value is true if that RF is active (user preference).
     */
    public Hashtable getAvailableRFs() {
        return availableRFs;
    }

    /**
     * A project has just been opened; it can be a newly created project or a project that was
     * closed. This method is called to initialize some volatile objects (session properties) that
     * are associated with the project. The project is represented as an IProject object. Will
     * associate with it a ProjectVO object and an ArchEFacade object. Will also startup Rete.
     * 
     * @param project
     * @param activeRFs
     * @throws CoreException in case Eclipse can't set a session property
     */
    private void initProject(IProject project) throws CoreException, ArchEException, IOException {
        ProjectVO projectVo = new ProjectVO(project.getName(), getAvailableRFs());
        ArchEFacade facade = new ArchEFacade(questionParser);
        project.setSessionProperty(PROJECTVO, projectVo);
        project.setSessionProperty(FACADE, facade);
        questionParser.setFacade(facade);

		URL libPluginURL = FileLocator.find(Platform.getBundle("SEI.ArchE.Lib"), new Path("/"), null);
        facade.setBaseLocation(FileLocator.resolve(libPluginURL).getPath());

        facade.startupArchE(project.getLocation(), this, projectVo, getDesignElementTypes("all"),
                getDesignRelationTypes("all"), getTrees("all"));
    }

    
    public void restartArchE(){
    	try {    		
    		availableRFs.clear();
    		availableTRs.clear();
        	IProject project = this.getOpenProject();
        	ProjectVO projectVo = (ProjectVO)project.getSessionProperty(PROJECTVO);
        	projectVo.getDesignElements().clear();
        	projectVo.getDesignRelations().clear();
        	projectVo.getEvaluationResult().clear();
        	projectVo.getFunctionResps().clear();
        	projectVo.getFunctions().clear();
        	projectVo.getModelElements().clear();
        	projectVo.getModelRelations().clear();
        	projectVo.getQuestions().clear();
        	projectVo.getRelationships().clear();
        	projectVo.getResponsibilities().clear();
        	projectVo.getScenarioResps().clear();
        	projectVo.getScenarios().clear();
        	projectVo.getTrees().clear();
        	projectVo.getActiveRFs().clear();
        	
        	ArchEFacade facade = (ArchEFacade)project.getSessionProperty(FACADE);
        	
            facade.startupArchE(project.getLocation(), this, projectVo, /* DesignElementTypes */ null,
                    /* DesignRelationTypes */ null, getTrees("all"));
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ArchEException e) {
			e.printStackTrace();
		}
    }
    /**
     * Calls facade to shutdown project (i.e. shutdown core for this project). Method is public
     * because it's used in JUnit PDE tests.
     * 
     * @param project
     * @throws CoreException Eclipse couldn't find the facade property
     * @throws ArchEException Trying to shutdown a project that was not open
     */
    public void shutdownProject(IProject project) throws CoreException, ArchEException {
        ArchEFacade facade = (ArchEFacade) project.getSessionProperty(FACADE);
        if (facade != null) {
            facade.shutdownArchE();
        }
    }

    /**
     * We want to know when a project is opened or closed so that we can refresh the views
     * accordingly. When a project is created, it's openned as well. So, all we need to track is
     * when projects are opened/closed:
     * <ul>
     * <li>If a project is opened, the previously opened project has to be closed. We need to
     * initialize the project data (facade, ProjectVO, active RFs). Views are asked to refresh so
     * that they can get data from the newly opened project.
     * <li>If a project is closed, we have to refresh views so that they do not show data from the
     * previous project. Also, we have to release resources such as ProjectVO and shutdown the
     * facade.
     * </ul>
     * NB: The event does not indicate what project is being openned (event.getResource() is null
     * for some unknown reason), so we have to look for the open project in the workspace.
     * <p>
     * NB2: We have to listen to POST_AUTO_BUILD because during a POST_CHANGE the workspace is
     * locked for change. Although we don't perform any "project build" in ArchE projects, the event
     * still fires. By the way, the POST_AUTO_BUILD event fires even if "Perform build automatically
     * on resource modification" is unchecked on the Workbench preferences. (Thank God!) NB3: We
     * have to listen to PRE_CLOSE to identify close project operations before they are concluded.
     * In this cases, we cannot refresh views, but we can shutdown the facade.
     * 
     * @throws CoreException
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    public void resourceChanged(IResourceChangeEvent event) {
        int eventType = event.getType();
        boolean needRefresh = false;

        // We catch PRE_CLOSE and PRE_DELETE of a project to shutdown ArchE Core for that project.
        // If it's PRE_CLOSE, we surely know we need to shutdown the project (because it was open
        // and because Eclipse doesn't let you close a closed project. If it's PRE_DELETE, then the
        // deleted project could be opened or closed. If it were the previously opened project, then
        // we know it was opened and we need to shutdown.
        // Also, we ignore the event in case the project is not an ArchE project.
        if (event.getResource() != null && event.getResource() instanceof IProject) {
            final IProject proj = (IProject) event.getResource();
            boolean isArchEProject = false;
            try {
                isArchEProject = proj.hasNature("SEI.ArchE.UI.ArchENature");
            } catch (CoreException e) {
                // do nothing. It could happen if the project didn't exist or were close and that's
                // not the case. Anyway, if it fails, we jus leave the boolean var as false
            }
            if (isArchEProject && eventType == IResourceChangeEvent.PRE_CLOSE
                    || (eventType == IResourceChangeEvent.PRE_DELETE && proj == prevOpenProject)) {
                // Project is being closed or deleted
                try {
                    shutdownProject(proj);
                } catch (Exception e) {
                    // An error occured while closing ArchE core for this
                    // project. There's nothing
                    // special we can do. We don't propagate the exception to
                    // Eclipse because it
                    // could halt the closing operation. We alert the user
                    // though.
                    Display.getDefault().syncExec(new Runnable() {

                        public void run() {
                            MessageDialog
                                    .openError(
                                               null,
                                               "Error",
                                               "An error occurred while closing project "
                                                       + proj.getName()
                                                       + ". The project was closed anyway as a precaution.");
                        }
                    });
                    e.printStackTrace(); // for debugging only
                }
           }
        }

        // We also catch POST_BUILD which is invoked (after the fact) after any resource
        // change--after opening, closing, deleting, renaming a project. One drawback of POST_BUILD
        // is that (surprisingly) event.getResource() is null. So we need to loop over all projects
        // in the working space to find out what's going on based on prevOpenProject and what
        // project is open now.
        if (event.getSource() instanceof IWorkspace && eventType == IResourceChangeEvent.POST_BUILD) {
            IProject projects[] = getWorkspace().getRoot().getProjects();
            boolean foundOpenProject = false;
            for (int i = 0; i < projects.length; i++) {
                IProject proj = projects[i];
                boolean isArchEProject = false;
                try {
                    isArchEProject = proj.hasNature("SEI.ArchE.UI.ArchENature");
                } catch (CoreException e) {
                    // do nothing. It can happen if the project doesn't exist or is closed.
                    // not the case. Anyway, if it fails, we jus leave the boolean var as false
                }
                foundOpenProject = isArchEProject && proj.isOpen() || foundOpenProject;
                if (isArchEProject && proj.isOpen() && proj != prevOpenProject) {
                    // Found a project that is opened but is not the one that was opened before
                    try {
                        if (prevOpenProject != null) {
                            // Close previous project --> this will cause a new ResourceChangeEvent
                            // to be raised and reenter this method with PRE_CLOSE, and then
                            // it will call shutdownProject()
                            prevOpenProject.close(null);
                        }
                        // Initialize newly opened project
                        initProject(proj);
                        // Workspace management note: When a project is opened we want ArchEUIPlugin
                        // to be notified. However, if nothing in the project (at the workspace
                        // level has changed), Eclipse does NOT notify registered listeners (this
                        // is an optimization in NotificationManager.broadcastEvents()). Therefore,
                        // to make sure the POST_BUILD notification is always sent, we 'touch' the
                        // project that has just been opened.
                        proj.touch(null);
                        prevOpenProject = proj;
                        needRefresh = true;
                    } catch (Exception e) {
                        // Could not initialize close previous project or initialize new opened
                        // project. The previous project is closed anyway. As a precaution, try to
                        // shutdown and close the newly opened project (proj). If another exception
                        // occurs, don't bother because we'll propagate the original exception to
                        // Eclipse anyway.
                        try {
                            shutdownProject(proj);
                        } catch (Exception e2) {
                        }
                        try {
                            proj.close(null);
                        } catch (Exception e3) {
                        }
                        prevOpenProject = null;
                        throw new RuntimeException(e.getMessage(), e);
                    }
                } else if (proj == prevOpenProject && !proj.isOpen()) {
                    // Project was closed
                    needRefresh = true;
                	prevOpenProject = null;
                }
            }
            if (!foundOpenProject) {
                // There's no open projects in the Navigator or (no projects at all)
                // Conclusion: a project was deleted. If it were opened, it was shutdown on
                // PRE_DELETE. Now we just reset the prevOpenProject variable and refresh views.
                needRefresh = prevOpenProject != null || needRefresh;
                prevOpenProject = null;
            }
            // finally we refresh the views
            if (needRefresh) 
            	refreshAllViews();
        }
    }

	/**
     * Remove resource listener.
     * 
     * @throws CoreException
     */
    public void shutDown() throws CoreException {
        super.shutdown();
        getWorkspace().removeResourceChangeListener(this);
    }

    /**
     * Set bitmap to refresh all existing views and calls refreshViews().
     */
    private void refreshAllViews() {
        viewsToRefreshBitmap = 0xFFFFFFFF; // in binary: 111...1
        refreshViews();
    }

    /**
     * @return Returns the questionParser.
     */
    public QuestionParser getQuestionParser() {
        return questionParser;
    }

    /**
     * Reload Questions.properties    
     */
	public void refreshQuestions(InputStream stream) throws IOException {
		this.questionParser.updateDynamicQuestions(stream);
	}
    
    /**
     * Initialize default user preferences. Default is set only when the preferences store is
     * initialized for the first time.
     * 
     * @see org.eclipse.core.runtime.Plugin#initializeDefaultPluginPreferences()
     */
    protected void initializeDefaultPluginPreferences() {
        IPreferenceStore store = getPreferenceStore();
        PreferenceConverter.setDefault(store, "src_ArchE_color", new RGB(0, 157, 0));
        PreferenceConverter.setDefault(store, "jess_console_color", new RGB(0, 0, 255));
        // there is also one preference for each RF (value indicates if it's
        // active or not)
        // but default for these RF active preferences are set after we load the
        // RFs available
    }

    /**
     * @return Returns the availableDFs, a Hashtable that maps DesignFilterVO objects to Boolean
     *         objects. It shall contain as keys all DesignFilterVOs that the DesignReader found as
     *         available from the designconfig.xml file. The Boolean value is set to true. It is
     *         however dertermined by the user preferences set for the eclipse workbench and is true
     *         if that design filter is active for and false otherwise.
     */
    public Hashtable getAvailableDFs() {
        return availableDFs;
    }
    
    public Hashtable getAvailableTRs() {
        return availableTRs;
    }

    /**
     * Retrieves the designElementTypes
     * 
     * @param nature String that tells this method to return all designElementTypes for "all"
     *            available DesignFilters or the ones that are valid for the Design Filter(s) set to
     *            "active" for this project.
     * @return designElementTypes the designElementTypes
     */
    public List getDesignElementTypes(String nature) {
        List designElementTypes = new ArrayList();

        // For predefined design elements
        for (Enumeration e = this.availableDFs.keys(); e.hasMoreElements();) {
            DesignFilterVO df = (DesignFilterVO) e.nextElement();

            if (nature.equalsIgnoreCase("active")) {
                // If the design filter is disabled, continue next loop.
                if (!((Boolean) this.availableDFs.get(df)).booleanValue()) {
                    continue;
                }
            }

            for (Iterator it = df.getDesignElementTypes().iterator(); it.hasNext();) {
                ViewElementTypeVO designElementType = (ViewElementTypeVO) it.next();
                if (!(designElementTypes.contains(designElementType))) {
                    designElementTypes.add(designElementType);
                }
            }
        }
        
        // For design elements defined by external reasoning frameworks
        for (Enumeration e = this.availableRFs.keys(); e.hasMoreElements();) {
        	ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();

            if (nature.equalsIgnoreCase("active")) {
                // If the reasoning framework is disabled, continue next loop.
                if (!((Boolean) this.availableRFs.get(rf)).booleanValue()) {
                    continue;
                }
            }

            for (Iterator it = rf.getViewElementTypes().iterator(); it.hasNext();) {
                ViewElementTypeVO designElementType = (ViewElementTypeVO) it.next();
                if (!(designElementTypes.contains(designElementType))) {
                    designElementTypes.add(designElementType);
                }
            }
        }
        return designElementTypes;
    }

    /**
     * @return Returns the designElements that are valid for the Design Filter(s) set to "active"
     *         for this project.
     */
    public HashSet getActiveDesignElements() {
        HashSet<String> activeDesignElementTypes = new HashSet<String>();
        HashSet<ViewElementVO> activeDesignElements = new HashSet<ViewElementVO>();

        for (Enumeration e = this.availableDFs.keys(); e.hasMoreElements();) {
            DesignFilterVO df = (DesignFilterVO) e.nextElement();
            // If the design filter is disabled, continue next loop.
            if (!((Boolean) this.availableDFs.get(df)).booleanValue()) {
                continue;
            }
            for(Iterator<ViewElementTypeVO> it=df.getDesignElementTypes().iterator(); it.hasNext(); ){
            	ViewElementTypeVO item = it.next();
                activeDesignElementTypes.add(item.getFactType());
            }
        }
        
        for (Enumeration<ReasoningFrameworkVO> e = this.availableRFs.keys(); e.hasMoreElements();) {
        	ReasoningFrameworkVO rf = e.nextElement();
            // If the ReasoningFramework is disabled, continue next loop.
            if (!((Boolean) this.availableRFs.get(rf)).booleanValue()) {
                continue;
            }
            for(Iterator<ViewElementTypeVO> it=rf.getViewElementTypes().iterator(); it.hasNext(); ){
            	ViewElementTypeVO item = it.next();
                activeDesignElementTypes.add(item.getFactType());
            }
        }
        
        for (Iterator<ViewElementVO> it = getProjectVo().getDesignElements().iterator(); it.hasNext();) {
            ViewElementVO designElement = it.next();
            if (activeDesignElementTypes.contains(designElement.getType().getFactType())) {
                activeDesignElements.add(designElement);
            }
        }
        return activeDesignElements;
    }

    /**
     * Retrieves the designRelationTypes
     * 
     * @param nature String that tells this method to return all designRelationTypes for "all"
     *            available DesignFilters or the ones that are valid for the Design Filter(s) set to
     *            "active" for this project.
     * @return designRelationTypes the designRelationTypes
     */
    public List getDesignRelationTypes(String nature) {
        List designRelationTypes = new ArrayList();

        for (Enumeration e = this.availableDFs.keys(); e.hasMoreElements();) {
            DesignFilterVO df = (DesignFilterVO) e.nextElement();

            if (nature.equalsIgnoreCase("active")) {
                // If the design filter is disabled, continue next loop.
                if (!((Boolean) this.availableDFs.get(df)).booleanValue()) {
                    continue;
                }
            }

            for (Iterator it = df.getDesignRelationTypes().iterator(); it.hasNext();) {
                ViewRelationTypeVO designRelationType = (ViewRelationTypeVO) it.next();
                if (!(designRelationTypes.contains(designRelationType))) {
                    designRelationTypes.add(designRelationType);
                }
            }
        }
        
        for (Enumeration e = this.availableRFs.keys(); e.hasMoreElements();) {
        	ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();

            if (nature.equalsIgnoreCase("active")) {
                // If the reasoning framework is disabled, continue next loop.
                if (!((Boolean) this.availableRFs.get(rf)).booleanValue()) {
                    continue;
                }
            }

            for (Iterator it = rf.getViewRelationTypes().iterator(); it.hasNext();) {
                ViewRelationTypeVO designRelationType = (ViewRelationTypeVO) it.next();
                if (!(designRelationTypes.contains(designRelationType))) {
                    designRelationTypes.add(designRelationType);
                }
            }
        }
        return designRelationTypes;
    }

    /**
     * @return Returns the designRelations that are valid for the Design Filter(s) set to "active"
     *         for this project.
     */
    public HashSet getActiveDesignRelations() {
        HashSet<String> activeDesignRelationTypes = new HashSet<String>();
        HashSet<ViewRelationVO> activeDesignRelations = new HashSet<ViewRelationVO>();

        for (Enumeration e = this.availableDFs.keys(); e.hasMoreElements();) {
            DesignFilterVO df = (DesignFilterVO) e.nextElement();
            // If the design filter is disabled, continue next loop.
            if (!((Boolean) this.availableDFs.get(df)).booleanValue()) {
                continue;
            }
            for(Iterator<ViewRelationTypeVO> it=df.getDesignRelationTypes().iterator(); it.hasNext(); ){
            	ViewRelationTypeVO item = it.next();
            	activeDesignRelationTypes.add(item.getFactType());
            }
//            activeDesignRelationTypes.addAll(df.getDesignRelationTypes());
        }
        
        for (Enumeration<ReasoningFrameworkVO> e = this.availableRFs.keys(); e.hasMoreElements();) {
        	ReasoningFrameworkVO rf = (ReasoningFrameworkVO) e.nextElement();
            // If the ReasoningFramework is disabled, continue next loop.
            if (!((Boolean) this.availableRFs.get(rf)).booleanValue()) {
                continue;
            }
            for(Iterator<ViewRelationTypeVO> it=rf.getViewRelationTypes().iterator(); it.hasNext(); ){
            	ViewRelationTypeVO item = it.next();
            	activeDesignRelationTypes.add(item.getFactType());
            }
        }
        
        for (Iterator<ViewRelationVO> it = getProjectVo().getDesignRelations().iterator(); it.hasNext();) {
            ViewRelationVO designRelation = (ViewRelationVO) it.next();
            if (activeDesignRelationTypes.contains(designRelation.getType().getFactType())) {
                activeDesignRelations.add(designRelation);
            }
        }
        return activeDesignRelations;
    }
    
    /**
     * Retrieves the trees
     * 
     * @param nature String that tells this method to return all trees for "all"
     *            available trees or the ones that are valid for the Design Filter(s) set to
     *            "active" for this project.
     * @return trees the trees
     */
    public List getTrees(String nature) {
        List trees = new ArrayList();

        for (Enumeration e = this.availableTRs.keys(); e.hasMoreElements();) {
            TreeVO tr = (TreeVO) e.nextElement();

            if (nature.equalsIgnoreCase("active")) {
                // If the design filter is disabled, continue next loop.
                if (!((Boolean) this.availableTRs.get(tr)).booleanValue()) {
                    continue;
                }
            }
        }
        return trees;
    }

    
	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle= ArchEUIPlugin.getDefault().getResourceBundle();
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	public static ImageDescriptor getImageDescriptor(String name) {
		String iconPath = "icons/designview/";
		try {
			URL installURL = getDefault().getDescriptor().getInstallURL();
			URL url = new URL(installURL, iconPath + name);
			return ImageDescriptor.createFromURL(url);
		} catch (MalformedURLException e) {
			// should not happen
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}
	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

    /**
     * @see org.eclipse.ui.IWindowListener#windowActivated(org.eclipse.ui.IWorkbenchWindow)
     */
    public void windowActivated(IWorkbenchWindow window) {
    }

    /**
     * @see org.eclipse.ui.IWindowListener#windowDeactivated(org.eclipse.ui.IWorkbenchWindow)
     */
    public void windowDeactivated(IWorkbenchWindow window) {
    }

    /**
     * Eclipse is being closed. If there's an ArchE project opened, persist fact base.
     * 
     * @see org.eclipse.ui.IWindowListener#windowClosed(org.eclipse.ui.IWorkbenchWindow)
     */
    public void windowClosed(IWorkbenchWindow window) {
//        (new PersistFactBaseAction()).run(null);
    }

    /**
     * @see org.eclipse.ui.IWindowListener#windowOpened(org.eclipse.ui.IWorkbenchWindow)
     */
    public void windowOpened(IWorkbenchWindow window) {
    }

}