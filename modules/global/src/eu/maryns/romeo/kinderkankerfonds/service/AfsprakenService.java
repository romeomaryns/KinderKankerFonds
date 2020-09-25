package eu.maryns.romeo.kinderkankerfonds.service;

import eu.maryns.romeo.kinderkankerfonds.entity.Afspraak;

import java.time.LocalDateTime;

public interface AfsprakenService {
    String NAME = "kinderkankerfonds_AfsprakenService";

    Afspraak verzetAfspraak(Afspraak afspraak, LocalDateTime newStartDate);
    void verwijderAfspraak(Afspraak afspraak);

    Afspraak resizeAfspraak(Afspraak entity, LocalDateTime newStart, LocalDateTime newEnd);

}