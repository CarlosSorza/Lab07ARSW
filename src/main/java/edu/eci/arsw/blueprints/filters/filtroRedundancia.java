package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service("FiltroRedundancia")
public class filtroRedundancia implements BPFilter {

    @Override
    public Blueprint filter(Blueprint blueprint){

        ArrayList<Point> pts = blueprint.getPoints();
        ArrayList<Point> repetidos = new ArrayList<>();
        for (int i=0; i<pts.size()-1; i++){
            Point p = pts.get(i);
            Point q = pts.get(i+1);
            if(p.getX() ==  q.getY()) {
                repetidos.add(p);}}
        for (Point p : repetidos){
            pts.remove(p);
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(), pts);

    }
}
