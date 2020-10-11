package application.models.sql.data;

public class UserData {
    private int _id;
    private String _name;
    private Boolean _international;

    /**
     * Create User DB Object
     * 
     * @param id
     * @param name
     * @param international
     */
    public UserData(final int id, final String name, final Boolean international) {
        _id = id;
        _name = name;
        _international = international;
    }

    /**
     * @return int return the _id
     */
    public int getID() {
        return _id;
    }

    /**
     * @return String return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @return Boolean return the _international
     */
    public Boolean isInternational() {
        return _international;
    }
}
