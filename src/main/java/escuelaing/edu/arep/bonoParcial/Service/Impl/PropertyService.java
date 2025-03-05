package escuelaing.edu.arep.bonoParcial.Service.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import escuelaing.edu.arep.bonoParcial.DTO.PropertyDTO;
import escuelaing.edu.arep.bonoParcial.Exception.PropertyException;
import escuelaing.edu.arep.bonoParcial.Repository.PropertyRepository;
import escuelaing.edu.arep.bonoParcial.Service.PropertyServiceInterface;
import escuelaing.edu.arep.bonoParcial.model.Property;

@Service
public class PropertyService implements PropertyServiceInterface{

    private final PropertyRepository propertyRepository;
    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) throws PropertyException{
        try {
            Property property = toEntity(propertyDTO);
            property = propertyRepository.save(property);
            return toDTO(property);
        } catch (Exception e) {
            throw new PropertyException(PropertyException.PROPERTY_NOT_CREATE);
        }
    }

    @Override
    public List<PropertyDTO> getallProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PropertyDTO getProperty(Long id) throws PropertyException{
        if(id == null){
            throw new PropertyException(PropertyException.ID_INVALID);
        }
        Property property = propertyRepository.findById(id).orElseThrow(() 
        -> new PropertyException(PropertyException.PROPERTY_NOT_FOUND,String.valueOf(id)));
        return toDTO(property);
    }

    @Override
    public PropertyDTO updateProperty(Long id , PropertyDTO propertyDTO) throws PropertyException{
        Property property = propertyRepository.findById(id).orElseThrow(() 
        -> new PropertyException("The property with id"+ String.valueOf(id)+" could not be updated, because it does not exist"));
        property.setAddress(propertyDTO.getAddress());
        property.setDescription(propertyDTO.getDescription());
        property.setPrice(propertyDTO.getPrice());
        property.setSize(propertyDTO.getSize());
        try{
            propertyRepository.save(property);
        }catch(Exception e){
            throw new PropertyException(PropertyException.PROPERTY_NOT_UPDATE);
        }        
        return toDTO(property);  
    }

    public void deleteProperty(Long id) throws PropertyException{
        propertyRepository.deleteById(id);
    } 

    private Property toEntity(PropertyDTO propertyDTO){
        return new Property(
            null,
            propertyDTO.getAddress(),
            propertyDTO.getPrice(),
            propertyDTO.getSize(),
            propertyDTO.getDescription()
        );
    }
    
    private PropertyDTO toDTO(Property property) {
        return new PropertyDTO(
            property.getId(),
            property.getAddress(),
            property.getPrice(),
            property.getSize(),
            property.getDescription()
        );
    }
    
}
