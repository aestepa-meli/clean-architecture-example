module slalom.example.db.sql {
	exports com.slalom.example.db.sql;

	requires slalom.example.domain;
	requires slalom.example.usecase;
	requires com.zaxxer.hikari;
	requires java.sql;
	requires mysql.connector.java;
}
