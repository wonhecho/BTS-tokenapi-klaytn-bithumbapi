package net.api.cho.stockdata.stock.api;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.WalletDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
public class Createwallet {
    private final RestTemplate restTemplates;
    private final String chain_id = "1001";
    private final String Auth = "Basic S0FTSzA0QTRYNEI1WVJBTE1EUDBUTzUzOnRTQ1VacVhoLUt4a0dnTklwdHZVQWV5aGtodnFLMmFHeVVKMHAtblg=";
    private final String URL = "https://wallet-api.klaytnapi.com/v2/account";

    public Object CheckWallet() throws IOException{
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id",chain_id);
        headers.set("Authorization",Auth);
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplates.exchange(URL,HttpMethod.GET,entity,String.class);
    }
    public WalletDto CreateWallet() throws IOException{
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id",chain_id);
        headers.set("Authorization",Auth);
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplates.exchange(URL,HttpMethod.POST,entity,WalletDto.class).getBody();
    }
    public String muchWallet() throws IOException, ParseException {
        String url = "https://node-api.klaytnapi.com/v1/klaytn";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id", chain_id);
        headers.set("Authorization", Auth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject request = new JSONObject();
        JSONArray array = new JSONArray();
        array.add("0x4c2AbfbB02A484CC1b43EEd9Bb744Cf11971dACF");
        array.add("latest");
        request.put("id", 1);
        request.put("jsonrpc", "2.0");
        request.put("method","klay_getBalance");
        request.put("params", array);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        System.out.println(request.toString());
        ResponseEntity<String> response = restTemplates.postForEntity(url,entity,String.class);
        String jText = response.getBody().toString();
        System.out.println(jText);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jText);
        System.out.println(obj.get("result"));
        String klayhex =  obj.get("result").toString();
        long klay = Long.decode(klayhex);
        System.out.println(klay);
        return response.toString();
    }
}
