package quinzical.util.models.util;

/**
 * This class is used to represent a single Category.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Category {

    private String _categoryName;
    private int _categoryID;

    /**
     * Create an category with category name
     * 
     * @param categoryName
     */
    public Category(final String categoryName) {
        _categoryName = categoryName.replace(".txt", "").replace('-', ' ');
    }

    /**
     * Create an category with category name and category id
     * 
     * @param categoryName
     * @param categoryID
     */
    public Category(final String categoryName, final int categoryID) {
        _categoryName = categoryName;
        _categoryID = categoryID;
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
     * Used to get categoryID
     * 
     * @return id
     */
    public int getCategoryID() {
        return _categoryID;
    }

    /**
     * Used to get category filename (spaces replaced with hyphen)
     * 
     * @return filename
     */
    public String getFilename() {
        return _categoryName.replace(' ', '-') + ".txt";
    }
}
