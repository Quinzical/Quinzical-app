package quinzical.controllers.helper;

/**
 * This Class is used to store Game State Data mainly state of answered
 * question.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameStateData {
    /**
     * state is an int[5][5], where there first index is int[category][question]. 0
     * = not answered, 1 = correct, (leave as 0 if already answered)
     */
    private int[][] _state;

    /**
     * Set Empty GameStateData
     */
    public GameStateData() {
        _state = new int[5][5];
    }

    /**
     * Load state using SQL (file is not supported)
     * 
     * @param state
     */
    public GameStateData(final int[][] state) {
        _state = state;
    }

    /**
     * Get all Question state for a category
     * 
     * @param index
     * @return int[] questions
     */
    public int[] getCategoryState(final int index) {
        return _state[index];
    }

    /**
     * Set a Question state using category and question index. Should only be used
     * if the state = 1 means correct
     * 
     * @param category
     * @param question
     * @param state
     */
    public void setQuestionState(final int category, final int question, final int state) {
        _state[category][question] = state;
    }
}
