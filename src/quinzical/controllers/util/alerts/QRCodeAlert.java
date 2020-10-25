package quinzical.controllers.util.alerts;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

/**
 * This class is used to display QR code
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QRCodeAlert {

    private static final int QRCODE_SIZE = 1000;
    private static final int ALERT_SIZE = 450;

    /**
     * Create QRCodeAlert that displays a window with QRcode.
     * 
     * @param url
     */
    public QRCodeAlert(final String url) {

        // GENERATE QR CODE
        ByteArrayOutputStream out = QRCode.from(url).to(ImageType.PNG).withSize(QRCODE_SIZE, QRCODE_SIZE).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        // SHOW QR CODE
        Label header = new Label(url);
        header.getStyleClass().add("qrcode");
        AnchorPane anchorPane = new AnchorPane();
        Image image = new Image(in);

        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        // then you set to your node
        anchorPane.setBackground(new Background(background));
        anchorPane.getChildren().add(header);

        Stage stage = new Stage();
        stage.setTitle("Join our game");

        stage.setScene(new Scene(anchorPane, ALERT_SIZE, ALERT_SIZE));
        stage.show();

    }
}
