package xbc.miniproject.com.xbcapplication.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    protected static SharedPreferences retrieveSharedReferences(Context context){
        return context.getSharedPreferences( Constanta.SHARED_PREFERENCE_NAME, context.MODE_PRIVATE);
    }

    protected static SharedPreferences.Editor retieveSharedPreferenceEditor (Context context){
        return retrieveSharedReferences(context).edit();
    }
    public static void saveregistrationdata(Context context,
                                            String fullname,
                                            int age,
                                            boolean register){
        SharedPreferences.Editor editor = retieveSharedPreferenceEditor(context);
        editor.putString(Constanta.KEY_FUULNAME, fullname);
        editor.putInt(Constanta.KEY_AGE, age);
        editor.putBoolean(Constanta.KEY_IS_REGISTER, register);

        editor.commit();
    }
    //ambil data
    public static boolean isRegister(Context context){
        return retrieveSharedReferences(context).getBoolean(Constanta.KEY_IS_REGISTER, false);
    }

    public static String getFullname(Context context){
        return retrieveSharedReferences(context).getString(Constanta.KEY_FUULNAME, "");
    }

    public static int getAge(Context context){
        return retrieveSharedReferences(context).getInt(Constanta.KEY_AGE, 0);
    }
}