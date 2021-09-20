package net.api.cho.stockdata.stock.NFT.Controller;

import lombok.RequiredArgsConstructor;
import net.api.cho.stockdata.stock.AWSS3.S3Service.S3uploader;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFT;
import net.api.cho.stockdata.stock.NFT.Domain.MakeNFTdto;
import net.api.cho.stockdata.stock.NFT.Domain.NFTdto;
import net.api.cho.stockdata.stock.NFT.Repository.NFTRepository;
import net.api.cho.stockdata.stock.NFT.Service.NFTService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/NFT")
public class NFTController {
    private final NFTService nftService;
    private final NFTRepository nftRepository;
    private final S3uploader s3uploader;

    @PostMapping("/makeNFT")
    public ResponseEntity makeNFT(@RequestPart(value = "NFTInfo") MakeNFTdto makeNFTdto,
                                  @RequestPart(value = "image") MultipartFile file) throws IOException, ParseException {
        String imagepath = s3uploader.upload(file,"static");
        MakeNFT insertNFT = new MakeNFT();
        insertNFT.setDescription(makeNFTdto.getDescription());
        insertNFT.setImage(makeNFTdto.getImage());
        insertNFT.setOwner(makeNFTdto.getOwner());
        insertNFT.setName(makeNFTdto.getName());
        insertNFT.setImagepath(imagepath);
        return ResponseEntity.ok(nftService.makeNFT(insertNFT));
    }
    @GetMapping("/checkNFTbyaccount/{account}")
    public ResponseEntity checkNFT(@PathVariable String account) throws ParseException{
        return ResponseEntity.ok(nftService.checkNFT(account));
    }
    @GetMapping("/checkNFTbyid/{NFTid}")
    public ResponseEntity checkbyNFTid(@PathVariable String NFTid){
        return ResponseEntity.ok(nftService.findByid(NFTid));
    }
    @PostMapping("/sendNFT")
    public ResponseEntity sendNFT(@RequestBody NFTdto NFTdto) throws ParseException{
        return ResponseEntity.ok(nftService.sendNFT(NFTdto));
    }
    @GetMapping("/allNFT")
    public ResponseEntity allNFT() throws ParseException{
        return ResponseEntity.ok(nftService.allNFT());
    }
}
