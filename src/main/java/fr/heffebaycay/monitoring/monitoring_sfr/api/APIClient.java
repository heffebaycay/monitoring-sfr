package fr.heffebaycay.monitoring.monitoring_sfr.api;

import com.squareup.okhttp.*;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.Auth;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.NeufboxRequest;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth.CheckTokenResponse;
import fr.heffebaycay.monitoring.monitoring_sfr.api.model.auth.GetTokenResponse;
import fr.heffebaycay.monitoring.monitoring_sfr.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class APIClient {

    private static final Logger logger = LoggerFactory.getLogger(APIClient.class);

    protected String mHost;
    protected String mUsername;
    protected String mPassword;
    protected String mToken;

    public APIClient(String host, String username, String password) {
        mHost = host;
        mUsername = username;
        mPassword = password;
    }


    public String sendRequest(NeufboxRequest neufboxRequest) {
        OkHttpClient client = new OkHttpClient();

        String url = generateMethodURL(neufboxRequest);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        switch (neufboxRequest.getHttpMethod()) {
            case POST:
                FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();

                for(Map.Entry<String, String> param : neufboxRequest.getParams().entrySet()) {
                    formEncodingBuilder.add(param.getKey(), param.getValue());
                }

                RequestBody body = formEncodingBuilder.build();
                requestBuilder.post(body);
                break;
            default:
                requestBuilder.get();
                break;
        }

        Request webRequest = requestBuilder.build();

        try {
            Response response = client.newCall(webRequest).execute();
            return response.body().string();
        } catch (IOException e) {
            logger.error("Failed to send request:  {}", e);
            return null;
        }
    }

    protected String generateMethodURL(NeufboxRequest neufboxRequest) {

        String url = String.format("http://%1$s/api/1.0/?method=%2$s", mHost, neufboxRequest.getMethodName());

        if (mToken != null) {
            url += String.format("&token=%1$s", mToken);
        }

        if (neufboxRequest.getParams() != null && neufboxRequest.getParams().size() > 0) {
            // Parse params and add them to the URL
            StringBuilder urlBuilder = new StringBuilder(url);
            for(Map.Entry<String, String> param : neufboxRequest.getParams().entrySet()) {
                //
                urlBuilder.append(String.format("&%1$s=%2$s", param.getKey(), param.getValue()));
            }
            url = urlBuilder.toString();
        }

        return url;
    }

    public boolean authenticate() {

        Auth authInterface = new Auth(this);
        GetTokenResponse response = authInterface.getToken();
        if (response == null) {
            logger.error("Failed to get Token");
            return false;
        }

        logger.debug("Successfully got token. Auth method is {}", response.getAuth().getMethod());

        mToken = response.getAuth().getToken();

        StringBuilder hashBuilder = new StringBuilder();

        String userNameHash = IOUtils.computeSHA256(mUsername);
        hashBuilder.append(IOUtils.hmacDigestSHA256(userNameHash, mToken));

        String passwordHash = IOUtils.computeSHA256(mPassword);
        hashBuilder.append(IOUtils.hmacDigestSHA256(passwordHash, mToken));

        String hash = hashBuilder.toString();

        CheckTokenResponse checkTokenResponse = authInterface.checkToken(hash);
        if (checkTokenResponse != null && checkTokenResponse.getAuth() != null) {
            logger.info("Successfully authenticated. Token is {}", checkTokenResponse.getAuth().getToken());
        } else {
            logger.error("Failed to authenticate");
            if (checkTokenResponse != null && checkTokenResponse.getError() != null) {
                logger.error("Error message: {}", checkTokenResponse.getError().getMsg());
            }
            return false;
        }

        return true;
    }

}
