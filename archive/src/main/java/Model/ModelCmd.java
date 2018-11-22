package Model;

import Api.APIService;
import Api.Keys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelCmd {

    public void onExecuteCmd(String message) {



        try {
            String url = Keys.MYARENA_URL;
            Retrofit retrofit = null;
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            APIService service = retrofit.create(APIService.class);
            Call<ViewConsoleCmd> call = service.getConsoleCmd("consolecmd", message, Keys.MYARENA_TOKEN);
            call.enqueue(new Callback<ViewConsoleCmd>() {
                @Override
                public void onResponse(Call<ViewConsoleCmd> call, Response<ViewConsoleCmd> response) {
                    response.body();
                }

                @Override
                public void onFailure(Call<ViewConsoleCmd> call, Throwable t) {

                }
            });
        } catch (Exception e) {
        }
    }


}
