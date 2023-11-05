//Elizabeth Szentmiklossy UCID: 30165216
//Justine Mangaliman UCID: 30164741
//Enzo Mutiso UCID: 30182555
//Abdelrahman Mohamed UCID: 30162037
//Mohammad Mustafa Mehtab UCID: 30189394

package com.thelocalmarketplace.software;

import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.scale.ElectronicScale;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodeScannerListener;
import com.jjjwelectronics.scanner.IBarcodeScanner;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.Product;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

import java.util.ArrayList;


/**
 * AddItemByBarcode class handles the addition of products to an order by scanning barcodes
 * and ensures that the expected weight matches the actual weight using a WeightDiscrepancy object.
 * 
 * @author Enzo Mutiso UCID: 30182555
 * @author Abdelrahman Mohamed UCID: 30162037
 * @author Elizabeth Szentmiklossy UCID: 30165216
 */
public final class AddItemByBarcode implements BarcodeScannerListener {



    private Mass expectedWeight;
    private final ArrayList<Product> order;
    private WeightDiscrepancy discrepancy;
    private ActionBlocker actionBlocker;
    private ElectronicScale scale;
    /**
     * Constructs an AddItemByBarcode object with the expected weight, order, and WeightDiscrepancy instance.
     *
     * @param expectedWeight The expected weight to match with the actual weight.
     * @param order          The order where products will be added.
     * @param discrepancy    The WeightDiscrepancy object for weight comparison.
     */

    public AddItemByBarcode(Mass expectedWeight, ArrayList<Product> order, WeightDiscrepancy discrepancy, ActionBlocker blocker, ElectronicScale scale) {
        this.expectedWeight = expectedWeight;
        this.order = order;
        this.discrepancy = discrepancy;
        this.actionBlocker = blocker;
        this.scale = scale;
    }


    /**
     * Adds a product to the order by scanning its barcode and verifies the weight matches the expected weight.
     *
     * @param barcodeScanner The barcode scanner.
     * @param barcode        The scanned barcode.
     */
    public void aBarcodeHasBeenScanned(IBarcodeScanner barcodeScanner, Barcode barcode) {
    	// Check if state is satisfying the precondition: The system is ready to accept customer input.

        try {
            // Add gui to block customer interaction
            actionBlocker.blockInteraction();
            System.out.println("Checking barcode...");

            Product product = getProductByBarcode(barcode);

            addBarcodedProductToOrder(product, order, barcodeScanner);
            // implement GUI saying to add to bagging area
            System.out.println("Item added.\nPlease add item to bagging area.\nWaiting...");
            //  Compare actual vs expected weights to check for any discrepancies (also checks if item is in bagging area)
            Mass actualWeight = scale.getCurrentMassOnTheScale();
            if (!compare(this.getExpectedWeight(), actualWeight)) {
                throw new WeightDiscrepancyException("Weight does not match the expected weight.");
            }


            actionBlocker.unblockInteraction();
        } catch (ProductNotFoundException e) { // need to implement exceptions in session simulation I think?
            // GUI message would go here
            e.printStackTrace();
        } catch (OverloadedDevice e) {
            throw new RuntimeException(e);
        }
    }
    // exception used earlier
    public class WeightDiscrepancyException extends RuntimeException {
        public WeightDiscrepancyException(String message) {
            super(message);
        }
    }

    /**
     * Retrieves product information by its barcode.
     *
     * @param scannedBarcode The scanned barcode.
     * @return The BarcodedProduct associated with the barcode.
     * @throws ProductNotFoundException If the product is not found with the specified barcode.
     */
    private BarcodedProduct getProductByBarcode(Barcode scannedBarcode) throws ProductNotFoundException {
        if (ProductDatabases.BARCODED_PRODUCT_DATABASE.containsKey(scannedBarcode)) {
            return ProductDatabases.BARCODED_PRODUCT_DATABASE.get(scannedBarcode);
        } else {
            throw new ProductNotFoundException("Product not found with specified barcode.");
        }
    }


    /**
     * Gets the expected weight.
     *
     * @return The expected weight.
     */
    public Mass getExpectedWeight( ) {
        return expectedWeight;
    }

    public boolean compare(Mass weight1, Mass weight2){
        if(weight1 == weight2){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Adds a barcoded product to the order and updates the expected weight.
     *
     * @param product       The barcoded product to add to the order.
     * @param order         The order where products will be added.
     * @param barcodeScanner The barcode scanner.
     */
    private void addBarcodedProductToOrder(Product product, ArrayList<Product> order, IBarcodeScanner barcodeScanner) {
        order.add(product);

        Mass weightOfProduct = new Mass(((BarcodedProduct) product).getExpectedWeight());
        expectedWeight = expectedWeight.sum(weightOfProduct);
        
        if(discrepancy.CompareWeight()) {
        	barcodeScanner.enable();
        } else {
        	barcodeScanner.disable();
        }
        	
    }

    @Override
    public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
    }

    @Override
    public void aDeviceHasBeenDisabled(IDevice<? extends IDeviceListener> device) {
    }

    @Override
    public void aDeviceHasBeenTurnedOn(IDevice<? extends IDeviceListener> device) {
    }

    @Override
    public void aDeviceHasBeenTurnedOff(IDevice<? extends IDeviceListener> device) {
    }


}
