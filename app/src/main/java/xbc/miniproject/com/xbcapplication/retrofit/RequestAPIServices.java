package xbc.miniproject.com.xbcapplication.retrofit;

import android.provider.ContactsContract;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import xbc.miniproject.com.xbcapplication.model.biodata.DataList;
import xbc.miniproject.com.xbcapplication.model.biodata.ModelBiodata;
import xbc.miniproject.com.xbcapplication.model.user.ModelUser;

public interface RequestAPIServices {

    //Koneksi API di menu Biodata
    //Get Data
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/biodata/name/123")
    Call<ModelBiodata> getListBiodata();

    //Koneksi API di menu User
    //Get Data
    @Headers("Authorization: JCZXSHTUOIW5PAAGXIYZFTTX43KGRGJGFKL8DLMPJUMNFRIYOSTZUSL2157WV2MKY8CNNJDP8SAYN1KHHGBHV0B2W1UFPCR4APQKYEW6HJVFM98F4KY5T0QVWRGZXRTP")
    @GET("xbc-ws/api/user/name/123")
    Call<ModelUser> getListUsser();

    //Create Data
    @POST("/xbc-ws/api/biodata/create")
    Call<DataList> createNewBiodata(@Header("Content-Type") String contentType,
                                    @Body DataList data);
}
