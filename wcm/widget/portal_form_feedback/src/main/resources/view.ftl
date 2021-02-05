<div id="portalFormFeedback_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" data-params="portalFormFeedback.instance()">
	
	<div id="conteudoFormFeedback_${instanceId}" class="row fs-xl-margin">
		
		<div class="panel panel-default col-md-6 col-md-offset-3">
		    <div class="panel-heading">
		        <h3 class="panel-title">${i18n.getTranslation('panel.title')}</h3>
		    </div>
		    
		    <div class="panel-body">
		        
		        <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label for="txt_nome_${instanceId}">${i18n.getTranslation('campo.nome')}</label>
							<input type="text" name="txt_nome_${instanceId}" id="txt_nome_${instanceId}" class="form-control">
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="txt_email_${instanceId}">${i18n.getTranslation('campo.email')}</label>
							<input type="text" name="txt_email_${instanceId}" id="txt_email_${instanceId}" class="form-control">
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label for="txt_feedback_${instanceId}">${i18n.getTranslation('campo.feeback')}</label>
							<textarea name="txt_feedback_${instanceId}" id="txt_feedback_${instanceId}" class="form-control" rows="5"></textarea>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6 col-md-offset-3 text-center">
						<button type="button" class="btn btn-success" data-enviarFeedback>${i18n.getTranslation('btn.enviar')}</button>
					</div>
				</div>
				
		    </div>
		</div>
		
	</div>
	
</div>
