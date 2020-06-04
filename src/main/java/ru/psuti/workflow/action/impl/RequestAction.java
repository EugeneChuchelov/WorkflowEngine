package ru.psuti.workflow.action.impl;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.action.ActionType;

import java.io.IOException;

public class RequestAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(RequestAction.class);

    private String url;

    public RequestAction(String url) {
        this.url = url;
    }

    @Override
    public boolean perform() {
        boolean isSent = true;

        HttpGet httpget = new HttpGet(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            httpclient.execute(httpget);
            log.debug("Sent request to {}", url);
        } catch (NoHttpResponseException e) {
            isSent = false;
            log.warn("No answer from url {}", url, e);
        } catch (ClientProtocolException e) {
            isSent = false;
            log.warn("Error in http protocol while requesting {}", url, e);
        } catch (IOException e) {
            isSent = false;
            log.error("Error while requesting {}", url, e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("Cold not close http client", e);
            }
        }

        return isSent;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public ActionType getType() {
        return ActionType.REQUEST;
    }
}
