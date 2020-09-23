package application.models.helper;

/**
 * This class is used to represent a single Category.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Category {

    private String _categoryName;

    public Category(String categoryName) {
        _categoryName = categoryName;
    }

    /**
     * Used to get category display name
     * 
     * @return display name
     */
    @Override
    public String toString() {
        return _categoryName;
    }

    /**
     * Used to get category filename (spaces replaced with hyphen)
     * 
     * @return filename
     */
    public String getFilename() {
        return _categoryName.replace(' ', '-');
    }
}
