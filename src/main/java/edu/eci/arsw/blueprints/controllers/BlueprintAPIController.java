/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.*;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */

 @RestController
 @RequestMapping("/blueprints")
public class BlueprintAPIController {

    @Autowired
    private BlueprintsServices blueprintsServices;

    @GetMapping
    public ResponseEntity<?> getAllBlueprints(){

    try{
        Set<Blueprint> allBlueprints = blueprintsServices.getAllBlueprints();
        return new ResponseEntity<>(allBlueprints, HttpStatus.ACCEPTED);
    }
    catch (Exception ex) {
        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
        return new ResponseEntity<>("Error en los planos: ",HttpStatus.NOT_FOUND);
    } 
    

    
    
    }
    @GetMapping(path = "/{author}")
    public ResponseEntity<?> getBlueprintsByAuthor(@PathVariable("author") String author){
        try{
            Set<Blueprint> bluePrintsByAuthor = blueprintsServices.getBlueprintsByAuthor(author);
            if (bluePrintsByAuthor.isEmpty()){
                return new ResponseEntity<>("No se encontraron planos para: : " + author,HttpStatus.NOT_FOUND );
            }
            return new ResponseEntity<>(bluePrintsByAuthor, HttpStatus.ACCEPTED);


        }catch(Exception e){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error en los planos: ",HttpStatus.NOT_FOUND);
        }

    }



    @GetMapping(path = "/{author}/{bpname}")
    public ResponseEntity <?> getBlueprintByAuthorAndName(@PathVariable("author") String author, @PathVariable("bpname") String bpname){
        try{
            Blueprint blueprintNameAndAuthor = blueprintsServices.getBlueprint(author, bpname);
            if (blueprintNameAndAuthor == null){
                return new ResponseEntity<>("No se encontraron planos para: " + author +"and "+ bpname,HttpStatus.NOT_FOUND );
            }
            return new ResponseEntity<>(blueprintNameAndAuthor, HttpStatus.ACCEPTED);


        }catch(Exception e){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error en los planos: ",HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(path = "/create")
    public ResponseEntity<?> createBlueprint(@RequestBody Blueprint blueprint){
        try {
            blueprintsServices.addNewBlueprint(blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>("Error en la creacion de planos",HttpStatus.FORBIDDEN);
        }
    }
    @PutMapping(path="/{author}/{bpname}")
    public ResponseEntity<?> manejadorPutRecursoBlueprints(@RequestBody Blueprint bp, @PathVariable("author") String author, @PathVariable("bpname") String bpname) {
        try {
            blueprintsServices.updateBlueprint(bp, author, bpname);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path="/{author}/{bpName}")
    public ResponseEntity<?> deleteBlueprint(@PathVariable String author,@PathVariable String bpName) {
        try{
            blueprintsServices.deleteBlueprint(author, bpName);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}



