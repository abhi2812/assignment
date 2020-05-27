package com.project.restaurant.model;

public class Item {

    private Integer itemId;

    private Integer count;

    public Item(Integer itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
