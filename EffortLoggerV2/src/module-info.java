module EffortLoggerV2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires junit;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
}
