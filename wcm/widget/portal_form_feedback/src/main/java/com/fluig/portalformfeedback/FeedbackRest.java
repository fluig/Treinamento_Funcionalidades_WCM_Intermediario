package com.fluig.portalformfeedback;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import com.fluig.customappkey.Keyring;
import com.fluig.sdk.api.customappkey.KeyVO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fluig.sdk.api.FluigAPI;
import com.fluig.sdk.api.common.SDKException;
import com.fluig.sdk.api.social.PostVO;
import com.fluig.sdk.service.PostService;
import com.fluig.sdk.service.UserService;
import com.fluig.sdk.user.UserVO;
import com.fluig.sdk.web.FluigRest;

// http://<URL_FLUIG>:<PORTA_FLUIG>/portal_form_feedback/api/rest/feedback/helloWorld

@Path("/feedback")
public class FeedbackRest extends FluigRest {
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/helloWorld")
    public Response helloWorld() {
    	// Retornar o nome do usuário logado
        try {
            UserService userService = new FluigAPI().getUserService();
            UserVO currentUser = userService.getCurrent();
            return super.buildSuccessResponse(currentUser.getFullName());
        } catch (SDKException e) {
            return super.buildErrorResponse(e);
        }
    }
    
    @POST
    @Path("/enviar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response iniciarProcessoFeedback (String params) throws Exception {
    	
    	// Transformar o corpo da requisição em um Objeto JSON
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(params);
        
        // Capturar os parametros enviados a partir do Objeto JSON
        String nome = (String) obj.get("nome"); 
        String email = (String) obj.get("email"); 
        String feedback = (String) obj.get("feedback");
        
        // Definir atividade destino
        String atividadeDestino = "2";

        // Definir usuário destino
        String usuarioDestino = "treinamento.aluno";

        // Comentario que será inserido no processo ao iniciar a solicitação
        String comentarios = "Iniciado via pagina portal";
        
        // Código da empresa corrente        
        int cod_empresa = 1;
        Long empresa = new Long ((long) 1);
             
        // Código do processo que sera iniciado
        String cod_processo = "portal_avaliar_feedback";
        
    	
        try {
        	
            KeyVO key = Keyring.getKeys(empresa, RestConstant.APP_KEY);
        	OAuthConsumer config = config(key);	
        	
            URL url = new URL( new FluigAPI().getPageService().getServerURL() +"/process-management/api/v2/processes/"+cod_processo+"/start");
            
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(RestConstant.REQUEST_METHOD_POST);
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            config.sign(conn);
            

            String json = 
            "{"
       		+ "\"targetState\": "+atividadeDestino+","
       		+ "\"targetAssignee\": \""+usuarioDestino+"\","
       		+ "\"comment\": \""+comentarios+"\","
       		+ "\"formFields\": "
        		+ "{  "
        			+ "\"txt_nome\": \""+nome+"\",  "
        			+ "\"txt_email\": \""+email+"\",  "
        			+ "\"txt_feedback\": \""+feedback+"\"  "
        		+ "}"
           + "}";

            
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json);
            wr.flush();
            wr.close();
            conn.connect();

            Reader inputCreateUser = new BufferedReader(new InputStreamReader(conn.getInputStream(), RestConstant.UTF_8_ENCODE));
            String result = "";
            for (int c = inputCreateUser.read(); c != -1; c = inputCreateUser.read()) {
            	result += (char) c;
            }
            int code = conn.getResponseCode();
            
            conn.disconnect();
            
        	return super.buildSuccessResponse(result);
        } catch (Exception e) {
            e.printStackTrace();
            return super.buildErrorResponse(e);
        }
        
    }
    
    private OAuthConsumer config(KeyVO key) {
        OAuthConsumer consumer = new DefaultOAuthConsumer(key.getConsumerKey(), key.getConsumerSecret());
        consumer.setTokenWithSecret(key.getToken(), key.getTokenSecret());
        return consumer;
    }
}