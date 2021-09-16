package net.api.cho.stockdata.stock.NFT.Controller;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import net.api.cho.stockdata.stock.NFT.Service.NFTService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/NFT")
public class NFTController {
    private final NFTService nftService;

    @GetMapping("/makeNFT")
    public ResponseEntity makeNFT() throws ParseException {
        return ResponseEntity.ok(nftService.makeNFT());
    }
    @GetMapping("/checkNFT/{account}")
    public ResponseEntity checkNFT(@PathVariable String account) throws ParseException{
        return ResponseEntity.ok(nftService.checkNFT(account));
    }
    @PostMapping("/sendNFT")
    public ResponseEntity sendNFT(@RequestBody NFT nft) throws ParseException{
        return ResponseEntity.ok(nftService.sendNFT(nft));
    }
}
