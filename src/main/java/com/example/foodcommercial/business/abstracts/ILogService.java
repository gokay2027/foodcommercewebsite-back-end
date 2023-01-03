package com.example.foodcommercial.business.abstracts;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Log;

import java.util.List;

public interface ILogService {
    public DataResult<List<Log>> getAllLogInfo();
    public Result add(String info, String date);
}
