module IPOS.PU.app.comms.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;

    exports ac.csg.in2033.ipos.pu.comms;
    opens ac.csg.in2033.ipos.pu.comms to javafx.graphics, javafx.fxml;
}