package eu.maryns.romeo.kinderkankerfonds.core.jmx;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Manages imports from csv
 */
@ManagedResource(description = "converts csv to entities")
public interface ImportsMBean {


    @ManagedOperation(description = "imports Persons")
    String importPersons();

    @ManagedOperation(description = "imports addresses for a given person")
    @ManagedOperationParameters({@ManagedOperationParameter(name = "personId", description = "person id, like 1797f54d-5bec-87a6-4330-d958955743a2")})
    String importAddresses(String personId);
}
