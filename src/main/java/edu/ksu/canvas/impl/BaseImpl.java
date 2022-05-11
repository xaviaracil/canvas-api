package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.net.UrlEscapers;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.interfaces.CanvasReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Base class for accessing the Canvas API
 */

@SuppressWarnings("unchecked")
public abstract class BaseImpl<T, READERTYPE extends CanvasReader, WRITERTYPE extends CanvasWriter> implements CanvasReader<T, READERTYPE>, CanvasWriter<T,WRITERTYPE>{
    private static final Logger LOG = LoggerFactory.getLogger(BaseImpl.class);

    protected String canvasBaseUrl;
    protected Integer apiVersion;
    protected AuthorizationToken authorizationToken;
    protected ResponseParser responseParser;
    protected CanvasMessenger canvasMessenger;
    protected Consumer<List<T>> responseCallback;
    protected String masqueradeAs;
    protected String masqueradeType;
    protected Integer paginationPageSize;
    protected Boolean serializeNulls = false;

    /**
     * Construct a new CanvasApi class with an OAuth token
     * @param canvasBaseUrl The base URL of your canvas instance
     * @param apiVersion The version of the Canvas API (currently 1)
     * @param authorizationToken Auth token to use when executing API calls
     * @param restClient a RestClient implementation to use when talking to Canvas
     * @param connectTimeout Timeout in seconds to use when connecting
     * @param readTimeout Timeout in seconds to use when waiting for data to come back from an open connection
     * @param paginationPageSize How many objects to request per page on paginated requests
     * @param serializeNulls Whether or not to include null fields in the serialized JSON. Defaults to false if null
     */
    public BaseImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
										int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        this.canvasBaseUrl = canvasBaseUrl;
        this.apiVersion = apiVersion;
        this.authorizationToken = authorizationToken;
        this.paginationPageSize = paginationPageSize;
        this.serializeNulls = BooleanUtils.isTrue(serializeNulls);
        responseParser = new GsonResponseParser();
        canvasMessenger = new RestCanvasMessenger(connectTimeout, readTimeout, restClient);
    }

    protected Optional<T> getFromCanvas(String url) throws IOException {
        Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.warn("Error {} on GET from url {}", response.getResponseCode(), url);
            throw new IOException("Error accessing url " + url);
        }
        return responseParser.parseToObject(objectType(), response);
    }

    protected List<T> getListFromCanvas(String url) throws IOException {
        Consumer<Response> consumer = null;
        if (responseCallback != null) {
            consumer = response -> responseCallback.accept(responseParser.parseToList(listType(), response));
        }
        List<Response> responses = canvasMessenger.getFromCanvas(authorizationToken, url, consumer);
        responseCallback = null;
        return parseListOfResponses(responses);
    }

    @Override
    public READERTYPE withCallback(Consumer<List<T>> responseReceivedCallBack) {
        responseCallback = responseReceivedCallBack;
        return (READERTYPE) this;
    }

    @Override
    public READERTYPE readAsCanvasUser(String masqueradeAs) {
        return readAsUser(masqueradeAs, CanvasConstants.MASQUERADE_CANVAS_USER);
    }

    @Override
    public READERTYPE readAsSisUser(String masqueradeAs) {
        return readAsUser(masqueradeAs, CanvasConstants.MASQUERADE_SIS_USER);
    }

    private READERTYPE readAsUser(String masqueradeAs, String masqueradeType){
        this.masqueradeAs = masqueradeAs;
        this.masqueradeType = masqueradeType;
        return (READERTYPE) this;
    }

    @Override
    public WRITERTYPE writeAsCanvasUser(String masqueradeAs) {
        return writeAsUser(masqueradeAs, CanvasConstants.MASQUERADE_CANVAS_USER);
    }

    @Override
    public WRITERTYPE writeAsSisUser(String masqueradeAs) {
        return writeAsUser(masqueradeAs, CanvasConstants.MASQUERADE_SIS_USER);
    }

    private WRITERTYPE writeAsUser(String masqueradeAs, String masqueradeType){
        this.masqueradeAs = masqueradeAs;
        this.masqueradeType = masqueradeType;
        return (WRITERTYPE) this;
    }

    protected String encode(String value) {
        return UrlEscapers.urlPathSegmentEscaper().escape(value);
    }

    protected String buildCanvasUrl(String canvasMethod, Map<String, List<String>> parameters) {
        Map<String, List<String>> allParameters = new HashMap<>();
        allParameters.putAll(parameters);

        // Add in possible URL parameters for masquerading and pagination page size
        if (StringUtils.isNotBlank(masqueradeAs)) {
            if(CanvasConstants.MASQUERADE_CANVAS_USER.equals(masqueradeType)) {
                allParameters.put("as_user_id", Arrays.asList(masqueradeAs));
            } else if(CanvasConstants.MASQUERADE_SIS_USER.equals(masqueradeType)) {
                allParameters.put("as_user_id", Arrays.asList("sis_user_id:" + masqueradeAs));
            }
            //Since masquerading options are added on a per-call basis, blank them out after using them for this call
            masqueradeAs = null;
            masqueradeType = null;
        }
        if(paginationPageSize != null) {
            allParameters.put("per_page", Arrays.asList(paginationPageSize.toString()));
        }

        Map<String, List<String>> nonEmptyParams = stripEmptyParams(allParameters);

        return CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, canvasMethod, nonEmptyParams);
    }

    /**
     * Returns URL parameters after removing any that have an empty list in them.
     * For optional list arguments, we pass an empty list to the API method. This generates
     * an entry like includes[]= with no value. This function strips them out so we only
     * pass parameters to Canvas that have a value to send along.
     */
    private Map<String, List<String>> stripEmptyParams(Map<String, List<String>> parameters) {
        Builder<String, List<String>> paramsBuilder = ImmutableMap.<String, List<String>>builder();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            if(entry.getValue() != null && !entry.getValue().isEmpty()) {
                paramsBuilder.put(entry.getKey(), entry.getValue());
            }
        }
        return paramsBuilder.build();
    }

    // Declaring this as a separate method fixes some of Java's type inference problems when using it in parseListOfResponses(..)
    private List<T> parseListResponse(Response response) {
        return responseParser.parseToList(listType(), response);
    }

    /*
     * Subclasses should return the type of a list that will be parsed by gson when using call that returns lists.
     * For example, CourseReaderImpl returns 'TypeToken<List<Course>>(){}.getType();'
     */
    protected abstract Type listType();

    /*
     * Subclasses should return the type of model that will be parsed by gson when using a call that returns a single
     * object. For example, CourseReaderImpl returns Course.class.
     */
    protected abstract Class<T> objectType();

    protected List<T> parseListOfResponses(List<Response> responses) {
        return responses
                .stream()
                .map(this::parseListResponse)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
