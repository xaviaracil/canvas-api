package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.ConversationReader;
import edu.ksu.canvas.interfaces.ConversationWriter;
import edu.ksu.canvas.model.Conversation;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.AddMessageToConversationOptions;
import edu.ksu.canvas.requestOptions.CreateConversationOptions;
import edu.ksu.canvas.requestOptions.GetSingleConversationOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConversationImpl extends BaseImpl<Conversation, ConversationReader, ConversationWriter> implements ConversationReader, ConversationWriter {
    private static final Logger LOG = LoggerFactory.getLogger(ConversationImpl.class);

    public ConversationImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
														int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Conversation>>(){}.getType();
    }

    @Override
    protected Class<Conversation> objectType() {
        return Conversation.class;
    }

    @Override
    public Optional<Conversation> getSingleConversation(GetSingleConversationOptions options) throws IOException {
        LOG.debug("getting single conversation: {}", options.getConversationId());
        String url = buildCanvasUrl("conversations/" + options.getConversationId(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
        return responseParser.parseToObject(Conversation.class, response);
    }

    @Override
    public List<Conversation> createConversation(CreateConversationOptions options) throws IOException {
        LOG.debug("Creating conversation");
        Map<String, List<String>> optionsMap = options.getOptionsMap();
        String url = buildCanvasUrl("conversations", optionsMap);
        Response response = canvasMessenger.sendToCanvas(authorizationToken, url, Collections.emptyMap());
        return responseParser.parseToList(listType(), response);
    }

    @Override
    public void markAllConversationsRead() throws IOException {
        LOG.debug("marking all conversations for user as read");
        String url = buildCanvasUrl("conversations/mark_all_as_read", Collections.emptyMap());
        canvasMessenger.sendToCanvas(authorizationToken, url, Collections.emptyMap());
    }

    @Override
    public Optional<Conversation> editConversation(Conversation conversation) throws IOException {
        LOG.debug("Editing conversation: {}", conversation.getId());
        String url = buildCanvasUrl("conversations/" + conversation.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(authorizationToken, url, conversation.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Conversation.class, response);
    }

    @Override
    public Optional<Conversation> addMessage(AddMessageToConversationOptions options) throws IOException {
        LOG.debug("Adding message to conversation: {}", options.getConversationId());
        String url = buildCanvasUrl("conversations/" + options.getConversationId() + "/add_message", options.getOptionsMap());
        Response response = canvasMessenger.sendToCanvas(authorizationToken, url, Collections.emptyMap());
        return responseParser.parseToObject(Conversation.class, response);
    }

}
