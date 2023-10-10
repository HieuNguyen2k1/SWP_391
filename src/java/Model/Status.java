package Model;

public enum Status {
    finished ("đã khám"),
    canceled ("đã huỷ"),
    not_yet ("chưa khám");
    final String msg;

    Status(String msg) {
        this.msg = msg;
    }
    public String getDetail(){
        return this.msg;
    }
}
