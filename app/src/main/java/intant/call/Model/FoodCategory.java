package intant.call.Model;

public class FoodCategory {
    private int id;
    private int imageId;
    private String title;

    public FoodCategory(int id, int imageId, String title) {
        this.id = id;
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
