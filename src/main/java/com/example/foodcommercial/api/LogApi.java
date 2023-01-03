package com.example.foodcommercial.api;

import com.example.foodcommercial.business.abstracts.ILogService;
import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogApi {
    ILogService logService;
    @Autowired
    public LogApi(ILogService logService) {
        this.logService = logService;
    }
    @GetMapping("/getall")
    public DataResult<List<Log>> getAllLogInfo(){
        return this.logService.getAllLogInfo();
    }
    @PostMapping("/add")
    public Result add(@RequestParam String info,@RequestParam String date){
        return this.logService.add(info,date);
    }
}
