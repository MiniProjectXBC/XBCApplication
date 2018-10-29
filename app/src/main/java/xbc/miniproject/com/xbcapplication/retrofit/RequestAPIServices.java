package xbc.miniproject.com.xbcapplication.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import xbc.miniproject.com.xbcapplication.model.batch.ModelBatch;
import xbc.miniproject.com.xbcapplication.model.biodata.Biodata;
import xbc.miniproject.com.xbcapplication.model.biodata.BiodataList;
import xbc.miniproject.com.xbcapplication.model.biodata.ModelBiodata;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNews;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.feedback.autoComplete.ModelAutocompleteFeedback;
import xbc.miniproject.com.xbcapplication.model.feedback.getQuestion.ModelQuestionFeedback;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;

import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.login.ModelLoginInput;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.login.ModelLoginMessage;
import xbc.miniproject.com.xbcapplication.model.monitoring.ModelMonitoring;
import xbc.miniproject.com.xbcapplication.model.monitoring.MonitoringDataList;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.model.role.ModelRole;
import xbc.miniproject.com.xbcapplication.model.technology.ModelTechnology;
import xbc.miniproject.com.xbcapplication.model.technology.DataList;
import xbc.miniproject.com.xbcapplication.model.testimony.DataListTestimony;
import xbc.miniproject.com.xbcapplication.model.kelas.ModelClass;
import xbc.miniproject.com.xbcapplication.model.testimony.ModelTestimony;
import xbc.miniproject.com.xbcapplication.model.testimony.Testimony;
import xbc.miniproject.com.xbcapplication.model.trainer.Trainer;
import xbc.miniproject.com.xbcapplication.model.user.ModelUser;
import xbc.miniproject.com.xbcapplication.model.trainer.DataListTrainer;
import xbc.miniproject.com.xbcapplication.model.trainer.ModelTrainer;

public interface RequestAPIServices {

    //Koneksi API di Login Page
    @POST("/xbc-ws/api/login")
    Call<ModelLoginMessage> goLogin(@Header("Content-Type") String contentType,
                                    @Body RequestBody data);

    //Koneksi API di menu Biodata
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/biodata/name/123")
    Call<ModelBiodata> getListBiodata();

    //POST Create
    @POST("/xbc-ws/api/biodata/create")
    Call<ModelBiodata> createNewBiodata(@Header("Content-Type") String contentType,
                                    @Body BiodataList data);

    //GET get_one
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("/xbc-ws/api/biodata/id/{id}")
    Call<ModelBiodata> getOneBiodata(@Path("id") int id);

    //PUT edit
    @PUT("/xbc-ws/api/biodata/update")
    Call<ModelBiodata> editBiodata(@Header("Content-Type") String contentType,
                                   @Header("Authorization") String authorization,
                                   @Body Biodata data);

    //PUT deactivate
    @PUT("/xbc-ws/api/biodata/deactivate/{id}")
    Call<ModelBiodata> deactivateBiodata(@Header("Content-Type") String contentType,
                                         @Header("Authorization") String authorization,
                                         @Path("id") int id);

    //Koneksi API ke menu Monitoring
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("/xbc-ws/api/monitoring/biodataname/a")
    Call<ModelMonitoring> getMonitoringList();

    @Headers("Authorization: MOGLK40NEYLUFKIORVFAFE5OCO60T4R140VTW35L9T72LRSRWKJIZXWTCD1HQKPZURKJPNYHIX0SO6SX672HASCKVAHPV6VHRXOKVV7KEQVZNETUBXRXM7CEKR5ZQJDA")
    @GET("/xbc-ws/api/monitoring/key/ge")
    Call<MonitoringDataList> getAutoCompleteMonitoringList();

    //Koneksi API di menu User
    //Get Data User
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/user/name/123")
    Call<ModelUser> getListUsser();

    @POST("/xbc-ws/api/user/create")
    Call<ModelUser> createNewUser(@Header("Content-Type")String contentTypeUser,
                                  @Body xbc.miniproject.com.xbcapplication.model.user.DataList datauser);
    //PUT Deactivate
    @PUT("/xbc-ws/api/user/deactivate/{id}")
    Call<ModelTrainer> deactivateUser(@Header("Content-Type") String contentType,
                                         @Header("Authorization") String authorization,
                                         @Path("id") int id);
    //Koneksi API di menu Technology
    //Get Data Technology
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/technology/name/123")
    Call<ModelTechnology> getListTechnology();
    //Create
    @POST("/xbc-ws/api/technology/create")
    Call<ModelTechnology> createNewTechnology(@Header("Content_Type")String contentType,
                                              @Body DataList data);
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("/xbc-ws/api/technology/id/{id}")
    Call<ModelTechnology> getOneTechnology(@Path("id") int id);
    //PUT edit
    @PUT("/xbc-ws/api/technology/update")
    Call<ModelTechnology> editTechnology(@Header("Content-Type") String contentType,
                                   @Header("Authorization") String authorization,
                                   @Body xbc.miniproject.com.xbcapplication.model.technology.Technology data);
    @PUT("/xbc-ws/api/technology/deactivate/{id}")
    Call<ModelTechnology> deactiveTechnology(@Header("Content-Type") String contentType,
                                         @Header("Authorization") String authorization,
                                         @Path ("id") int id);

