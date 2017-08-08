package exercise.tbecker.aetnacodingexercise.models;

/**
 * Created by Android on 8/7/2017.
 */

public class MyData {

    private String title;
    private int imgHeight;
    private int imgWidth;

    private String imgUrl;

    public MyData(String title, int imgHeight, int imgWidth, String imgUrl) {
        this.title = title;
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }
}
