package application.models.helper;

/**
 * This class is used to represent a single Category.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Category {

    private String _categoryName;

    /**
     * Create an category with category name
     * 
     * @param categoryName
     */
    public Category(final String categoryName) {
        _categoryName = categoryName.replace(".txt", "").replace('-', ' ');
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
        return _categoryName.replace(' ', '-') + ".txt";
    }
}
