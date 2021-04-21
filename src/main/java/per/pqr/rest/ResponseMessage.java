package per.pqr.rest;

public class ResponseMessage<E> {

    public static final int code_OK    = 200;
    public static final int code_ERROR = 500;

    public static final String _OK    = "Okay";
    public static final String _ERROR = "Server exception";

    public static final ResponseMessage<String> message_OK    = ResponseMessage.buildMessage(_OK);
    public static final ResponseMessage<String> message_ERROR = ResponseMessage.buildErrorMessage(_ERROR);

    private int code;
    private String message;
    private E data;

    public static <E> ResponseMessage<E> buildMessage(E data) {
        ResponseMessage<E> message = new ResponseMessage<>();
        message.code    = code_OK;
        message.message = _OK;
        message.data    = data;
        return message;
    }

    public static <E> ResponseMessage<E> buildMessage(String _message, E _data) {
        ResponseMessage<E> message = new ResponseMessage<>();
        message.code    = code_OK;
        message.message = _message;
        message.data    = _data;
        return message;
    }

    public static <E> ResponseMessage<E> buildMessage(int _code, String _message, E _data) {
        ResponseMessage<E> message = new ResponseMessage<>();
        message.code    = _code;
        message.message = _message;
        message.data    = _data;
        return message;
    }

    public static ResponseMessage<String> buildErrorMessage(String _message) {
        ResponseMessage<String> message = new ResponseMessage<>();
        message.code    = code_ERROR;
        message.message = _message;
        return message;
    }

    public static ResponseMessage<String> buildErrorMessage(int _code, String _message) {
        ResponseMessage<String> message = new ResponseMessage<>();
        message.code    = _code;
        message.message = _message;
        return message;
    }

    public int    getCode   () { return code   ; }
    public String getMessage() { return message; }
    public E      getData   () { return data   ; }

    public void setCode   (int    code   ) { this.code    = code   ; }
    public void setMessage(String message) { this.message = message; }
    public void setData   (E      data   ) { this.data    = data   ; }

    @Override
    public String toString() {
        return "{\"code\":"      + code    +
                ",\"message\":\"" + message +
                "\",\"data\":"    + data    +
                '}';
    }
}
