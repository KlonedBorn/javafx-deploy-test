module com.kloneborn.github {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.kloneborn.github to javafx.fxml;
    exports com.kloneborn.github;
}
