<#import "/wcm.ftl" as wcm/>
 
<!-- Adicionar o menu do fluig somente quando estiver logado no sistema -->
<#if user.login?has_content>
  <@wcm.header />
</#if>

<!-- WCM Wrapper content -->
<div class="wcm-wrapper-content">
	 
	 <!-- 
    <@wcm.menu />
 	 -->
 	     
    <!-- Wrapper -->
    <div class="wcm-all-content fluig-style-guide">
	    
	    <#if user.login?has_content>
	    	<!-- Adicionar um padding top de 60px para o menu não ficar por baixo do menu do fluig -->
		   <div id="wcm-content" class="clearfix wcm-background" style="padding-top: 60px;">
		   <#else>
		   <div id="wcm-content" class="clearfix wcm-background">
		</#if>

    		<div class="row">
				<div class="col-md-12">
					<div class="editable-slot slotfull layout-1-1" id="slotContainer001">
					    <@wcm.renderSlot id="Slot001" decorator="false" editableSlot="true" />
					</div>
				</div>
    		</div>

			<div class="row">
				<div class="col-md-12">
					<div class="editable-slot slotfull layout-1-1" id="slotContainer002">
					    <@wcm.renderSlot id="Slot002" decorator="false" editableSlot="true" />
					</div>
				</div>
	   		</div>
    	
    		<div class="row">
				<div class="col-md-12">
					<div class="editable-slot slotfull layout-1-1" id="slotContainer003">
					    <@wcm.renderSlot id="Slot003" decorator="false" editableSlot="true" />
					</div>
				</div>
	   		</div>
	    		
	  		<div class="row">
				<div class="col-md-12">
					<div class="editable-slot slotfull layout-1-1" id="slotContainer004">
					    <@wcm.renderSlot id="Slot004" decorator="false" editableSlot="true" />
					</div>
				</div>
	   		</div>
	          
	        <@wcm.footer layoutuserlabel="wcm.layoutdefault.user" />
		</div>
    </div>
</div>
