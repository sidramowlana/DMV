package com.example.DMV.Controller;

import com.example.DMV.Model.DMV;
import com.example.DMV.Service.DMVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/dmv")
@RestController
public class DMVController {
    private DMVService dmvService;
    @Autowired

    public DMVController(DMVService dmvService) {
        this.dmvService = dmvService;
    }

//    @GetMapping(value = "/getAllDrivingLicence")
//    public DMV getAllLicense (@PathVariable String drivingLicence)
//    {
//        return dmvService.validateLicenseNo(drivingLicence);
//    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<DMV>> getAllDrivingLicense(){
        return dmvService.getAllDrivingLicense();
    }
}
