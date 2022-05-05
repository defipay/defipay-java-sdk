package com.defipay.api.client;

import com.defipay.api.client.config.Env;
import com.defipay.api.client.domain.ApiResponse;
import com.defipay.api.client.domain.request.*;
import com.defipay.api.client.domain.response.*;
import com.defipay.api.client.domain.response.external.ChainTokenInfoDTO;
import com.defipay.api.client.domain.response.external.CoinApiAssetInfoDTO;
import com.defipay.api.client.impl.LocalSigner;
import com.defipay.api.client.impl.Utils;
import com.defipay.api.client.security.AuthenticationInterceptor;
import junit.framework.TestCase;
import org.apache.commons.lang3.time.DateUtils;
import retrofit2.Retrofit;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

public class DefipayJavaApiApplicationTests extends TestCase {

    private final String apiSecret = "2f4aa1551067f7bf50c350f923a3da6cc3e6ba5138f1c743b6f65e6e6b2e78f4";
    private DefipayApiRestClient client;

    public void setUp() throws Exception {
        super.setUp();
        client = DefipayApiClientFactory.newInstance(
                new LocalSigner(apiSecret),
                Env.SANDBOX,
                true).newRestClient();
    }

    public void tearDown() {
    }

    public void testCreateOrder() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setNotifyUrl("http://xcsewvb.ao/nhhcn");
        request.setReturnUrl("http://xcsewvb.ao/nhhcn");
        request.setAmount("1000");
        request.setCurrency("USDT");
        request.setMemberTransNo(getMemberTransNo(1));
        request.setTokenIds("2");
        ApiResponse<CreateOrderResponse> order = client.createOrder(request);
        System.out.println(order);
    }

    private String getMemberTransNo(int type){
        Date date = new Date();
        int random = new Random().nextInt(10000);
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = yyyyMMddHHmmss.format(date);
        return type == 1?"B":"PB"+format+random;
    }


    public void testQueryOrder(){
        ApiResponse<OrderQueryResponse> orderQueryResponseApiResponse = client.queryOrder("UG45OID2");
        System.out.println(orderQueryResponseApiResponse);

    }

    public void testQueryPayOutOrder(){
        ApiResponse<PayoutOrderQueryResponse> response = client.queryPayoutOrder("5NONKD04");
        System.out.println(response);
    }

    public void testCreatePayOutOrder(){
        CreatePayoutOrderRequest createPayoutOrderRequest = new CreatePayoutOrderRequest();
        createPayoutOrderRequest.setNotifyUrl("http://xcsewvb.ao/nhhcn");
        createPayoutOrderRequest.setAmount("1000");
        createPayoutOrderRequest.setCurrency("USDT");
        createPayoutOrderRequest.setMemberTransNo(getMemberTransNo(2));
        createPayoutOrderRequest.setTokenId(2l);
        createPayoutOrderRequest.setToAddress("0x3531C5F7540aDC5e5d640De11DE524cD379CC717");
        ApiResponse<CreatePayoutOrderResponse> payoutOrder = client.createPayoutOrder(createPayoutOrderRequest);
        System.out.println(payoutOrder);
    }

    public void testQueryBillCurrency(){
        ApiResponse<List<CoinApiAssetInfoDTO>> listApiResponse = client.queryBillCurrency(1, 10);
        System.out.println(listApiResponse);
    }
    public void testQueryOrderList(){
        ApiResponse<List<CoinApiAssetInfoDTO>> listApiResponse = client.queryBillCurrency(1, 10);
        System.out.println(listApiResponse);
    }
    public void testGetOrderDetail(){
        ApiResponse<OrderQueryResponse> orderDetail = client.getOrderDetail(253l);
        System.out.println(orderDetail);
    }
    public void testQueryPayCurrency(){
        ApiResponse<List<ChainTokenInfoDTO>> listApiResponse1 = client.queryPayCurrency(1, 10);
        System.out.println(listApiResponse1);
    }
    public void testQueryCryptoAmount(){
        ApiResponse<List<MemberUserVirtualAccountInfoResponse>> listApiResponse2 = client.queryCryptoAmount();
        System.out.println(listApiResponse2);
    }
    public void testQueryRate(){
        ApiResponse<RateDTO> rateDTOApiResponse = client.queryRate("ETH", "USDT");
        System.out.println(rateDTOApiResponse);
    }
}
