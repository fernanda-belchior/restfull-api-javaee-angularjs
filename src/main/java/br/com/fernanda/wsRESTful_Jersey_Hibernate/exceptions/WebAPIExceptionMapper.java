package br.com.fernanda.wsRESTful_Jersey_Hibernate.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WebAPIExceptionMapper implements ExceptionMapper<WebApplicationException> {


    @Override
    public Response toResponse(WebApplicationException exception) {
        ErrorMessage error = new ErrorMessage(exception.getMessage(), exception.getResponse().getStatus());
        return Response.status((exception.getResponse().getStatus()))
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
