package com.kv.test;

import java.util.List;

class HoldingPriceResponse {
    List<HoldingCount> data;

    public List<HoldingCount> getData() {
        return data;
    }

    public void setData(List<HoldingCount> data) {
        this.data = data;
    }

    class HoldingCount {
        String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSecurity() {
            return security;
        }

        public void setSecurity(String security) {
            this.security = security;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        String  security;
        Integer quantity;

    }
}
