package cn.com.gome.page.excp;

public class PageException extends RuntimeException{

    public PageException(String msg) {
        super(msg);
    }

    public PageException(String msg,Throwable cause) {
        super(msg,cause);
    }
}
