package escuelaing.edu.arep.bonoParcial.Exception;

public class PropertyException extends Exception{

    public final static String PROPERTY_NOT_FOUND = "The property hasn't been found";
    public final static String ID_INVALID = "The id provided is not valid";
    public final static String PROPERTY_NOT_UPDATE = "The property could be updated";
    public final static String PROPERTY_NOT_CREATE = "The property could be created";
    public final static String PROPERTY_NOT_DELTETE = "The property could be deleted";

    public PropertyException(String message){
        super(message);
    }

    public PropertyException(String message, String e){
        super("The requested property with id:"+ e +" hasn't been found");
    }

    
}
