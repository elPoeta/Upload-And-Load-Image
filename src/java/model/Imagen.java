package model;

/**
 *
 * @author elpoeta
 */
public class Imagen {

    private Integer id;
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Imagen{" + "id=" + id + ", img=" + img + '}';
    }

}
