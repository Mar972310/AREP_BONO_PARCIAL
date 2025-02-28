package escuelaing.edu.arep.bonoParcial.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import escuelaing.edu.arep.bonoParcial.Service.PropertyService;
import escuelaing.edu.arep.bonoParcial.model.Property;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProperty(@RequestBody Property property){
        try{
            Property propertySave = propertyService.createProperty(property);
            return ResponseEntity.ok(propertySave);
        }catch(Exception e){
            return (ResponseEntity<String>) ResponseEntity.badRequest().body("Error create property");
        }
        
    }

    @GetMapping("/all")
    public ResponseEntity<List<Property>> allProperty(){
        List<Property> propeties = propertyService.getallProperties();
        return ResponseEntity.ok(propeties);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<?> getProperty(@PathVariable Long id){
        Property propety = propertyService.getProperty(id);
        return ResponseEntity.ok(propety);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody Property property){
        try{
            Property propertyUpdate = propertyService.updateProperty(id,property);
            return ResponseEntity.ok(propertyUpdate);
        }catch(Exception e){
            return (ResponseEntity<String>) ResponseEntity.badRequest().body("Error update property");
        } 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id){
        try{
            propertyService.deleteProperty(id);
            return ResponseEntity.ok("Eliminado con exito");
        }catch(Exception e){
            return (ResponseEntity<String>) ResponseEntity.badRequest().body("Error delete property");
        } 
    }
    





}
