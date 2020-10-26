package quinzical.controllers.util;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

/**
 * This class is used for animating stars in the background using a template
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class SheepBackground {

    private Sheep[] _images = { Sheep.BLUE, Sheep.CYAN, Sheep.GREEN, Sheep.ORANGE, Sheep.PINK, Sheep.RED, Sheep.WHITE,
            Sheep.YELLOW };

    private List<SheepAnimation> _sheeps = new ArrayList<SheepAnimation>();

    private static final int SHEEP_HEIGHT = 150;
    private static final int SHEEP_WIDTH = 150;

    private AnchorPane _pane;

    /**
     * Used to setup sheep background, generate random sheeps and animates them
     * 
     * @param pane
     */
    public SheepBackground(final AnchorPane pane) {
        _pane = pane;
        Platform.runLater(() -> {
            createSheep();
            createSheep();
            createSheep();
            createSheep();
            createSheep();
        });
    }

    private void createSheep() {
        double x = 0;
        double y = 0;
        switch (randomInt(3, 0)) {
            case 0:
                x = -SHEEP_WIDTH;
                y = randomFloat(_pane.getHeight(), 0);
                break;
            case 1:
                x = _pane.getWidth();
                y = randomFloat(_pane.getHeight(), 0);
                break;
            case 2:
                x = randomFloat(_pane.getWidth(), 0);
                y = -SHEEP_HEIGHT;
                break;
            case 3:
                x = randomFloat(_pane.getWidth(), 0);
                y = _pane.getHeight();
                break;
            default:
                x = 0;
                y = 0;
        }
        SheepAnimation sheep = new SheepAnimation(_pane, _images[randomInt(_images.length - 1, 0)].getFilename(), x,
                y) {
            @Override
            protected void kill() {
                _pane.getChildren().remove(this);
                _sheeps.remove(this);
                Platform.runLater(() -> {
                    createSheep();
                });
            }
        };
        _pane.getChildren().add(sheep);
        _sheeps.add(sheep);
    }

    private int randomInt(final int maxInt, final int minInt) {
        return (int) (Math.random() * (maxInt - minInt) + minInt);
    }

    private double randomFloat(final double maxFloat, final double minFloat) {
        return (Math.random() * ((maxFloat - minFloat) + 1)) + minFloat;
    }
}
