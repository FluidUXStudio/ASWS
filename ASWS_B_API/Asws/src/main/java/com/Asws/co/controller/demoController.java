package com.Asws.co.controller;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Asws.co.domain.demo;
import com.Asws.co.repository.demoRepository;
import com.Asws.co.response.demoResponse;
import com.Asws.co.service.demoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;



@RestController
@RequestMapping("/d")
public class demoController {

    @Autowired
    private demoService demoServices;

    @Autowired
    private demoRepository demoRepository;

    @Autowired
    private ObjectMapper mapper;
    // public demo creaDemo(@RequestParam("view_json") demo obj){
    //     //    obj.setImage(image);
    //     return demoService.createDemo(obj);
    // }
    
    
    @PostMapping("/demo")
    public ResponseEntity< demo> createBga(@RequestParam("demo") String demo, @RequestParam("file") MultipartFile file) throws IOException {

        // demoServices.save(bga, file); // NEW: passing in 'bga' here
        
        demo d1 = mapper.readValue(demo,demo.class);
        d1.setImage(file.getBytes());
        demoRepository.save(d1);

        

        //  demoServices.save(d1, file);
    
        // bgaRepository.save(bga); .. DELETED
    
        return ResponseEntity.status(HttpStatus.OK).body(d1);
    }

    // @GetMapping("/getAllDemo")
    // public List<demo> getalldemo(){
    //     return demoService.getalldempos();
    // }


    @GetMapping("/search")
	public ResponseEntity<demoResponse> search(@RequestParam Map<String, String> obj){
		demoResponse sdr = demoServices.searchServiceDetailWithFilter(obj);
		return ResponseEntity.status(HttpStatus.OK).body(sdr);
	}
    
}
