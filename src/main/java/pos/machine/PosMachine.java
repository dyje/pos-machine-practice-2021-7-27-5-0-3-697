package pos.machine;

import java.util.*;


public class PosMachine {

    public String printReceipt(List<String> barcodes) {
        return null;
    }

    private List<Item> getItems(List<String> barcodes) {
        LinkedList<Item> items = new LinkedList<Item>();
        List<ItemInfo> itemInfo = loadAllItemsInfo();
        barcodes = new ArrayList<>(new LinkedHashSet<>(barcodes));
        for (String barcode : barcodes) {
            Item itemValue = new Item();
            for (ItemInfo itemInfoValue : itemInfo) {
                if (barcode.equals(itemInfoValue.getBarcode())) {
                    itemValue.setName(itemInfoValue.getName());
                    itemValue.setUnitPrice(itemInfoValue.getPrice());
                    itemValue.setQuantity(retrieveItemCount(barcode));
                }
            }
            items.add(itemValue);
        }
        return items;
    }

    private String generateItemDetail(Receipt receipt) {
        String itemsDetail = "";
        for (Item itemValue : receipt.getItems())
        {
            itemsDetail += "Name: "+ itemValue.getName() +
                    ", Quantity: " + itemValue.getQuantity() +
                    ", Unit price: " + itemValue.getUnitPrice() + " (yuan)" +
                    ", Subtotal: " + itemValue.getSubTotal() + " (yuan)\n";
        }
        return itemsDetail;
    }

    private List<Item> calculateItemSubtotal(List<Item> itemsList) {
        for (Item itemValue : itemsList) {
            itemValue.setSubTotal(itemValue.getQuantity() * itemValue.getUnitPrice());
        }
        return itemsList;
    }

    private int calculateTotalPrice(List<Item> itemsList) {
        int grandTotal = 0;
        for (Item itemValue : itemsList) {
            grandTotal += itemValue.getSubTotal();
        }
        return grandTotal;
    }

    private int retrieveItemCount(String currentItemBarcode) {
        return Collections.frequency(ItemDataLoader.loadBarcodes(), currentItemBarcode);
    }

    private List<ItemInfo> loadAllItemsInfo() {
        return ItemDataLoader.loadAllItemInfos();
    }
}
