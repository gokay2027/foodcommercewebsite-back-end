package com.example.foodcommercial.business.concretes;

import com.example.foodcommercial.business.abstracts.ILogService;
import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.core.utilities.results.SuccessDataResult;
import com.example.foodcommercial.core.utilities.results.SuccessResult;
import com.example.foodcommercial.entities.Log;
import com.example.foodcommercial.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService implements ILogService {
    private LogRepository logRepo;

    @Autowired
    public LogService(LogRepository logRepo) {
        this.logRepo = logRepo;
    }

    @Override
    public DataResult<List<Log>> getAllLogInfo() {
        return new SuccessDataResult<List<Log>>(logRepo.findAll());
    }

    @Override
    public Result add(String info, String date) {
        Log log = new Log(null,info,date);
        this.logRepo.save(log);
        return new SuccessResult("Add Log Successfully!");
    }
}
