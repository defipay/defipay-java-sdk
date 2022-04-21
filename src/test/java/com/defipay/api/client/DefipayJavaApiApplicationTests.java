package com.defipay.api.client;

import com.defipay.api.client.config.Env;
import com.defipay.api.client.domain.ApiResponse;
import com.defipay.api.client.domain.request.*;
import com.defipay.api.client.domain.response.*;
import com.defipay.api.client.domain.response.external.ChainTokenInfoDTO;
import com.defipay.api.client.domain.response.external.CoinApiAssetInfoDTO;
import com.defipay.api.client.impl.LocalSigner;
import org.junit.Test;

import java.util.List;

class DefipayJavaApiApplicationTests {

    @Test
    void contextLoads() {
        String apiPkey = "303e020100301006072a8648ce3d020106052b8104000a0427302502010104206d7b5fda6a8ac759ebef99a453b07b54370a1be4a7b26f1d89b61439e3f4c02a";
        DefipayApiRestClient client = DefipayApiClientFactory.newInstance(
                new LocalSigner(apiPkey),
                false ? Env.PROD : Env.SANDBOX,
                false).newRestClient();
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
        orderQueryRequest.setMemberTransNo("testasdafasf001");
        ApiResponse<OrderQueryResponse> orderQueryResponseApiResponse = client.queryOrder(orderQueryRequest);
        System.out.println(orderQueryResponseApiResponse);

        PayoutOrderQueryRequest payoutOrderQueryRequest = new PayoutOrderQueryRequest();
        payoutOrderQueryRequest.setTransNo("5NONKD04");
        payoutOrderQueryRequest.setMemberTransNo("testasdafasf002");
        ApiResponse<PayoutOrderQueryResponse> response = client.queryPayoutOrder(payoutOrderQueryRequest);
        System.out.println(response);

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

        ApiResponse<List<CoinApiAssetInfoDTO>> listApiResponse = client.queryBillCurrency(1, 10);
        System.out.println(listApiResponse);

        ApiResponse<List<ChainTokenInfoDTO>> listApiResponse1 = client.queryPayCurrency(1, 10);
        System.out.println(listApiResponse1);

        RateQueryRequest rateQueryRequest = new RateQueryRequest();
        rateQueryRequest.setBase("ETH");
        rateQueryRequest.setQuote("USDT");
        ApiResponse<RateDTO> rateDTOApiResponse = client.queryRate(rateQueryRequest);
        System.out.println(rateDTOApiResponse);

        ApiResponse<List<MemberUserVirtualAccountInfoResponse>> listApiResponse2 = client.queryCryptoAmount();
        System.out.println(listApiResponse2);
    }

}
