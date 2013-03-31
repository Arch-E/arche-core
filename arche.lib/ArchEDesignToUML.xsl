<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xmi" encoding = "ISO-8859-1"/>

<xsl:variable name="module" select="ArchE//Module"/>
<xsl:variable name="responsibilities" select="ArchE//Responsibilities"/>
<xsl:variable name="moduleRefinement" select="ArchE//ModuleRefinementRelation"/>
<xsl:variable name="moduleDependency" select="ArchE//ModuleDependencyRelation"/>
<xsl:variable name="moduleInterface" select="ArchE//ModuleInterface"/>
<xsl:variable name="responsibilityToModule" select="ArchE//ResponsibilityToModuleRelation"/>
<xsl:variable name="responsibilityRefinement" select="ArchE//ResponsibilityRefinementRelation"/>
<xsl:variable name="responsibilityToResponsibility" select="ArchE//ResponsibilityToResponsibilityRelation"/>
<xsl:variable name="realize" select="ArchE//RealizeRelation"/>
<xsl:variable name="sequenceRelation" select="ArchE//SequenceRelation"/>
<xsl:variable name="p_CostOfChange" select="ArchE//P_CostOfChange"/>
<xsl:variable name="p_ExecutionTime" select="ArchE//P_ExecutionTime"/>
<xsl:variable name="p_ProbabilityOutgoing" select="ArchE//P_ProbabilityOutgoing"/>
<xsl:variable name="p_ProbabilityIncoming" select="ArchE//P_ProbabilityIncoming"/>
<xsl:variable name="task" select="ArchE//Task"/>
<xsl:variable name="subTask" select="ArchE//SubTask"/>

<xsl:template match="/">	
	<XMI xmi.version = '1.1' xmlns:UML='href://org.omg/UML/1.3' timestamp = 'Tue Mar 09 11:38:29 2004' >
	<xsl:text>
	</xsl:text> 
	<XMI.header>
	<xsl:text>
	</xsl:text>  
	<XMI.documentation>
	<xsl:text>
	</xsl:text>   
	<XMI.exporter>Unisys.JCR.2</XMI.exporter>
	<xsl:text>
	</xsl:text>    
	<XMI.exporterVersion>1.3.6</XMI.exporterVersion>
	<xsl:text>
	</xsl:text>    
	</XMI.documentation>
	<xsl:text>
	</xsl:text>   
	<XMI.metamodel xmi.name = 'UML' xmi.version = '1.3'/>
	<xsl:text>
	</xsl:text>   
	</XMI.header>
	<xsl:text>
	</xsl:text>  
	<XMI.content> 
	<xsl:text>
	</xsl:text> 	
	<UML:Model xmi.id="G.0" name="ArchEDesign" visibility="public" isSpecification="false" isRoot="false" isLeaf="false" isAbstract="false">  	
    <xsl:text>
    </xsl:text>       
	<UML:Namespace.ownedElement> 
    <xsl:text>
    </xsl:text>               
    <xsl:for-each select="ArchE/Module">
          <xsl:call-template name="moduleTemplate">  
            <xsl:with-param name="factID" select="@factId"/>
          </xsl:call-template>
    </xsl:for-each>                   
	<xsl:text>
	</xsl:text> 	       
    <UML:Package name = 'Responsibilities' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf ='false' isAbstract = 'false' namespace ='G.0' xmi.id='R.0'>
    <xsl:text>
    </xsl:text> 	       
    <UML:Namespace.ownedElement> 
    <xsl:text>
    </xsl:text> 

    <xsl:for-each select="$responsibilities">
        <xsl:variable name="factID" select="@factId"/>
        <xsl:variable name="name" select="name"/>		       
        <xsl:if test="count($responsibilityToModule[child=$factID])=0">
		   	<xsl:call-template name="responsibilityTemplate">
	    	    <xsl:with-param name="child" select="$factID"/>
 	      	</xsl:call-template>			          
		</xsl:if>	
   </xsl:for-each>
	         
     <xsl:for-each select="ArchE/ResponsibilityRefinementRelation">
       <xsl:call-template name="responsibilityRefinementTemplate">    
           <xsl:with-param name="factID" select="@factId"/>
           <xsl:with-param name="parent" select="parent"/>
           <xsl:with-param name="child" select="child"/>
       </xsl:call-template>
     </xsl:for-each>
     		      		       
	 <xsl:for-each select="ArchE/SequenceRelation">
	   <xsl:call-template name="sequenceTemplate">
	   	   <xsl:with-param name="factID" select="@factId"/>
	       <xsl:with-param name="parent" select="parent"/>
	       <xsl:with-param name="child" select="child"/>
	   </xsl:call-template>    
	</xsl:for-each>
	
    <xsl:for-each select="$moduleInterface">
       <xsl:call-template name="interfaceTemplate">
         <xsl:with-param name="factID" select="@factId"/>
       </xsl:call-template>    
    </xsl:for-each>        
    <xsl:text>
    </xsl:text> 
    
    <xsl:for-each select="$subTask"> 
 		<UML:Comment visibility = 'public' isSpecification ='false' >
	   		<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
	  		<xsl:attribute name = "name"><xsl:text>Task: </xsl:text><xsl:value-of select="name"/> 
	   		</xsl:attribute>
	   		<xsl:attribute name = "annotatedElement">S.<xsl:value-of select="responsibility"/></xsl:attribute>                
		</UML:Comment>
	    <xsl:text>
	    </xsl:text> 
	</xsl:for-each>	              

    <xsl:for-each select="$responsibilities">
        <xsl:variable name="factID" select="@factId"/>
        <xsl:variable name="name" select="name"/>		       				

		<UML:Comment visibility = 'public' isSpecification ='false' >
	    	<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="$factID"/></xsl:attribute>	    		
	    	<xsl:attribute name = "annotatedElement">S.<xsl:value-of select="$factID"/></xsl:attribute>                
		    <xsl:text>
		    </xsl:text>   
	   		<UML:ModelElement.name>
			    <xsl:for-each select="$p_CostOfChange[owner=$factID]"><xsl:text>Cost of changing this module: </xsl:text><xsl:value-of select="value"/><xsl:text> </xsl:text>
			    </xsl:for-each>
			    <xsl:for-each select="$p_ExecutionTime[owner=$factID]"><xsl:text>Execution time: </xsl:text><xsl:value-of select="value"/><xsl:text> </xsl:text> 
			    </xsl:for-each> 
		    </UML:ModelElement.name>
	 		<xsl:text>
	        </xsl:text>	     		
	    </UML:Comment>	    			
	    <xsl:text>
	    </xsl:text>	    			 	       
    </xsl:for-each>

    <xsl:for-each select="$moduleDependency">  
	 	 <UML:Comment visibility="public" isSpecification="false" > 
	     	 <xsl:attribute name = "name"><xsl:text>Source: </xsl:text><xsl:value-of select="source"/></xsl:attribute>    
	      	 <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
	      	 <xsl:attribute name = "annotatedElement">G.<xsl:value-of select="@factId"/></xsl:attribute>
	  	 </UML:Comment>
	  	 <xsl:text>
	 	 </xsl:text>  
   	</xsl:for-each> 

 	<xsl:for-each select="$sequenceRelation"> 
 		<UML:Comment visibility = 'public' isSpecification ='false' >
   			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
  			<xsl:attribute name = "name"><xsl:text>Source: </xsl:text><xsl:value-of select="source"/> 
   			</xsl:attribute>
   			<xsl:attribute name = "annotatedElement">G.<xsl:value-of select="@factId"/></xsl:attribute>                
		</UML:Comment>
        <xsl:text>
        </xsl:text>        		
 	</xsl:for-each>
 		     
  	<xsl:for-each select="$responsibilityRefinement">
  	<xsl:variable name="factID" select="@factId"/> 
 		<UML:Comment visibility = 'public' isSpecification ='false' >
   			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
  			<xsl:attribute name = "name"><xsl:text>Source: </xsl:text><xsl:value-of select="source"/> 
   			</xsl:attribute>
   			<xsl:attribute name = "annotatedElement">G.<xsl:value-of select="@factId"/></xsl:attribute>                
		</UML:Comment>
        <xsl:text>
        </xsl:text>         		                      		
 	</xsl:for-each>
         	
 	<xsl:for-each select="$responsibilityToResponsibility"> 
 		<xsl:variable name="factID" select="@factId"/> 
 		<UML:Comment visibility = 'public' isSpecification ='false' >
   			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
  			<xsl:attribute name = "name"><xsl:text>Source: </xsl:text><xsl:value-of select="source"/> 
   			</xsl:attribute>
   			<xsl:attribute name = "annotatedElement">G.<xsl:value-of select="@factId"/></xsl:attribute>                
		</UML:Comment>
        <xsl:text>
        </xsl:text>        		
      
		<!-- Start of comment on ProbabilityOutgoing -->
        <xsl:for-each select="$p_ProbabilityOutgoing[owner=$factID]">  
        <xsl:variable name="owner" select="owner"/>     
	          <UML:Comment visibility="public" isSpecification="false" > 
	         	  <xsl:attribute name = "name"><xsl:text>Probability that a change propagates from </xsl:text>
	          	  <xsl:for-each select="$responsibilityToResponsibility[@factId=$owner]">
		              <xsl:variable name="parent" select="parent"/> 
		              <xsl:variable name="child" select="child"/> 
		              <xsl:for-each select="$responsibilities">
		                  <xsl:if test="@factId=$parent">'<xsl:value-of select="name"/>'
		                  </xsl:if>
		                  <xsl:if test="@factId=$child"> to '<xsl:value-of select="name"/>'   
		                  </xsl:if>			                  
		              </xsl:for-each>
		          </xsl:for-each>
		          <xsl:text>: </xsl:text><xsl:value-of select="value"/>
	          	  </xsl:attribute>
	         	  <xsl:attribute name = "xmi.id">XX.O.<xsl:value-of select="owner"/></xsl:attribute>
	          	  <xsl:attribute name = "annotatedElement">G.<xsl:value-of select="owner"/></xsl:attribute>
	          </UML:Comment>
	          <xsl:text>
	          </xsl:text> 
        </xsl:for-each >  
    	<!-- End of comment on ProbabilityOutgoing -->    
    	    
   		<!-- Start of comment on ProbabilityIncoming -->
        <xsl:for-each select="$p_ProbabilityIncoming[owner=$factID]">       
        <xsl:variable name="owner" select="owner"/>   
	          <UML:Comment visibility="public" isSpecification="false" > 
	          	  <xsl:attribute name = "name"><xsl:text>Probability that a change propagates from </xsl:text>
	          	  <xsl:for-each select="$responsibilityToResponsibility[@factId=$owner]">
		              <xsl:variable name="parent" select="parent"/> 
		              <xsl:variable name="child" select="child"/> 
		              <xsl:for-each select="$responsibilities[@factId=$child]">'<xsl:value-of select="name"/>'
	                  </xsl:for-each>
	                  <xsl:for-each select="$responsibilities[@factId=$parent]"> to '<xsl:value-of select="name"/>'		                  
		              </xsl:for-each>
		          </xsl:for-each>
		          <xsl:text>: </xsl:text><xsl:value-of select="value"/>
		          </xsl:attribute>
		          <xsl:attribute name = "xmi.id">XX.I.<xsl:value-of select="owner"/></xsl:attribute>
		          <xsl:attribute name = "annotatedElement">G.<xsl:value-of select="owner"/></xsl:attribute>
	          </UML:Comment>
	          <xsl:text>
	          </xsl:text>	          
        </xsl:for-each >  
    	<!-- End of comment on ProbabilityIncoming --> 	        	
        <xsl:text>
        </xsl:text>        	
 	</xsl:for-each>     
 	    	  		                                   
    </UML:Namespace.ownedElement>
    <xsl:text>
    </xsl:text>       
    </UML:Package> 
    <xsl:text>
    </xsl:text>     
              
    <UML:Subsystem xmi.id = 'S.subsystem' name = 'Component View' visibility = 'public' isSpecification = 'false' isRoot = 'true' isLeaf = 'false' isAbstract = 'false' isInstantiable = 'false' >
    <UML:Namespace.ownedElement>
    <xsl:text>
    </xsl:text>      
    <xsl:for-each select="$task">
	    <xsl:call-template name="componentViewTemplate" >
	         <xsl:with-param name="factID" select="@factId"/>
	    </xsl:call-template>    
    </xsl:for-each>     

 	<UML:Stereotype name="Task Specification" visibility="public" isSpecification ="false" isRoot = "false" isLeaf ="false" isAbstract ="false" icon ="" baseClass="Component"> 
      	  <xsl:attribute name="xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute>
      	  <xsl:attribute name="extendedElement">
      		<xsl:for-each select="$task">S.<xsl:value-of select="@factId"/><xsl:text> </xsl:text></xsl:for-each>
    	  </xsl:attribute>    	  
 	</UML:Stereotype>
    <xsl:text>
    </xsl:text> 
           
