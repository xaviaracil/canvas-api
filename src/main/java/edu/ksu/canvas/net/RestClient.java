package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidAuthTokenException;
import edu.ksu.canvas.net.auth.AuthorizationToken;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface RestClient {
    Response sendApiGet(@NotNull AuthorizationToken token, @NotNull String url, int connectTimeout, int readTimeout) throws IOException;
    Response sendJsonPost(@NotNull AuthorizationToken token, @NotNull String url, String json, int connectTimeout, int readTimeout) throws IOException;
    Response sendJsonPut(@NotNull AuthorizationToken token, @NotNull String url, String json, int connectTimeout, int readTimeout) throws IOException;
    Response sendApiPost(@NotNull AuthorizationToken token, @NotNull String url, Map<String, List<String>> postParameters, int connectTimeout, int readTimeout) throws InvalidAuthTokenException, IOException;
    Response sendApiPostFile(@NotNull AuthorizationToken token, @NotNull String url, Map<String, List<String>> postParameters, String fileParameter, String filePath, InputStream is, int connectTimeout, int readTimeout) throws InvalidAuthTokenException, IOException;
    Response sendApiDelete(@NotNull AuthorizationToken token, @NotNull String url, Map<String, List<String>> deleteParameters, int connectTimeout, int readTimeout) throws InvalidAuthTokenException, IOException;
    Response sendApiPut(@NotNull AuthorizationToken token, @NotNull String url, Map<String, List<String>> putParameters, int connectTimeout, int readTimeout) throws InvalidAuthTokenException, IOException;

    String sendUpload(String uploadUrl, Map<String, List<String>> params, InputStream in, String filename, int connectTimeout, int readTimeout) throws IOException;
}
