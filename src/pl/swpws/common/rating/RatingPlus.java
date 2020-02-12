package pl.swpws.common.rating;

import impl.org.controlsfx.skin.RatingSkin;
import javafx.beans.property.*;
import javafx.geometry.Orientation;
import javafx.scene.control.Skin;
import org.controlsfx.control.Rating;

public class RatingPlus extends Rating {
    private DoubleProperty rating;
    private IntegerProperty max;
    private ObjectProperty<Orientation> orientation;
    private BooleanProperty partialRating;
    private BooleanProperty updateOnHover;

    public RatingPlus() {
        this(5);
    }

    public RatingPlus(int max) {
        this(max, -1);
    }

    public RatingPlus(int max, int rating) {
        this.rating = new SimpleDoubleProperty(this, "rating", 3.0D);
        this.max = new SimpleIntegerProperty(this, "max", 5);
        this.partialRating = new SimpleBooleanProperty(this, "partialRating", false);
        this.updateOnHover = new SimpleBooleanProperty(this, "updateOnHover", false);
        this.getStyleClass().setAll(new String[]{"rating"});
        this.setMax(max);
        this.setRating(rating == -1 ? (double)((int)Math.floor((double)max / 2.0D)) : (double)rating);
    }



    @Override
    protected Skin<?> createDefaultSkin() {
        return new RatingSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return this.getUserAgentStylesheet(RatingPlus.class, "rating-plus.css");
    }

}