<!--Start of Task Dependencies  --> 
<!--TODO:: Change this(relationship between responsibility and task) into relationship between tasks later-->
    <xsl:call-template name="taskDependencyTemplate"/>  		         
<!--End of Task Dependencies  --> 

    </UML:Namespace.ownedElement>
    <xsl:text>
    </xsl:text>     
	</UML:Subsystem>     
	<xsl:text>
	</xsl:text>        
	<!-- Start of abstraction  -->     
    <xsl:call-template name="abstractionTemplate"/>  		         
	<!-- End of abstraction  -->           
     
    <xsl:for-each select="$moduleDependency">
    <xsl:variable name="factID" select="@factId"/>
	<!-- Start of comment on ProbabilityOutgoing -->
        <xsl:for-each select="$p_ProbabilityOutgoing[owner=$factID]">    
	          <xsl:variable name="owner" select="owner"/>   
	          <UML:Comment visibility="public" isSpecification="false" > 
	          <xsl:attribute name = "name"><xsl:text>Probability that a change propagates from </xsl:text>
	          	  <xsl:for-each select="$responsibilityToResponsibility[@factId=$owner]">
		              <xsl:variable name="parent" select="parent"/> 
		              <xsl:variable name="child" select="child"/> 
		              <xsl:for-each select="$responsibilities">
		                  <xsl:if test="@factId=$parent">'<xsl:value-of select="name"/>'
		                  </xsl:if>
		                  <xsl:if test="@factId=$child"> to '<xsl:value-of select="name"/>'   
		                  </xsl:if>			                  
		              </xsl:for-each>
		          </xsl:for-each>
		          <xsl:text>: </xsl:text><xsl:value-of select="value"/>
	          </xsl:attribute>
	          <xsl:attribute name = "xmi.id">XX.O.<xsl:value-of select="owner"/></xsl:attribute>
	          <xsl:attribute name = "annotatedElement">G.<xsl:value-of select="owner"/></xsl:attribute>
	          </UML:Comment>
	          <xsl:text>
	          </xsl:text> 
        </xsl:for-each >  
	    <!-- End of comment on ProbabilityOutgoing -->        
	    <!-- Start of comment on ProbabilityIncoming -->
		<xsl:for-each select="$p_ProbabilityIncoming[owner=$factID]">  
 		<xsl:variable name="owner" select="owner"/>        
	          <UML:Comment visibility="public" isSpecification="false" > 
	          <xsl:attribute name = "name"><xsl:text>Probability that a change propagates from </xsl:text>
	          	  <xsl:for-each select="$responsibilityToResponsibility[@factId=$owner]">
		              <xsl:variable name="parent" select="parent"/> 
		              <xsl:variable name="child" select="child"/> 
		              <xsl:for-each select="$responsibilities[@factId=$child]">'<xsl:value-of select="name"/>'
	                  </xsl:for-each>
	                  <xsl:for-each select="$responsibilities[@factId=$parent]"> to '<xsl:value-of select="name"/>'		                  
		              </xsl:for-each>
		          </xsl:for-each>
		          <xsl:text>: </xsl:text><xsl:value-of select="value"/>
	          </xsl:attribute>
	          <xsl:attribute name = "xmi.id">XX.I.<xsl:value-of select="owner"/></xsl:attribute>
	          <xsl:attribute name = "annotatedElement">G.<xsl:value-of select="owner"/></xsl:attribute>
	          </UML:Comment>
	          <xsl:text>
	          </xsl:text>	          
		 </xsl:for-each > 
     </xsl:for-each>   
	 <!-- End of comment on ProbabilityIncoming -->     
       
     <UML:Comment xmi.id="XX.Dummy.Comment" name="Dummy element for lame repository" visibility="public" isSpecification="false" /> 
     </UML:Namespace.ownedElement>  
     <xsl:text>
     </xsl:text>     
     </UML:Model>
     <xsl:text>
     </xsl:text>  
     
     <xsl:for-each select="$responsibilityRefinement">
	     <xsl:call-template name="taggedValueTemplate1"> 
	       <xsl:with-param name="parent"  select="parent"/>
	       <xsl:with-param name="child"  select="child"/>
	     </xsl:call-template>
     </xsl:for-each>
     
     <xsl:for-each select="$moduleInterface">
	     <xsl:call-template name="taggedValueTemplate2"> 
	       <xsl:with-param name="factID"  select="@factId"/>
	     </xsl:call-template>
     </xsl:for-each>   
      
     <xsl:for-each select="$sequenceRelation">
	     <xsl:call-template name="taggedValueTemplate3"> 
	       <xsl:with-param name="factID"  select="@factId"/>
	     </xsl:call-template>
     </xsl:for-each>      
        
