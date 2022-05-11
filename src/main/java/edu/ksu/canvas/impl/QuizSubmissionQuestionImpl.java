package edu.ksu.canvas.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizSubmissionQuestionReader;
import edu.ksu.canvas.interfaces.QuizSubmissionQuestionWriter;
import edu.ksu.canvas.model.assignment.QuizAnswer;
import edu.ksu.canvas.model.assignment.QuizSubmissionQuestion;
import edu.ksu.canvas.model.wrapper.QuizSubmissionQuestionWrapper;
import edu.ksu.canvas.model.wrapper.QuizSubmissionWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.AnswerQuizQuestionOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class QuizSubmissionQuestionImpl extends BaseImpl<QuizSubmissionQuestion, QuizSubmissionQuestionReader, QuizSubmissionQuestionWriter> implements QuizSubmissionQuestionReader, QuizSubmissionQuestionWriter {
    private static final Logger LOG = LoggerFactory.getLogger(QuizSubmissionQuestionImpl.class);

    public QuizSubmissionQuestionImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
																			int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<QuizSubmissionQuestion> answerQuestions(AnswerQuizQuestionOptions options, String answerArrayJson) throws IOException {
        if(options == null || answerArrayJson == null) {
            throw new IllegalArgumentException("options and answers must not be null");
        }
        LOG.debug("answering questions for quiz submission: {}", options.getQuizSubmissionid());
        String url = buildCanvasUrl("quiz_submissions/" + options.getQuizSubmissionid() + "/questions", options.getOptionsMap());
        JsonObject requestBody = new JsonObject();

        //Setup the quiz question array that Canvas requires
        JsonParser parser = new JsonParser();
        JsonArray answerJson = (JsonArray) parser.parse(answerArrayJson); //handling escaped serialization
        requestBody.add("quiz_questions", answerJson);
        Response response = canvasMessenger.sendJsonPostToCanvas(authorizationToken, url, requestBody);
        if(response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.error("Error answering questions. Returning null");
            LOG.debug(response.getContent());
            return null;
        }

        Type responseType = new TypeToken<QuizSubmissionWrapper>() {
        }.getType();
        Gson responseGson = new GsonBuilder().registerTypeAdapter(responseType, new QuizSubmissionQuestionTypeAdapter()).create();
        QuizSubmissionQuestionWrapper wrapper = responseGson.fromJson(response.getContent(), responseType);

        return wrapper.getQuizSubmissionQuestions();
    }

    /**
     * Type adapter for parsing out QuizSubmissionQuestions from JSON
     */
    private static class QuizSubmissionQuestionTypeAdapter implements JsonDeserializer<QuizSubmissionQuestionWrapper> {

        @Override
        public QuizSubmissionQuestionWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            QuizSubmissionQuestionWrapper wrapper = new QuizSubmissionQuestionWrapper();
            if (json.getAsJsonObject().get("quiz_submission_questions").isJsonArray()) {
                JsonArray questionArray = json.getAsJsonObject().getAsJsonArray("quiz_submission_questions");
                List<QuizSubmissionQuestion> questionList = new LinkedList<>();
                for (JsonElement question : questionArray) {
                    QuizSubmissionQuestion newQuestion = new QuizSubmissionQuestion();
                    JsonObject questionObject = question.getAsJsonObject();
                    newQuestion.setId(questionObject.has("id") ? questionObject.get("id").getAsLong() : null);
                    newQuestion.setFlagged(questionObject.has("flagged") ? questionObject.get("flagged").getAsBoolean() : null);

                    List<Long> answerList = new LinkedList<>();
                    if (questionObject.has("answer")) {
                        if (questionObject.get("answer").isJsonArray()) {
                            for (JsonElement answer : questionObject.getAsJsonArray("answer")) {
                                answerList.add(answer.getAsLong());
                            }
                        } else answerList.add(questionObject.get("answer").getAsLong());
                    }
                    newQuestion.setAnswer(answerList);

                    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                    Type listType = new TypeToken<List<QuizAnswer>>() {
                    }.getType();
                    newQuestion.setAnswers(gson.fromJson(questionObject.get("answers").toString(), listType));

                    questionList.add(newQuestion);

                }
                wrapper.setQuizsubmissionquestions(questionList);
            }
            return wrapper;
        }

    }

    @Override
    protected Type listType() {
        return new TypeToken<List<QuizSubmissionQuestion>>(){}.getType();
    }

    @Override
    protected Class<QuizSubmissionQuestion> objectType() {
        return QuizSubmissionQuestion.class;
    }

}
