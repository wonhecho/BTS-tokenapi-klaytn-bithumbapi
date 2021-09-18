package net.api.cho.stockdata.stock.NFT.Api;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.NFT.Repository.NFTRepository;
import net.api.cho.stockdata.stock.NFT.Service.NFTService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NFTapi {
    private final NFTRepository nftRepository;
    private final RestTemplate restTemplates;
    private final String chain_id = "1001";
    private final String Auth = "Basic S0FTSzA0QTRYNEI1WVJBTE1EUDBUTzUzOnRTQ1VacVhoLUt4a0dnTklwdHZVQWV5aGtodnFLMmFHeVVKMHAtblg=";
    private final String address_alias = "kas-tutorial";
    private final String URL = "https://kip17-api.klaytnapi.com/v1/contract/"+address_alias+"/token";
    private final String CheckURL = "https://kip17-api.klaytnapi.com/v1/contract/"+address_alias+"/owner/";
    private String SendURL = "https://kip17-api.klaytnapi.com/v1/contract/"+address_alias+"/token/";

    public Object makeNFT(MakeNFT makeNFT) throws ParseException {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id", chain_id);
        headers.set("Authorization", Auth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String NFTid = changeNumber(makeNFT.getNO()*Math.pow(10,5));
        makeNFT.setId(NFTid);
        JSONObject request = new JSONObject();
        request.put("to",makeNFT.getOwner());
        request.put("id",NFTid);
        request.put("uri","127.0.0.1:8080/NFT/checkNFTbyid/"+makeNFT.getId());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        System.out.println(request.toString());
        ResponseEntity<String> response = restTemplates.postForEntity(URL,entity,String.class);
        String jText = response.getBody().toString();
        System.out.println(jText);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jText);
        System.out.println(obj.get("status"));
        if(obj.get("status").equals("Submitted")){
            Optional<MakeNFT> update = nftRepository.findByNO(makeNFT.getNO());
            update.ifPresent(selectUser -> {
                selectUser.setNO(makeNFT.getNO());
                selectUser.setId(NFTid);
                selectUser.setDescription(makeNFT.getDescription());
                selectUser.setName(makeNFT.getName());
                selectUser.setImage(makeNFT.getImage());
                selectUser.setOwner(makeNFT.getOwner());
                selectUser.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                MakeNFT newMakeNFT = nftRepository.save(selectUser);
                System.out.println("NFT "+ newMakeNFT);
            });
        }
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
    public Object sendNFT(NFT nft) throws ParseException{
        final HttpHeaders headers = new HttpHeaders();
        headers.set("x-chain-id", chain_id);
        headers.set("Authorization", Auth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject request = new JSONObject();
        request.put("sender",nft.getFrom());
        request.put("owner",nft.getFrom());
        request.put("to",nft.getTo());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        System.out.println(request.toString());
        SendURL = SendURL + nft.getId();
        System.out.println("SendURL = " + SendURL);
        ResponseEntity<String> response = restTemplates.postForEntity(SendURL,entity,String.class);
        String jText = response.getBody().toString();
        System.out.println(jText);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jText);
        return obj;
    }
    public static String changeNumber(double num) {
        String answer = "";
        int remainder = 0;
        while(num != 0) {
            remainder = (int) (num%16);
            if(num%16 < 10) {
                answer = remainder + answer;
            } else {
                answer = (char)(remainder + 55) + answer;
            }
            num /= 16;
        }
        String resultdata = "";
        for (int i = 0; i<answer.length()-1;i++){
            if(answer.charAt(i) == '0'){
                continue;
            }
            else {
                resultdata = answer.substring(i,answer.length());
                break;
            }
        }
        System.out.println("return data =" + resultdata);
        return "0x"+resultdata;
    }
}
