package fb.fandroid.adv.rxjava2albumapp.albums;

import android.support.v4.app.Fragment;

import fb.fandroid.adv.rxjava2albumapp.SingleFragmentActivity;


public class AlbumsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return AlbumsFragment.newInstance();
    }
}
