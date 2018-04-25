package com.gildedrose;

class GildedRose {
    Item[] items;
    private static final int MAX_QUALITY = 50;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            final Item item = items[i];

            if (qualityIncreaseWithAge(item)) {
                increaseQuality(item,
                        true,
                        sellInDateHasPassed(item),
                        isBackStage(item) && item.sellIn < 11,
                        isBackStage(item) && item.sellIn < 6);
            } else {
                decreaseQuality(item,
                        true,
                        isConjured(item),
                        isNotLegendaryItem(item) && sellInDateHasPassed(item));
            }

            if (isBackStage(item) && sellInDateHasPassed(item)) {
                item.quality = 0;
            }

            decreaseSellIn(item);
        }
    }

    private boolean sellInDateHasPassed(Item item) {
        return item.sellIn <= 0;
    }

    private void decreaseSellIn(Item item) {
        if (isNotLegendaryItem(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private boolean isNotLegendaryItem(Item item) {
        return !item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean qualityIncreaseWithAge(Item item) {
        return item.name.equals("Aged Brie") || isBackStage(item) || !isNotLegendaryItem(item);
    }

    private boolean isBackStage(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void increaseQuality(Item item, boolean... conditions) {
        for (boolean condition : conditions) {
            if (condition && item.quality < MAX_QUALITY) {
                item.quality = item.quality + 1;
            }
        }
    }

    private void decreaseQuality(Item item, boolean... conditions) {
        for (boolean condition : conditions) {
            if (condition && item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    private boolean isConjured(Item item) {
        return item.name.contains("Conjured");
    }
}