<!-- Start of drawing class diagram for main diagram  -->    
     <xsl:call-template name="mainDiagramTemplate"/>  
<!-- End of drawing class diagram for main diagram  -->   
  
<!-- Start of drawing class diagram for modules  -->    
	 <xsl:for-each select="$module">  
        <UML:Diagram name="Module" toolName="Rational Rose 98" diagramType="ClassDiagram" style="">
    		<xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   			<xsl:attribute name = "owner">S.<xsl:value-of select="@factId"/></xsl:attribute>
            <UML:Diagram.element>    	       
                <xsl:call-template name="moduleDiagramTemplate">
                    <xsl:with-param name="factID" select="@factId"/>
                </xsl:call-template>
            </UML:Diagram.element>
    	    <xsl:text>
     	    </xsl:text>              
        </UML:Diagram>       
	    <xsl:text>
 	    </xsl:text>               
     </xsl:for-each>     
<!-- End of drawing class diagram for modules  --> 

<!-- Start of drawing class diagram  for responsibilities -->    
     <xsl:for-each select="$responsibilities">  
         <xsl:call-template name="responsibilityDiagramTemplate">
             <xsl:with-param name="name" select="name" />
             <xsl:with-param name="factID" select="@factId" />
         </xsl:call-template>  
     </xsl:for-each>
<!-- End of drawing class diagram for responsibilities --> 

<!-- Start of drawing module diagram  for tasks -->    
     <xsl:call-template name="taskDiagramTemplate"/>  
<!-- End of drawing module diagram for tasks --> 
  	</XMI.content>
    <xsl:text>
    </xsl:text>  	
    </XMI> 	
</xsl:template>

<xsl:template name="moduleTemplate" match="ArchE/Module">
  <xsl:param name="factID"/>  
      <xsl:if test="count($moduleRefinement[child=$factID])=0">
<!--  
		  <UML:Package visibility = "public" isSpecification = "false" namespace="G.0" isRoot = "false" isLeaf = "false" isAbstract = "false">
-->	      
	      <xsl:attribute name = "xmi.id">S.<xsl:value-of select="$factID"/></xsl:attribute> 
	        <xsl:attribute name = "name"><xsl:value-of select="name"/></xsl:attribute> 
	        <xsl:attribute name = "clientDependency">
		          <xsl:for-each select="$moduleDependency[parent=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
		          </xsl:for-each>
		          <xsl:for-each select="$realize[parent=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
		          </xsl:for-each>          
	        </xsl:attribute> 
	        <xsl:attribute name = "supplierDependency">
		          <xsl:for-each select="$moduleDependency[child=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
		          </xsl:for-each>
		          <xsl:for-each select="$realize[child=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
		          </xsl:for-each>           
	        </xsl:attribute>              
	        <UML:Namespace.ownedElement>
	        <xsl:text>
	        </xsl:text>
	        <UML:Stereotype name='Module' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false' icon = '' baseClass = 'Package' >   
		          <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute>           
		          <xsl:attribute name = "extendedElement">S.<xsl:value-of select="$factID"/></xsl:attribute>                            
	        </UML:Stereotype>
            <xsl:text>
            </xsl:text>        
	       
	 		<xsl:for-each select="$realize[parent=$factID]">  
			      <UML:Dependency visibility = 'public' isSpecification ='false' name=''>
				        <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute>
				        <xsl:attribute name = "client">S.<xsl:value-of select="parent"/></xsl:attribute>
				        <xsl:attribute name = "supplier">S.<xsl:value-of select="child"/></xsl:attribute>        
			      </UML:Dependency>
			      <xsl:text>
		          </xsl:text>	      
		    </xsl:for-each> 	    
	 
	        <xsl:for-each select="$moduleRefinement[parent=$factID]">          
	             <xsl:call-template name="subModuleTemplate">
	               <xsl:with-param name="parent" select="parent"/>
	               <xsl:with-param name="child" select="child"/>
	             </xsl:call-template>		  
	        </xsl:for-each>  
			         
	        <xsl:for-each select="$responsibilityToModule[parent=$factID]">
	            <xsl:call-template name="responsibilityTemplate">
	              <xsl:with-param name="parent" select="parent"/>
	              <xsl:with-param name="child" select="child"/>
	            </xsl:call-template>
	        </xsl:for-each>       
	        
	        </UML:Namespace.ownedElement>     
            <xsl:text>
            </xsl:text>
	        </UML:Package> 
            <xsl:text>
            </xsl:text>      
      </xsl:if>
</xsl:template>

<xsl:template name="subModuleTemplate" match="ArchE/Module">
    <xsl:param name="parent"/>
    <xsl:param name="child"/>
    <xsl:for-each select="$module[@factId=$child]">         
   		 <UML:Package visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false'>
  			<xsl:attribute name = "xmi.id">S.<xsl:value-of select="$child"/></xsl:attribute> 
  			<xsl:attribute name = "name"><xsl:value-of select="name"/></xsl:attribute> 
  			<xsl:attribute name = "namespace">S.<xsl:value-of select="$parent"/></xsl:attribute> 
  			<xsl:attribute name = "clientDependency">
     			<xsl:for-each select="$moduleDependency[parent=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
      			</xsl:for-each>
     			<xsl:for-each select="$realize[parent=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
      			</xsl:for-each>
 			</xsl:attribute> 
  			<xsl:attribute name = "supplierDependency">
     			 <xsl:for-each select="$moduleDependency[child=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
    			  </xsl:for-each>
     			 <xsl:for-each select="$realize[child=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
    			  </xsl:for-each>
  			</xsl:attribute>              
          <xsl:text>
          </xsl:text>
  		  <UML:Namespace.ownedElement> 
          <xsl:text>
          </xsl:text>
  		  <UML:Stereotype name='Module' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false' icon = '' baseClass = 'Package' >   
    			  <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute> 
    			  <xsl:attribute name = "extendedElement">S.<xsl:value-of select="$child"/></xsl:attribute>                            
 		  </UML:Stereotype>
          <xsl:text>
          </xsl:text>    
		         	      			 
   		  <xsl:for-each select="$responsibilityToModule[parent=$child]">
   			    <xsl:variable name="child" select="child"/>
   			    <xsl:variable name="parent" select="parent"/>       			    
   			    <xsl:call-template name="responsibilityTemplate">
    			   <xsl:with-param name="child" select="$child"/>
      			   <xsl:with-param name="parent" select="$parent"/>
      			 </xsl:call-template>
   		  </xsl:for-each>
    		
		  <UML:Comment visibility = 'public' isSpecification ='false' >
			    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
	   			<xsl:attribute name = "name"><xsl:text>Cost of changing this module: </xsl:text><xsl:value-of select="costOfChange"/></xsl:attribute>
	   			<xsl:attribute name = "annotatedElement">S.<xsl:value-of select="@factId"/></xsl:attribute>                
          </UML:Comment>
          <xsl:text>
          </xsl:text>                                   		
                  	       	        	             	                         
      	  <xsl:for-each select="$moduleRefinement[parent=$child]">
             <xsl:call-template name="subModuleTemplate">
               <xsl:with-param name="parent" select="parent"/>
               <xsl:with-param name="child" select="child"/>
             </xsl:call-template>
      	  </xsl:for-each>  
      	  </UML:Namespace.ownedElement> 
          <xsl:text>
          </xsl:text>
     	  </UML:Package>
          <xsl:text>
          </xsl:text>     	  
    </xsl:for-each>      		
</xsl:template>

