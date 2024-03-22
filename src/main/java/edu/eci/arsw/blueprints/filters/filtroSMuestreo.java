package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

//@Service ("FiltroSMuestreo")
public class filtroSMuestreo implements BPFilter {
    @Override
    public Blueprint filter(Blueprint blueprint) {

        ArrayList<Point> pts = blueprint.getPoints();
        ArrayList<Point> escalados = new ArrayList<>();
        for (int i=0; i<pts.size()-1; i=i+2){
            Point p = pts.get(i);
            escalados.add(p);
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(), pts);

    }
    
}
