/* ---------------------------------------------------------------------
*
*  LICENSE
*
*  This file is part of the GLPI API Client Library for Java,
*  a subproject of GLPI. GLPI is a free IT Asset Management.
*
*  GLPI is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License
*  as published by the Free Software Foundation; either version 3
*  of the License, or (at your option) any later version.
*
*  GLPI is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  --------------------------------------------------------------------
*  @author    Rafael Hernandez - <rhernandez@teclib.com>
*  @author    Ivan Del Pino - <idelpino@teclib.com>
*  @copyright (C) 2017 Teclib' and contributors.
*  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
*  @link      https://github.com/glpi-project/java-library-glpi
*  @link      http://www.glpi-project.org/
*  --------------------------------------------------------------------
*/

package org.glpi.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.glpi.api.query.GetAllItemQuery;
import org.glpi.api.query.GetAnItemQuery;
import org.glpi.api.query.GetSubItemQuery;
import org.glpi.api.request.ChangeActiveEntitiesRequest;
import org.glpi.api.request.ChangeActiveProfileRequest;
import org.glpi.api.request.RecoveryPasswordRequest;
import org.glpi.api.request.ResetPasswordRequest;
import org.glpi.api.response.FullSessionModel;
import org.glpi.api.response.InitSession;
import org.glpi.api.utils.Helpers;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GLPI extends ServiceGenerator {

    private Routes interfaces;
    private String sessionToken = "";
    private String appToken;
    private Context context;
    private boolean isFinishSplitArray;

    /**
     * GLPI REST API Constructor this class will help you to interact with GLPI endpoints
     *
     * @param context is the context
     * @param glpiUrl is the url glpi instance
     */
    public GLPI(Context context, String glpiUrl) {
        start(glpiUrl);
        this.context = context;
        interfaces = retrofit.create(Routes.class);
    }

    /**
     * Request a session token to uses other api endpoints.
     *
     * @param userToken defined in User Preference (See 'Remote access key' on GLPI)
     * @param callback  here you are going to get the asynchronous response
     */
    public void initSessionByUserToken(String userToken, final ResponseHandle<InitSession, String> callback) {
        Call<InitSession> call = interfaces.initSessionByUserToken(userToken, userToken);
        responseInitSession(callback, call);
    }

    /**
     * Synchronous Request a session token to uses other api endpoints. with a couple login & password:
     * 2 parameters to login with user authentication
     *
     * @param user     valid user on GLPI
     * @param password valid password on GLPI
     * @return SessionToken
     */
    public String initSessionByCredentialsSync(String user, String password) {
        String authorization = Helpers.base64encode(user + ":" + password);
        Call<InitSession> responseCall = interfaces.initSessionByCredentials("Basic " + authorization.trim());
        try {
            sessionToken = responseCall.execute().body().getSessionToken();
            return sessionToken;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Request a session token to uses other api endpoints. with a couple login & password:
     * 2 parameters to login with user authentication
     *
     * @param user     valid user on GLPI
     * @param password valid password on GLPI
     * @param callback here you are going to get the asynchronous response
     */
    public void initSessionByCredentials(String user, String password, final ResponseHandle<InitSession, String> callback) {
        this.appToken = null;
        String authorization = Helpers.base64encode(user + ":" + password);
        responseInitSession(callback, interfaces.initSessionByCredentials("Basic " + authorization.trim()));
    }

    public void fullSession(String userToken, final ResponseHandle<FullSessionModel, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Session-Token", userToken);
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json" + ";" + "charset=UTF-8");
        header.put("User-Agent", "Flyve MDM");
        header.put("Referer", "/getFullSession");
        interfaces.getFullSession(header).enqueue(new Callback<FullSessionModel>() {
            @Override
            public void onResponse(@NonNull Call<FullSessionModel> call, @NonNull Response<FullSessionModel> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        assert response.errorBody() != null;
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }
            @Override
            public void onFailure(@NonNull Call<FullSessionModel> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

    /**
     * This endpoint allows to request password reset
     *
     * @param email    email address of the user to recover
     * @param callback here you are going to get the asynchronous response
     */
    public void recoveryPassword(String email, final ResponseHandle<String, String>  callback) {
        RecoveryPasswordRequest requestPost = new RecoveryPasswordRequest(email);
        responseInitSession(callback, interfaces.lostPassword(requestPost), R.string.lost_password_success);
    }

    private void responseInitSession(final ResponseHandle<InitSession, String> callback, Call<InitSession> responseCall) {
        responseCall.enqueue(new Callback<InitSession>() {
            @Override
            public void onResponse(@NonNull Call<InitSession> call, @NonNull Response<InitSession> response) {
                if (response.isSuccessful()) {
                    try {
                        sessionToken = response.body().getSessionToken();
                    } catch (NullPointerException ex) {
                        Log.d("initSession", ex.getMessage());
                    }
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<InitSession> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    /**
     * Return all the profiles associated to logged user.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getMyProfiles(final ResponseHandle<JsonObject, String> callback) {
        responseJsonObject(callback, interfaces.getMyProfiles(getHeader()));
    }

    /**
     * Return the current active profile.
     *
     * @param data
     * @param callback here you are going to get the asynchronous response
     */
    public void getPluginFlyve(String sessionToken, JSONObject data, final ResponseHandle<JsonObject, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Session-Token", sessionToken);
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json" + ";" + "charset=UTF-8");
        responseJsonObject(callback, interfaces.getPluginFlyve(header, data));
    }

    /**
     * Return the current active profile.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getPluginFlyveAgentID(String sessionToken, String agentID, final ResponseHandle<JsonObject, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Session-Token", sessionToken);
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json" + ";" + "charset=UTF-8");
        header.put("User-Agent", "Flyve MDM");
        header.put("Referer", "/getFullSession");
        responseJsonObject(callback, interfaces.getPluginFlyveAgentID(header, agentID));
    }

    /**
     * Return the current active profile.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getActiveProfile(final ResponseHandle<JsonObject, String> callback) {
        responseJsonObject(callback, interfaces.getActiveProfile(getHeader()));
    }

    /**
     * Return all the possible entities of the current logged user (and for current active profile).
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getMyEntities(final ResponseHandle<JsonObject, String> callback) {
        responseJsonObject(callback, interfaces.getMyEntities(getHeader()));
    }

    /**
     * Return active entities of current logged user.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getActiveEntities(final ResponseHandle<JsonObject, String> callback) {
        responseJsonObject(callback, interfaces.getActiveEntities(getHeader()));
    }

    /**
     * Return the current $CFG_GLPI.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getGlpiConfig(final ResponseHandle<JsonObject, String> callback) {
        responseJsonObject(callback, interfaces.getGlpiConfig(getHeader()));
    }

    /**
     * Return the instance fields of itemtype identified by id.
     *
     * @param itemType These are the item type available on GLPI
     * @param id       unique identifier of the itemtype
     * @param callback here you are going to get the asynchronous response
     */
    public void getItem(itemType itemType, String id, final ResponseHandle<JsonObject, String> callback) {
        Map<String, String> options = new GetAnItemQuery().getQuery();
        responseJsonObject(callback, interfaces.getAnItem(getHeader(), itemType.name(), id, options));
    }

    /**
     * Return a collection of rows of the sub_itemtype for the identified item.
     *
     * @param itemType    These are the item type available on GLPI
     * @param id          unique identifier of the parent itemtype
     * @param subItemType These are the item type available on GLPI
     * @param callback    here you are going to get the asynchronous response
     */
    public void getSubItems(String itemType, String id, String subItemType, final ResponseHandle<JsonObject, String> callback) {
        Map<String, String> options = new GetSubItemQuery(this.context).getQuery();
        responseJsonObject(callback, interfaces.getSubItem(getHeader(), itemType, id, subItemType, options));
    }

    /**
     * Return a collection of rows of the sub_itemtype for the identified item.
     *
     * @param itemType    These are the item type available on GLPI
     * @param id          unique identifier of the parent itemtype
     * @param subItemType These are the item type available on GLPI
     * @param callback    here you are going to get the asynchronous response
     */
    public void getSubItems(itemType itemType, String id, itemType subItemType, final ResponseHandle<JsonObject, String> callback) {
        Map<String, String> options = new GetSubItemQuery(this.context).getQuery();
        responseJsonObject(callback, interfaces.getSubItem(getHeader(), itemType.name(), id, subItemType.name(), options));
    }

    private void responseJsonObject(final ResponseHandle<JsonObject, String> callback, Call<JsonObject> responseCall) {
        responseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }
            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    /**
     * Return a collection of rows of the itemtype.
     *
     * @param itemType These are the item type available on GLPI
     * @param callback here you are going to get the asynchronous response
     */
    public void getAllItems(itemType itemType, final ResponseHandle<JsonArray, String>  callback) {
        Map<String, String> options = new GetAllItemQuery(this.context).getQuery();
        responseJsonArray(callback, interfaces.getAllItem(getHeader(), itemType.name(), options));
    }

    /**
     * Add an object (or multiple objects) into GLPI.
     *
     * @param itemType These are the item type available on GLPI
     * @param payload  input: an object with fields of itemtype to be inserted. You can add several
     *                 items in one action by passing an array of objects.
     * @param callback here you are going to get the asynchronous response
     */
    public void addItems(itemType itemType, Object payload, final ResponseHandle<JsonArray, String>  callback) {
        responseJsonArray(callback, interfaces.addItem(getHeader(), itemType.name(), payload));
    }

    /**
     * Update an object (or multiple objects) existing in GLPI.
     *
     * @param itemType These are the item type available on GLPI
     * @param id       the unique identifier of the itemtype
     * @param payload  Array of objects with fields of itemtype to be updated.
     * @param callback here you are going to get the asynchronous response
     */
    public void updateItems(itemType itemType, String id, Object payload, final ResponseHandle<JsonArray, String>  callback) {
        responseJsonArray(callback, interfaces.updateItem(getHeader(), itemType.name(), id, payload));
    }

    /**
     * Delete an object existing in GLPI.
     *
     * @param itemType These are the item type available on GLPI
     * @param id       unique identifier of the itemtype
     * @param callback here you are going to get the asynchronous response
     */
    public void deleteItems(itemType itemType, String id, final ResponseHandle<JsonArray, String>  callback) {
        responseJsonArray(callback, interfaces.deleteItem(getHeader(), itemType.name(), id));
    }

    /**
     * Delete multiples objects existing in GLPI.
     *
     * @param itemType These are the item type available on GLPI
     * @param payload  Array of id who need to be deleted
     * @param callback here you are going to get the asynchronous response
     */
    public void deleteItems(itemType itemType, Object payload, final ResponseHandle<JsonArray, String>  callback) {
        responseJsonArray(callback, interfaces.deleteMultiplesItem(getHeader(), itemType.name(), payload));
    }

    private void responseJsonArray(final ResponseHandle<JsonArray, String>  callback, Call<JsonArray> responseCall) {
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    private void responseInitSession(ResponseHandle<String, String>  callback, Call<Void> responseCall, int lost_password_success) {
        responseVoid(callback, responseCall, lost_password_success);
    }

    /**
     * This endpoint allows to request password recovery
     *
     * @param email       email address of the user to recover
     * @param token       reset token
     * @param newPassword the new password for the user
     * @param callback    here you are going to get the asynchronous response
     */
    public void resetPassword(String email, String token, String newPassword, final ResponseHandle<String, String>  callback) {
        ResetPasswordRequest requestPost = new ResetPasswordRequest(email, token, newPassword);
        responseVoid(callback, interfaces.recoveryPassword(requestPost), R.string.recovery_password_success);
    }

    /**
     * Change active profile to the profiles_id one. See getMyProfiles endpoint for possible profiles.
     *
     * @param profilesId (default 'all') ID of the new active profile.
     * @param callback   here you are going to get the asynchronous response
     */
    public void changeActiveProfile(String profilesId, final ResponseHandle<String, String>  callback) {
        ChangeActiveProfileRequest requestPost = new ChangeActiveProfileRequest(profilesId);
        int message = R.string.change_active_profile_success;
        responseVoid(callback, interfaces.changeActiveProfile(getHeader(), profilesId, requestPost), message);
    }

    /**
     * Change active entity to the entities_id one. See getMyEntities endpoint for possible entities.
     *
     * @param entitiesId   (default 'all') ID of the new active entity ("all" => load all possible entities).
     * @param is_recursive (default false) Also display sub entities of the active entity.
     * @param callback     here you are going to get the asynchronous response
     */
    public void changeActiveEntities(String entitiesId, Boolean is_recursive, final ResponseHandle<String, String>  callback) {
        ChangeActiveEntitiesRequest requestPost = new ChangeActiveEntitiesRequest(entitiesId, is_recursive.toString());
        int message = R.string.change_active_entities_success;
        responseVoid(callback, interfaces.changeActiveEntities(getHeader(), requestPost), message);
    }

    private void responseVoid(final ResponseHandle<String, String>  callback, Call<Void> responseCall, final int message) {
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(context.getResources().getString(message));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    /**
     * Destroy a session identified by a session token.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void killSession(final ResponseHandle<String, String>  callback) {
        responseKillSession(callback, interfaces.killSession(getHeader()));
    }

    private void responseKillSession(final ResponseHandle<String, String> callback, Call<Void> responseCall) {
        responseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    GLPI.this.appToken = null;
                    GLPI.this.sessionToken = "";
                    callback.onResponse(context.getResources().getString(R.string.kill_session_success));
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    /**
     *
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getPluginFile(String fileId, final ResponseHandle<JsonArray, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Session-Token", sessionToken);
        responseFile(callback, interfaces.getPluginFile(header, fileId));
    }

    /**
     * Destroy a session identified by a session token.
     *
     * @param callback here you are going to get the asynchronous response
     */
    public void getPluginPackage(String fileId, final ResponseHandle<JsonArray, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Session-Token", sessionToken);
        responseFile(callback, interfaces.getPluginPackage(header, fileId));
    }

    private void responseFile(final ResponseHandle<JsonArray, String> callback, Call<JsonArray> responseCall) {
        responseCall.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void handleMultipleDownload(final String[] urls, int start, int step, final ResponseHandle<ArrayList<ResponseBody>, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept","application/octet-stream");
        header.put("Content-Type","application/json");
        header.put("Session-Token", sessionToken);

        /*ArraySplit splitArray = getSplitArray(urls);*/

        ArrayList<Observable<ResponseBody>> listUrls = new ArrayList<>();
        ArrayList<ArrayList<Observable<ResponseBody>>> listRxUrls = new ArrayList<>();

        String[] listModifyUrls = splitArray(urls, start, step);
        int starts = start;

        while (true) {
            for (String url : listModifyUrls) {
                if (url != null) {
                    Observable<ResponseBody> observableOne = interfaces.downloadFileRX(url, header)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                    listUrls.add(observableOne);
                }
            }
            starts = starts + step;
            listModifyUrls = splitArray(urls, starts, step);
            listRxUrls.add(listUrls);
            listUrls = new ArrayList<>();
            if (listModifyUrls.length == 0)
                break;
        }
        isFinishSplitArray = false;

        final ArrayList<ResponseBody> listResponseBody = new ArrayList<>();
        Disposable subscribe1 = Observable.fromIterable(listRxUrls)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Observable<ResponseBody>>>() {
                               @Override
                               public void accept(ArrayList<Observable<ResponseBody>> responseBodyObservable) throws Exception {
                                   Disposable subscribe = Observable.merge(responseBodyObservable)
                                           .subscribeOn(Schedulers.io())
                                           .observeOn(AndroidSchedulers.mainThread())
                                           .doOnComplete(new Action() {
                                               @Override
                                               public void run() throws Exception {
                                                   callback.onResponse(listResponseBody);
                                                   listResponseBody.clear();
                                               }
                                           })
                                           .subscribe(new Consumer<ResponseBody>() {
                                                          @Override
                                                          public void accept(ResponseBody responseBody) throws Exception {
                                                              listResponseBody.add(responseBody);
                                                          }
                                                      },
                                                   new Consumer<Throwable>() {
                                                       @Override
                                                       public void accept(Throwable e) throws Exception {
                                                           callback.onFailure(e.getMessage());
                                                       }
                                                   });
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                callback.onFailure(e.getMessage());
                            }
                        });
    }

    private String[] splitArray(String[] values, int start, int step) {
        if (isFinishSplitArray)
            return new String[0];
        if (start >= values.length - step)
            isFinishSplitArray = true;
        return Arrays.copyOfRange(values, start, start + step);
    }

    /**
     * Download file specifying route.
     *
     * @param callback here you are going to get the asynchronous response
     * @param url: route to download file
     */
    public void downloadFile(String url, final ResponseHandle<ResponseBody, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Accept","application/octet-stream");
        header.put("Content-Type","application/json");
        header.put("Session-Token", sessionToken);
        responseFileDownload(callback, interfaces.downloadFile(url, header));
    }

    private void responseFileDownload(final ResponseHandle<ResponseBody, String> callback, final Call<ResponseBody> responseCall) {
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getMultipleItems(Map<String, String> query, final ResponseHandle<JsonArray, String>  callback) {
        interfaces.getMultipleItem(getHeader(), query).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    /**
     * Search items
     *
     * @param itemType : search item
     * @param query
     * @param callback here you are going to get the asynchronous response
     */
    public void searchItems(String itemType, Map<String, String> query, final ResponseHandle<JsonObject, String> callback) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Session-Token", sessionToken);
        interfaces.searchItem(header, itemType, query).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    /**
     * Create a valid Array with common headers needs
     *
     * @return Map<String   ,       String> with all the headers
     */
    private Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("Session-Token", this.sessionToken);
        if (appToken != null) {
            map.put("App-Token", appToken);
        }
        return map;
    }

    public void getMultipleItems() {
    }

    public void listSearchOptions(Map<String, String> query, final ResponseHandle<JsonObject, String> callback) {
        interfaces.getListSearchOption(getHeader(), itemType.Computer.name(), query).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    String errorMessage;
                    try {
                        errorMessage = response.errorBody().string();
                    } catch (Exception ex) {
                        errorMessage = context.getResources().getString(R.string.error_generic);
                    }
                    callback.onFailure(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface ResponseHandle<T, U> {
        void onResponse(T response);

        void onFailure(U errorMessage);
    }

    /**
     * Interface definition for a callback to be invoked when an endpoint return void.
     */
    public class GLPIModel {
        private String profileId;

        public String getProfileId() {
            return profileId;
        }

        public void setProfileId(String profileId) {
            this.profileId = profileId;
        }
    }

}
