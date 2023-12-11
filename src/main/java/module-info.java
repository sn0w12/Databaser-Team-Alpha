module com.teamalpha.teamalphapipergames {

    requires javafx.controls;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;



    exports com.teamalpha.teamalphapipergames;
    //opens com.teamalpha.teamalphapipergames.model to org.hibernate.orm.core;   // det stod innan

    // opens application to javafx.graphics, javafx.base;       //Det tyckte någon att man skulle skriva in, jag ändrade till det som är under
    opens com.teamalpha.teamalphapipergames.model to org.hibernate.orm.core, javafx.graphics, javafx.base;
}
