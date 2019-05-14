package fb.fandroid.adv.rxjava2albumapp;


import fb.fandroid.adv.rxjava2albumapp.model.Album;
import fb.fandroid.adv.rxjava2albumapp.model.Albums;
import fb.fandroid.adv.rxjava2albumapp.model.Song;
import fb.fandroid.adv.rxjava2albumapp.model.Songs;
import fb.fandroid.adv.rxjava2albumapp.model.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by marat.taychinov
 */

public interface AcademyApi {

    @POST("registration")
    Completable registration(@Body User user);

    @GET("albums")
    Single<Albums> getAlbums();

    @GET("albums/{id}")
    Call<Album> getAlbum(@Path("id") int id);

    @GET("songs")
    Call<Songs> getSongs();

    @GET("songs/{id}")
    Call<Song> getSong(@Path("id") int id);

    @GET("user")
    Single<User>  authentication();
}
