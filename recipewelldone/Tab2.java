package com.recipewelldone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final ArrayList<card1> list = new ArrayList<>();
    CustomListAdapter adapter;
    private ListView listView2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        listView2 = (ListView) view.findViewById(R.id.list2);
        String url = getContext().getResources().getString(R.string.mainurl);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("Response", response);
                ParseXML(response);
                if(adapter != null)
                    adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> mParams = new HashMap<String, String>();
                mParams.put("query", "PREFIX semantic: <http://www.semanticweb.org/ser594/ontologies/2017/9/untitled-ontology-7#>  SELECT Distinct ?Recipe ?DrinkName ?url WHERE { ?Recipe semantic:hasDrinkName ?DrinkName. ?Recipe <http://127.0.0.1:3333/drinkHasImage> ?url. } LIMIT 25");
                return mParams;
            }

        };

        ReceipeApplication.getInstance().addToRequestQueue(stringRequest);



//        list.add(new card1("drawable://" + R.drawable.brooklyn,"Brooklyn"));
//        list.add(new card1("drawable://" + R.drawable.martini,"Martini"));
//        list.add(new card1("drawable://" + R.drawable.careyjones,"Carey Jones"));
//        list.add(new card1("drawable://" + R.drawable.martinez,"Martinez"));
//        list.add(new card1("drawable://" + R.drawable.margarita,"Margarita"));
//        list.add(new card1("drawable://" + R.drawable.jackrose,"Jack Rose"));
//        list.add(new card1("drawable://" + R.drawable.manhattan,"Manhattan"));
        adapter = new CustomListAdapter(getActivity(), R.layout.card_layout, list);
        listView2.setAdapter(adapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {

                Intent intent = new Intent(getActivity(), CocktailDetails.class);
                intent.putExtra("uri", list.get(position).getUri());
                intent.putExtra("name",list.get(position).getTitle());
                intent.putExtra("img", list.get(position).getImgURL());
                startActivity(intent);
            }
        });
        return view;
    }

    public void ParseXML(String xmlString){

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            int eventType = parser.getEventType();
            int counter=0;
            String Name=new String();
            String Nam=new String();
            String uri= new String();
            while (eventType != XmlPullParser.END_DOCUMENT){

                if(eventType== XmlPullParser.START_TAG){

                    String name = parser.getName();
//                    if(name.equals("binding")){
//
//                        String ref = com.recipewelldone.parser.getAttributeValue(null,"name");
//                        //Log.e("ref:" , );
//                        if(ref.equals("DrinkName")){
//                            if(com.recipewelldone.parser.next()== XmlPullParser.START_TAG){
//                                String nam=com.recipewelldone.parser.getName();
//                                Log.e("nam",nam);
//                                if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                                    String UpdateFlag = com.recipewelldone.parser.getText();
//                                    Log.e("UpdateFlag:" ,UpdateFlag);
//                                }
//                            }
//                        }

//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String UpdateFlag = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"UpdateFlag:" + UpdateFlag);
//                        }


//                    }
                    if(name.equals("literal")) {

                        if (parser.next() == XmlPullParser.TEXT) {
                            counter=counter+1;
                            if(counter==1){
                                 Name = parser.getText();
                            }
                            else if(counter==2){
                                Nam=parser.getText();
                            }
                            //list.add(new card1(Name));
                            //Log.e("Name:" , Name);
                        }
//                    }else if(name.equals("Range")) {
//
//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String Range = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"Range:" + Range);
//                        }
//                    }
                    }

                    if(name.equals("uri")) {

                        if (parser.next() == XmlPullParser.TEXT) {
                            uri=parser.getText();
                            //list.add(new card1(Name));
                            //Log.e("Name:" , Name);
                        }
//                    }else if(name.equals("Range")) {
//
//                        if(com.recipewelldone.parser.next() == XmlPullParser.TEXT) {
//                            String Range = com.recipewelldone.parser.getText();
//                            //Log.d(LOG_TAG,"Range:" + Range);
//                        }
//                    }
                    }


                }else if(eventType== XmlPullParser.END_TAG){


                }
                if(counter==2){
                    list.add(new card1(Nam,Name,uri));
                    counter=0;
                }
                eventType = parser.next();

            }


        }catch (Exception e){
            Log.e("Error in ParseXML()",e.toString());
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
