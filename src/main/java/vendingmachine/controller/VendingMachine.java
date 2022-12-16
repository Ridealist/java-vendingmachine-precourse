package vendingmachine.controller;

import java.util.List;
import vendingmachine.domain.Change;
import vendingmachine.domain.Product;
import vendingmachine.domain.ProductRepository;
import vendingmachine.domain.Purchase;
import vendingmachine.domain.RandomNumberGenerator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {
    private Change change;
    private Purchase purchase;

    public void run() {
        initCoins();
        registerProducts();
        setInputMoney();
        while (ProductRepository.canBuyAnyProduct() && ProductRepository.canAffordPrice(purchase.getMoney())) {
            buyProduct();
        }
        showFinalChange();
    }

    private void initCoins() {
        try {
            int number = InputView.readChangeAmount();
            this.change = new Change(number, new RandomNumberGenerator());
            OutputView.printCoinStatus(change.getCoins());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            initCoins();
        }
    }

    private static void registerProducts() {
        try {
            List<List<String>> products = InputView.readProductList();
            makeProduct(products);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            registerProducts();
        }
    }

    private static void makeProduct(List<List<String>> products) throws IllegalArgumentException {
        for (List<String> product : products) {
            String name = product.get(0);
            int price = Integer.parseInt(product.get(1));
            int amount = Integer.parseInt(product.get(2));
            ProductRepository.add(new Product(name, price, amount));
        }
    }

    private void setInputMoney() {
        try {
            int money = InputView.readInputMoney();
            this.purchase = new Purchase(money);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            setInputMoney();
        }
    }

    private void buyProduct() {
        try {
            OutputView.printInputMoneyStatus(purchase.getMoney());
            String name = InputView.readProductName();
            purchase.buyProduct(name);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            buyProduct();
        }
    }

    private void showFinalChange() {
        int leftMoney = purchase.getMoney();
        OutputView.printInputMoneyStatus(leftMoney);
        OutputView.printChangeStatus(change.getFinalCoins(leftMoney));
    }
}
