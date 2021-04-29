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
import com.fluig.sdk.service.SecurityService;

// http://<URL_FLUIG>:<PORTA_FLUIG>/portal_form_feedback/api/rest/treinamento/primeiraAPI

@Path("/treinamento")
public class TreinamentoRest extends FluigRest {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/primeiraAPI")
    public Response primeiraAPI() {
        // https://api.fluig.com/old/sdk/com/fluig/sdk/api/FluigAPI.html
        
        try{
            Long empresa = new FluigAPI().getSecurityService().getCurrentTenantId();
            return super.buildSuccessResponse("empresa: " + empresa);
        } catch (SDKException e) {
            return super.buildSuccessResponse("Erro: " + e);
        }
    }
}