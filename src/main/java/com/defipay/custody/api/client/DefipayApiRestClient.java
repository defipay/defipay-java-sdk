package com.defipay.custody.api.client;

import com.defipay.custody.api.client.domain.ApiResponse;
import com.defipay.custody.api.client.domain.request.*;
import com.defipay.custody.api.client.domain.response.*;
import com.defipay.custody.api.client.domain.response.external.ChainTokenInfoDTO;
import com.defipay.custody.api.client.domain.response.external.CoinApiAssetInfoDTO;

import java.util.List;

public interface DefipayApiRestClient {


     ApiResponse<CreateOrderResponse> createOrder(CreateOrderRequest request);

     ApiResponse<OrderQueryResponse> queryOrder(OrderQueryRequest request);

     ApiResponse<CreatePayoutOrderResponse> createPayoutOrder(CreatePayoutOrderRequest request);

     ApiResponse<PayoutOrderQueryResponse> queryPayoutOrder(PayoutOrderQueryRequest request);

     ApiResponse<RateDTO> queryRate(RateQueryRequest request);
     ApiResponse<List<CoinApiAssetInfoDTO>> queryBillCurrency(Integer offset, Integer limit);

     ApiResponse<List<ChainTokenInfoDTO>> queryPayCurrency(Integer offset, Integer limit);

     ApiResponse<List<MemberUserVirtualAccountInfoResponse>> queryCryptoAmount();
}
