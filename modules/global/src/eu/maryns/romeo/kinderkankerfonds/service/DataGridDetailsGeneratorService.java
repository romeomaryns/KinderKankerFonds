package eu.maryns.romeo.kinderkankerfonds.service;

import eu.maryns.romeo.kinderkankerfonds.entity.Notitie;

import java.util.List;
import java.util.UUID;

public interface DataGridDetailsGeneratorService {
    String NAME = "kinderkankerfonds_DataGridDetailsGeneratorService";

    List<Notitie> loadAfspraakNotitesById(UUID afspraakId);
}