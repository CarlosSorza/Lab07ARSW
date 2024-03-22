package edu.eci.arsw.blueprints.model;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;


public class main {

    
    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext prueba = new ClassPathXmlApplicationContext("applicationContext.xml"); 
        BlueprintsServices cs = prueba.getBean(BlueprintsServices.class);
        
        ArrayList<Point> punto1 = new ArrayList<>(Arrays.asList(new Point(4,4), new Point(4,4)));
        ArrayList<Point> punto2 = new ArrayList<>(Arrays.asList(new Point(3,3), new Point(5,5)));
        ArrayList<Point> punto3 = new ArrayList<>(Arrays.asList(new Point(14,16), new Point(11,14)));

        Blueprint bp0 = new Blueprint("Jose", "Punto Jose", punto1);
        System.out.println("Originales+" + bp0.toString());
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + "),");
        }
        System.out.println("Filtrados" + bp0.toString());
        cs.addNewBlueprint(bp0);
        for(Point punto: bp0.getPoints()){
            System.out.print("(" + punto.getX() + "," + punto.getY() + "),");
        }
        System.out.println("Blueprints:  ");
        for (Blueprint i:cs.getAllBlueprints()){
            System.out.println(" - " + i.toString());
        }
        
        System.out.println("Filtros");
       
    }
}
