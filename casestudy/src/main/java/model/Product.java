package model;

import java.sql.Date;

public class Product {
    private Long id;
    private String name;
    private Float price;
    private Integer quantity;
    private Category category;
    private String avatar;
    private String description;

    private Date create_at;




    public Product() {
    }

    public Product(Long id, String name, Float price, Integer quantity, Category category, String avatar, String description, Date create_at ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.avatar = avatar;
        this.description = description;
        this.create_at = create_at;

    }
    public Product(Long id, String name, Float price, Integer quantity, String avatar, String description, Date create_at ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.create_at = create_at;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_at() {
        if(create_at == null) return "";
        return create_at.toString();
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}