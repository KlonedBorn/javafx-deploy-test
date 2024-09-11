module com.kloneborn.github {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens com.kloneborn.github to javafx.fxml;
    exports com.kloneborn.github;
}
