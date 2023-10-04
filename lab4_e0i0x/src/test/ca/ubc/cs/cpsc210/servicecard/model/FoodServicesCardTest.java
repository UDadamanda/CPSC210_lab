package ca.ubc.cs.cpsc210.servicecard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for FoodServiceCard class
class FoodServicesCardTest {
    private FoodServicesCard testFoodServicesCard1;
    private FoodServicesCard testFoodServicesCard2;
    private FoodServicesCard testFoodServicesCard3;

    @BeforeEach
    void runBefore() {
        testFoodServicesCard1 = new FoodServicesCard(0);
        testFoodServicesCard2 = new FoodServicesCard(2010);
        testFoodServicesCard3 = new FoodServicesCard(4000);

    }

    @Test
    void testConstructor() {
        assertEquals(0, testFoodServicesCard1.getBalance());
        assertEquals(0, testFoodServicesCard1.getRewardPoints());
        assertEquals(2010, testFoodServicesCard2.getBalance());
        assertEquals(0, testFoodServicesCard2.getRewardPoints());
    }

    @Test
    void testReload() {
        testFoodServicesCard1.reload(100);
        assertEquals(100, testFoodServicesCard1.getBalance());
    }

    @Test
    void testMakePurchase(){
        assertTrue(testFoodServicesCard1.makePurchase(0));
        assertEquals(0, testFoodServicesCard1.getBalance());
        assertEquals(0, testFoodServicesCard1.getRewardPoints());
    }

    @Test
    void testMakePurchaseSufficient(){
        assertTrue(testFoodServicesCard2.makePurchase(100));
        assertEquals((2010 - 100), testFoodServicesCard2.getBalance());
        assertEquals(100, testFoodServicesCard2.getRewardPoints());
    }

    @Test
    void testMakePurchaseNotSufficient() {
        assertFalse(testFoodServicesCard1.makePurchase(100));
    }

    @Test
    void testMakePurchaseReward() {
        assertTrue(testFoodServicesCard2.makePurchase(2000));
        assertEquals(0,testFoodServicesCard2.getRewardPoints());
        assertEquals(20, testFoodServicesCard2.getBalance());
    }

    @Test
    void testMakePurchaseExceedReward() {
        assertTrue(testFoodServicesCard2.makePurchase(2010));
        assertEquals(10,testFoodServicesCard2.getRewardPoints());
        assertEquals(10, testFoodServicesCard2.getBalance());
    }

    @Test
    void testMakePurchaseMultipleReward() {
        assertTrue(testFoodServicesCard3.makePurchase(4000));
        assertEquals(0,testFoodServicesCard3.getRewardPoints());
        assertEquals(20, testFoodServicesCard3.getBalance());
    }


}