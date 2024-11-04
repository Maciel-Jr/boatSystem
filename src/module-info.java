module JavaFX_BoatSystem {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	
	opens gui to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
	
}
