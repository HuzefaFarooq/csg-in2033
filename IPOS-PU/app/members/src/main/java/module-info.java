module IPOS.PU.app.members.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;

    exports ac.csg.in2033.ipos.pu.members;
    opens ac.csg.in2033.ipos.pu.members to javafx.graphics, javafx.fxml;
}