    //koneksi API di menu Role
    //Get Role
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("/xbc-ws/api/role/key/g")
    Call<ModelRole> getListRole();

    //koneksi API di menu trainer
    //get search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/trainer/name/123")
    Call<ModelTrainer> getListTrainer();

    //Create data
    //post Create
    @POST("xbc-ws/api/trainer/create")
    Call<ModelTrainer> createNewTrainer(@Header("Content-Type") String contentType,
                                           @Body DataListTrainer data);

    //GET get_one search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/trainer/id/{id}")
    Call<ModelTrainer> getOneTrainer(@Path("id") int id);

    //PUT edit
    @PUT("/xbc-ws/api/trainer/update")
    Call<ModelTrainer> editTrainer (@Header("Content-Type") String contentType,
                                    @Header("Authorization") String authorization,
                                       @Body Trainer data);

    //PUT Deactivate
    @PUT("/xbc-ws/api/trainer/deactivate/{id}")
    Call<ModelTrainer> deactivateTrainer(@Header("Content-Type") String contentType,
                                         @Header("Authorization") String authorization,
                                         @Path("id") int id);


    //Koneksi api di menu testimony
    //GET SEARCH
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/testimony/title/a")
    Call<ModelTestimony> getListTestimony();

    //GET ONE-SEARCH
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/testimony/id/{id}")
    Call<ModelTestimony> getOneTestimony(@Path("id") int id);

    //POST CREATE
    @POST("/xbc-ws/api/testimony/create")
    Call<ModelTestimony> createNewTestimony(@Header("Content-Type") String contentType,
                                        @Body DataListTestimony data);

    //PUT EDIT
    @PUT("/xbc-ws/api/testimony/update")
    Call<ModelTestimony> editTestimony(@Header("Content-Type") String contentType,
                                   @Header("Authorization") String authorization,
                                   @Body Testimony data);

    //DEL delete
    @DELETE("xbc-ws/api/testimony/delete/{id}")
    Call<ModelTestimony> deleteTestimony(@Header("Content-Type") String contentType,
                                         @Header("Authorization") String authorization,
                                         @Path("id") int id);


    //konek API di menu Batch
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/batch/name/123")
    Call<ModelBatch> getListBatch();

    //POST CREATE
    @POST("xbc-ws/api/batch/create")
    Call<ModelBatch> createNewBatch(@Header("Content-Type") String contentType,
                                    @Body xbc.miniproject.com.xbcapplication.model.batch.DataList data);

    //PUT EDIT
    @PUT("xbc-ws/api/batch/update")
    Call<ModelBatch> editBatch(@Header("Content-Type") String contentType,
                               @Header("Authorization") String authorization,
                               @Body xbc.miniproject.com.xbcapplication.model.batch.DataList data);

    //GET get_one
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("/xbc-ws/api/batch/id/{id}")
    Call<ModelBatch> getOneBatch(@Path("id") int id);


    //Koneksi API di menu Idle News
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/idlenews/title/a")
    Call<ModelIdleNews> getListIdleNews();

    //POST Create
    @POST("xbc-ws/api/idlenews/create")
    Call<ModelIdleNews> createNewIdleNews(@Header("Content-Type") String contentType,
                                        @Body RequestBody data);

    //GET get_one
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("/xbc-ws/api/idlenews/id/{id}")
    Call<ModelIdleNews> getOneIdleNews(@Path("id") int id);

    //PUT edit
    @PUT("/xbc-ws/api/idlenews/update")
    Call<ModelIdleNews> editIdleNews(@Header("Content-Type") String contentType,
                                   @Header("Authorization") String authorization,
                                   @Body IdleNews data);


    //Koneksi API di menu Class
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/class/batch/101")
    Call<ModelClass> getListClass();
    //KONEKSI API FEEDBACK
    //get autocomplete
    @Headers("Authorization: MOGLK40NEYLUFKIORVFAFE5OCO60T4R140VTW35L9T72LRSRWKJIZXWTCD1HQKPZURKJPNYHIX0SO6SX672HASCKVAHPV6VHRXOKVV7KEQVZNETUBXRXM7CEKR5ZQJDA")
    @GET("role/key/{keyword}")
    Call<ModelAutocompleteFeedback> roleautocomplete(@Header("Content-Type") String contentType,
                                                     @Header("Authorization") String tokenAuthorization,
                                                     @Path("keyword") String keyword);
    //get question
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/technology/name/123")
    Call<ModelQuestionFeedback>  getListQuestionFeedback();






}
