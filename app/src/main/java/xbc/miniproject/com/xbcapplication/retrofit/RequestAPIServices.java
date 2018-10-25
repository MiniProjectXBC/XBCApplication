package xbc.miniproject.com.xbcapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import xbc.miniproject.com.xbcapplication.dummyModel.TestimonyModel;
import xbc.miniproject.com.xbcapplication.model.batch.ModelBatch;
import xbc.miniproject.com.xbcapplication.model.biodata.Biodata;
import xbc.miniproject.com.xbcapplication.model.biodata.BiodataList;
import xbc.miniproject.com.xbcapplication.model.biodata.ModelBiodata;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.model.testimony.DataListTestimony;
import xbc.miniproject.com.xbcapplication.model.testimony.ModelTestimony;
import xbc.miniproject.com.xbcapplication.model.testimony.Testimony;
import xbc.miniproject.com.xbcapplication.model.user.DataList;
import xbc.miniproject.com.xbcapplication.model.trainer.Trainer;
import xbc.miniproject.com.xbcapplication.model.user.ModelUser;
import xbc.miniproject.com.xbcapplication.model.trainer.DataListTrainer;
import xbc.miniproject.com.xbcapplication.model.trainer.ModelTrainer;

public interface RequestAPIServices {

    //Koneksi API di menu Biodata
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
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


    //Koneksi API di menu User
    //Get Testimony
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/user/name/123")
    Call<ModelUser> getListUsser();
    @POST("/xbc-ws/api/user/create")
    Call<ModelUser> createNewUser(@Header("Content-Type")String contentTypeUser,
                                  @Body DataList datauser);

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

    //POST CREATEE

    @POST("/xbc-ws/api/testimony/create")
    Call<ModelTestimony> createNewTestimony(@Header("Content-Type") String contentType,
                                        @Body DataListTestimony data);
    //PUT EDIT

    @PUT("/xbc-ws/api/testimony/update")
    Call<ModelTestimony> editTestimony(@Header("Content-Type") String contentType,
                                   @Header("Authorization") String authorization,
                                   @Body Testimony data);



    //konek API di menu Batch
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/batch/name/123")
    Call<ModelBatch> getListBatch();


    //Koneksi API di menu Idle News
    //GET Search
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/idlenews/title/a")
    Call<ModelIdleNews> getListIdleNews();


}
