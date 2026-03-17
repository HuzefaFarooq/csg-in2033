module IPOS.PU.app.rpt.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;

    exports ac.csg.in2033.ipos.pu.rpt;
    opens ac.csg.in2033.ipos.pu.rpt to javafx.graphics, javafx.fxml;
}