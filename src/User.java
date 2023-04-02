import java.util.Objects;
public class User {
    private int id; //	1
    private String name; //	"Leanne Graham"
    private String username; //	"Bret"
    private String email; //	"Sincere@april.biz"



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }




  //  address
    private String street;	//"Kulas Light"
    private String suite;   //	"Apt. 556"
    private String city;    //	"Gwenborough"
    private String zipcode; //	"92998-3874"
    //geo
    private double lat;   //	"-37.3159"
    private double lng;   //	"81.1496"
    private String phone;	//"1-770-736-8031 x56442"
    private String website;	//"hildegard.org"
    //company
    private String nameCompany; //	"Romaguera-Crona"
    private String catchPhrase; //	"Multi-layered client-server neural-net"
    private String bs; //	"harness real-time e-markets"



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
//                ", street='" + street + '\'' +
//                ", suite='" + suite + '\'' +
//                ", city='" + city + '\'' +
//                ", zipcode='" + zipcode + '\'' +
//                ", lat=" + lat +
//                ", lng=" + lng +
//                ", phone='" + phone + '\'' +
//                ", website='" + website + '\'' +
//                ", nameCompany='" + nameCompany + '\'' +
//                ", catchPhrase='" + catchPhrase + '\'' +
//                ", bs='" + bs + '\'' +
                '}';
    }
}
