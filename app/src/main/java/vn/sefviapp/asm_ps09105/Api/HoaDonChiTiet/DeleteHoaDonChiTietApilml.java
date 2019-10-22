package vn.sefviapp.asm_ps09105.Api.HoaDonChiTiet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.sefviapp.asm_ps09105.Api.BaseRetrofitIml;
import vn.sefviapp.asm_ps09105.Interface.HoaDonChiTietListener;
import vn.sefviapp.asm_ps09105.Model.HoaDonChiTiet;

public class DeleteHoaDonChiTietApilml extends BaseRetrofitIml {
    DeleteHoaDonChiTietApi deleteHoaDonChiTietApi;
    Retrofit retrofit = getRetrofit();
    public void DeleteHoaDonChiTiet(String id, final HoaDonChiTietListener hoaDonChiTietListener){
        deleteHoaDonChiTietApi = retrofit.create(DeleteHoaDonChiTietApi.class);
        Call<ResponseBody> call = deleteHoaDonChiTietApi.DeleteHoaDonChiTiet(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("success");
                        if (status == 200) {
                            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                            hoaDonChiTietListener.getDataSuccess(hoaDonChiTiet);
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                hoaDonChiTietListener.getMessageError(new Exception(t));
            }
        });
    }
}
