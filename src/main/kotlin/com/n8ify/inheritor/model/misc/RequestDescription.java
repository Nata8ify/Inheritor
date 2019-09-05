package com.n8ify.inheritor.model.misc;

import javax.servlet.http.HttpServletRequest;

/** Provide briefly information of incoming request with some <u>custom attributes</u>.
 * <br/>
 * <br/><b>Question</b> : What is custom attribute?
 * <br/><b>Answer</b> : An attribute which <i>HttpServletRequest</i> doesn't provided to you, But you may need these custom attributes to identified somethings or make them existed for some purposes.
 * */
public class RequestDescription {

    /** Request id for tagging any incoming request, Similar to session id but more way to define. */
    private String id;

    private HttpServletRequest request;
    // add more custom attribute here.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "RequestDescription{" +
                "id='" + id + '\'' +
                ", request=" + request +
                '}';
    }
}