<xsl:template name="responsibilityTemplate" match="ArchE/Responsibilities">
    <xsl:param name="parent"/>
    <xsl:param name="child"/>	
 
    <xsl:for-each select="$responsibilities[@factId=$child]"> 
    <UML:Class visibility = 'public' isSpecification = 'false' isRoot = 'true' isLeaf = 'true' isAbstract = 'false' isActive = 'false'>
      <xsl:attribute name = "xmi.id">S.<xsl:value-of select="@factId"/></xsl:attribute> 
      <xsl:attribute name = "name"><xsl:value-of select="name"/></xsl:attribute> 
      <xsl:attribute name = "namespace">S.<xsl:value-of select="$parent"/></xsl:attribute> 
      <xsl:attribute name = "clientDependency">
          <xsl:for-each select="$responsibilityToModule[parent=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each>
          <xsl:for-each select="$responsibilityToResponsibility[parent=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each>
          <xsl:for-each select="$realize[parent=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each>                    
      </xsl:attribute> 
      <xsl:attribute name = "supplierDependency">
          <xsl:for-each select="$responsibilityToModule[child=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each>
          <xsl:for-each select="$responsibilityToResponsibility[child=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each> 
          <xsl:for-each select="$realize[child=$child]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each>                     
      </xsl:attribute>              
      <!--xsl:attribute name = "elementResidence">
         <xsl:for-each select="$subTask[responsibility=$child]">XX.<xsl:value-of select="@factId"/>
         </xsl:for-each>
      </xsl:attribute-->  
      <xsl:attribute name = "elementResidence"><xsl:value-of select="id"/></xsl:attribute>  
      </UML:Class>                  
      <xsl:text>
      </xsl:text>     
      <UML:Stereotype name='Responsibility' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false' icon = '' baseClass = 'Class' >   
		  <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute>          
          <xsl:attribute name = "extendedElement">S.<xsl:value-of select="@factId"/></xsl:attribute>                            
      </UML:Stereotype>
      <xsl:text>
      </xsl:text>  
                       
      <xsl:for-each select="$moduleDependency[parent=$parent]"> 
	      <UML:Dependency visibility = 'public' isSpecification ='false' name=''>
	        <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute>
	        <xsl:attribute name = "client">S.<xsl:value-of select="parent"/></xsl:attribute>
	        <xsl:attribute name = "supplier">S.<xsl:value-of select="child"/></xsl:attribute>        
	      </UML:Dependency>
	      <xsl:text>
	      </xsl:text>      
      </xsl:for-each>
 
      <xsl:for-each select="$responsibilityToModule[child=$child]"> 
	        <UML:Dependency visibility = 'public' isSpecification ='false' name='contains'>
	          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute>
	          <xsl:attribute name = "client">S.<xsl:value-of select="parent"/></xsl:attribute>
	          <xsl:attribute name = "supplier">S.<xsl:value-of select="child"/></xsl:attribute>        
	        </UML:Dependency>
	        <xsl:text>
	        </xsl:text>
      </xsl:for-each>       

 	  <xsl:for-each select="$realize[parent=$child]">  
	      <UML:Dependency visibility = 'public' isSpecification ='false' name=''>
	        <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute>
	        <xsl:attribute name = "client">S.<xsl:value-of select="parent"/></xsl:attribute>
	        <xsl:attribute name = "supplier">S.<xsl:value-of select="child"/></xsl:attribute>        
	      </UML:Dependency>
          <xsl:text>
          </xsl:text>	      
	  </xsl:for-each>       
      
      <xsl:for-each select="$responsibilityToResponsibility[parent=$child]"> 
	        <xsl:variable name="currentChild" select="child"/>
	        <xsl:variable name="currentParent" select="parent"/>
	        <UML:Dependency visibility = 'public' isSpecification ='false' name=''>
	          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute>
	          <xsl:attribute name = "client">S.<xsl:value-of select="parent"/></xsl:attribute>
	          <xsl:attribute name = "supplier">S.<xsl:value-of select="child"/></xsl:attribute>        
	        </UML:Dependency>
	        <xsl:text>
	        </xsl:text> 
	        
      </xsl:for-each>                 
    </xsl:for-each>           
</xsl:template>

<xsl:template name="responsibilityRefinementTemplate" match="ArchE/ResponsibilityRefinementRelation">
        <xsl:param name="factID"/>
        <xsl:param name="parent"/>
        <xsl:param name="child"/>
        
        <UML:Association name = '' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false' >
          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="$factID"/></xsl:attribute>            
          <UML:Association.connection>
            <UML:AssociationEnd  name = '' visibility = 'public' isSpecification = 'false' isNavigable = 'true' ordering = 'unordered' aggregation = 'none' targetScope = 'instance' changeability = 'changeable' >
	          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/>.1</xsl:attribute>   
	          <xsl:attribute name = "type">S.<xsl:value-of select="$child"/></xsl:attribute>         	                
              <UML:AssociationEnd.multiplicity>
                <UML:Multiplicity >
                  <UML:Multiplicity.range>
                    <UML:MultiplicityRange 
                      lower = '0' upper = '-1' >
                      <xsl:attribute name = "xmi.id">id.<xsl:value-of select="generate-id()"/></xsl:attribute>
                    </UML:MultiplicityRange>
                  </UML:Multiplicity.range>
		          <xsl:text>
	              </xsl:text>                  
                </UML:Multiplicity>
		        <xsl:text>
	            </xsl:text>                
              </UML:AssociationEnd.multiplicity>
              <xsl:text>
              </xsl:text>              
            </UML:AssociationEnd>
	        <xsl:text>
            </xsl:text>            
            
            <UML:AssociationEnd name = '' visibility = 'public' isSpecification = 'false' isNavigable = 'true' ordering = 'unordered' aggregation = 'composite' targetScope = 'instance' changeability = 'changeable' >
	          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/>.2</xsl:attribute>  
	          <xsl:attribute name = "type">S.<xsl:value-of select="$parent"/></xsl:attribute>          	  
              <UML:AssociationEnd.multiplicity>
                <UML:Multiplicity >
                  <UML:Multiplicity.range>
                    <UML:MultiplicityRange 
                      lower = '0' upper = '-1' >
 	          			<xsl:attribute name = "xmi.id">id.<xsl:value-of select="generate-id()"/></xsl:attribute>                       
                    </UML:MultiplicityRange>
                  </UML:Multiplicity.range>
	              <xsl:text>
                  </xsl:text>
                </UML:Multiplicity>
	            <xsl:text>
                </xsl:text>
              </UML:AssociationEnd.multiplicity>
	          <xsl:text>
              </xsl:text>
            </UML:AssociationEnd>
	        <xsl:text>
            </xsl:text>            
          </UML:Association.connection>
	      <xsl:text>
          </xsl:text>          
        </UML:Association>
	    <xsl:text>
        </xsl:text>                                        
</xsl:template>	

<xsl:template name="sequenceTemplate" match="ArchE/SequenceRelation">
        <xsl:param name="factID"/>
        <xsl:param name="parent"/>
        <xsl:param name="child"/>
        <UML:Association name = '' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false' >
          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="$factID"/></xsl:attribute>            
          <UML:Association.connection>
            <UML:AssociationEnd  name = '' visibility = 'public' isSpecification = 'false' isNavigable = 'true' ordering = 'unordered' aggregation = 'none' targetScope = 'instance' changeability = 'changeable' >
	          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="$factID"/>.1</xsl:attribute>   
	          <xsl:attribute name = "type">S.<xsl:value-of select="$parent"/></xsl:attribute>         	                
              <UML:AssociationEnd.multiplicity>
                <UML:Multiplicity >
                  <UML:Multiplicity.range>
                    <UML:MultiplicityRange 
                      lower = '0' upper = '-1'>
                      <xsl:attribute name = "xmi.id">id.<xsl:value-of select="generate-id()"/></xsl:attribute>
                    </UML:MultiplicityRange> 
                  </UML:Multiplicity.range>
		          <xsl:text>
	              </xsl:text>                  
                </UML:Multiplicity>
		        <xsl:text>
	            </xsl:text>                
              </UML:AssociationEnd.multiplicity>
	          <xsl:text>
              </xsl:text>              
            </UML:AssociationEnd>
	        <xsl:text>
            </xsl:text>            
            <UML:AssociationEnd name = '' visibility = 'public' isSpecification = 'false' isNavigable = 'false' ordering = 'unordered' aggregation = 'none' targetScope = 'instance' changeability = 'changeable' >
	          <xsl:attribute name = "xmi.id">G.<xsl:value-of select="$factID"/>.2</xsl:attribute>  
	          <xsl:attribute name = "type">S.<xsl:value-of select="$child"/></xsl:attribute>          	  
              <UML:AssociationEnd.multiplicity>
                <UML:Multiplicity >
                  <UML:Multiplicity.range>
                    <UML:MultiplicityRange 
                      lower = '0' upper = '-1'>
 	          			<xsl:attribute name = "xmi.id">id.<xsl:value-of select="generate-id()"/></xsl:attribute>                       
                    </UML:MultiplicityRange>
                  </UML:Multiplicity.range>
		          <xsl:text>
	              </xsl:text>
                </UML:Multiplicity>
		        <xsl:text>
	            </xsl:text>
              </UML:AssociationEnd.multiplicity>
	          <xsl:text>
              </xsl:text>
            </UML:AssociationEnd>
            <xsl:text>
            </xsl:text>            
          </UML:Association.connection>
          <xsl:text>
          </xsl:text>          
        </UML:Association>
        <xsl:text>
        </xsl:text>   
             
        <UML:Stereotype name = 'sequence' visibility = 'public' isSpecification = 'false' isRoot = 'false' isLeaf = 'false' isAbstract = 'false' icon = '' baseClass = 'Association'>    
			  <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute>  
	          <xsl:attribute name = "extendedElement">G.<xsl:value-of select="$factID"/></xsl:attribute>     
        </UML:Stereotype> 
        <xsl:text>
        </xsl:text>                                
