module org.uabc.ic.proyectotamagochicongui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uabc.ic.proyectotamagochicongui to javafx.fxml;
    exports org.uabc.ic.proyectotamagochicongui;
}