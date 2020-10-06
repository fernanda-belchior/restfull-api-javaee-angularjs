package br.com.fernanda.wsrestful.exceptions;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException> {

    @Override
    public Response toResponse(DaoException exception) {
        ErrorMessage error = new ErrorMessage();
        if (exception.getCode() == ErrorCode.BAD_REQUEST.getCode()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }else if (exception.getCode() == ErrorCode.NOT_FOUND.getCode()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
            }
        }
}


