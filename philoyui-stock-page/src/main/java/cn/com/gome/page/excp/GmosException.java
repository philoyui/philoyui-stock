package cn.com.gome.page.excp;

public class GmosException extends RuntimeException {

    private int exceptionCode;

    public GmosException(String msg) {
        super(msg);
    }

    public GmosException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public GmosException(int exceptionCode, String msg) {
        this(msg);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return this.exceptionCode;
    }

    public void setExceptionCode(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