</xsl:template>	

<xsl:template name="abstractionTemplate" match="ArchE/ModuleInterface">
    <xsl:for-each select="$moduleInterface">
        <xsl:variable name="interfaceFactID" select="@factId"/> 
		 <UML:Abstraction name="" visibility="public" isSpecification="false" >
	    	    <xsl:attribute name = "xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute> 
		        <xsl:attribute name = "client"><xsl:for-each select="$realize[child=$interfaceFactID]">S.<xsl:value-of select="parent"/><xsl:text> </xsl:text></xsl:for-each>
		        </xsl:attribute> 
		        <xsl:attribute name = "supplier">S.<xsl:value-of select="@factId"/></xsl:attribute>			
		  <UML:Abstraction.mapping>
		  <UML:MappingExpression language="" body="" /> 
		  </UML:Abstraction.mapping>
		  </UML:Abstraction>
	      <xsl:text>
	      </xsl:text> 
	      <UML:Stereotype visibility = 'public' isSpecification ='false' name ='realize' baseClass='Abstraction' >
		       <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute>
		       <xsl:attribute name = "extendedElement">G.<xsl:value-of select="@factId"/></xsl:attribute>                
	      </UML:Stereotype>
	      <xsl:text>
	      </xsl:text>
	     <UML:Comment name="Dummy element for lame repository" visibility="public" isSpecification="false" > 
 	 	   	   <xsl:attribute name = "xmi.id">XX.Dummy.<xsl:value-of select="@factId"/></xsl:attribute>
 	 	 </UML:Comment> 	                          
    </xsl:for-each> 
    <xsl:text>
    </xsl:text>           
</xsl:template>


<xsl:template name="interfaceTemplate" match="ArchE/ModuleInterface">
      <xsl:param name="factID"/>  
        <UML:Interface namespace = 'R.0' visibility = 'public' isSpecification = 'false' isRoot = 'true' isLeaf = 'false' isAbstract = 'false'>
          <xsl:attribute name = "name"><xsl:value-of select="name"/></xsl:attribute>    
          <xsl:attribute name = "xmi.id">S.<xsl:value-of select="@factId"/></xsl:attribute>
          <xsl:variable name="factID" select="@factId"/>
          <xsl:variable name="name" select="name"/>
          <xsl:attribute name = "supplierDependency">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>        
          <xsl:for-each select="$realize[child=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
          </xsl:for-each>                         
          </xsl:attribute>                 
        </UML:Interface>
        <xsl:text>
        </xsl:text>        
</xsl:template>

<xsl:template name="componentViewTemplate" match="ArchE/Task">
  <xsl:param name="factID"/>
  <UML:Component visibility ='public' isSpecification = 'false' isRoot = 'false' isLeaf ='true' isAbstract = 'false'> 
	    <xsl:attribute name="xmi.id">S.<xsl:value-of select="@factId"/></xsl:attribute>
	    <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
	<!--TODO::No information on relationship between tasks. Should be changed later
	    <xsl:if test="">
	    <xsl:attribute name="clientDependency">
	          <xsl:for-each select="$subTask[task=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
	          </xsl:for-each>
	    </xsl:attribute>
	    </xsl:if>
	    <xsl:if test="">
	    <xsl:attribute name="supplierDependency">
	          <xsl:for-each select="$subTask[task=$factID]">G.<xsl:value-of select="@factId"/><xsl:text> </xsl:text>
	          </xsl:for-each>
	    </xsl:attribute>   
	    </xsl:if> 
	-->    
	  	<UML:Component.residentElement>
	    <xsl:text>
	    </xsl:text>  	    
	    <xsl:for-each select="$subTask[task=$factID]">
	        <xsl:variable name="taskFactID" select="task"/>	  
	    	  <UML:ElementResidence visibility = 'public'>
	    	    <xsl:attribute name="xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
	    	    <xsl:attribute name="resident">S.<xsl:value-of select="responsibility"/></xsl:attribute>
	    	    <xsl:attribute name="implementationLocataion">S.<xsl:value-of select="$factID"/></xsl:attribute>            
	    	  </UML:ElementResidence>
	          <xsl:text>
	          </xsl:text>    	    	  
	    </xsl:for-each>
	    </UML:Component.residentElement>
	    <xsl:text>
	    </xsl:text>     
    </UML:Component> 
    <xsl:text>
    </xsl:text>                             
</xsl:template>	  

<xsl:template name="taggedValueTemplate1" match="ArchE/responsibilityRefinementRelation">
  <xsl:param name="parent"/>
  <xsl:param name="child"/>
  <UML:TaggedValue  tag = 'persistence' value = 'transient' >
    	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>
    	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$parent"/></xsl:attribute>
  </UML:TaggedValue>
  <xsl:text>
  </xsl:text>  
  
  <UML:TaggedValue  tag = 'documentation' value = 'Description' >
	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>
	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$parent"/></xsl:attribute>
  </UML:TaggedValue> 
  <xsl:text>
  </xsl:text>
  
  <UML:TaggedValue  tag = 'persistence' value = 'transient' >
    	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>
    	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$child"/></xsl:attribute>
  </UML:TaggedValue>
  <xsl:text>
  </xsl:text>  
  
  <UML:TaggedValue  tag = 'documentation' value = 'Description' >
	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>
	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$child"/></xsl:attribute>
  </UML:TaggedValue> 
  <xsl:text>
  </xsl:text>  
</xsl:template>

<xsl:template name="taggedValueTemplate2" match="ArchE/InterfaceModule">
  <xsl:param name="factID"/>
  <UML:TaggedValue  tag = 'persistence' value = 'transient' >
    	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
    	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$factID"/></xsl:attribute>
  </UML:TaggedValue>
  <xsl:text>
  </xsl:text>  
</xsl:template>

<xsl:template name="taggedValueTemplate3" match="ArchE/SequenceRelation">
  <xsl:param name="factID"/>
  <UML:TaggedValue  tag = 'persistence' value = 'transient' >
    	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
    	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$factID"/></xsl:attribute>
  </UML:TaggedValue>
  <xsl:text>
  </xsl:text>  
  <UML:TaggedValue  tag = 'documentation' value = 'Description' >
    	  <xsl:attribute name="xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>
    	  <xsl:attribute name="modelElement">S.<xsl:value-of select="$factID"/></xsl:attribute>
  </UML:TaggedValue> 
  <xsl:text>
  </xsl:text>
</xsl:template>

