package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;


public class GildedRoseTest {

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_TICKET = "Backstage passes to a TAFKAL80ETC concert";
    private static final String OTHER = "other";

    @Test
    public void sellInValueIsDecreasingEveryDay() {
        Item[] items = new Item[] { new Item("foo", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void sameSellInWhichSpecialItems() {
        Item[] items = new Item[] { new Item(SULFURAS_HAND_OF_RAGNAROS, 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    public void noChangesWithSellInUnderZeroAndQualityOver50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, -1, 51) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(51, app.items[0].quality);
    }

    @Test
    public void qualityIncreaseWithSellInUnderZeroAndQualityUnder50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, -1, 47) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
    }

    @Test
    public void qualityGoesToZeroWithSellInUnderZeroAndIsBackStage() {
        Item[] items = new Item[] { new Item(BACKSTAGE_TICKET, -1, 47) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void qualityDecreeseTwiceWithSellInUnderZeroAndNotBackStage() {
        Item[] items = new Item[] { new Item(OTHER, -1, 47) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(45, app.items[0].quality);
    }

}
