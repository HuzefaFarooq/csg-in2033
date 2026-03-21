module IPOS.PU.app.sales.main {
    requires javafx.controls;
    requires org.slf4j;
    requires javafx.fxml;

    exports ac.csg.in2033.ipos.pu.sales;
    opens ac.csg.in2033.ipos.pu.sales to javafx.graphics, javafx.fxml;
}