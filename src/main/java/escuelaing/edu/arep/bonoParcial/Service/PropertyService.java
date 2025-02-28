package escuelaing.edu.arep.bonoParcial.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import escuelaing.edu.arep.bonoParcial.Repository.PropertyRepository;
import escuelaing.edu.arep.bonoParcial.model.Property;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property createProperty(Property property){
        return propertyRepository.save(property);
    }

    public List<Property> getallProperties(){
        List<Property> properties = propertyRepository.findAll();
        return properties; 
    }

    public Property getProperty(Long id){
        Property property = propertyRepository.findById(id).get();
        return property;
    }

    public Property updateProperty(Long id , Property property){
        Property property2 = propertyRepository.findById(id).get();
        property2.setAddress(property.getAddress());
        property2.setDescription(property.getDescription());
        property2.setPrice(property.getPrice());
        property2.setSize(property.getSize());
        return propertyRepository.save(property2);  
    }

    public void deleteProperty(Long id){
        propertyRepository.deleteById(id);
    }  
}
