package com.myapplication.utils.json;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.myapplication.utils.NullUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import ikidou.reflect.TypeBuilder;

/**
 * Created by wangxiaotao on 2017/3/17 0017.
 * @author wangxiaotao
 *
 * 使用方法：JsonUtil.getInstance().parseJsonFromResponse(..) 等直接方法调用，方法上有注释
 *          JsonUtil.getInstance().isSuccess() 判断是否成功
 *
 */
public class JsonUtil<T>  {
    private static Gson gson;
    private static JsonUtil instance;
    private static Gson getGson(){
        if(gson==null){
            gson=new Gson();
        }
        return gson;
    }
    public static JsonUtil getInstance(){
        if(instance==null){
            instance=new JsonUtil();
        }
        return instance;
    }

    private boolean canParseResponse(@NonNull JSONObject response, @NonNull String dataString){
        try {
            return response!=null&&response.has("status") && response.getInt("status") == 1
                    &&!NullUtil.isStringEmpty(dataString)&&response.has(dataString)
                    &&!NullUtil.isStringEmpty(response.getString(dataString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @CheckResult
    public T parseJsonFromResponse(@NonNull JSONObject response,@NonNull String dataString,Class<T> clazz){
        try {
            if (canParseResponse(response,dataString)) {
                T bean= getGson().fromJson(response.getString(dataString),clazz);
                return bean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CheckResult
    public T parseJsonFromResponse(@NonNull JSONObject response,Class<T> clazz){
        return parseJsonFromResponse(response,"data",clazz);
    }

    @CheckResult
    public T parseJson(@NonNull String response,Class<T> clazz){
        return !NullUtil.isStringEmpty(response)?getGson().fromJson(response,clazz):null;
    }

    public boolean isSuccess(@NonNull JSONObject response){
        try {
            return response.has("status") && response.getInt("status") == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getMsgFromResponse(@NonNull JSONObject response,String defaultMsg) {
        try {
            if (response.has("msg")) {
                return response.getString("msg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultMsg;
    }

    public String getStrFromResponse(@Nullable JSONObject response, String dataStr) {
        try {
            if (response!=null&&response.has(dataStr)) {
                return response.getString(dataStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String toJson(Object obj){
        return getGson().toJson(obj);
    }

    /**
     * 解析的数据必须符合{@link BaseResponse},数据部分是data字段,{status:1,msg:"",data:[]}
     * @param response 已判断了空，不用判断后再传入
     * @param clazz
     * @param <T>
     * @return BaseResponse类型的数据
     */
    public <T> BaseResponse<List<T>> getDataArray(@Nullable JSONObject response, @NonNull Class<T> clazz) {
        if(response!=null&&clazz!=null) {
            return getDataArray(response.toString(),clazz);
        }
        return null;
    }

    /**
     * 解析的数据必须符合{@link BaseResponse},数据部分是data字段,{status:1,msg:"",data:[]}
     * @param response 已判断了空，不用判断后再传入
     * @param clazz
     * @param <T>
     * @return BaseResponse类型的数据
     */
    public <T> BaseResponse<List<T>> getDataArray(@Nullable String response,@NonNull Class<T> clazz) {
        if(!NullUtil.isStringEmpty(response)&&clazz!=null) {
            Type type = TypeBuilder
                    .newInstance(BaseResponse.class)
                    .beginSubType(List.class)
                    .addTypeParam(clazz)
                    .endSubType()
                    .build();
            return getGson().fromJson(response.toString(), type);
        }
        return null;
    }

    /**
     * 解析的数据必须符合{@link BaseResponse},数据部分是data字段,{status:1,msg:"",data:{}}
     * @param response 已判断了空，不用判断后再传入
     * @param clazz
     * @param <T>
     * @return BaseResponse类型的数据
     */
    public <T> BaseResponse<T> getDataObject(@Nullable JSONObject response,@NonNull Class<T> clazz) {
        if(response!=null&&clazz!=null) {
            return getDataObject(response.toString(),clazz);
        }
        return null;
    }

    /**
     * 根据字段名称获取数据对象
     * @param response
     * @param dataName
     * @param clazz
     * @return
     */
    public T getDataObjectByName(@Nullable JSONObject response,@NonNull String dataName,@NonNull Class<T> clazz) {
        if(response!=null&&!NullUtil.isStringEmpty(dataName)&&clazz!=null&&response.has(dataName)) {
            try {
                return getGson().fromJson(response.getString(dataName),clazz);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据字段名称获取数据集合
     * @param response
     * @param dataName
     * @param clazz
     * @return
     */
    public List<T> getDataArrayByName(@Nullable JSONObject response,@NonNull String dataName,@NonNull Class<T> clazz) {
        if(response!=null&&!NullUtil.isStringEmpty(dataName)&&clazz!=null&&response.has(dataName)) {
            Type type = TypeBuilder
                    .newInstance(List.class)
                    .addTypeParam(clazz)
                    .build();
            try {
                return getGson().fromJson(response.getString(dataName), type);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解析的数据必须符合{@link BaseResponse},数据部分是data字段,{status:1,msg:"",data:{}}
     * @param response 已判断了空，不用判断后再传入
     * @param clazz
     * @param <T>
     * @return BaseResponse类型的数据
     */
    public <T> BaseResponse<T> getDataObject(@Nullable String response,@NonNull Class<T> clazz) {
        if(!NullUtil.isStringEmpty(response)&&clazz!=null) {
            Type type = TypeBuilder
                    .newInstance(BaseResponse.class)
                    .addTypeParam(clazz)
                    .build();
            return getGson().fromJson(response.toString(), type);
        }
        return null;
    }
}
