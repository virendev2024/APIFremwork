package resources;

//enum is a special class in java which has collection of constants or methods
public enum APIResources
{

    // enum treat these as a method , no need of paranthesis body or return type
    AddPlaceAPI("/maps/api/place/add/json"),   // method returning the string only (url)
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resource;  // global variable name

    // this constructor will accept argument
    APIResources(String resource)
    {
            this.resource=resource;    // this refers to the current class variable
    }

    public String getResource()   // for returning the resource
    {
        return resource;
    }


}

