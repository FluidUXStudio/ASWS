package com.Asws.co.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.Multipart;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Asws.co.domain.demo;
import com.Asws.co.domain.demoImages;
import com.Asws.co.repository.demoImageRepository;
import com.Asws.co.repository.demoRepository;
import com.Asws.co.response.demoResponse;


@Service
public class demoService {

    @Autowired
    private demoRepository demoRepository;


    @Autowired
    private demoImageRepository imagemRepository;
   

    @Transactional // NEW
    public demoImages save(demo demo, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        demoImages Imagem = new demoImages(fileName, file.getContentType(),file.getBytes());

        demoRepository.save(demo); // NEW
        Imagem.setDemo(demo); // NEW

        return imagemRepository.save(Imagem);
    }



    public demoResponse searchServiceDetailWithFilter(Map<String, String> obj ) {
		
	    demoResponse resp = demoResponse.builder().build();

		List<demo> list = demoRepository.findAll();

		if (obj.size() == 0) {
			resp.setDemos(list);
			resp.setTotal(list.size());
			return resp;
		}

		List<demo> list2 = list;
        if (obj.containsKey("name")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getName()).equalsIgnoreCase(obj.get("name")))
                .collect(Collectors.toList());
        }
		
		resp.setDemos(list2);
		resp.setTotal(list2.size());
		return resp;
	}


    public demo createDemo(demo obj){
        return demoRepository.save(obj);
    }

    public List<demo> getalldempos(){
        return demoRepository.findAll();
    }
    


}
