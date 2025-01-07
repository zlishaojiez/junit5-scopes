package cn.shaojiel.junit5.registration;

public class WebClient implements AutoCloseable {

    public WebResponse get(String string) {
        return new WebResponse();
    }

    @Override
    public void close() {
        /* no-op for demo */
    }
}
