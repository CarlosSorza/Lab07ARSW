package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.stereotype.Service;

public interface BPFilter {
    
    Blueprint filter(Blueprint blueprint) throws BlueprintPersistenceException;
}
