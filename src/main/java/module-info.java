module com.teamalpha.teamalphapipergames {

    requires javafx.controls;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;

    exports com.teamalpha.teamalphapipergames;
    opens com.teamalpha.teamalphapipergames.model to org.hibernate.orm.core;
}
