package com.Asws.co.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Center;
import com.Asws.co.repository.CenterRepository;
import com.Asws.co.response.CenterResponse;
import com.Asws.co.service.CenterService;

@Service
public class CenterServiceImpl implements CenterService{


    @Autowired
    private CenterRepository centerRepository;

    @Override
    public Center creatTeacher(Center obj) {

        Center ct = centerRepository.save(obj);
        return ct;
    }


    @Override
    public List<Center> getAllTeachers() {
        return centerRepository.findAll();
    }

    public List<String> getAllCenterNames() {
        List<Center> c1 = centerRepository.findAll();
        List<String> l1 = new ArrayList<>();
        for(Center lc:c1){
            lc.getCenterName();
            l1.add(lc.getCenterName());
        }
        return l1;
    }


    public CenterResponse searchCenterName(Map<String, String> obj ) {
		
	    CenterResponse resp = CenterResponse.builder().build();

		List<Center> list = centerRepository.findAll();

		if (obj.size() == 0) {
            resp.setCenters(list);
			resp.setTotal(list.size());
			return resp;
		}

		List<Center> list2 = list;
       
        if (obj.containsKey("centerName")) {
            list2 = list2.stream().filter(sd -> ((String) sd.getCenterName()).equalsIgnoreCase(obj.get("centerName")))
                .collect(Collectors.toList());
        }
        
		resp.setCenters(list2);
		resp.setTotal(list2.size());
		return resp;
	}

}
