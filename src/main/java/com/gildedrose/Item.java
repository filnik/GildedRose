package com.gildedrose;

public class Item {
    private static final int MAX_QUALITY = 50;

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void decreaseSellIn() {
        sellIn = sellIn - 1;
    }

    public void increaseQuality() {
        if (quality < MAX_QUALITY) {
            quality = quality + 1;
        }
    }

    public void decreaseQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

}
