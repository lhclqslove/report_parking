package zstu.domain;

public class Report_infoRm {
    private  int report_id;
    private  String report_person_name;
    private  String report_person_tel;
    private  double latitude ;
    private  double longitude;
    private String pic1;
    private  String pic2;
    private  String pic3;
    private  String description;
    private  String date;
    private  String  license;
    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense() {
        return license;
    }

    public int getReport_id() {
        return report_id;
    }

    public String getReport_person_name() {
        return report_person_name;
    }

    public void setReport_person_name(String report_person_name) {
        this.report_person_name = report_person_name;
    }

    public String getReport_person_tel() {
        return report_person_tel;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPic1() {
        return pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }



    public void setReport_person_tel(String report_person_tel) {
        this.report_person_tel = report_person_tel;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Report_infoRm{" +
                "report_id=" + report_id +
                ", report_person_name=" + report_person_name +
                ", report_person_tel='" + report_person_tel + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pic1='" + pic1 + '\'' +
                ", pic2='" + pic2 + '\'' +
                ", pic3='" + pic3 + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}
