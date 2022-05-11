package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.QuizQuestionReader;
import edu.ksu.canvas.interfaces.QuizQuestionWriter;
import edu.ksu.canvas.model.assignment.QuizQuestion;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.GetQuizQuestionsOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class QuizQuestionImpl extends BaseImpl<QuizQuestion, QuizQuestionReader, QuizQuestionWriter> implements QuizQuestionReader, QuizQuestionWriter {
    private static final Logger LOG = LoggerFactory.getLogger(QuizQuestionImpl.class);

    public QuizQuestionImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
														int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<QuizQuestion> getQuizQuestions(GetQuizQuestionsOptions options) throws IOException {
        LOG.debug("Fetching quiz questions for quiz {} in course {}", options.getQuizId(), options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/quizzes/" + options.getQuizId() + "/questions",
                options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public boolean deleteQuizQuestion(String courseId, Long quizId, Long questionId) throws IOException {
        LOG.debug("Deleting quiz question in course {}, quiz {}, question {}", courseId, quizId, questionId);
        String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId + "/questions/" + questionId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(authorizationToken, url, Collections.emptyMap());
        int responseCode = response.getResponseCode();
        if (responseCode == 204) {
            return true;
        }
        LOG.error("Canvas returned code {} (success = 204) when deleting question {}", responseCode,  questionId);
        return false;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<QuizQuestion>>(){}.getType();
    }

    @Override
    protected Class<QuizQuestion> objectType() {
        return QuizQuestion.class;
    }
}
