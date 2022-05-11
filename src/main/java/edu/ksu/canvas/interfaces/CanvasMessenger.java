package edu.ksu.canvas.interfaces;

import com.google.gson.JsonObject;

import edu.ksu.canvas.exception.InvalidAuthTokenException;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.oauth.OauthToken;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface CanvasMessenger {
    List<Response> getFromCanvas(OauthToken oauthToken, String url) throws InvalidAuthTokenException, IOException;
    List<Response> getFromCanvas(OauthToken oauthToken, String url, Consumer<Response> consumer) throws InvalidAuthTokenException, IOException;
    //TODO: Should probably make this parameter list more sane
    Response sendToCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters) throws InvalidAuthTokenException, IOException;
    Response sendFileToCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters, String fileParameter, String filePath, InputStream is) throws InvalidAuthTokenException, IOException;
    Response sendJsonPostToCanvas(OauthToken oauthToken, String url, JsonObject requestBody) throws InvalidAuthTokenException, IOException;
    Response sendJsonPutToCanvas(OauthToken oauthToken, String url, JsonObject requestBody) throws InvalidAuthTokenException, IOException;
    Response deleteFromCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters) throws InvalidAuthTokenException, IOException;
    Response getSingleResponseFromCanvas(OauthToken oauthToken, String url) throws InvalidAuthTokenException, IOException;
    Response putToCanvas(OauthToken oauthToken, String url, Map<String, List<String>> parameters) throws InvalidAuthTokenException, IOException;

    String sendUpload(String uploadUrl, Map<String, List<String>> params, InputStream in, String filename) throws IOException;
}
