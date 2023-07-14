package com.example.dictionaryapp;

import com.example.dictionaryapp.models.APIResponse;

public interface onFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
