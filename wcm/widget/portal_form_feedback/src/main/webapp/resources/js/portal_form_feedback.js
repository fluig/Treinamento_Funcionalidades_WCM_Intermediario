var portalFormFeedback = SuperWidget.extend({
    //variáveis da widget
    variavelNumerica: null,
    variavelCaracter: null,

    //método iniciado quando a widget é carregada
    init: function() {
    },
  
    //BIND de eventos
    bindings: {
        local: {
            'enviarFeedback': ['click_initProcessFeedback']
        },
        global: {}
    },
 
    initProcessFeedback: function () {
        var nome = $("#txt_nome_"+this.instanceId).val();
        var email = $("#txt_email_"+this.instanceId).val();
        var feedback = $("#txt_feedback_"+this.instanceId).val();

        var url = WCMAPI.getServerURL() + '/portal_form_feedback/api/rest/feedback/enviar';
        var data = {
        		'nome': nome,
        		'email': email,
        		'feedback': feedback
        };

        WCMAPI.Read({
            type: "POST",
            url: url,
            async: true,
            data: JSON.stringify(data),
            success: function funcao(data) {
                console.log(data);

                FLUIGC.toast({
                	title: '${i18n.getTranslation("btn.enviar.success.title")}',
                	message: '${i18n.getTranslation("btn.enviar.success.descr")}',
                	type:"success"
                });
            },
            
            error: function (requestObject, error, errorThrown) {
                console.log(requestObject);
                console.log(error);
                console.log(errorThrown);
                
                FLUIGC.toast({
                	title: '${i18n.getTranslation("btn.enviar.erro.title")}',
                	message: '${i18n.getTranslation("btn.enviar.erro.descr")}',
                	type:"danger"
                });
            }
        });
    }

});

