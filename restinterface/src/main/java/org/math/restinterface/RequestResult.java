package org.math.restinterface;

class RequestResult {

    private String status;
    private String item;

    public RequestResult() {
        this.status = "";
        this.item = null;
    }

    public RequestResult(String status, String item) {
        this.status = status;
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public String getItem() {
        return item;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
