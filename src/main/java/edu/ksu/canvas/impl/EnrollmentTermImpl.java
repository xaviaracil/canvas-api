package edu.ksu.canvas.impl;


import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.EnrollmentTermReader;
import edu.ksu.canvas.interfaces.EnrollmentTermWriter;
import edu.ksu.canvas.model.EnrollmentTerm;
import edu.ksu.canvas.model.wrapper.EnrollmentTermWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.GetEnrollmentTermOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentTermImpl extends BaseImpl<EnrollmentTerm, EnrollmentTermReader, EnrollmentTermWriter> implements EnrollmentTermReader, EnrollmentTermWriter {
    private static final Logger LOG = LoggerFactory.getLogger(EnrollmentTermImpl.class);

    public EnrollmentTermImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
															int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<EnrollmentTerm> getEnrollmentTerms(GetEnrollmentTermOptions options) throws IOException {
        LOG.debug("getting enrollment term with account id {}", options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/terms/" , options.getOptionsMap());
        List<Response> response = canvasMessenger.getFromCanvas(authorizationToken, url);
        return parseEnrollmentTermList(response);
    }

    @Override
    public Optional<EnrollmentTerm> getEnrollmentTerm(String accountId, String termId) throws IOException {
        LOG.debug("getting enrollment term with account id {}", accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/terms/" +termId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
        return Optional.of(GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), EnrollmentTerm.class));
    }


    //Unfortunately we can't use the generic list parse methods in BaseImpl because Canvas wraps enrollment terms in
    //a useless object at the top level of the response JSON for no reason at all.
    private List<EnrollmentTerm> parseEnrollmentTermList(final List<Response> responses) {
        return responses.stream().
                map(this::parseEnrollmentTermList).
                flatMap(Collection::stream).
                collect(Collectors.toList());
    }

    private List<EnrollmentTerm> parseEnrollmentTermList(final Response response) {
        EnrollmentTermWrapper wrapper = GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), EnrollmentTermWrapper.class);
        return wrapper.getEnrollmentTerms();
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<EnrollmentTerm>>(){}.getType();
    }

    @Override
    protected Class<EnrollmentTerm> objectType() {
        return EnrollmentTerm.class;
    }

}
