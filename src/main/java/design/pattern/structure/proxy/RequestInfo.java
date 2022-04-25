package design.pattern.structure.proxy;

public class RequestInfo {
    String requestType;
    long startTimestamp;
    long responseTime;

    public RequestInfo(String requestType, long startTimestamp, long responseTime) {
        this.requestType = requestType;
        this.startTimestamp = startTimestamp;
        this.responseTime = responseTime;
    }
}
