package io.wams.meli.features.country;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.wams.meli.R;
import io.wams.meli.data.model.response.country.Country;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryList;
    private Subject<String> countryClickSubject;

    @Inject
    CountryAdapter() {
        countryClickSubject = PublishSubject.create();
        countryList = Collections.emptyList();
    }

    public void setCountry(List<Country> country) {
        this.countryList = country;
        notifyDataSetChanged();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.country_list_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        Country country = this.countryList.get(position);
        Context context = holder.imgCountry.getContext();
        holder.onBind(country, context);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    Observable<String> getCountryClick() {
        return countryClickSubject;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView nameCountry;

        @BindView(R.id.img_country)
        ImageView imgCountry;

        private Country country;

        CountryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> countryClickSubject.onNext(country.getId()));
        }

        void onBind(Country country, Context context) {
            this.country = country;
            nameCountry.setText(country.getName());
            Glide.with(context).load(country.getImage()).into(imgCountry);
        }
    }
}
