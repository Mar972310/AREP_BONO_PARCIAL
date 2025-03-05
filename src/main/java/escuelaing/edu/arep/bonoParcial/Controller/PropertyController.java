package escuelaing.edu.arep.bonoParcial.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import escuelaing.edu.arep.bonoParcial.DTO.PropertyDTO;
import escuelaing.edu.arep.bonoParcial.Exception.PropertyException;
import escuelaing.edu.arep.bonoParcial.Service.Impl.PropertyService;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProperty(@RequestBody PropertyDTO property){
        try{
            PropertyDTO propertySave = propertyService.createProperty(property);
            return ResponseEntity.status(201).body(propertySave);
        }catch(Exception e){
            return (ResponseEntity<String>) ResponseEntity.badRequest().body("Error create property");
        }
        
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyDTO>> allProperty(){
        List<PropertyDTO> propeties = propertyService.getallProperties();
        return ResponseEntity.ok(propeties);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<?> getProperty(@PathVariable Long id){
        try {
            PropertyDTO propety;
            propety = propertyService.getProperty(id);
            return ResponseEntity.ok(propety);
        } catch (PropertyException e) {
            return (ResponseEntity<String>) ResponseEntity.badRequest().body(e.getMessage());   
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO property){
        try{
            PropertyDTO propertyUpdate = propertyService.updateProperty(id,property);
            return ResponseEntity.ok(propertyUpdate);
        }catch(PropertyException e){
            String error = e.getMessage();
            if(error.equals(PropertyException.PROPERTY_NOT_UPDATE)){
                return (ResponseEntity<String>) ResponseEntity.status(400).body(e.getMessage());
            }else{
                return (ResponseEntity<String>) ResponseEntity.status(404).body(e.getMessage());
            }
        } 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id){
        try{
            propertyService.deleteProperty(id);
            return ResponseEntity.ok("Eliminado con exito");
        }catch(PropertyException e){
            return (ResponseEntity<String>) ResponseEntity.status(404).body(e.getMessage());
        } 
    }
}