<xsl:template name="mainDiagramTemplate" match="ArchE/Module">
	   <UML:Diagram name = 'Main' toolName = 'Rational Rose 98' diagramType = 'ClassDiagram' style = '' owner = 'G.0' >
        <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute> 
        <UML:Diagram.element>
		<xsl:comment>Start of Main diagram</xsl:comment>
        <xsl:for-each select="$module">
           <xsl:variable name="factID" select="@factId"/>
           <xsl:if test="count($moduleRefinement[child=$factID])=0">
          <!--Start of creating diagram elements for modules -->   
                 <UML:DiagramElement style = 'Category' >
        			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute> 
 
       			 	<xsl:attribute name = "subject">S.<xsl:value-of select="$factID"/></xsl:attribute>
      			 </UML:DiagramElement>
	          <xsl:text>
              </xsl:text>              
	 		 <!--End of creating diagram elements for modules -->
	         <!--Start of creating diagram elements for moduleDependency -->             
     		 <xsl:for-each select="$moduleDependency[child=$factID]">
     		 	<xsl:variable name="currentFactID" select="@factId"/>
                 <UML:DiagramElement style = 'CategoryDependency' >
        			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute> 

       			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
      			 </UML:DiagramElement>
	          	<xsl:text>
              	</xsl:text>
              	
              	<xsl:if test="$p_ProbabilityOutgoing[owner=$currentFactID]!=''">          	
              	 <UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
        			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

       			 	<xsl:attribute name = "subject">XX.O.<xsl:value-of select="owner"/></xsl:attribute>
      	      	</UML:DiagramElement>  
	            <xsl:text>
                </xsl:text>             
                </xsl:if>  
              
	            <xsl:if test="$p_ProbabilityIncoming[owner=$currentFactID]!=''">          		 
	              	 <UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
	        			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	       			 	<xsl:attribute name = "subject">XX.I.<xsl:value-of select="owner"/></xsl:attribute>
	      	      	</UML:DiagramElement>  
		            <xsl:text>
	                </xsl:text>             
	            </xsl:if> 
	                           	
             </xsl:for-each>                                  	      	                
         <!--End of creating diagram elements for moduleDependency -->                         			  	         			
           </xsl:if>
         </xsl:for-each> 
		<xsl:comment>End of Main diagram</xsl:comment>         
        </UML:Diagram.element>  
        <xsl:text>
        </xsl:text>           
  	 </UML:Diagram>  
     <xsl:text>
     </xsl:text>  	 
</xsl:template>

<xsl:template name="moduleDiagramTemplate" match="ArchE/Module">
        <xsl:param name="factID"/>
        <!--Start of creating diagram elements for moduleDependency --> 
	    <xsl:text>
	    </xsl:text>                    
		<xsl:comment>Start of moduleDiagramTemplate</xsl:comment>
        <xsl:text>
        </xsl:text>
        <xsl:if test="count($moduleRefinement[parent=$factID])=0">
            <xsl:variable name="currentFactID" select="@factId"/>
            <UML:DiagramElement style="Category" > 
    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="$factID"/></xsl:attribute>
  	      	</UML:DiagramElement> 
  	      	<xsl:text>
         	</xsl:text>             
         	
         	<xsl:for-each select="$moduleDependency[parent=$factID]">
         	    <xsl:variable name="Child" select="child"/>
	         	<UML:DiagramElement style="Category" > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="child"/></xsl:attribute>
	  	      	</UML:DiagramElement> 
  	      	    <xsl:text>
         	    </xsl:text>  
	
	         	<UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   			 	<xsl:attribute name = "subject">XX.<xsl:value-of select="child"/></xsl:attribute>
	  	      	</UML:DiagramElement> 
  	      	    <xsl:text>
         	    </xsl:text>   
	         	    
	         	<UML:DiagramElement style="ClassDependency"> 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
	  	      	</UML:DiagramElement>          	            	               

				<xsl:comment>Start of dependency between sub modules</xsl:comment>
				<xsl:if test="$moduleDependency[child=$factID and parent=$Child]">
		         	<UML:DiagramElement style="ClassDependency"> 
		    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

		   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
		  	      	</UML:DiagramElement> 
				</xsl:if>
				<xsl:comment>End of dependency between sub modules</xsl:comment>
         	</xsl:for-each>    

         	<xsl:for-each select="$moduleDependency[child=$factID]">
	         	<UML:DiagramElement style="Category" > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="parent"/></xsl:attribute>
	  	      	</UML:DiagramElement> 
  	      	    <xsl:text>
         	    </xsl:text>  
	         	<UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
 
	   			 	<xsl:attribute name = "subject">XX.<xsl:value-of select="parent"/></xsl:attribute>
	  	      	</UML:DiagramElement> 
  	      	    <xsl:text>
         	    </xsl:text> 
	         	<UML:DiagramElement style="ClassDependency"> 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
 
	   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
	  	      	</UML:DiagramElement>             	            	               
         	</xsl:for-each> 
         	               
            <xsl:for-each select="$responsibilityToModule[parent=$currentFactID]">
                <xsl:variable name="Child" select="child"/>
                <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   			 	<xsl:attribute name ="subject">S.<xsl:value-of select="child"/></xsl:attribute>    	   
  	      	    </UML:DiagramElement> 
  	      	    <xsl:text>
         	    </xsl:text>    	    

                <xsl:for-each select="$responsibilityToResponsibility[parent=$Child]">
                    <xsl:variable name="respFactID" select="child"/>
                    <xsl:if test="count($responsibilityToModule[$respFactID=child and parent=$factID])!=0">
	                <UML:DiagramElement style="ClassDependency"> 
		    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

		   			 	<xsl:attribute name ="subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
	  	      	    </UML:DiagramElement> 
	  	      	    <xsl:text>
	         	    </xsl:text> 
		         	    <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
			 				<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

			   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="@factId"/></xsl:attribute>
		  	      	    </UML:DiagramElement>      
		  	      	    <xsl:text>
		         	    </xsl:text>   
		         	</xsl:if>    	      	       	    
	         	</xsl:for-each> 	         	    

			<xsl:for-each select="$responsibilityToResponsibility[child=$Child]">
                    <xsl:variable name="respFactID" select="parent"/>
	                <xsl:if test="count($responsibilityToModule[$respFactID=child and parent=$factID])!=0">
		                <UML:DiagramElement style="ClassDependency"> 
			    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

			   			 	<xsl:attribute name ="subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
		  	      	    </UML:DiagramElement> 
		  	      	    <xsl:text>
		         	    </xsl:text> 
	         	    
		         	    <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
			 				<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

			   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="@factId"/></xsl:attribute>
		  	      	    </UML:DiagramElement>      
		  	      	    <xsl:text>
		         	    </xsl:text>   	      	       	    
         	        </xsl:if> 
	         	</xsl:for-each>  
	        </xsl:for-each>
        </xsl:if>
        
        <xsl:if test="count($moduleRefinement[parent=$factID])!=0">
     		<xsl:for-each select="$moduleRefinement[parent=$factID]">
     		 	<xsl:variable name="currentFactID" select="@factId"/>
     		 	<xsl:variable name="Child" select="child"/>
	            <!--Start of creating diagram elements for modules-->   
	            <UML:DiagramElement style="Category" > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="$currentFactID"/></xsl:attribute> 

	   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="child"/></xsl:attribute>
	  			 </UML:DiagramElement>
	        	  <xsl:text>
	         	 </xsl:text> 
	         	 
	          	 <UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,"  > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   			 	<xsl:attribute name = "subject">XX.<xsl:value-of select="child"/></xsl:attribute>
	  	      	</UML:DiagramElement>              
	        	  <xsl:text>
	         	 </xsl:text>
             
                <xsl:for-each select="$moduleDependency[child=$Child]">
                    <xsl:variable name="ModuleParent" select="parent"/>
                    <xsl:if test="count($moduleRefinement[child=$ModuleParent and parent=$factID])!=0">
		                    <UML:DiagramElement style="CategoryDependency"> 
			    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
			   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
			  	      	    </UML:DiagramElement> 
			  	      	    <xsl:text>
			         	    </xsl:text>   
	         	   </xsl:if>              	           
	  	      	</xsl:for-each>                           	 
   
   				<xsl:for-each select="$realize[parent=$currentFactID]">
   				    <xsl:variable name="Child" select="child"/>
	   				<xsl:for-each select="$moduleInterface[@factId=$Child]">
		   				<UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
			    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
			   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="@factId"/></xsl:attribute>
			  	      	    </UML:DiagramElement>
				            <xsl:text>
			                </xsl:text>  			  	      	    
 					    <UML:DiagramElement style="RealizeRelation" > 
 			    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
 			    			<xsl:attribute name = "subject">XX.Dummy.<xsl:value-of select="@factId"/></xsl:attribute> 
			  	      	</UML:DiagramElement>
				          <xsl:text>
			              </xsl:text>  			  	      	
  						<UML:DiagramElement style="DependencyRelation" > 			  	      	    
 			    			<xsl:attribute name = "xmi.id">id.<xsl:value-of select="generate-id()"/></xsl:attribute> 
 			    			<xsl:attribute name = "subject">XX.Dummy.<xsl:value-of select="@factId"/></xsl:attribute>
			  	      	</UML:DiagramElement>
				        <xsl:text>
			            </xsl:text>  			  	      	
	   				</xsl:for-each>
   				</xsl:for-each>

            <xsl:for-each select="$responsibilityToModule[parent=$currentFactID]">
                <xsl:variable name="Child" select="child"/>
                <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
	    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
	   			 	<xsl:attribute name ="subject">S.<xsl:value-of select="child"/></xsl:attribute>    	   
  	      	    </UML:DiagramElement> 
  	      	    <xsl:text>
         	    </xsl:text>    	    

                <xsl:for-each select="$responsibilityToResponsibility[parent=$Child]">
                    <xsl:variable name="respFactID" select="child"/>
	                <UML:DiagramElement style="ClassDependency"> 
		    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
		   			 	<xsl:attribute name ="subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
	  	      	    </UML:DiagramElement> 
	  	      	    <xsl:text>
	         	    </xsl:text> 
	         	    <xsl:for-each select="$responsibilities[@factId=$respFactID]">
		         	    <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
			 				<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
			   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="@factId"/></xsl:attribute>
		  	      	    </UML:DiagramElement>      
		  	      	    <xsl:text>
		         	    </xsl:text>   	      	       	    
         	        </xsl:for-each>   
	         	</xsl:for-each> 	         	    

				<xsl:for-each select="$responsibilityToResponsibility[child=$Child]">
                    <xsl:variable name="respFactID" select="parent"/>
	                <UML:DiagramElement style="ClassDependency"> 
		    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>  
		   			 	<xsl:attribute name ="subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
	  	      	    </UML:DiagramElement> 
	  	      	    <xsl:text>
	         	    </xsl:text> 
	         	    
	         	    <xsl:for-each select="$responsibilities[@factId=$respFactID]">
		         	    <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
			 				<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>  
			   			 	<xsl:attribute name = "subject">S.<xsl:value-of select="@factId"/></xsl:attribute>
		  	      	    </UML:DiagramElement>      
		  	      	    <xsl:text>
		         	    </xsl:text>   	      	       	    
         	        </xsl:for-each> 
	         	</xsl:for-each>  
	        </xsl:for-each>
        
           </xsl:for-each> 
	      <xsl:text>
	      </xsl:text>             	      	                    
         <xsl:comment>End of moduleDiagramTemplate</xsl:comment>
	      <xsl:text>
	      </xsl:text>         
         <!--End of creating diagram elements for moduleDependency -->                         			  	         			               	 
     </xsl:if>
