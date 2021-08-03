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
            for (ItemInfo itemInfoVal : itemInfo) {
                if (barcode.equals(itemInfoVal.getBarcode())) {
                    itemValue.setName(itemInfoVal.getName());
                    itemValue.setUnitPrice(itemInfoVal.getPrice());
                    itemValue.setQuantity(retrieveItemCount(barcode));
                }
            }
            items.add(itemValue);
        }
        return items;
    }

    private int retrieveItemCount(String currentItemBarcode) {
        return Collections.frequency(ItemDataLoader.loadBarcodes(), currentItemBarcode);
    }

        private List<ItemInfo> loadAllItemsInfo(){
        return ItemDataLoader.loadAllItemInfos();
    }
}
