package com.kv.j8.filenio;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class IOvsNIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = "{\"sequential_dimension\":false,\"redirect_url\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?discoverability=online\",\"redirect\":false,\"shareurl\":\"https://dev1.paytmmall.com/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?discoverability=online\",\"canonical_url\":\"https://dev1.paytmmall.com/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp\",\"qty_pdp\":false,\"action_text\":\"Buy for\",\"showMrpTag\":true,\"category_id\":9049,\"product_id\":19592739,\"parent_id\":19593032,\"product_type\":\"product\",\"name\":\"Zovi Blue Acrylic Pullover\",\"merchant_sku\":\"106300961010L\",\"productName\":\"Zovi Blue Acrylic Pullover by Display name test\",\"bargain_name\":\"Zovi Blue Acrylic Pullover\",\"brand\":\"Zovi\",\"brand_id\":116,\"brand_logo\":null,\"installation_eligible\":false,\"attributes\":{\"color\":\"Blue\",\"size\":\"L\",\"material\":\"Acrylic\",\"occasion\":\"\",\"rise\":\"\",\"length\":null,\"pattern\":\"Full Sleeves\",\"sleeve\":\"\",\"neck_type\":\"Round Neck\",\"fit\":\"Regular\",\"bra_type\":\"\",\"wiring\":\"\",\"color_filter\":\"Blue\",\"type_filter\":null,\"type\":null,\"gender\":\"Men\",\"collar_type\":null,\"collection\":null,\"hood\":null,\"nightdress_type\":null,\"padding\":null,\"pockets\":null,\"set_contents\":null,\"style\":null,\"wash_care\":null,\"wash_type\":null,\"designed_by\":null},\"short_desc\":\"\",\"bullet_points\":{\"salient_feature\":[]},\"long_rich_desc\":[{\"title\":\"Description\",\"description\":\" Soft and immensely comfortable this full-sleeved pullover will keep you warm during the cold days of winter and also give you a look that is in vogue. Navy blue and crimson red striped pullover. Ribbed round neck. Full sleeves with ribbed cuffs. Ribbed hem. Brand detailing on the left side of the chest. Put on this pullover with distressed denims or chinos and boat shoes. Fabric: 100% Acrylic. \",\"attributes\":{\"Brand\":\"Zovi\",\"Product Code\":\"APPZOVI-BLUE-ACZOVI106A59B1FB0\",\"Color\":\"Blue\",\"Size\":\"L\",\"Material\":\"Acrylic\",\"Pattern\":\"Full Sleeves\",\"Neck Type\":\"Round Neck\",\"Fit\":\"Regular\",\"Gender\":\"Men\"}},{\"title\":\"Return Policy\",\"attributes\":{\"Return Policy\":\"Seller will gladly accept the return within 15 days of delivery & refund can take up to 2 weeks\"},\"show_wt\":1}],\"return_policy\":null,\"other_rich_desc\":null,\"promo_text\":null,\"actual_price\":999,\"offer_price\":799,\"totalScore\":null,\"listing_tag\":[],\"pincode\":null,\"tag\":null,\"convenience_fee\":0,\"product_rating\":null,\"autocode\":null,\"need_shipping\":true,\"image_url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/8.jpg\",\"thumbnail\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/10.jpg\",\"product_created_date\":null,\"product_end_date\":null,\"status\":true,\"instock\":true,\"meta_title\":\"Buy Zovi Blue Acrylic Pullover Online at Low Prices in India - Paytm.com\",\"meta_description\":\"Paytm.com - Buy Zovi Blue Acrylic Pullover online at best prices in India on Paytm.com\",\"meta_keyword\":null,\"vertical_id\":2,\"vertical_label\":\"Apparel\",\"merchant\":{\"merchant_id\":106,\"merchant_name\":\"Display name test\",\"merchant_image\":\"https://assetscdn1.paytm.com/images/merchant/106/logo_1.png\",\"selling_since\":\"2014-03-25T07:23:37.000Z\",\"stores\":[{\"store_name\":\"Zovi\",\"store_image\":\"\"}],\"is_C2I_Merchant\":1,\"paytm_verified\":0,\"address\":{\"address\":\"101, 1st floor, Embassy Classic, 11, Vittal Mallya ,Bangalore - 560001\",\"city\":\"Bangalore \",\"state\":\"Bangalore \",\"pin_code\":\"560001\"}},\"preorder\":false,\"terms_conditions\":{\"title\":\"Terms & Conditions\",\"message\":\"* Promo codes are subject to maximum cash back amounts\\n* Promo codes are not valid for recharges, bill payments, bus ticket bookings, gift cards & deals\\n* Cash back acquired from promo codes to be credited within 24 hours of your product being shipped\\n* Cash back received will not be credited back to your bank account\"},\"bulk_pricing\":false,\"no_index\":false,\"no_follow\":false,\"pay_type_supported\":{\"COD\":0,\"CC\":1,\"DC\":1,\"NB\":1,\"ESCROW\":0,\"PPI\":1,\"EMI\":1,\"UPI\":1,\"PAYTM_DIGITAL_CREDIT\":1},\"emi_enabled\":false,\"upi_enabled\":true,\"paytm_digital_credit_enabled\":true,\"offer_url\":\"/v1/promosearch/product/19592739/offers?parent_id=19593032&price=799&merchant_id=106&brand_id=116\",\"weight\":396,\"dimensions\":\"\",\"brand_seller_url\":\"https://catalog-staging.paytm.com/dev1/brand/seller?brand_id=116&merchant_id=106&category_id=5028,5029,9048,9049\",\"other_sellers\":{\"values\":[]},\"ancestors\":[{\"name\":\"tshirt\",\"id\":5028,\"url_key\":\"men\",\"url_type\":\"grid\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/g/men\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/men-fashion-glpid-5028\"},{\"name\":\"Clothing\",\"id\":5029,\"url_key\":\"men/clothes-all\",\"url_type\":\"grid\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/g/men/clothes-all\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/men-clothing-glpid-5029\"},{\"name\":\"Winter Wear New\",\"id\":9048,\"url_key\":\"men/clothes-all/winter-wear\",\"url_type\":\"grid\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/g/men/clothes-all/winter-wear\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/men-winter-wear-glpid-9048\"},{\"name\":\"Sweaters\",\"id\":9049,\"url_key\":\"men/clothes-all/winter-wear/sweaters\",\"url_type\":\"grid\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/g/men/clothes-all/winter-wear/sweaters\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/men-sweaters-glpid-9049\"},{\"id\":19593032,\"name\":\"Zovi Blue Acrylic Pullover \",\"url_type\":\"product\",\"url_key\":\"zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0\"}],\"input_fields\":[],\"is_mall\":true,\"enabled\":true,\"offers\":{\"bank_offers\":{\"offer\":[]},\"freebie\":[],\"emi\":{\"plans\":[],\"offer_url\":\"https://catalog-staging.paytm.com/v1/emi/plans\",\"no_cost_emi\":false,\"offer_text\":\"Low cost EMI available\",\"offer_icon\":\"https://s3-ap-southeast-1.amazonaws.com/assets.paytm.com/images/catalog/offers/Zero_Cost.png\",\"additional_text\":null,\"offer_description\":null}},\"brand_category_url\":\"https://catalog-staging.paytm.com/dev1/zovi-sweaters-bclpid-116-9049\",\"manufacturing_details\":null,\"brand_url\":\"https://search-staging.paytm.com/zovi-blpid-116\",\"is_svc_change\":0,\"emi_url\":\"https://paytmmall.com/emi?bajajfn=false&amount=799\",\"exchange_details\":{\"eligible\":false,\"category_type\":\"1\",\"exchange_partners\":[]},\"min_quantity\":1,\"max_quantity\":5,\"add_qty_using_price\":false,\"button_text\":null,\"discount\":\"20%\",\"quantity\":10,\"autolocate\":true,\"attribute_chart\":{},\"contextParams\":{\"discoverability\":\"online\"},\"filters\":[{\"title\":\"Select Size\",\"type\":\"linear-rectangular\",\"metadata\":{\"sort_type\":\"manual\",\"sort_order\":[\"L\",\"M\"]},\"display_value\":\"Size\",\"filter_param\":\"size\",\"values\":[{\"id\":\"L\",\"name\":\"L\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-l-APPZOVI-BLUE-ACZOVI106A59B1FB0?discoverability=online\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-l-APPZOVI-BLUE-ACZOVI106A59B1FB0?discoverability=online\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?product_id=19592739&discoverability=online\",\"product_id\":19592739,\"totalScore\":null,\"exist\":true,\"applied\":true,\"attributes\":\"\",\"position\":0},{\"id\":\"M\",\"name\":\"M\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-m-APPZOVI-BLUE-ACZOVI106C664830F?discoverability=online\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-m-APPZOVI-BLUE-ACZOVI106C664830F?discoverability=online\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?product_id=19592740&discoverability=online\",\"product_id\":19592740,\"totalScore\":0,\"exist\":true,\"applied\":false,\"attributes\":\"\",\"position\":1},{\"id\":\"S\",\"name\":\"S\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-s-APPZOVI-BLUE-ACZOVI1067546F303?discoverability=online\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-s-APPZOVI-BLUE-ACZOVI1067546F303?discoverability=online\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?product_id=19592741&discoverability=online\",\"product_id\":19592741,\"totalScore\":0,\"exist\":true,\"applied\":false,\"attributes\":\"\",\"position\":2},{\"id\":\"XL\",\"name\":\"XL\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-xl-APPZOVI-BLUE-ACZOVI106C9BD9B9B?discoverability=online\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-xl-APPZOVI-BLUE-ACZOVI106C9BD9B9B?discoverability=online\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?product_id=19592742&discoverability=online\",\"product_id\":19592742,\"totalScore\":0,\"exist\":true,\"applied\":false,\"attributes\":\"\",\"position\":3},{\"id\":\"XXL\",\"name\":\"XXL\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-xxl-APPZOVI-BLUE-ACZOVI106D99DD946?discoverability=online\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-xxl-APPZOVI-BLUE-ACZOVI106D99DD946?discoverability=online\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?product_id=19592743&discoverability=online\",\"product_id\":19592743,\"totalScore\":0,\"exist\":true,\"applied\":false,\"attributes\":\"\",\"position\":4}]},{\"title\":\"Select Color\",\"type\":\"linear-rectangular\",\"display_value\":\"Color\",\"filter_param\":\"color\",\"values\":[{\"id\":\"BLUE\",\"name\":\"BLUE\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-l-APPZOVI-BLUE-ACZOVI106A59B1FB0?discoverability=online\",\"seourl\":\"https://catalog-staging.paytm.com/dev1/v1/p/zovi-blue-acrylic-pullover-size-l-APPZOVI-BLUE-ACZOVI106A59B1FB0?discoverability=online\",\"newurl\":\"https://catalog-staging.paytm.com/dev1/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0-pdp?product_id=19592739&discoverability=online\",\"product_id\":19592739,\"totalScore\":null,\"exist\":true,\"applied\":true,\"attributes\":\"\",\"color_code\":\"saurabh\"}]}],\"quantity_across_merchant\":10,\"interlink_page_url\":\"https://catalog-staging.paytm.com/dev1/v1/mobile/getInterlinkedPageData?category_id=9049\",\"upcfields\":[],\"bargainable\":false,\"ga_key\":\"/p/men/clothes-all/winter-wear/sweaters/zovi-blue-acrylic-pullover-CMPLXAPPZOVI-BLUE-ACZOVI106A59B1FB0\",\"category_ids\":[5028,5029,9048,9049],\"fulfilled_by_paytm\":0,\"badges\":[],\"validations\":[],\"product_id_changed\":0,\"similar_product_url\":\"https://catalog-staging.paytm.com/dev1/v2/products/similar/19592739\",\"url\":\"https://catalog-staging.paytm.com/dev1/v1/mobile/product/19593032\",\"other_images\":[\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/0.jpg\",\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/2.jpg\",\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/4.jpg\",\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/6.jpg\"],\"resources\":[{\"type\":\"image\",\"url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/8.jpg\"},{\"type\":\"image\",\"url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/0.jpg\"},{\"type\":\"image\",\"url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/2.jpg\"},{\"type\":\"image\",\"url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/4.jpg\"},{\"type\":\"image\",\"url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/6.jpg\"},{\"type\":\"video\",\"url\":\"https://www.youtube.com/watch?v=yEB1P6hUV8M\",\"thumbnail_url\":\"https://assetscdn1.paytm.com/images/catalog/product/A/AP/APPZOVI-BLUE-ACZOVI106A59B1FB0/0x1920/70/8.jpg\"}]}\n"; 
				
		
		InputStream is = new ByteArrayInputStream( content.getBytes());
		try {
			String ioContent = readIOContent(is);
			System.out.println("IO Content : "+ioContent);
			
			String ioUtilsContent = readIOUtils(is);
			System.out.println("IOUtils Content : "+ioUtilsContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String readIOContent(InputStream is) throws IOException {
		long time = System.currentTimeMillis();
		
        //String charset = EntityUtils.getContentCharSet(entity);
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        StringBuilder response = new StringBuilder();
        try {
                inputStreamReader = new InputStreamReader(is);
            
	            br = new BufferedReader(inputStreamReader);
	            String line = null;
	            while ((line = br.readLine()) != null) {
	                response.append(line).append("\n");
	            }
	            // This is to ensure the resources are released properly
	            //EntityUtils.consume(entity);
        } finally {
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (br != null) {
                br.close();
            }
        }
        System.out.println("Time to Complete via IO : "+((System.currentTimeMillis()-time))+" ms.");
        return response.toString();
    }
	
	
	public static String readIOUtils(InputStream is) throws IOException {
		long time = System.currentTimeMillis();
	    String res =  IOUtils.toString(is, StandardCharsets.UTF_8.name());
	    System.out.println("Time to Complete via IOUtils : "+((System.currentTimeMillis()-time))+" ms.");
	    return res;
	}
	
//	public final void readNIOContent(InputStream is) 
//	  throws IOException {
//	    InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());
//	 
//	    File f = Files.
//	    
//	    Path tempFile = Files.createTempDirectory("").resolve(UUID.randomUUID().toString() + ".tmp");
//	    Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
//	    String result = new String(Files.readAllBytes(tempFile));
//	 
//	    assertThat(result, equalTo(originalString));
//	}

}
