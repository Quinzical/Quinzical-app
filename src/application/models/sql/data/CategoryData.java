package application.models.sql.data;

public class CategoryData {
    private int _id;
    private String _name;

    /**
     * Create Category DB Object
     * 
     * @param id
     * @param name
     */
    public CategoryData(final int id, final String name) {
        _id = id;
        _name = name;
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
}