</xsl:template>

<xsl:template name="responsibilityDiagramTemplate" match="ArchE/Responsibilities">
	   <xsl:param name="name"/>
	   <xsl:param name="factID"/>
	   <UML:Diagram toolName = 'Rational Rose 98' diagramType = 'ClassDiagram' style = '' owner = 'R.0' >
        <xsl:attribute name = "xmi.id">S.<xsl:value-of select="generate-id()"/></xsl:attribute> 
        <xsl:attribute name = "name"><xsl:value-of select="$name"/></xsl:attribute> 
        <UML:Diagram.element>    
	      <xsl:text>
	      </xsl:text> 	 
		  <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 128,FillColor.Green= 255,FillColor.Red= 128,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0,"  > 
               <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>
    			<xsl:attribute name = "subject">S.<xsl:value-of select="$factID"/></xsl:attribute>         
          </UML:DiagramElement>
	      <xsl:text>
          </xsl:text>  	     
                         	             
    	  <!--Start of responsibilityToResponsibility-->
          <xsl:for-each select="$responsibilityToResponsibility[parent=$factID or child=$factID]">
               <xsl:variable name="child" select="child"/>
               <xsl:variable name="parent" select="parent"/>
               <xsl:variable name="currentFactID" select="@factId"/>
                             
			   <xsl:comment>Start of responsibility = parent (responsibilityToResponsibility)</xsl:comment>              
               <xsl:if test="$parent=$factID">
					 <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
	                   <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>

					    <xsl:attribute name = "subject">S.<xsl:value-of select="$child"/></xsl:attribute>                 			                   
	                 </UML:DiagramElement>
			         <xsl:text>
		             </xsl:text>  	                 
			   </xsl:if>
			   <xsl:comment>End of responsibility = parent(responsibilityToResponsibility) </xsl:comment> 

			   <xsl:comment>Start of responsibility = child(responsibilityToResponsibility)</xsl:comment>   			   
               <xsl:if test="$child=$factID">
					 <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
	                    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
					    <xsl:attribute name = "subject">S.<xsl:value-of select="$parent"/></xsl:attribute>                 			                   
	                 </UML:DiagramElement>
			         <xsl:text>
		             </xsl:text>  	                 
				</xsl:if>	
			    <xsl:comment>End of responsibility = child(responsibilityToResponsibility) </xsl:comment>  												 
    	</xsl:for-each>
        <!--End of responsibilityToResponsibility-->    	
    	
        <!--Start of responsibilityRefinementRelation(association) -->          
        <xsl:for-each select="$responsibilityRefinement[parent=$factID or child=$factID]">
               <xsl:variable name="child" select="child"/>
               <xsl:variable name="parent" select="parent"/>
               <xsl:variable name="currentFactID" select="@factId"/>
              
			   <xsl:comment>Start of responsibility = parent(responsibilityRefinementRelation) </xsl:comment>              
               <xsl:if test="parent=$factID">
					 <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0,"  > 
	                   <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>

					    <xsl:attribute name = "subject">S.<xsl:value-of select="$child"/></xsl:attribute>                 			                   
	                 </UML:DiagramElement>
			         <xsl:text>
		             </xsl:text>    
			   </xsl:if>
			   <xsl:comment>End of responsibility = parent(responsibilityRefinementRelation) </xsl:comment> 

			   <xsl:comment>Start of responsibility = child(responsibilityRefinementRelation) </xsl:comment>   			   
               <xsl:if test="child=$factID">
					 <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
	                   <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>

					    <xsl:attribute name = "subject">S.<xsl:value-of select="$parent"/></xsl:attribute>                 			                   
	                 </UML:DiagramElement>
			         <xsl:text>
		             </xsl:text> 	                 
				</xsl:if>	
				<xsl:comment>End of responsibility = child(responsibilityRefinementRelation) </xsl:comment>  		
     			<UML:DiagramElement style="Association:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153," > 
   				    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   					<xsl:attribute name = "subject">G.<xsl:value-of select="$currentFactID"/></xsl:attribute>
         			 </UML:DiagramElement>
   		             <xsl:text>
   	                 </xsl:text>      			 
	   				 <UML:DiagramElement style="Role" > 
	   				    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

	   					<xsl:attribute name = "subject">G.<xsl:value-of select="$currentFactID"/>.1</xsl:attribute>        			
	         	</UML:DiagramElement>
	   		    <xsl:text>
	   	        </xsl:text>      			 
	   			<UML:DiagramElement style="Role" > 
	   			    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>  
	   				<xsl:attribute name = "subject">G.<xsl:value-of select="$currentFactID"/>.2</xsl:attribute>        			
	         	</UML:DiagramElement> 
	   		    <xsl:text>
	   	        </xsl:text> 					        															 
    	</xsl:for-each>      			             
        <!--End of responsibilityRefinementRelation(association) --> 
            	

        <!--Start of creating diagram elements for sequenceRelation(association) -->          
        <xsl:for-each select="$sequenceRelation[parent=$factID]">
               <xsl:variable name="child" select="child"/>
               <xsl:variable name="parent" select="parent"/>
               <xsl:variable name="currentFactID" select="@factId"/>
              
			   <xsl:comment>Start of responsibility = child </xsl:comment>   			   
               <xsl:if test="child=$factID">
					 <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
	                    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>
					    <xsl:attribute name = "subject">S.<xsl:value-of select="$parent"/></xsl:attribute>                 			                   
	                 </UML:DiagramElement>
			         <xsl:text>
		             </xsl:text> 
		             <xsl:for-each select="$responsibilityRefinement[parent=$parent]">
		             <xsl:variable name="currentChild" select="child" />
			               <xsl:if test="$currentChild!=$factID">
								 <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0,"  > 
				                   <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>

								    <xsl:attribute name = "subject">S.<xsl:value-of select="$currentChild"/></xsl:attribute>                 			                   
				                 </UML:DiagramElement>
						         <xsl:text>
					             </xsl:text>  	
				            </xsl:if>                   	              
		              </xsl:for-each> 	                 
			   </xsl:if>	
			   <xsl:comment>End of responsibility = child </xsl:comment>  	    

     		   <UML:DiagramElement style="Association:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153," > 
   				    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   					<xsl:attribute name = "subject">G.<xsl:value-of select="$currentFactID"/></xsl:attribute>
         		 </UML:DiagramElement>
   		         <xsl:text>
   	             </xsl:text>      			 
   				 <UML:DiagramElement style="Role" > 
   				    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   					<xsl:attribute name = "subject">G.<xsl:value-of select="$currentFactID"/>.1</xsl:attribute>        			
         		</UML:DiagramElement>
   		        <xsl:text>
   	            </xsl:text>      			 
   				<UML:DiagramElement style="Role" > 
   				    <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   					<xsl:attribute name = "subject">G.<xsl:value-of select="$currentFactID"/>.2</xsl:attribute>        			
         		</UML:DiagramElement> 
   		        <xsl:text>
   	            </xsl:text> 			        			 
      	</xsl:for-each>	      			             
        <!--End of creating comments on sequenceRelation(association) -->  
     	
     	<xsl:for-each select="$responsibilityToResponsibility[parent=$factID or child=$factID]">
     	    <xsl:variable name="respFactID" select="@factId"/>
                 <UML:DiagramElement style = 'ClassDependency' >
        			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
       			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
      			 </UML:DiagramElement>
		         <xsl:text>
	             </xsl:text>  

		        <!-- Start of comment on ProbabilityOutgoing -->
			    <xsl:for-each select="$p_ProbabilityOutgoing[owner=$respFactID]">       
					  <UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
			            <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>			          
			            <xsl:attribute name = "subject">XX.O.<xsl:value-of select="owner"/></xsl:attribute>
			          </UML:DiagramElement>
			          <xsl:text>
		              </xsl:text> 
			    </xsl:for-each >
		        <!-- End of comment on ProbabilityOutgoing -->
		    
		        <!-- Start of comment on ProbabilityIncoming -->
			    <xsl:for-each select="$p_ProbabilityIncoming[owner=$respFactID]">       
					  <UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
			            <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>			          
			            <xsl:attribute name = "subject">XX.I.<xsl:value-of select="owner"/></xsl:attribute>
			          </UML:DiagramElement>
			          <xsl:text>
		              </xsl:text> 
			    </xsl:for-each >
		        <!-- End of comment on ProbabilityIncoming -->                 			       	     			   	         			
        </xsl:for-each>   
  		 <!--xsl:for-each select="$subTask[responsibility=$factID]">
             <UML:DiagramElement style = 'CategoryDependency' >
    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
  			 </UML:DiagramElement>
             <xsl:text>
             </xsl:text>      			       	     			   	         			
         </xsl:for-each-->            

     	<xsl:for-each select="$moduleInterface[name=$name]">
  			    <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1,ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
       			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 

       			 	<xsl:attribute name = "subject">S.<xsl:value-of select="@factId"/></xsl:attribute>
      			 </UML:DiagramElement>
	         	 <xsl:text>
              	 </xsl:text>      			 

 			     <UML:DiagramElement style="RealizeRelation"> 
       			 	<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>  
       			 	<xsl:attribute name = "subject">XX.Dummy.<xsl:value-of select="@factId"/></xsl:attribute> 
      			 </UML:DiagramElement>
	         	 <xsl:text>
             	 </xsl:text>
             	 
  				 <UML:DiagramElement style="DependencyRelation" > 
       			    <xsl:attribute name = "xmi.id">id.<xsl:value-of select="generate-id()"/></xsl:attribute> 
       			    <xsl:attribute name = "subject">XX.Dummy.<xsl:value-of select="@factId"/></xsl:attribute>
      			 </UML:DiagramElement>
	             <xsl:text>
                 </xsl:text>      			       	     			   	         			
        </xsl:for-each>              


        <xsl:comment>Start of P_CostOfChange</xsl:comment>
    		<!--xsl:for-each select="$p_CostOfChange[owner=$factID]"-->
    		<xsl:if test="$p_CostOfChange[owner=$factID]!='' or $p_ExecutionTime[owner=$factID]!=''"> 
   			  <UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255,FillColor.Transparent=1," > 
                <xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute> 
			    <xsl:attribute name = "subject">XX.<xsl:value-of select="$factID"/></xsl:attribute>                 			                   
             </UML:DiagramElement>     			  
          <xsl:text>
          </xsl:text>         		
     	  </xsl:if>
     	<xsl:comment>End of P_CostOfChange</xsl:comment>
             
        </UML:Diagram.element>  
	    <xsl:text>
        </xsl:text>           
  	 </UML:Diagram>  
	 <xsl:text>
     </xsl:text>        		
