package application.models.helper;

/**
 * This class is used to represent a single Category
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Category {
	
	private String _categoryName;
	
	public Category(String categoryName) {
		_categoryName = categoryName;
	}
    
    @Override
	public String toString() {
		return _categoryName;
    }
    
    public String getFilename() {
        return _categoryName.replace(' ', '-');
    }
}
