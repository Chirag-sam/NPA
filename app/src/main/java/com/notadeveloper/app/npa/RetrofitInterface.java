package com.notadeveloper.app.npa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by krsnv on 13-Mar-17.
 */

public interface RetrofitInterface {
    //seagate root
    @GET("json_Validate.jsp")
    Call<Nurse> Login(
            @Query("username") String username,
            @Query("password") String password);

    @GET("json_sync2.jsp")
    Call<PostReport> getallpatients(
            @Query("nid") String nid);

    @FormUrlEncoded
    @POST("json_Response.jsp")
    Call<Void> postreporttoserver(@Field("Json")
                                          String postReport);
    //1	20170322110413929	Shade2105	pen121	Vignesh	B	Male

//http://192.168.0.101:8080/HIK2.0/Dashboard/json_Sync.jsp?nid=20170322110413929
    //http://192.168.0.101:8080/HIK2.0/Dashboard/json_Validate.jsp?username=test101&password=password

}
