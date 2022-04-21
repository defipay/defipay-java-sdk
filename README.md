# Defipay Java API

defipay-java-api 是一个轻量级的 Java 库，用于与[Defipay API](http://doc.defipay.biz/)交互，提供完整的 API 覆盖。


* [安装](#安装)
* [测试](#测试)
* [用法](#用法)
  * [初始化](#初始化)
     * [生成密钥对](#生成密钥对)
     * [初始化 RestClient](#初始化-restclient)
     * [初始化 ApiSigner](#初始化-apisigner)
  * [请求下单](#请求下单)
     * [充值请求下单](#充值请求下单)
     * [提现请求下单](#提现请求下单)
  * [交易查询](#交易查询)
     * [充值交易查询](#充值交易查询)
     * [提现交易查询](#提现交易查询)
  * [币种查询](#币种查询)
     * [支持账单币种查询](#支持账单币种查询)
     * [支持支付币种查询](#支持支付币种查询)
     * [费率查询](#费率查询)
  * [账户余额查询](#账户余额查询)
     * [商户账户余额查询](#商户账户余额查询)

## 安装

步骤 1. 将 JitPack 存储库添加到您的构建文件

gradle:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

maven:

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

步骤 2. 添加依赖项

gradle:

```
dependencies {
    implementation 'com.github.CoboCustody:cobo-java-api:v0.29'
}
```

maven:

```
<dependency>
    <groupId>com.github.CoboCustody</groupId>
    <artifactId>cobo-java-api</artifactId>
    <version>v0.29</version>
</dependency>
```



## 测试

```
 ./gradlew test
```

## 用法

### 初始化

#### 生成密钥对
```java
import LocalSigner;

String[] key = LocalSigner.generateKeyPair();
Stirng secretKey = key[0];
Stirng apiKey = key[1];
```
apiKey的使用方法请参考[链接](http://doc.defipay.biz/index.html#title1_child2)

#### 初始化-restClient
这些可以通过相应的工厂方法实例化 `DefipayApiClientFactory`

```java
import ccom.defipay.api.client.DefipayApiClientFactory;
import ccom.defipay.api.client.DefipayApiRestClient;
import ccom.defipay.api.client.config.DefipayApiConfig;
import ccom.defipay.api.client.config.Env;
import ccom.defipay.api.client.impl.LocalSigner;

DefipayApiRestClient client = DefipayApiClientFactory.newInstance(
                new LocalSigner(apiSecret),
                Env.SANDBOX,
                false).newRestClient();
```

#### 初始化-apiSigner


`ApiSigner` 可以通过实例化 `new LocalSigner("secretkey" )`

在某些情况下，您的私钥无法导出，例如，您的私钥在 aws kms 中，您应该通过实现`ApiSigner`接口传入您自己的实现：


```java

import com.defipay.api.client.ApiSigner;
new ApiSigner() {
    @Override
    public String sign(byte[] message) {
        return null;
    }

    @Override
    public String getPublicKey() {
        return null;
    }
}
```

### 请求下单

#### 充值请求下单
```java
CreateOrderRequest request = new CreateOrderRequest();
request.setNotifyUrl("www.baidu.com");
request.setReturnUrl("www.baidu.com");
request.setAmount("0.01");
request.setCurrency("ETH");
request.setMemberTransNo("testasdafasf001");
request.setTokenIds("2");
ApiResponse<CreateOrderResponse> order = client.createOrder(request);
```
<details>
<summary>响应视图</summary>


```java
CreateOrderResponse{cashierUrl='http://47.97.49.47/customer/#/order/LBGL6HAH', tokenInfo=[PayOrderAddressInfoDTO{address='0x62b0f4dcc559a57482f7cf800e10ab9e72e270ae', amount='0', shortName='ETH', displayName='ETH', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/ba40da70bed74489a7ed6adaed495763.png', coinType='Mainnet', chainId=null, chainAssertDecimal=18, chainAssertId=''}], memberTransNo='testasdafasf001', currency='ETH', amount='0.01', transNo='LBGL6HAH'}
```
</details>

#### 提现请求下单
```java
CreatePayoutOrderRequest createPayoutOrderRequest = new CreatePayoutOrderRequest();
createPayoutOrderRequest.setNotifyUrl("www.baidu.com");
createPayoutOrderRequest.setPayAmount("0.01");
createPayoutOrderRequest.setAmount("0.01");
createPayoutOrderRequest.setCurrency("ETH");
createPayoutOrderRequest.setMemberTransNo("testasdafasf002");
createPayoutOrderRequest.setTokenId(2l);
createPayoutOrderRequest.setToAddress("0x3531C5F7540aDC5e5d640De11DE524cD379CC717");
ApiResponse<CreatePayoutOrderResponse> payoutOrder = client.createPayoutOrder(createPayoutOrderRequest);
```
<details>
<summary>响应视图</summary>


```java
CreatePayoutOrderResponse{transNo='5NONKD04', memberTransNo='null', currency='null', amount='null', tokenId=null, tokenAmount='null'}
```
</details>

### 交易查询
#### 充值交易查询
```java
OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
orderQueryRequest.setTransNo("UG45OID2");
orderQueryRequest.setMemberTransNo("testasdafasf001");
ApiResponse<OrderQueryResponse> orderQueryResponseApiResponse = client.queryOrder(orderQueryRequest);
```
<details>
<summary>响应视图</summary>


```java
OrderQueryResponse{id=null, transNo='UG45OID2', memberTransNo='testasdafasf001', tokenId=null, shortName='null', billAmount='null', billCurrency='null', txHash='null', blockNumber=null, fromAddress='null', toAddress='null', memo='null', comment='null', confirmNum=null, state=null, scanUrl='null', expiredTime=null, settleTime=null}
```
</details>

#### 提现交易查询
```java
PayoutOrderQueryRequest request = new PayoutOrderQueryRequest();
request.setTransNo("UG45OID2");
request.setMemberTransNo("testasdafasf001");
ApiResponse<PayoutOrderQueryResponse> payoutOrderQueryResponse = client.queryPayoutOrder(request);
```
<details>
<summary>响应视图</summary>


```java
PayoutOrderQueryResponse{id=null, transNo='UG45OID2', memberTransNo='testasdafasf001', tokenId=null, shortName='null', billAmount='null', billCurrency='null', txHash='null', blockNumber=null, fromAddress='null', toAddress='null', memo='null', comment='null', confirmNum=null, state=null, scanUrl='null', expiredTime=null, settleTime=null}
```
</details>

### 币种查询
#### 支持账单币种查询
```java
ApiResponse<List<CoinApiAssetInfoDTO>> listApiResponse = client.queryBillCurrency(1, 10);
```
<details>
<summary>响应视图</summary>


```java
CoinApiAssetInfoDTO{assertId='AED', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='AUD', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='BRC', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='BYN', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='CAD', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='CHF', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='CLP', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='DEM', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='DKK', typeIsCrypto=0}, CoinApiAssetInfoDTO{assertId='ESP', typeIsCrypto=0}
```
</details>

#### 支持支付币种查询
```java
ApiResponse<List<ChainTokenInfoDTO>> listApiResponse1 = client.queryPayCurrency(1, 10);
```
<details>
<summary>响应视图</summary>


```java
ChainTokenInfoDTO{id=1, name='BTC', displayName='BTC', shortName='BTC', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/8f6e5e2382f94028b87307ad5c73c52e.png', chainAssertId='', chainAssertDecimal='6'}, ChainTokenInfoDTO{id=2, name='ETH', displayName='ETH', shortName='ETH', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/ba40da70bed74489a7ed6adaed495763.png', chainAssertId='', chainAssertDecimal='18'}, ChainTokenInfoDTO{id=3, name='Tether', displayName='USDT-ERC20', shortName='USDT', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/1b88c0c2dba04080bd3165843de3ffae.png', chainAssertId='0xdac17f958d2ee523a2206206994597c13d831ec7', chainAssertDecimal='6'}, ChainTokenInfoDTO{id=4, name='Binance Coin', displayName='BNB', shortName='BNB', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/6300d391da1642c58c6673f32235db89.png', chainAssertId='null', chainAssertDecimal='18'}, ChainTokenInfoDTO{id=5, name='USDC', displayName='USDC-ERC20', shortName='USDC', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/b5afa9c1d46842cea775d3e63c9287b7.png', chainAssertId='0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48', chainAssertDecimal='6'}, ChainTokenInfoDTO{id=6, name='Ripple', displayName='XRP-BEP20', shortName='XRP', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/7df1c4bc20054894b52b4aa6a7ae1e81.png', chainAssertId='0x1d2f0da169ceb9fc7b3144628db156f3f6c60dbe', chainAssertDecimal='18'}, ChainTokenInfoDTO{id=7, name='Cardano', displayName='ADA-BEP20', shortName='ADA', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/35d6f3167e9c4a9dae0ef34c50a1deb0.png', chainAssertId='0x3ee2200efb3400fabb9aacf31297cbdd1d435d47', chainAssertDecimal='18'}, ChainTokenInfoDTO{id=8, name='Solana', displayName='SOL', shortName='SOL', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/068abeb1076c40189d64c69cf70ed6e6.png', chainAssertId='null', chainAssertDecimal='18'}, ChainTokenInfoDTO{id=9, name='Luna Coin', displayName='LUNA', shortName='LUNA', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/a8a96dd9240c425291bb87178aae935c.png', chainAssertId='null', chainAssertDecimal='18'}, ChainTokenInfoDTO{id=10, name='AVAX', displayName='AVAX-C', shortName='AVAX', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/62f25f5cf608415587a7cda95d9238c0.png', chainAssertId='null', chainAssertDecimal='18'}
```
</details>

#### 费率查询
```java
RateQueryRequest rateQueryRequest = new RateQueryRequest();
rateQueryRequest.setBase("ETH");
rateQueryRequest.setQuote("USDT");
ApiResponse<RateDTO> rateDTOApiResponse = client.queryRate(rateQueryRequest);
```
<details>
<summary>响应视图</summary>


```java
RateDTO{rate='3101.1034362075067355785325348', rateTime=1650398432}
```
</details>

### 账户余额查询
#### 商户账户余额查询
```java
ApiResponse<List<MemberUserVirtualAccountInfoResponse>> listApiResponse2 = client.queryCryptoAmount();
```
<details>
<summary>响应视图</summary>


```java
MemberUserVirtualAccountInfoResponse{totalAmount=1.100000000000000000000000000000, frozenAmount=0E-30, availableAmount=1.100000000000000000000000000000, tokenId=1, shortName='BTC', name='BTC', displayName='BTC', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/8f6e5e2382f94028b87307ad5c73c52e.png'}, MemberUserVirtualAccountInfoResponse{totalAmount=-1.566048866588656755000000000000, frozenAmount=-2.125287338844321266000000000000, availableAmount=0.559238472255664511000000000000, tokenId=2, shortName='ETH', name='ETH', displayName='ETH', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/ba40da70bed74489a7ed6adaed495763.png'}, MemberUserVirtualAccountInfoResponse{totalAmount=1.000000000000000000000000000000, frozenAmount=0E-30, availableAmount=1.000000000000000000000000000000, tokenId=3, shortName='USDT', name='Tether', displayName='USDT-ERC20', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/1b88c0c2dba04080bd3165843de3ffae.png'}, MemberUserVirtualAccountInfoResponse{totalAmount=919.000000000000000000000000000000, frozenAmount=0E-30, availableAmount=919.000000000000000000000000000000, tokenId=25, shortName='TRX', name='TRON', displayName='TRX', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/c205901ff60f4d588d4617bbaa0bc939.png'}, MemberUserVirtualAccountInfoResponse{totalAmount=1.000000000000000000000000000000, frozenAmount=0E-30, availableAmount=1.000000000000000000000000000000, tokenId=30, shortName='XLM', name='Stellar Lumens', displayName='XLM', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/f15e6a83d9e34060b1cbb84c628aa2ec.png'}, MemberUserVirtualAccountInfoResponse{totalAmount=10.000000000000000000000000000000, frozenAmount=0E-30, availableAmount=10.000000000000000000000000000000, tokenId=31, shortName='USDT', name='Tether', displayName='USDT-TRC20', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/1b88c0c2dba04080bd3165843de3ffae.png'}, MemberUserVirtualAccountInfoResponse{totalAmount=0E-30, frozenAmount=0E-30, availableAmount=0E-30, tokenId=42, shortName='TETH_CTT', name='Cobo Test Token', displayName='TETH_CTT', logoUrl='https://ipi-cdn.oss-cn-hangzhou.aliyuncs.com/tadle_v1.1/user/avater/e9a72a3480054fd7968d84617c4c0db2.jpg'}, MemberUserVirtualAccountInfoResponse{totalAmount=0E-30, frozenAmount=0E-30, availableAmount=0E-30, tokenId=12, shortName='DOGE', name='DogeCoin', displayName='DOGE', logoUrl='https://defipay-test.oss-cn-hangzhou.aliyuncs.com/defipay_v_1.0/ec90d3d5635747b199967ce06a5be4c3.png'}
```
</details>

