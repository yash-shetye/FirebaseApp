package v.abhijeet.firebaselogin;

public class Hotel {
    public String hotelname , hotelprice;

    Hotel(){};

    public Hotel(String hotelname, String hotelprice) {
        this.hotelname = hotelname;
        this.hotelprice = hotelprice;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotelprice() {
        return hotelprice;
    }

    public void setHotelprice(String hotelprice) {
        this.hotelprice = hotelprice;
    }
}
