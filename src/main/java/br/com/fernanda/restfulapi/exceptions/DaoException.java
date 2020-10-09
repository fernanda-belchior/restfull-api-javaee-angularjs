package br.com.fernanda.restfulapi.exceptions;

public class DaoException extends RuntimeException {


    private static final long serialVersionUID;

    static {
        serialVersionUID = -5586453067070132015L;
    }

    private final int code;

    public DaoException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
