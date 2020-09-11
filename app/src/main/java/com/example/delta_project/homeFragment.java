package com.example.delta_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment implements SliderAdapterExample.OnIemClickListener, modelAdapter.OnIemClickListener1 {
    SliderView sliderView;
    List<SliderItem> sliderItemList;
    private RecyclerView mustvisitrv;
    private modelAdapter ma;
    private modelAdapter ma2;
    private RecyclerView popularrv;
    private TextView viewall;
    private SliderAdapterExample adapter;
    private ProgressBar loader;
    public static final String Extraurl = "imageUrl";
    public static final String Extraname = "stateName";
    public static final String Finalurl = "finalUrl";
    public static final String Finalname = "finalName";
    private FirebaseRecyclerOptions<model> fro;
    private static final String TAG = "homeFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.home_fragment, container, false);
        viewall = view.findViewById(R.id.homeMustVisitviewall);
        mustvisitrv = view.findViewById(R.id.homeMustVisitrv);
        loader=view.findViewById(R.id.loader);
        popularrv = view.findViewById(R.id.homePopularrv);


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Must Visit Destinations"), model.class)
                        .build();
        mustvisitrv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ma = new modelAdapter(options);
        mustvisitrv.setAdapter(ma);
        ma.setOnItemClickListener1(homeFragment.this);
fro=options;


        final FirebaseRecyclerOptions<model> options2 =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Popular In India"), model.class)
                        .build();
        popularrv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ma2 = new modelAdapter(options2);
        ma2.setOnItemClickListener1(new modelAdapter.OnIemClickListener1() {
            @Override
            public void onItemClick1(int position) {
//                Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                model current_item=options2.getSnapshots().get(position);
                Intent i=new Intent(getContext(),popularInIndiaDisplay.class);
                i.putExtra("name",current_item.getName());
                i.putExtra("info",current_item.getInfo());
                i.putExtra("url",current_item.getUrl());
                startActivity(i);
            }
        });
        popularrv.setAdapter(ma2);



        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getContext());

        sliderView.setSliderAdapter(adapter);
        adapter.setOnItemClickListener(homeFragment.this);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        renewItems(view);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 destinationsFragment df=new destinationsFragment();
                  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,df).commit();
            }
        });

        loader.setVisibility(View.INVISIBLE);
        return view;


    }

    public void renewItems(View view) {
        sliderItemList = new ArrayList<>();
        SliderItem s1 = new SliderItem();
        s1.setDescription("The Taj Mahal");
        s1.setImageUrl("https://static.toiimg.com/photo/31346158.cms.cms");
        s1.setInfo("The Taj Mahal is an ivory-white marble mausoleum on the southern bank of the river Yamuna in the Indian city of Agra. It was commissioned in 1632 by the Mughal emperor Shah Jahan (reigned from 1628 to 1658) to house the tomb of his favourite wife, Mumtaz Mahal; it also houses the tomb of Shah Jahan himself. The tomb is the centrepiece of a 17-hectare (42-acre) complex, which includes a mosque and a guest house, and is set in formal gardens bounded on three sides by a crenellated wall.");
        SliderItem s2 = new SliderItem();
        s2.setDescription("The Red Fort");
        s2.setImageUrl("https://images.indianexpress.com/2018/06/red-fort-1200-getty-images.jpg");
        s2.setInfo("Red Fort, also called Lal Qalʿah, also spelled Lal Kila or Lal Qila, Mughal fort in Old Delhi, India. It was built by Shah Jahān in the mid-17th century and remains a major tourist attraction. The fort was designated a UNESCO World Heritage site in 2007.The fort’s massive red sandstone walls, which stand 75 feet (23 metres) high, enclose a complex of palaces and entertainment halls, projecting balconies, baths and indoor canals, and geometrical gardens, as well as an ornate mosque. Among the most famous structures of the complex are the Hall of Public Audience (Diwan-i-Am), which has 60 red sandstone pillars supporting a flat roof, and the Hall of Private Audience (Diwan-i-Khas), which is smaller, with a pavilion of white marble.");
        SliderItem s3 = new SliderItem();
        s3.setDescription("The Hawa Mahal");
        s3.setImageUrl("https://www.swantour.com/sites/default/files/hawa-mahal-jaipur.jpg");
        s3.setInfo("Hawa Mahal is a palace in Jaipur, India approximately 300 kilometers from the capital city of Delhi. Built from red and pink sandstone, the palace sits on the edge of the City Palace, Jaipur, and extends to the Zenana, or women's chambers.The structure was built in 1799 by Maharaja Sawai Pratap Singh, the grandson of Maharaja Sawai Jai Singh, who was the founder of Jaipur.[1] He was so inspired by the unique structure of Khetri Mahal that he built this grand and historical palace. It was designed by Lal Chand Ustad. Its five floor exterior is akin to honeycomb with its 953 small windows called Jharokhas decorated with intricate latticework.");
        SliderItem s4 = new SliderItem();
        s4.setDescription("The Golden Temple");
        s4.setImageUrl("https://static.toiimg.com/photo/61820954.cms");
        s4.setInfo("The Golden Temple, also known as Harmandir Sahib, is a Gurdwara located in the city of Amritsar, Punjab, India. It is the preeminent spiritual site of Sikhism.The Gurdwara is built around a man-made pool (sarovar) that was completed by the fourth Sikh Guru, Guru Ram Das in 1577.[5][6] Guru Arjan, the fifth Guru of Sikhism, requested Sai Mir Mian Mohammed, a Muslim Pir of Lahore, to lay its foundation stone in 1589.[7] In 1604, Guru Arjan placed a copy of the Adi Granth in Harmandir Sahib.[2][8] The Gurdwara was repeatedly rebuilt by the Sikhs after it became a target of persecution and was destroyed several times by the Mughal and invading Afghan armies. Maharaja Ranjit Singh after founding the Sikh Empire, rebuilt it in marble and copper in 1809, overlaid the sanctum with gold foil in 1830. This has led to the name the Golden Temple.");
        SliderItem s5 = new SliderItem();
        s5.setDescription("The Ellora Caves");
        s5.setImageUrl("https://media.tacdn.com/media/attractions-splice-spp-674x446/0a/01/38/a7.jpg");
        s5.setInfo("Ellora Caves, Ellora also spelled Elura, a series of 34 magnificent rock-cut temples in northwest-central Maharashtra state, western India. They are located near the village of Ellora, 19 miles (30 km) northwest of Aurangabad and 50 miles (80 km) southwest of the Ajanta Caves. Spread over a distance of 1.2 miles (2 km), the temples were cut from basaltic cliffs and have elaborate facades and interior walls. The Ellora complex was designated a UNESCO World Heritage site in 1983.The 12 Buddhist caves (in the south) date from about 200 BCE to 600 CE, the 17 Hindu temples (in the centre) date from about 500 to 900 CE, and the 5 Jain temples (in the north) date from about 800 to 1000. The Hindu caves are the most dramatic in design, and the Buddhist caves contain the simplest ornamentation.");
        sliderItemList.add(s1);
        sliderItemList.add(s2);
        sliderItemList.add(s3);
        sliderItemList.add(s4);
        sliderItemList.add(s5);

        adapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        adapter.addItem(sliderItem);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(), finalDisplay.class);
        SliderItem currentItem = sliderItemList.get(position);
        i.putExtra("from","sliderItem");
        i.putExtra(Finalname, currentItem.getDescription());
        i.putExtra(Finalurl, currentItem.getImageUrl());
        i.putExtra("info",currentItem.getInfo());
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        ma2.startListening();
        ma.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Travel India");
    }

    @Override
    public void onItemClick1(int position) {

        Intent i=new Intent(getContext(),middleDisplay.class);

        model clickedItem= fro.getSnapshots().get(position);
        i.putExtra("Dname",clickedItem.getName());
        Log.i(TAG,clickedItem.getName());
        i.putExtra("Dimageurl",clickedItem.getUrl());
        Log.i(TAG,clickedItem.getUrl());
        i.putExtra("from","HomeFragment");
        i.putExtra("info",clickedItem.getInfo());
        startActivity(i);
    }

}

