package zstu.domain;

public class administrator {
    private  int id;
    private String name;
    private  String password;
    private  String tel;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassward() {
        return password;
    }

    public String getTel() {
        return tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassward(String passward) {
        this.password = passward;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "administrator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passward='" + password+ '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
