package risabhmishra.com.onedirect_flightsearch;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Risabh Mishra on 8/22/2018.
 */

public class flight_adapter extends RecyclerView.Adapter<flight_adapter.flight_view_holder> {

   private ArrayList<flight> arrayList;
   Cursor cursor;

   public flight_adapter(ArrayList<flight> ar){this.arrayList=ar;}

    MaryPopup marypopup;

   int pics[] ={R.drawable.indigo,R.drawable.spicejet,R.drawable.jetairways,R.drawable.airindia};
    @NonNull
    @Override
    public flight_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.flight_card_details,parent,false);
        return new flight_view_holder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull flight_view_holder holder, int position) {
     int id =  Integer.parseInt(arrayList.get(position).getFlight_id());
       holder.imageView.setImageResource(pics[id]);
       holder.src.setText(arrayList.get(position).getSrc_time());
       holder.dest.setText(arrayList.get(position).getDest_time());
       holder.price.setText("₹"+ arrayList.get(position).getPrice());
       holder.tot.setText(arrayList.get(position).getTotal_time());
       holder.book.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               marypopup = MaryPopup.with(context)
                       .cancellable(true)
                       .blackOverlayColor(Color.parseColor("#DD444444"))
                       .backgroundColor(Color.parseColor("#EFF4F5"))
                       .content(R.layout.popup_content)
                       .from(clickedView)
                       .center(true)
                       .draggable(true)
                       .show();
           }
       });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBackPressed() {
        if(!marypopup.close(true)){
            super.onBackPressed();
        }
    }
    public class flight_view_holder extends RecyclerView.ViewHolder{

       ImageView imageView,img2;
       TextView src,dest,tot,price;
       Button book;

       public flight_view_holder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            img2 = (ImageView)itemView.findViewById(R.id.imageView2);
            src = (TextView)itemView.findViewById(R.id.tv_src);
            dest = (TextView)itemView.findViewById(R.id.tv_dest);
            tot = (TextView)itemView.findViewById(R.id.tv_time);
            price = (TextView)itemView.findViewById(R.id.tv_price);
            book = (Button)itemView.findViewById(R.id.bu_book);
        }
    }
}
