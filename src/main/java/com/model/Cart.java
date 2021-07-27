package com.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product,Long> productMap= new HashMap<>();

    public Cart(){

    }

    public Map<Product, Long> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product, Long> productMap) {
        this.productMap = productMap;
    }

    private boolean checkItemInCart(Product product){
        for (Map.Entry<Product,Long> entry : productMap.entrySet()){
            if (entry.getKey().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product,Long> selectItemInCart(Product product){
        for (Map.Entry<Product,Long> entry :productMap.entrySet()){
            if (entry.getKey().getId().equals(product.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product){
        if (!checkItemInCart(product)){
            productMap.put(product,1L);
        }else {
            Map.Entry<Product,Long> itemEntry  = selectItemInCart(product);
            Long newQuantity = itemEntry.getValue();
            productMap.replace(itemEntry.getKey(),newQuantity);
        }
    }

    //dùng để đếm số lượng sản phẩm đó hiện có trong giỏ hàng
    public Long countProductQuantity(){
        Long productQuantity = 0L;
        for (Map.Entry<Product,Long> entry: productMap.entrySet()
             ) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    // để đếm số lượng sản phẩm có trong giỏ hàng.
    public Long countItemQuantity(){
        return Long.valueOf(productMap.size());
    }

    // tinh tong tien thanh toan
    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Product, Long> entry : productMap.entrySet()){
            payment +=  entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }
}
