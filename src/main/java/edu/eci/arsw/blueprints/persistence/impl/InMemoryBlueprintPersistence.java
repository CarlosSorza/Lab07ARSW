/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("InMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        ArrayList<Point> pts=new ArrayList<>(Arrays.asList(new Point(399, 210), new Point(245, 258), new Point(246, 420), new Point(153, 288), new Point(0, 339), new Point(95, 210), new Point(0, 80), new Point(153, 131), new Point(246, 0), new Point(245, 161), new Point(399, 210)));
        ArrayList<Point> pts1=new ArrayList<>(Arrays.asList(new Point(368, 212), new Point(208, 52), new Point(64, 212), new Point(368, 212), new Point(368, 404), new Point(64, 404), new Point(64,92)));
        ArrayList<Point> pts2=new ArrayList<>(Arrays.asList(new Point(70, 50),new Point(80, 90)));
        ArrayList<Point> pts3=new ArrayList<>(Arrays.asList(new Point(64, 212), new Point(368, 212), new Point(368, 404), new Point(64, 404), new Point(64, 212), new Point(208, 52), new Point(64, 212), new Point(368, 212), new Point(208, 52)));
        Blueprint bp=new Blueprint("Jose", "Estrella",pts);
        Blueprint bp1=new Blueprint("Jose", "Casa",pts1);
        Blueprint bp2=new Blueprint("Carlos", "_bpname_2",pts2);
        Blueprint bp3=new Blueprint("johnconnor", "Casa1",pts3);


        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
    }
    
  
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor (String author) throws BlueprintNotFoundException{
        Set<Blueprint> Author = new HashSet<Blueprint>();
        for (Tuple<String,String> tupla : blueprints.keySet()){
            if(tupla.getElem1().equals(author)){
                Author.add(blueprints.get(tupla));
            }
        }
        return Author;
    }

    @Override
    public Set<Blueprint> getBlueprintByAll() throws BlueprintNotFoundException{
        Set<Blueprint> Author = new HashSet<Blueprint>();
        for(Tuple<String,String> tupla : blueprints.keySet()){
            Author.add(blueprints.get(tupla));
        }
        return Author;
    }


    @Override
    public void updateBlueprint(Blueprint newBp, String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint actualBp = getBlueprint(author, bprintname);
        if (actualBp != null) {
            actualBp.setPoints(newBp.getPoints());
        } else {
            throw new BlueprintNotFoundException(BlueprintNotFoundException.NOT_EXIST + author + bprintname);
        }
    }

    @Override
    public void deleteBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint blueprint = blueprints.get(new Tuple<>(author, bprintname));
        if (blueprint == null) {
            throw new BlueprintNotFoundException(BlueprintNotFoundException.NOT_FOUND + author + bprintname);
        }
        blueprints.remove(new Tuple<>(author, bprintname));
    }



   
    
}
