package tech.tedybear.breakingnyus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import tech.tedybear.breakingnyus.R;
import tech.tedybear.breakingnyus.holder.NewsViewHolder;
import tech.tedybear.breakingnyus.model.ModelNews;
import tech.tedybear.breakingnyus.utils.TimeUnits;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    public List<ModelNews> androidList;
    private Context mContext;
    private NewsAdapter.onSelectData onSelectData;

    public NewsAdapter(Context context, List<ModelNews> android, NewsAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.androidList = android;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_berita, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder viewHolder, int i) {

        final ModelNews berita = androidList.get(i);

        Glide.with(mContext)
                .load(berita.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_list)
                //.transform(new CenterInside(), new RoundedCorners(30))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(viewHolder.image);

        viewHolder.title.setText(berita.getTitle());
        viewHolder.publishedAt.setText(TimeUnits.getTimeAgo(berita.getPublishedAt()));
        viewHolder.cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(berita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidList.size();
    }

    public interface onSelectData {
        void onSelected(ModelNews mdlNews);
    }

}
