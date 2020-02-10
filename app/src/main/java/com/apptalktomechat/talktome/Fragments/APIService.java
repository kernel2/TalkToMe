package com.apptalktomechat.talktome.Fragments;


import com.apptalktomechat.talktome.Notifications.MyResponse;
import com.apptalktomechat.talktome.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAJuw866I:APA91bEE16CMfxzamc-YCXCZrD_0y5ZuYTvM-JuWsKAu7OAd_u346rvRxNQyfA_yGaRnbxWkiZIlIQ10lAlajFuJeDRQdifU4ii3lI4M0fxg5FXcy-F-Q-vBty9WGZyrspgwsnvbLOAs"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
