package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            final Item item = items[i];
            if (isWorstWhenOld(item)) {
                if (isNotLegendaryItem(item)) {
                    item.decreaseQuality();
                }
            } else {
                item.increaseQuality();

                if (isBackStage(item)) {
                    if (item.sellIn < 11) {
                        item.increaseQuality();
                    }

                    if (item.sellIn < 6) {
                        item.increaseQuality();
                    }
                }
            }

            if (isNotLegendaryItem(item)) {
                item.decreaseSellIn();
            }

            if (item.sellIn < 0) {
                if (isBackStage(item)){
                    item.quality = 0;
                } else if (isWorstWhenOld(item) && isNotLegendaryItem(item)) {
                    item.decreaseQuality();
                } else {
                    item.increaseQuality();
                }
            }
        }
    }

    private boolean isWorstWhenOld(Item item) {
        return !item.name.equals("Aged Brie") && !isBackStage(item);
    }

    private boolean isBackStage(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isNotLegendaryItem(Item item) {
        return !item.name.equals("Sulfuras, Hand of Ragnaros");
    }
}
