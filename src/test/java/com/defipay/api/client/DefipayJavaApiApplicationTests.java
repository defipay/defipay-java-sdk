package com.defipay.api.client;

import com.defipay.api.client.config.Env;
import com.defipay.api.client.domain.ApiResponse;
import com.defipay.api.client.domain.request.*;
import com.defipay.api.client.domain.response.*;
import com.defipay.api.client.domain.response.external.ChainTokenInfoDTO;
import com.defipay.api.client.domain.response.external.CoinApiAssetInfoDTO;
import com.defipay.api.client.impl.LocalSigner;
import junit.framework.TestCase;

import java.util.List;

public class DefipayJavaApiApplicationTests extends TestCase {

    private final String apiSecret = "303e020100301006072a8648ce3d020106052b8104000a0427302502010104204d1ec99a93307841e23d1ed5cdd149a1eea7a77aaec6a083e7a62480c8cf8851";
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
        request.setNotifyUrl("www.baidu.com");
        request.setReturnUrl("www.baidu.com");
        request.setAmount("0.01");
        request.setCurrency("ETH");
        request.setMemberTransNo("testasdafasf001");
        request.setTokenIds("2");
        ApiResponse<CreateOrderResponse> order = client.createOrder(request);
        System.out.println(order);
        OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
        orderQueryRequest.setTransNo("UG45OID2");
        ApiResponse<OrderQueryResponse> orderQueryResponseApiResponse = client.queryOrder("UG45OID2");
        System.out.println(orderQueryResponseApiResponse);
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
        createPayoutOrderRequest.setNotifyUrl("www.baidu.com");
        createPayoutOrderRequest.setPayAmount("0.01");
        createPayoutOrderRequest.setAmount("0.01");
        createPayoutOrderRequest.setCurrency("ETH");
        createPayoutOrderRequest.setMemberTransNo("testasdafasf002");
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
}
