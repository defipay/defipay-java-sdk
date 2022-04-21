package com.defipay.custody.api.client.impl;

import com.defipay.custody.api.client.ApiSigner;
import com.defipay.custody.api.client.DefipayApiRestClient;
import com.defipay.custody.api.client.config.Env;
import com.defipay.custody.api.client.domain.ApiResponse;
import com.defipay.custody.api.client.domain.request.*;
import com.defipay.custody.api.client.domain.response.*;
import com.defipay.custody.api.client.domain.response.external.ChainTokenInfoDTO;
import com.defipay.custody.api.client.domain.response.external.CoinApiAssetInfoDTO;

import java.util.List;

import static com.defipay.custody.api.client.impl.DefipayApiServiceGenerator.createService;
import static com.defipay.custody.api.client.impl.DefipayApiServiceGenerator.executeSync;

public class DefipayApiRestClientImpl implements DefipayApiRestClient {
    private final DefipayApiService defipayApiService;

    public DefipayApiRestClientImpl(ApiSigner signer, Env env, boolean debug) {
        defipayApiService = createService(DefipayApiService.class, signer, env, debug);
    }

    @Override
    public ApiResponse<CreateOrderResponse> createOrder(CreateOrderRequest request) {
        return executeSync(defipayApiService.createOrder(request.getNotifyUrl() ,request.getNotifyUrl(), request.getMemberTransNo(), request.getAmount()
                                                            ,request.getCurrency(), request.getTokenIds(), request.getMakingUp()));
    }

    @Override
    public ApiResponse<OrderQueryResponse> queryOrder(OrderQueryRequest request) {
        return executeSync(defipayApiService.queryOrder(request.getMemberTransNo(), request.getTransNo()));
    }

    @Override
    public ApiResponse<CreatePayoutOrderResponse> createPayoutOrder(CreatePayoutOrderRequest request) {
        return executeSync(defipayApiService.createPayoutOrder(request.getNotifyUrl(), request.getMemberTransNo(), request.getAmount(), request.getCurrency(),
                            request.getToAddress(), request.getTokenId(), request.getPayAmount()));
    }

    @Override
    public ApiResponse<PayoutOrderQueryResponse> queryPayoutOrder(PayoutOrderQueryRequest request) {
        return executeSync(defipayApiService.queryPayoutOrder());
    }

    @Override
    public ApiResponse<RateDTO> queryRate(RateQueryRequest request) {
        return executeSync(defipayApiService.queryRate(request.getBase() ,request.getQuote()));
    }

    @Override
    public ApiResponse<List<CoinApiAssetInfoDTO>> queryBillCurrency(Integer offset, Integer limit) {
        return executeSync(defipayApiService.queryBillCurrency(offset, limit));
    }

    @Override
    public ApiResponse<List<ChainTokenInfoDTO>> queryPayCurrency(Integer offset, Integer limit) {
        return executeSync(defipayApiService.queryPayCurrency(offset, limit));
    }

    @Override
    public ApiResponse<List<MemberUserVirtualAccountInfoResponse>> queryCryptoAmount() {
        return executeSync(defipayApiService.queryCryptoAmount());
    }

}
