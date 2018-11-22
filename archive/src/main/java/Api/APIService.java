package Api;

import java.util.List;

import Model.ViewConsoleCmd;
import Presenter.UsersAllWords;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    //url for "All" Words
    @GET("hlstats_Events_AllWords.php")
    Call<List<UsersAllWords>> getAllWords();

    @GET("api.php")
    Call<ViewConsoleCmd> getConsoleCmd(@Query("query") String query, @Query("cmd") String cmd, @Query("token") String token);

}
