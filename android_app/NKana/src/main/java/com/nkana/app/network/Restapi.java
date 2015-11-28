package com.nkana.app.network;

import com.nkana.app.model.ChangePassword;
import com.nkana.app.model.DeviceConfig;
import com.nkana.app.model.Groups;
import com.nkana.app.network.Responses.GroupDeleteResponse;
import com.nkana.app.network.Responses.GroupResponse;
import com.nkana.app.network.Responses.GroupResponseList;
import com.nkana.app.network.Responses.LoginResponse;
import com.nkana.app.network.Responses.MembersDataResponse;
import com.nkana.app.network.Responses.MembersDeleteResponse;
import com.nkana.app.network.Responses.MembersInfoResponse;
import com.nkana.app.network.Responses.RegisterResponse;
import com.nkana.app.model.Registration;
import com.nkana.app.network.Responses.UpdateProfileResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by chokkar
 */

public interface Restapi {
    @POST("/account/login/")
    void login(@Body DeviceConfig deviceConfig, Callback<LoginResponse> callback);

    @POST("/account/register/")
    void register(@Body Registration registration, Callback<RegisterResponse> callback);

    @GET("/account/users/")
    void userProfile(@Header("Authorization") String token, Callback<UpdateProfileResponse> callback);

    @PUT("/account/users/")
    void userUpdateProfile(@Header("Authorization") String token, Callback<UpdateProfileResponse> callback);

    @POST("/account/setpasswd/")
    void changePassword(@Header("Authorization") String token, @Body ChangePassword changePassword, Callback<RegisterResponse> callback);

    @POST("/account/logout/")
    void logout(@Header("Authorization") String token, Callback<RegisterResponse> callback);

    @POST("/groups/")
    void groupCreate(@Header("Authorization") String token, @Body Groups groups, Callback<GroupResponse> callback);

    @GET("/groups/")
    void groupsList(@Header("Authorization") String token, Callback<List<GroupResponseList>> callback);

    @GET("/groups/{id}")
    void groupsListFetch(@Header("Authorization") String token,@Path("id") String groupId, Callback<GroupResponse> callback);

    @DELETE("/groups/{id}")
    void groupDatasDelete(@Header("Authorization") String token,@Path("id") String groupId , Callback<GroupDeleteResponse> callback);

    @PUT("/groups/{id}")
    void groupDatasUpdate(@Header("Authorization") String token,@Path("id") String groupId , Callback<GroupResponse> callback);

    @GET("/groups/{id}/places/")
    void placesList(@Header("Authorization") String token,@Path("id") String groupId, Callback<List<GroupDeleteResponse>> callback);

    @GET("/groups/{id}/members/")
    void membersList(@Header("Authorization") String token,@Path("id") String groupId, Callback<List<MembersDataResponse>> callback);

    @GET("/groups/{id}/members/{memberid}")
    void membersInfoFetch(@Header("Authorization") String token,@Path("id") String groupId, @Path("memberid") String memberId, Callback<MembersInfoResponse> callback);

    @DELETE("/groups/{id}/members/{memberid}")
    void membersDataDelete(@Header("Authorization") String token,@Path("id") String groupId, @Path("memberid") String memberId, Callback<MembersDeleteResponse> callback);
}
