package net.api.cho.stockdata.stock.api;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.WalletDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NFT {

    private final RestTemplate restTemplates;
    private final String chain_id = "1001";
    private final String Auth = "Basic S0FTSzA0QTRYNEI1WVJBTE1EUDBUTzUzOnRTQ1VacVhoLUt4a0dnTklwdHZVQWV5aGtodnFLMmFHeVVKMHAtblg=";
    private final String address_alias = "kas-tutorial";
    private final String URL = "https://kip17-api.klaytnapi.com/v1/contract/"+address_alias+"/token";
    private final String testaddress = "0x5819790c4214e8c91801C2E8Fa469e3221e95FA5";
    private final String testaddress2 = "0xC5233f6b0b4d731317eaF5F1de0299e721bdCB09";
    private final String CheckURL = "https://kip17-api.klaytnapi.com/v1/contract/"+address_alias+"/owner/";
    private final String token_id = "0x131";
    private final String SendURL = "https://kip17-api.klaytnapi.com/v1/contract/"+address_alias+"/token/"+token_id;

    public Object makeNFT() throws ParseException {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id", chain_id);
        headers.set("Authorization", Auth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject request = new JSONObject();
        request.put("to","0x5819790c4214e8c91801C2E8Fa469e3221e95FA5");
        request.put("id","0x131");
        request.put("uri","https://metastore.kip17.com/0xbe02aba/0x1");
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        System.out.println(request.toString());
        ResponseEntity<String> response = restTemplates.postForEntity(URL,entity,String.class);
        String jText = response.getBody().toString();
        System.out.println(jText);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jText);
        return obj;

    }
    public Object checkNFT(String account) throws ParseException{
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id", chain_id);
        headers.set("Authorization", Auth);
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        String checkURL = CheckURL+account;
        JSONObject response = restTemplates.exchange(checkURL, HttpMethod.GET,entity, JSONObject.class).getBody();
        String jText = response.toString();
        System.out.println(jText);
        return response.toString();
    }
    public Object sendNFT() throws ParseException{
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id", chain_id);
        headers.set("Authorization", Auth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject request = new JSONObject();
        request.put("sender",testaddress2);
        request.put("owner",testaddress2);
        request.put("to",testaddress);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        System.out.println(request.toString());
        ResponseEntity<String> response = restTemplates.postForEntity(SendURL,entity,String.class);
        String jText = response.getBody().toString();
        System.out.println(jText);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jText);
        return obj;
    }
}
