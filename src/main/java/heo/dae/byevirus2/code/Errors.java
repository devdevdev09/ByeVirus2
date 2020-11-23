package heo.dae.byevirus2.code;

public enum Errors {
    SERVICE_KET_NOT_REGISTERED("99", "서비스키가 등록되지 않았습니다.");

    public String code;
    public String msg;

    Errors(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
