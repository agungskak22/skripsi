package intant.call.Model;

public class FoodItem {
    private String imageUrl;
    private String name;
    private String description;
    private int calory;

    public FoodItem(String imageUrl, String name, String description, int calory) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.calory = calory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getCalory() {
        return calory;
    }

    public String getDescription() {
        return description;
    }
}