</xsl:template>

<xsl:template name="taskDiagramTemplate" match="ArchE/Task">
	   <UML:Diagram name = 'Main' toolName = 'Rational Rose 98' diagramType = 'ModuleDiagram' 
	   style = '' owner = 'S.subsystem' >
        <xsl:attribute name = "xmi.id"><xsl:value-of select="generate-id()"/></xsl:attribute> 
        <UML:Diagram.element>
	    <xsl:text>
	    </xsl:text> 	        
        <!--Start of creating diagram elements for modules -->
             <xsl:for-each select="$task">
               <xsl:variable name="factID" select="@factId"/>
                 <UML:DiagramElement style = 'Module' >
        			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute> 
       			 	<xsl:attribute name = "subject">S.<xsl:value-of select="$factID"/></xsl:attribute>
      			 </UML:DiagramElement>	
	             <xsl:text>
                 </xsl:text>      			 		  	         			
             </xsl:for-each> 
         <!--End of creating diagram elements for modules -->
	     
	     <!--Start of creating diagram elements for relation between task and subtask -->
		 <!--TODO:: VisibilityRelation is likely to be changed later based on other changes in mapping between resopnsibilities and tasks -->
	       <xsl:for-each select="$subTask">
 	         <UML:DiagramElement style="VisibilityRelation" >
    			<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="@factId"/></xsl:attribute>  
   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
	        </UML:DiagramElement> 
	        <xsl:text>
            </xsl:text>	        
	      </xsl:for-each>
	     <!--End of creating diagram elements for relation between task and subtask -->
	     
	     <!--Start of creating diagram elements for comments -->
			<!--xsl:for-each select="$subTask">
               <xsl:variable name="child" select="@factId"/>
               <xsl:variable name="parent" select="task"/>
				<UML:DiagramElement style="Note:Font.Blue= 0,Font.Green= 0,Font.Red= 0,
				Font.FaceName=Arial,Font.Size= 10, Font.Bold=0,Font.Italic=0,Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51, LineColor.Green= 0,LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255, FillColor.Red= 255,FillColor.Transparent=1," >     
				<xsl:attribute name = "xmi.id">XX.<xsl:value-of select="generate-id()"/></xsl:attribute>	
       			<xsl:attribute name = "subject">S.<xsl:value-of select="$parent"/></xsl:attribute>    						 
  			   </UML:DiagramElement> 
	           <xsl:text>
               </xsl:text>  			    
           </xsl:for-each-->   			   
         <!--End of creating diagram elements for for comments --> 

         <!--Start of creating comments on modulerefinementrelation -->
         <!--xsl:for-each select="$moduleRefinement">
           <UML:DiagramElement style="Font.Blue= 0,Font.Green= 0,Font.Red= 0,Font.FaceName=Arial,Font.Size= 10,Font.Bold=0,Font.Italic=0, Font.Strikethrough=0,Font.Underline=0,LineColor.Blue= 51,LineColor.Green= 0, LineColor.Red= 153,FillColor.Blue= 204,FillColor.Green= 255,FillColor.Red= 255, FillColor.Transparent=1,AutomaticResize=1,ShowAllAttributes=1,ShowAllOperations=1, ShowOperationSignature=0,SuppressAttributes=0,SuppressOperations=0," > 
    			<xsl:attribute name = "xmi.id"><xsl:value-of select="generate-id()"/></xsl:attribute>  
   			 	<xsl:attribute name = "subject">G.<xsl:value-of select="@factId"/></xsl:attribute>
  	      </UML:DiagramElement> 
	      <xsl:text>
          </xsl:text>  	      
  	    </xsl:for-each-->                       
        <!--End of creating comments on modulerefinementrelation -->
        </UML:Diagram.element>  
	    <xsl:text>
        </xsl:text>           
  	 </UML:Diagram> 	
	 <xsl:text>
     </xsl:text>  	  
</xsl:template>

<xsl:template name="taskDependencyTemplate" match="ArchE/Task">
    <xsl:for-each select="$subTask">
		<UML:Dependency visibility = 'public' name='' isSpecification='false'>
		  <xsl:attribute name="xmi.id">G.<xsl:value-of select="@factId"/></xsl:attribute>
		  <xsl:attribute name="client">S.<xsl:value-of select="task"/></xsl:attribute>
		  <xsl:attribute name="supplier">S.<xsl:value-of select="responsibility"/></xsl:attribute>            
		</UML:Dependency>
	    <xsl:text>
	    </xsl:text>	
	</xsl:for-each>  
</xsl:template> 
</xsl:stylesheet>