package fb.fandroid.adv.rxjava2albumapp.album;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fb.fandroid.adv.rxjava2albumapp.R;
import fb.fandroid.adv.rxjava2albumapp.model.Song;


public class SongsHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private TextView mDuration;

    public SongsHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.tv_title);
        mDuration = itemView.findViewById(R.id.tv_duration);
    }

    public void bind(Song.DataBean item) {
        mTitle.setText(item.getName());
        mDuration.setText(item.getDuration());
    }
}
