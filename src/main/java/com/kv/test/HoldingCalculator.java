package com.kv.test;


import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import java.net.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.lang.reflect.Type;




class Result {

  /*
   * Complete the 'calculateNAV' function below.
   *
   * The function is expected to return a DOUBLE.
   * The function accepts STRING date as parameter.
   */


  private static String pricingEndpoint = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/pricing";

  private static String holdingEndpoint = "https://raw.githubusercontent.com/arcjsonapi/HoldingValueCalculator/master/api/holding";

  private static Map<String, Map<String, Double>> pricingMap = new HashMap<>();
  private static Map<String, Map<String, Integer>> holdingMap = new HashMap<>();

  private static DecimalFormat df2 = new DecimalFormat("#.##");

  public static double calculateNAV(String date) {

    double res = 0.0d;
    callHoldingService(date);
    callPricingService(date);

    Map<String, Integer> hMap = holdingMap.get(date);
    Map<String, Double> pMap = pricingMap.get(date);

    for (Map.Entry<String, Integer> entry : hMap.entrySet()) {
      String security = entry.getKey();
      Integer quantity = entry.getValue();
      Double marketPrice = pMap.get(security);

      res += (quantity * marketPrice);
    }

    return new Double(df2.format(res));
  }

  public static void callHoldingService(String date) {
    String content = call(holdingEndpoint);

    Gson gson = new Gson();
    Type userListType = new TypeToken<ArrayList<Holding>>(){}.getType();
    ArrayList<Holding> holdings = gson.fromJson(String.valueOf(content), userListType);


    for (Holding h : holdings) {
      if (h.getDate().equals(date)) {

        if (!holdingMap.containsKey(h.getDate())) {
          Map<String, Integer> m = new HashMap<>();
          m.put(h.getSecurity(), h.getQuantity());

          holdingMap.put(h.getDate(), m);
        } else {
          Map<String, Integer> smap = holdingMap.get(h.getDate());

          if(!smap.containsKey(h.getSecurity())) {
            smap.put(h.getSecurity(), h.getQuantity());
          }
        }
      }

    }
  }

  public static void callPricingService(String date) {
    String content = call(pricingEndpoint);

    Gson gson = new Gson();
    Type userListType = new TypeToken<ArrayList<MarketPrice>>(){}.getType();
    ArrayList<MarketPrice> marketPrices = gson.fromJson(String.valueOf(content), userListType);

    for (MarketPrice mp : marketPrices) {
      if (mp.getDate().equals(date)) {
        if (!pricingMap.containsKey(mp.getDate())) {

          Map<String, Double> m = new HashMap<>();
          m.put(mp.getSecurity(), mp.getPrice());

          pricingMap.put(mp.getDate(), m);

        } else {
          Map<String, Double> smap = pricingMap.get(mp.getDate());

          if(!smap.containsKey(mp.getSecurity())) {
            smap.put(mp.getSecurity(), mp.getPrice());
          }
        }
      }

    }
  }

  public static String call(String service) {
    StringBuffer response = new StringBuffer();
    try {
      URL url = new URL(service);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Content-Type", "application/json");
      con.setConnectTimeout(5000);
      con.setReadTimeout(5000);

      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
      con.disconnect();
    } catch(Exception e ) {
      e.printStackTrace();
    }

    return response.toString();
  }
}

class Holding {
  String security;
  int quantity;
  String portfolio;
  String date;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getSecurity() {
    return security;
  }

  public void setSecurity(String security) {
    this.security = security;
  }

  public String getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(String portfolio) {
    this.portfolio = portfolio;
  }
}

class MarketPrice {
  String security;
  double price;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}

public class HoldingCalculator {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String date = bufferedReader.readLine();

    double result = Result.calculateNAV(date);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
