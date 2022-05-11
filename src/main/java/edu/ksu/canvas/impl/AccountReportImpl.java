package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AccountReportReader;
import edu.ksu.canvas.interfaces.AccountReportWriter;
import edu.ksu.canvas.model.report.AccountReport;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.AccountReportOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountReportImpl extends BaseImpl<AccountReport, AccountReportReader, AccountReportWriter> implements AccountReportReader, AccountReportWriter {
    private static final Logger LOG = LoggerFactory.getLogger(AccountReportImpl.class);

    public AccountReportImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
														 int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public List<AccountReport> listReports(String accountId, String report) throws IOException {
        if(StringUtils.isBlank(report)) {
            throw new IllegalArgumentException("You must specify the report you want to run.");
        }
        LOG.debug("Retrieving information about all {} reports for account {}", report, accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports/" + report, Collections.emptyMap());

        return getListFromCanvas(url);
    }

    @Override
    public Optional<AccountReport> reportStatus(String accountId, String report, Long id) throws IOException {
        LOG.debug("Retrieving information about report ID {} of report {} for account {}", id, report, accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports/" + report + "/" + id, Collections.emptyMap());

        return getFromCanvas(url);
    }

    @Override
    public Optional<AccountReport> startReport(AccountReportOptions options) throws IOException {
        LOG.debug("Starting new report of type {} for account {}", options.getReportType(), options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/reports/" + options.getReportType(), Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(authorizationToken, url, options.getOptionsMap());
        return responseParser.parseToObject(objectType(), response);
    }

    @Override
    public Optional<AccountReport> deleteReport(String accountId, String report, Long reportId) throws IOException {
        LOG.debug("Deleting report ID {} for report {} on behalf of account {}", reportId, report, accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports/" + report + "/" + reportId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(authorizationToken, url, Collections.emptyMap());
        return responseParser.parseToObject(objectType(), response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AccountReport>>() {
        }.getType();
    }

    @Override
    protected Class<AccountReport> objectType() {
        return AccountReport.class;
    }
}
