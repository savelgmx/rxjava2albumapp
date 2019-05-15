package fb.fandroid.adv.rxjava2albumapp.albums;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fb.fandroid.adv.rxjava2albumapp.R;
import fb.fandroid.adv.rxjava2albumapp.model.Albums;


/**
 * @author Azret Magometov
 */

public class AlbumsHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private TextView mReleaseDate;

    public AlbumsHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.tv_title);
        mReleaseDate = itemView.findViewById(R.id.tv_release_date);
    }

    public void bind(Albums.DataBean item, AlbumsAdapter.OnItemClickListener onItemClickListener) {
        mTitle.setText(item.getName());
        mReleaseDate.setText(item.getReleaseDate());
        if (onItemClickListener != null) {
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
        }
    }
}
