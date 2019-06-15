package io.wams.meli.features.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.wams.meli.R;
import io.wams.meli.data.model.response.search.Result;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Result> searchList;
    private Subject<String> productClickSubject;

    @Inject
    SearchAdapter() {
        productClickSubject = PublishSubject.create();
        searchList = Collections.emptyList();
    }

    public void setResult(List<Result> searchList) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }

    public void addResult(List<Result> searchList) {
        this.searchList.addAll(searchList);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_list_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Result result = this.searchList.get(position);
        holder.onBind(result, holder.imgProduct.getContext());
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    Observable<String> getProductClick() {
        return productClickSubject;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView txtTitle;

        @BindView(R.id.img_product)
        ImageView imgProduct;

        @BindView(R.id.txt_price)
        TextView txtPrice;

        @BindView(R.id.txt_address)
        TextView txtAddress;


        private Result result;

        SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> productClickSubject.onNext(result.getId()));
        }

        void onBind(Result result, Context context) {

            NumberFormat format = NumberFormat.getCurrencyInstance();

            this.result = result;
            txtTitle.setText(result.getTitle());

            if(result.getPrice()!=null)
            txtPrice.setText(format.format(result.getPrice()));

            if(result.getAddress()!=null){
                txtAddress.setText(result.getAddress().getCityName() +" - "+result.getAddress().getStateName());
            }
            String optimizedImage = result.getThumbnail();
            if(result.getThumbnail().contains("-I.jpg")){
                 optimizedImage = result.getThumbnail().replace("-I.jpg","-O.jpg");
            }
            Glide.with(context)
                    .load(optimizedImage)
                    .apply(RequestOptions.centerCropTransform())
                    .into(imgProduct);
        }
    }
}
